class Solution {
    public int longestAlternating(int[] nums) {
        int n = nums.length;
        int[] l1 = new int[n];
        int[] l2 = new int[n];
        int[] r1 = new int[n];
        int[] r2 = new int[n];

        for (int i = 0; i < n; i++) {
            l1[i] = 1;
            l2[i] = 1;
            r1[i] = 1;
            r2[i] = 1;
        }

        int ans = 0;

        for (int i = 1; i < n; i++) {
            if (nums[i - 1] < nums[i]) {
                l1[i] = l2[i - 1] + 1;
            } else if (nums[i - 1] > nums[i]) {
                l2[i] = l1[i - 1] + 1;
            }
            ans = Math.max(ans, l1[i]);
            ans = Math.max(ans, l2[i]);
        }

        for (int i = n - 2; i >= 0; i--) {
            if (nums[i + 1] > nums[i]) {
                r1[i] = r2[i + 1] + 1;
            } else if (nums[i + 1] < nums[i]) {
                r2[i] = r1[i + 1] + 1;
            }
        }

        for (int i = 1; i < n - 1; i++) {
            if (nums[i - 1] < nums[i + 1]) {
                ans = Math.max(ans, l2[i - 1] + r2[i + 1]);
            } else if (nums[i - 1] > nums[i + 1]) {
                ans = Math.max(ans, l1[i - 1] + r1[i + 1]);
            }
        }

        return ans;
    }
}
