class Solution {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        int[] cnt = new int[26];
        for (var b : words2) {
            int[] t = new int[26];
            for (int i = 0; i < b.length(); ++i) {
                t[b.charAt(i) - 'a']++;
            }
            for (int i = 0; i < 26; ++i) {
                cnt[i] = Math.max(cnt[i], t[i]);
            }
        }
        List<String> ans = new ArrayList<>();
        for (var a : words1) {
            int[] t = new int[26];
            for (int i = 0; i < a.length(); ++i) {
                t[a.charAt(i) - 'a']++;
            }
            boolean ok = true;
            for (int i = 0; i < 26; ++i) {
                if (cnt[i] > t[i]) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                ans.add(a);
            }
        }
        return ans;
    }
}