public class Solution {
    public int NumSubseq(int[] nums, int target) {
        const int MOD = 1000000007;

        Array.Sort(nums);
        int n = nums.Length;
        int leftIndex = 0, rightIndex = n - 1;
        int result = 0;

        long[] pow2 = new long[n];
        pow2[0] = 1;
        for (int i = 1; i < n; i++) {
            pow2[i] = (pow2[i - 1] * 2) % MOD;
        }

        while (leftIndex <= rightIndex) {
            if (nums[leftIndex] + nums[rightIndex] <= target) {
                result = (int)((result + pow2[rightIndex - leftIndex]) % MOD);
                leftIndex++;
            } else {
                rightIndex--;
            }
        }

        return result;
    }
}