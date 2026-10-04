class ThroneInheritance {
    private String king;
    private Set<String> dead = new HashSet<>();
    private Map<String, List<String>> g = new HashMap<>();

    public ThroneInheritance(String kingName) {
        king = kingName;
    }

    public void birth(String parentName, String childName) {
        g.computeIfAbsent(parentName, k -> new ArrayList<>()).add(childName);
    }

    public void death(String name) {
        dead.add(name);
    }

    public List<String> getInheritanceOrder() {
        List<String> ans = new ArrayList<>();
        Deque<String> stk = new ArrayDeque<>();
        stk.push(king);
        while (!stk.isEmpty()) {
            String x = stk.pop();
            if (!dead.contains(x)) {
                ans.add(x);
            }
            List<String> children = g.getOrDefault(x, List.of());
            for (int i = children.size() - 1; i >= 0; --i) {
                stk.push(children.get(i));
            }
        }
        return ans;
    }
}

/**
 * Your ThroneInheritance object will be instantiated and called as such:
 * ThroneInheritance obj = new ThroneInheritance(kingName);
 * obj.birth(parentName,childName);
 * obj.death(name);
 * List<String> param_3 = obj.getInheritanceOrder();
 */
