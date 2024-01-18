class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int m = s.length();
        int n = p.length();
        List<Integer> ans = new ArrayList<>();
        if (m < n) {
            return ans;
        }
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < n; ++i) {
            ++cnt1[s.charAt(i) - 'a'];
            ++cnt2[p.charAt(i) - 'a'];
        }
        if (Arrays.equals(cnt1, cnt2)) {
            ans.add(0);
        }
        for (int i = n; i < m; ++i) {
            ++cnt1[s.charAt(i) - 'a'];
            --cnt1[s.charAt(i - n) - 'a'];
            if (Arrays.equals(cnt1, cnt2)) {
                ans.add(i - n + 1);
            }
        }
        return ans;
    }
}