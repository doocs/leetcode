class LockingTree {
    private Map<Integer, Integer> nums;
    private int[] parent;
    private List<Integer>[] children;

    public LockingTree(int[] parent) {
        nums = new HashMap<>();
        this.parent = parent;
        int n = parent.length;
        children = new List[n];
        Arrays.setAll(children, k -> new ArrayList<>());
        for (int i = 0; i < n; ++i) {
            if (parent[i] != -1) {
                children[parent[i]].add(i);
            }
        }
    }

    public boolean lock(int num, int user) {
        if (nums.containsKey(num)) {
            return false;
        }
        nums.put(num, user);
        return true;
    }

    public boolean unlock(int num, int user) {
        if (!nums.containsKey(num) || nums.get(num) != user) {
            return false;
        }
        nums.remove(num);
        return true;
    }

    public boolean upgrade(int num, int user) {
        int t = num;
        while (t != -1) {
            if (nums.containsKey(t)) {
                return false;
            }
            t = parent[t];
        }
        boolean[] find = new boolean[1];
        dfs(num, find);
        if (!find[0]) {
            return false;
        }
        nums.put(num, user);
        return true;
    }

    private void dfs(int num, boolean[] find) {
        for (int child : children[num]) {
            if (nums.containsKey(child)) {
                nums.remove(child);
                find[0] = true;
            }
            dfs(child, find);
        }
    }
}

/**
 * Your LockingTree object will be instantiated and called as such:
 * LockingTree obj = new LockingTree(parent);
 * boolean param_1 = obj.lock(num,user);
 * boolean param_2 = obj.unlock(num,user);
 * boolean param_3 = obj.upgrade(num,user);
 */