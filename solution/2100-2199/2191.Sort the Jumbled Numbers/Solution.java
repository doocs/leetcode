class Solution {
    public int[] sortJumbled(int[] mapping, int[] nums) {
        int n = nums.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; ++i) {
            int x = nums[i];
            int y = x == 0 ? mapping[0] : 0;
            int k = 1;
            for (; x > 0; x /= 10) {
                y += k * mapping[x % 10];
                k *= 10;
            }
            arr[i] = new int[] {y, i};
        }
        Arrays.sort(arr, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = nums[arr[i][1]];
        }
        return ans;
    }
}