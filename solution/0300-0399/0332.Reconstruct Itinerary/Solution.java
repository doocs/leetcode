class Solution {
    int n;
    boolean found;
    void dfs(Map<String, Queue<String>> adjLists, 
             List<String> ans, String curr) {
        Queue<String> neighbors = adjLists.get(curr);
        if (neighbors == null) {
            ans.add(curr);
            return ;
        }
        while (!neighbors.isEmpty()) {
            String neighbor = neighbors.poll();
            dfs(adjLists, ans, neighbor);
        }
        ans.add(curr);
        return ;
    }
    public List<String> findItinerary(List<List<String>> tickets) {
        n = tickets.size();
        Map<String, Queue<String>> adjLists = new HashMap<>();
        for (List<String> ticket : tickets) {
            String from = ticket.get(0);
            String to = ticket.get(1);
            if (!adjLists.containsKey(from)) {
                adjLists.put(from, new PriorityQueue<>());
            }
            adjLists.get(from).add(to);
        }
        List<String> ans = new ArrayList<>();
        dfs(adjLists, ans, "JFK");
        Collections.reverse(ans);
        return ans;
    }
}