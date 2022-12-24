class Solution {
    public int[] numSmallerByFrequency(String[] queries, String[] words) {
        int n = words.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = f(words[i]);
        }
        Arrays.sort(arr);
        int m = queries.length;
        int[] ans = new int[m];
        for (int i = 0; i < m; ++i) {
            int x = f(queries[i]);
            ans[i] = n - search(arr, x);
        }
        return ans;
    }

    private int search(int[] arr, int x) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (arr[mid] > x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private int f(String s) {
        int[] cnt = new int[26];
        for (int i = 0; i < s.length(); ++i) {
            ++cnt[s.charAt(i) - 'a'];
        }
        for (int v : cnt) {
            if (v > 0) {
                return v;
            }
        }
        return 0;
    }
}