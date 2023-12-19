class Solution {
    public String sortSentence(String s) {
        String[] ws = s.split(" ");
        int n = ws.length;
        String[] ans = new String[n];
        for (int i = 0; i < n; ++i) {
            String w = ws[i];
            ans[w.charAt(w.length() - 1) - '1'] = w.substring(0, w.length() - 1);
        }
        return String.join(" ", ans);
    }
}