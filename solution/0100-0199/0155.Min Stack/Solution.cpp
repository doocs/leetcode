class MinStack {//方法1
public:
    /** initialize your data structure here. */
    stack<int> s;
    MinStack() {
        
    }
    
    void push(int x) {
        if(s.empty()) {
            s.push(x);
            s.push(x);
        } else {
            int temp = s.top();
            s.push(x);
            if(x < temp) {
                s.push(x);
            } else {
                s.push(temp);
            }
        }
    }
    
    void pop() {
        s.pop();
        s.pop();
    }
    
    int top() {
        int temp = s.top();
        s.pop();
        int top = s.top();
        s.push(temp);
        return top;
    }
    
    int getMin() {
        return s.top();
    }
};