class Solution {
    public String sortSentence(String s) {
        String[] words = s.split(" ");
        String[] ans = new String[words.length];
        for (String w : words) {
            int i = w.charAt(w.length() - 1) - '1';
            ans[i] = w.substring(0, w.length() - 1);
        }
        return String.join(" ", ans);
    }
}