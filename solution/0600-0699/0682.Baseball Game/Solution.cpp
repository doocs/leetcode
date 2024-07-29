class Solution {
public:
    int calPoints(vector<string>& operations) {
        vector<int> stk;
        for (auto& op : operations) {
            int n = stk.size();
            if (op == "+") {
                stk.push_back(stk[n - 1] + stk[n - 2]);
            } else if (op == "D") {
                stk.push_back(stk[n - 1] << 1);
            } else if (op == "C") {
                stk.pop_back();
            } else {
                stk.push_back(stoi(op));
            }
        }
        return accumulate(stk.begin(), stk.end(), 0);
    }
};