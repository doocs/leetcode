class Solution {
    private Map<String, List<String>> g = new HashMap<>();
    private List<String> ans = new ArrayList<>();

    public List<String> findItinerary(List<List<String>> tickets) {
        Collections.sort(tickets, (a, b) -> b.get(1).compareTo(a.get(1)));
        for (List<String> ticket : tickets) {
            g.computeIfAbsent(ticket.get(0), k -> new ArrayList<>()).add(ticket.get(1));
        }
        dfs("JFK");
        Collections.reverse(ans);
        return ans;
    }

    private void dfs(String f) {
        while (g.containsKey(f) && !g.get(f).isEmpty()) {
            String t = g.get(f).remove(g.get(f).size() - 1);
            dfs(t);
        }
        ans.add(f);
    }
}