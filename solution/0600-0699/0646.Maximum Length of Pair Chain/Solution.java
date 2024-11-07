class Solution {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[1], b[1]));
        int ans = 0, pre = Integer.MIN_VALUE;
        for (var p : pairs) {
            if (pre < p[0]) {
                ++ans;
                pre = p[1];
            }
        }
        return ans;
    }
}
