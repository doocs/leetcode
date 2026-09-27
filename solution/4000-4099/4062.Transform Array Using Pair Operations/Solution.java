class Solution {
    public boolean canTransform(int[] source, int[] target) {
        long d = 0;
        for (int i = 0; i < source.length; ++i) {
            d += source[i] - target[i];
        }
        return d == 0;
    }
}