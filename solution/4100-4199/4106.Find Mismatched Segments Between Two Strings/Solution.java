class Solution {
    public List<List<String>> findMismatchedSegments(String s1, String s2) {
        int n = s1.length();
        List<List<String>> ans = new ArrayList<>();
        for (int i = 0; i < n;) {
            if (s1.charAt(i) == s2.charAt(i)) {
                ++i;
                continue;
            }
            int j = i + 1;
            while (j < n && s1.charAt(j) != s2.charAt(j)) {
                ++j;
            }
            ans.add(List.of(s1.substring(i, j), s2.substring(i, j)));
            i = j;
        }
        return ans;
    }
}