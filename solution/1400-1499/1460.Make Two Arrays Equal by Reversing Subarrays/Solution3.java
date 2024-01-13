class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {
        int[] cnt = new int[1001];
        for (int v : target) {
            ++cnt[v];
        }
        for (int v : arr) {
            if (--cnt[v] < 0) {
                return false;
            }
        }
        return true;
    }
}