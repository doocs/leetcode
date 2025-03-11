class Solution {
    private final Map<String, String> d = new HashMap<>();

    public String applySubstitutions(List<List<String>> replacements, String text) {
        for (List<String> e : replacements) {
            d.put(e.get(0), e.get(1));
        }
        return dfs(text);
    }

    private String dfs(String s) {
        int i = s.indexOf("%");
        if (i == -1) {
            return s;
        }
        int j = s.indexOf("%", i + 1);
        if (j == -1) {
            return s;
        }
        String key = s.substring(i + 1, j);
        String replacement = dfs(d.getOrDefault(key, ""));
        return s.substring(0, i) + replacement + dfs(s.substring(j + 1));
    }
}
