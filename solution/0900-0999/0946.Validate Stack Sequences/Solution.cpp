class Solution {
public:
    bool validateStackSequences(vector<int>& pushed, vector<int>& popped) {
        int j = 0, n = popped.size();
        stack<int> stk;
        for (int x : pushed) {
            stk.push(x);
            while (!stk.empty() && j < n && stk.top() == popped[j]) {
                stk.pop();
                ++j;
            }
        }
        return j == n;
    }
};