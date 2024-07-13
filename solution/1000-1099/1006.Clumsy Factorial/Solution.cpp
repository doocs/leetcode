class Solution {
public:
    int clumsy(int n) {
        stack<int> stk;
        stk.push(n);
        int k = 0;
        for (int x = n - 1; x; --x) {
            if (k == 0) {
                stk.top() *= x;
            } else if (k == 1) {
                stk.top() /= x;
            } else if (k == 2) {
                stk.push(x);
            } else {
                stk.push(-x);
            }
            k = (k + 1) % 4;
        }
        int ans = 0;
        while (!stk.empty()) {
            ans += stk.top();
            stk.pop();
        }
        return ans;
    }
};