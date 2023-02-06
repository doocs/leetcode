class StackOfPlates {
    private List<Deque<Integer>> stk = new ArrayList<>();
    private int cap;

    public StackOfPlates(int cap) {
        this.cap = cap;
    }

    public void push(int val) {
        if (cap == 0) {
            return;
        }
        if (stk.isEmpty() || stk.get(stk.size() - 1).size() >= cap) {
            stk.add(new ArrayDeque<>());
        }
        stk.get(stk.size() - 1).push(val);
    }

    public int pop() {
        return popAt(stk.size() - 1);
    }

    public int popAt(int index) {
        int ans = -1;
        if (index >= 0 && index < stk.size()) {
            ans = stk.get(index).pop();
            if (stk.get(index).isEmpty()) {
                stk.remove(index);
            }
        }
        return ans;
    }
}

/**
 * Your StackOfPlates object will be instantiated and called as such:
 * StackOfPlates obj = new StackOfPlates(cap);
 * obj.push(val);
 * int param_2 = obj.pop();
 * int param_3 = obj.popAt(index);
 */