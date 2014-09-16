package MyHelloWorld;

import java.util.BitSet;
import java.util.Set;

public class bloomFilter {
	
	private int defaultSize = 5000 << 10000;
	
	private int basic = defaultSize - 1;
	
	private BitSet bits = new BitSet(defaultSize);
	
	public bloomFilter()
	{
		
	}
	
	private int[] indexInSet(element ele)
	{
		int[] indexes = new int[8];
		for(int i=0;i<8;i++)
		{
			indexes[i] = hasCode(ele.getKey(), i);
		}
		return indexes;
	}
	
	private void add(element ele) {
		if(exist(ele)){
			System.out.println("Already exist(" + ele.getKey() + ")");
			return;
		}
		int keyCode[] = indexInSet(ele);
		for(int i=0;i<8;i++){
			bits.set(keyCode[i]);
		}
	}
	
	private boolean exist(element ele) {
		int keyCode[] = indexInSet(ele);
		if(bits.get(keyCode[0])
				&& bits.get(keyCode[1])
				&& bits.get(keyCode[2])
				&& bits.get(keyCode[3])
				&& bits.get(keyCode[4])
				&& bits.get(keyCode[5])
				&& bits.get(keyCode[6])
				&& bits.get(keyCode[7])
				){
			return true;
		}
		return false;
	}
	
	private boolean deleteElement(element ele) {
		if(exist(ele)){
			int keyCode[] = indexInSet(ele);
			for (int i = 0; i < 8; i++) {
				bits.clear(keyCode[i]);
			}
			return true;
		}
		return false;
	}
	
	private int	hasCode(String key, int Q) {
		int h=0;
		int off=0;
		char val[] = key.toCharArray();
		int len = val.length;
		for(int i=0;i<len;i++){
			h = (30+Q)*h + val[off++];
		}
		return changeInteger(h);
	}
	
	private int changeInteger(int h) {
		return basic & h;
	}
	
	public static void main(String[] args) {
		bloomFilter filter = new bloomFilter();
		element element = new element("blog.csdn.net/zy825316");
		System.out.println("Set Size:" + filter.defaultSize);
		filter.add(element);
		System.out.println(filter.exist(element));
		filter.deleteElement(element);
		System.out.println(filter.exist(element));
	}
}

