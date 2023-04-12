class Solution {
    public int findLucky(int[] arr) {
        int[] cnt = new int[510];
        for (int x : cnt) {
            ++cnt[x];
        }
        int ans = -1;
        for (int x = 1; x < cnt.length; ++x) {
            if (cnt[x] == x) {
                ans = x;
            }
        }
        return ans;
    }
}