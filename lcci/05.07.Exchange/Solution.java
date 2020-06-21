class Solution {
    public int exchangeBits(int num) {
        int t1 = num >> 1;
    	int t2 = num << 1;
    	return t1 & 0x55555555 | t2 & 0xaaaaaaaa;
    }
}