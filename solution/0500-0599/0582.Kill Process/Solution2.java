class Solution {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        Map<Integer, List<Integer>> g = new HashMap<>();
        int n = pid.size();
        for (int i = 0; i < n; ++i) {
            g.computeIfAbsent(ppid.get(i), k -> new ArrayList<>()).add(pid.get(i));
        }
        List<Integer> ans = new ArrayList<>();
        Deque<Integer> stk = new ArrayDeque<>();
        stk.push(kill);
        while (!stk.isEmpty()) {
            int i = stk.pop();
            ans.add(i);
            List<Integer> children = g.getOrDefault(i, List.of());
            for (int k = children.size() - 1; k >= 0; --k) {
                stk.push(children.get(k));
            }
        }
        return ans;
    }
}
