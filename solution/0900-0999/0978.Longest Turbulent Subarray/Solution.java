class Solution {
    public int maxTurbulenceSize(int[] arr) {
        int ans = 1, f = 1, g = 1;
        for (int i = 1; i < arr.length; ++i) {
            int ff = arr[i - 1] < arr[i] ? g + 1 : 1;
            int gg = arr[i - 1] > arr[i] ? f + 1 : 1;
            f = ff;
            g = gg;
            ans = Math.max(ans, Math.max(f, g));
        }
        return ans;
    }
}