class Solution {
    public String answerString(String word, int numFriends) {
        if (numFriends == 1) {
            return word;
        }
        int n = word.length();
        String ans = "";
        for (int i = 0; i < n; ++i) {
            String t = word.substring(i, Math.min(n, i + n - (numFriends - 1)));
            if (ans.compareTo(t) < 0) {
                ans = t;
            }
        }
        return ans;
    }
}