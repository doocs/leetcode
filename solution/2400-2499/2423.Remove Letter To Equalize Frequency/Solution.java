class Solution {
    public boolean equalFrequency(String word) {
        for (int i = 0; i < word.length(); ++i) {
            int[] cnt = new int[26];
            for (int j = 0; j < word.length(); ++j) {
                if (j != i) {
                    ++cnt[word.charAt(j) - 'a'];
                }
            }
            Set<Integer> vis = new HashSet<>();
            for (int v : cnt) {
                if (v > 0) {
                    vis.add(v);
                }
            }
            if (vis.size() == 1) {
                return true;
            }
        }
        return false;
    }
}