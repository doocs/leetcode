class DinnerPlates {
    private int capacity;
    private List<Deque<Integer>> stacks = new ArrayList<>();
    private TreeSet<Integer> notFull = new TreeSet<>();

    public DinnerPlates(int capacity) {
        this.capacity = capacity;
    }

    public void push(int val) {
        if (notFull.isEmpty()) {
            stacks.add(new ArrayDeque<>());
            stacks.get(stacks.size() - 1).push(val);
            if (capacity > 1) {
                notFull.add(stacks.size() - 1);
            }
        } else {
            int index = notFull.first();
            stacks.get(index).push(val);
            if (stacks.get(index).size() == capacity) {
                notFull.pollFirst();
            }
        }
    }

    public int pop() {
        return popAtStack(stacks.size() - 1);
    }

    public int popAtStack(int index) {
        if (index < 0 || index >= stacks.size() || stacks.get(index).isEmpty()) {
            return -1;
        }
        int val = stacks.get(index).pop();
        if (index == stacks.size() - 1 && stacks.get(stacks.size() - 1).isEmpty()) {
            while (!stacks.isEmpty() && stacks.get(stacks.size() - 1).isEmpty()) {
                notFull.remove(stacks.size() - 1);
                stacks.remove(stacks.size() - 1);
            }
        } else {
            notFull.add(index);
        }
        return val;
    }
}

/**
 * Your DinnerPlates object will be instantiated and called as such:
 * DinnerPlates obj = new DinnerPlates(capacity);
 * obj.push(val);
 * int param_2 = obj.pop();
 * int param_3 = obj.popAtStack(index);
 */