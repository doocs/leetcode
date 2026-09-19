class Solution {
    public int[][] indexPairs(String text, String[] words) {
        Set<String> s = new HashSet<>(Arrays.asList(words));
        int n = text.length();
        List<int[]> ans = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n; ++j) {
                if (s.contains(text.substring(i, j + 1))) {
                    ans.add(new int[] {i, j});
                }
            }
        }
        return ans.toArray(new int[ans.size()][2]);
    }
}
