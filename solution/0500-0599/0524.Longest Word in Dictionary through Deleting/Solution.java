class Solution {
    public String findLongestWord(String s, List<String> dictionary) {
        String ans = "";
        for (String a : dictionary) {
            if (check(s, a)
                && (ans.length() < a.length()
                    || (ans.length() == a.length() && a.compareTo(ans) < 0))) {
                ans = a;
            }
        }
        return ans;
    }

    private boolean check(String a, String b) {
        int m = a.length(), n = b.length();
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (a.charAt(i) == b.charAt(j)) {
                ++j;
            }
            ++i;
        }
        return j == n;
    }
}