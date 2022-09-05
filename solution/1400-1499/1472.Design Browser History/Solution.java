class BrowserHistory {
    private Deque<String> stk1 = new ArrayDeque<>();
    private Deque<String> stk2 = new ArrayDeque<>();

    public BrowserHistory(String homepage) {
        visit(homepage);
    }

    public void visit(String url) {
        stk1.push(url);
        stk2.clear();
    }

    public String back(int steps) {
        for (; steps > 0 && stk1.size() > 1; --steps) {
            stk2.push(stk1.pop());
        }
        return stk1.peek();
    }

    public String forward(int steps) {
        for (; steps > 0 && !stk2.isEmpty(); --steps) {
            stk1.push(stk2.pop());
        }
        return stk1.peek();
    }
}

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory obj = new BrowserHistory(homepage);
 * obj.visit(url);
 * String param_2 = obj.back(steps);
 * String param_3 = obj.forward(steps);
 */