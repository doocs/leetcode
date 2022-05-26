class Solution {
    public int[] fairCandySwap(int[] aliceSizes, int[] bobSizes) {
        int s1 = 0, s2 = 0;
        Set<Integer> s = new HashSet<>();
        for (int a : aliceSizes) {
            s1 += a;
        }
        for (int b : bobSizes) {
            s.add(b);
            s2 += b;
        }
        int diff = (s1 - s2) >> 1;
        for (int a : aliceSizes) {
            int target = a - diff;
            if (s.contains(target)) {
                return new int[]{a, target};
            }
        }
        return null;
    }
}