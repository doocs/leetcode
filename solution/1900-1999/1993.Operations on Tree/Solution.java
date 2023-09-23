class LockingTree {
    private int[] locked;
    private int[] parent;
    private List<Integer>[] children;

    public LockingTree(int[] parent) {
        int n = parent.length;
        locked = new int[n];
        this.parent = parent;
        children = new List[n];
        Arrays.fill(locked, -1);
        Arrays.setAll(children, i -> new ArrayList<>());
        for (int i = 1; i < n; i++) {
            children[parent[i]].add(i);
        }
    }
    
    public boolean lock(int num, int user) {
        if (locked[num] == -1) {
            locked[num] = user;
            return true;
        }
        return false;
    }
    
    public boolean unlock(int num, int user) {
        if (locked[num] == user) {
            locked[num] = -1;
            return true;
        }
        return false;
    }
    
    public boolean upgrade(int num, int user) {
        int x = num;
        while (x != -1) {
            if (locked[x] != -1) {
                return false;
            }
            x = parent[x];
        }
        boolean[] find = new boolean[1];
        dfs(num, find);
        if (!find[0]) {
            return false;
        }
        locked[num] = user;
        return true;
    }

    private void dfs(int x, boolean[] find) {
        for (int y : children[x]) {
            if (locked[y] != -1) {
                locked[y] = -1;
                find[0] = true;
            }
            dfs(y, find);
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