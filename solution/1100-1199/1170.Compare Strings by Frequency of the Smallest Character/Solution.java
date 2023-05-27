class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int n = words.length;
        int[] nums = new int[n];
        for (int i = 0; i < n; ++i) {
            nums[i] = f(words[i]);
        }
        Arrays.sort(nums);
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            int x = f(queries[i]);
            int l = 0, r = n;
            while (l < r) {
                int mid = (l + r) >> 1;
                if (nums[mid] > x) {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            ans[i] = n - l;
        }
        return ans;
    }

    private int f(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        for (int x : cnt) {
            if (x > 0) {
                return x;
            }
        }
        return 0;
    }
}