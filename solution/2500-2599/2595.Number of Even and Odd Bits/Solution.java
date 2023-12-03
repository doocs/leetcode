class Solution {
    public int[] evenOddBit(int n) {
        int mask = 0x5555;
        int even = Integer.bitCount(n & mask);
        int odd = Integer.bitCount(n & ~mask);
        return new int[] {even, odd};
    }
}