class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> d = new HashMap<>();
        for (int i = 0; i < words.length; ++i) {
            d.put(words[i], i);
        }
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < words.length; ++i) {
            String w = words[i];
            int m = w.length();
            for (int j = 0; j <= m; ++j) {
                String a = w.substring(0, j);
                String b = w.substring(j);
                String ra = new StringBuilder(a).reverse().toString();
                String rb = new StringBuilder(b).reverse().toString();
                if (d.containsKey(ra) && d.get(ra) != i && b.equals(rb)) {
                    ans.add(Arrays.asList(i, d.get(ra)));
                }
                if (j > 0 && d.containsKey(rb) && d.get(rb) != i && a.equals(ra)) {
                    ans.add(Arrays.asList(d.get(rb), i));
                }
            }
        }
        return ans;
    }
}
