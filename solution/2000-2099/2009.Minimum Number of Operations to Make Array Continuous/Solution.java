class Solution {
    public int minOperations(int[] nums) {
        int N = nums.length;
        if (N == 1) return 0;
        Arrays.sort(nums);
        int M = 1;
        for (int i = 1; i < N; i++) {
            if (nums[i] != nums[i - 1])
                nums[M++] = nums[i];
        }

        int j = 0;
        int ans = N;
        for (int i = 0; i < M; i++) {
            while (j < M && nums[j] <= N + nums[i] - 1)
                j++;
            ans = Math.min(ans, N - j + i);
        }

        return ans;
    }
}
