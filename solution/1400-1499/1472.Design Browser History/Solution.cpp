class BrowserHistory {
public:
    stack<string> stk1;
    stack<string> stk2;

    BrowserHistory(string homepage) {
        visit(homepage);
    }

    void visit(string url) {
        stk1.push(url);
        stk2 = stack<string>();
    }

    string back(int steps) {
        for (; steps && stk1.size() > 1; --steps) {
            stk2.push(stk1.top());
            stk1.pop();
        }
        return stk1.top();
    }

    string forward(int steps) {
        for (; steps && !stk2.empty(); --steps) {
            stk1.push(stk2.top());
            stk2.pop();
        }
        return stk1.top();
    }
};

/**
 * Your BrowserHistory object will be instantiated and called as such:
 * BrowserHistory* obj = new BrowserHistory(homepage);
 * obj->visit(url);
 * string param_2 = obj->back(steps);
 * string param_3 = obj->forward(steps);
 */