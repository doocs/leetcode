class Solution {
    private Set<String> s;

    public String longestWord(String[] words) {
        s = new HashSet<>(Arrays.asList(words));
        int cnt = 0;
        String ans = "";
        for (String w : s) {
            int n = w.length();
            if (check(w)) {
                if (cnt < n) {
                    cnt = n;
                    ans = w;
                } else if (cnt == n && w.compareTo(ans) < 0) {
                    ans = w;
                }
            }
        }
        return ans;
    }

    private boolean check(String word) {
        for (int i = 1, n = word.length(); i < n; ++i) {
            if (!s.contains(word.substring(0, i))) {
                return false;
            }
        }
        return true;
    }
}