class Solution {
    public int insertBits(int N, int M, int i, int j) {
        for (int k = i; k <= j; k++) {
            N &= ~(1 << k);
        }
        return N ^ (M << i);
    }
}