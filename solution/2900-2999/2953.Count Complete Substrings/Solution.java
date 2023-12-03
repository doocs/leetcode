class Solution {
    public int countCompleteSubstrings(String word, int k) {
        int n = word.length();
        int ans = 0;
        for (int i = 0; i < n;) {
            int j = i + 1;
            while (j < n && Math.abs(word.charAt(j) - word.charAt(j - 1)) <= 2) {
                ++j;
            }
            ans += f(word.substring(i, j), k);
            i = j;
        }
        return ans;
    }

    private int f(String s, int k) {
        int m = s.length();
        int ans = 0;
        for (int i = 1; i <= 26 && i * k <= m; ++i) {
            int l = i * k;
            int[] cnt = new int[26];
            for (int j = 0; j < l; ++j) {
                ++cnt[s.charAt(j) - 'a'];
            }
            Map<Integer, Integer> freq = new HashMap<>();
            for (int x : cnt) {
                if (x > 0) {
                    freq.merge(x, 1, Integer::sum);
                }
            }
            if (freq.getOrDefault(k, 0) == i) {
                ++ans;
            }
            for (int j = l; j < m; ++j) {
                int a = s.charAt(j) - 'a';
                int b = s.charAt(j - l) - 'a';
                freq.merge(cnt[a], -1, Integer::sum);
                ++cnt[a];
                freq.merge(cnt[a], 1, Integer::sum);

                freq.merge(cnt[b], -1, Integer::sum);
                --cnt[b];
                freq.merge(cnt[b], 1, Integer::sum);
                if (freq.getOrDefault(k, 0) == i) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}