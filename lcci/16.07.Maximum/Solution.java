class Solution {
    public int maximum(int a, int b) {
        int k = (int) (((long) a - (long) b) >> 63) & 1;
        return a * (k ^ 1) + b * k;
    }
}