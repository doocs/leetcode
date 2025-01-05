class TaskManager {
    private final Map<Integer, int[]> d = new HashMap<>();
    private final TreeSet<int[]> st = new TreeSet<>((a, b) -> {
        if (a[0] == b[0]) {
            return b[1] - a[1];
        }
        return b[0] - a[0];
    });

    public TaskManager(List<List<Integer>> tasks) {
        for (var task : tasks) {
            add(task.get(0), task.get(1), task.get(2));
        }
    }

    public void add(int userId, int taskId, int priority) {
        d.put(taskId, new int[] {userId, priority});
        st.add(new int[] {priority, taskId});
    }

    public void edit(int taskId, int newPriority) {
        var e = d.get(taskId);
        int userId = e[0], priority = e[1];
        st.remove(new int[] {priority, taskId});
        st.add(new int[] {newPriority, taskId});
        d.put(taskId, new int[] {userId, newPriority});
    }

    public void rmv(int taskId) {
        var e = d.remove(taskId);
        int priority = e[1];
        st.remove(new int[] {priority, taskId});
    }

    public int execTop() {
        if (st.isEmpty()) {
            return -1;
        }
        var e = st.pollFirst();
        var t = d.remove(e[1]);
        return t[0];
    }
}

/**
 * Your TaskManager object will be instantiated and called as such:
 * TaskManager obj = new TaskManager(tasks);
 * obj.add(userId,taskId,priority);
 * obj.edit(taskId,newPriority);
 * obj.rmv(taskId);
 * int param_4 = obj.execTop();
 */
