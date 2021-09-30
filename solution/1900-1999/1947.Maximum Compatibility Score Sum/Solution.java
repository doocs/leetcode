class Solution {
    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        int m = students.length, n = students[0].length;
        int[][] scores = new int[m][m];
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < m; ++j) {
                scores[i][j] = score(students[i], mentors[j]);
            }
        }
        int[] idx = new int[m];
        for (int i = 0; i < m; ++i) {
            idx[i] = i;
        }
        int mx = 0;
        List<List<Integer>> p = permute(idx);
        for (List<Integer> item : p) {
            int t = 0;
            int sidx = 0;
            for (int midx : item) {
                t += scores[sidx][midx];
                ++sidx;
            }
            mx = Math.max(mx, t);
        }
        return mx;
    }

    private int score(int[] s, int[] m) {
        int res = 0;
        for (int i = 0; i < s.length; ++i) {
            res += s[i] == m[i] ? 1 : 0;
        }
        return res;
    }

    private List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        permute(res, nums, 0);
        return res;
    }

    private void permute(List<List<Integer>> res, int[] nums, int start) {
        if (start == nums.length) {
            List<Integer> t = new ArrayList<>();
            for (int e : nums) {
                t.add(e);
            }
            res.add(t);
            return;
        }
        for (int i = start; i < nums.length; ++i) {
            swap(nums, i, start);
            permute(res, nums, start + 1);
            swap(nums, i, start);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}