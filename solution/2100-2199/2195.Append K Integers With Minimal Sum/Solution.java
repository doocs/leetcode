class Solution {
    public long minimalKSum(int[] nums, int k) {
        int[] arr = new int[nums.length + 2];
        arr[arr.length - 1] = (int) 2e9;
        for (int i = 0; i < nums.length; ++i) {
            arr[i + 1] = nums[i];
        }
        Arrays.sort(arr);
        long ans = 0;
        for (int i = 1; i < arr.length; ++i) {
            int a = arr[i - 1], b = arr[i];
            int n = Math.min(k, b - a - 1);
            if (n <= 0) {
                continue;
            }
            k -= n;
            ans += (long) (a + 1 + a + n) * n / 2;
            if (k == 0) {
                break;
            }
        }
        return ans;
    }
}