class Solution {
public:
    int calPoints(vector<string>& ops) {
        vector<int> stk;
        for (auto& op : ops) {
            int n = stk.size();
            if (op == "+") {
                int a = stk[n - 1];
                int b = stk[n - 2];
                stk.push_back(a + b);
            } else if (op == "D")
                stk.push_back(stk[n - 1] * 2);
            else if (op == "C")
                stk.pop_back();
            else
                stk.push_back(stoi(op));
        }
        return accumulate(stk.begin(), stk.end(), 0);
    }
};