class Solution {
    public List<String> commonChars(String[] words) {
        int[] cnt = new int[26];
        Arrays.fill(cnt, 10000);
        for (String w : words) {
            int[] ccnt = new int[26];
            for (int i = 0; i < w.length(); ++i) {
                ++ccnt[w.charAt(i) - 'a'];
            }
            for (int i = 0; i < 26; ++i) {
                cnt[i] = Math.min(cnt[i], ccnt[i]);
            }
        }
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 26; ++i) {
            while (cnt[i]-- > 0) {
                ans.add(String.valueOf((char) (i + 'a')));
            }
        }
        return ans;
    }
}