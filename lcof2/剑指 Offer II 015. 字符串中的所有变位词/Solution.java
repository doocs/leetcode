class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int m = s.length(), n = p.length();
        if (n > m) {
            return ans;
        }
        int[] window = new int[26];
        for (int i = 0; i < n; i++) {
            window[p.charAt(i) - 'a']++;
            window[s.charAt(i) - 'a']--;
        }
        if (check(window)) {
            ans.add(0);
        }
        for (int i = n; i < m; i++) {
            window[s.charAt(i) - 'a']--;
            window[s.charAt(i - n) - 'a']++;
            if (check(window)) {
                ans.add(i - n + 1);
            }
        }
        return ans;
    }

    private boolean check(int[] window) {
        return Arrays.stream(window).allMatch(cnt -> cnt == 0);
    }
}
