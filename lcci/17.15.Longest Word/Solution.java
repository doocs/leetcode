class Solution {
    private Set<String> s = new HashSet<>();

    public String longestWord(String[] words) {
        for (String w : words) {
            s.add(w);
        }
        Arrays.sort(words, (a, b) -> {
            if (a.length() != b.length()) {
                return b.length() - a.length();
            }
            return a.compareTo(b);
        });
        for (String w : words) {
            s.remove(w);
            if (dfs(w)) {
                return w;
            }
        }
        return "";
    }

    private boolean dfs(String w) {
        if (w.length() == 0) {
            return true;
        }
        for (int k = 1; k <= w.length(); ++k) {
            if (s.contains(w.substring(0, k)) && dfs(w.substring(k))) {
                return true;
            }
        }
        return false;
    }
}
