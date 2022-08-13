class Solution {
public:
    bool verifyPreorder(vector<int>& preorder) {
        stack<int> stk;
        int last = INT_MIN;
        for (int x : preorder) {
            if (x < last) return false;
            while (!stk.empty() && stk.top() < x) {
                last = stk.top();
                stk.pop();
            }
            stk.push(x);
        }
        return true;
    }
};