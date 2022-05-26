class Solution {
    public List<String> printVertically(String s) {
        String[] words = s.split(" ");
        int m = words.length, n = maxLen(words);
        List<String> ans = new ArrayList<>();
        for (int j = 0; j < n; ++j) {
            StringBuilder t = new StringBuilder();
            for (int i = 0; i < m; ++i) {
                String word = words[i];
                t.append(j < word.length() ? word.charAt(j) : ' ');
            }
            ans.add(rstrip(t));
        }
        return ans;
    }

    private int maxLen(String[] words) {
        int ans = 0;
        for (String word : words) {
            ans = Math.max(ans, word.length());
        }
        return ans;
    }

    private String rstrip(StringBuilder s) {
        for (int i = s.length() - 1; i >= 0; --i) {
            if (s.charAt(i) != ' ') {
                return s.substring(0, i + 1);
            }
        }
        return "";
    }
}