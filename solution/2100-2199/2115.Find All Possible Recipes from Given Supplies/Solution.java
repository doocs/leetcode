class Solution {
    public List<String> findAllRecipes(
        String[] recipes, List<List<String>> ingredients, String[] supplies) {
        Map<String, List<String>> g = new HashMap<>();
        Map<String, Integer> indeg = new HashMap<>();
        for (int i = 0; i < recipes.length; ++i) {
            for (String v : ingredients.get(i)) {
                g.computeIfAbsent(v, k -> new ArrayList<>()).add(recipes[i]);
            }
            indeg.put(recipes[i], ingredients.get(i).size());
        }
        Deque<String> q = new ArrayDeque<>();
        for (String s : supplies) {
            q.offer(s);
        }
        List<String> ans = new ArrayList<>();
        while (!q.isEmpty()) {
            for (int n = q.size(); n > 0; --n) {
                String i = q.pollFirst();
                for (String j : g.getOrDefault(i, Collections.emptyList())) {
                    indeg.put(j, indeg.get(j) - 1);
                    if (indeg.get(j) == 0) {
                        ans.add(j);
                        q.offer(j);
                    }
                }
            }
        }
        return ans;
    }
}