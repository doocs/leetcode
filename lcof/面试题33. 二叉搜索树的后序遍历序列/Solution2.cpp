class Solution {
public:
    bool verifyPostorder(vector<int>& postorder) {
        stack<int> stk;
        int mx = 1 << 30;
        reverse(postorder.begin(), postorder.end());
        for (int& x : postorder) {
            if (x > mx) {
                return false;
            }
            while (!stk.empty() && stk.top() > x) {
                mx = stk.top();
                stk.pop();
            }
            stk.push(x);
        }
        return true;
    }
};