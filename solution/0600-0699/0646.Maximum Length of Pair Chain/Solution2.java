class Solution {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, Comparator.comparingInt(a -> a[1]));
        int ans = 0;
        int cur = Integer.MIN_VALUE;
        for (int[] p : pairs) {
            if (cur < p[0]) {
                cur = p[1];
                ++ans;
            }
        }
        return ans;
    }
}