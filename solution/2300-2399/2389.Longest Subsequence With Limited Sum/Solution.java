class Solution {
    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] s = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            ans[i] = search(s, queries[i]);
        }
        return ans;
    }
    
    private int search(int[] s, int v) {
        int left = 1, right = s.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (s[mid] > v) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left - 1;
    }
}