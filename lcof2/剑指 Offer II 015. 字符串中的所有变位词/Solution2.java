class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int m = s.length();
        int n = p.length();
        List<Integer> ans = new ArrayList<>();
        if (m < n) {
            return ans;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt[s.charAt(i) - 'a'];
            --cnt[p.charAt(i) - 'a'];
        }
        int diff = 0;
        for (int x : cnt) {
            if (x != 0) {
                ++diff;
            }
        }
        if (diff == 0) {
            ans.add(0);
        }
        for (int i = n; i < m; ++i) {
            int a = s.charAt(i - n) - 'a';
            int b = s.charAt(i) - 'a';
            if (cnt[a] == 0) {
                ++diff;
            }
            --cnt[a];
            if (cnt[a] == 0) {
                --diff;
            }
            if (cnt[b] == 0) {
                ++diff;
            }
            ++cnt[b];
            if (cnt[b] == 0) {
                --diff;
            }
            if (diff == 0) {
                ans.add(i - n + 1);
            }
        }
        return ans;
    }
}