class Solution {
public:
    vector<int> exclusiveTime(int n, vector<string>& logs) {
        vector<int> ans(n);
        stack<int> stk;
        int curr = -1;
        for (auto& log : logs) {
            char type[10];
            int fid, ts;
            sscanf(log.c_str(), "%d:%[^:]:%d", &fid, type, &ts);
            if (type[0] == 's') {
                if (!stk.empty()) ans[stk.top()] += ts - curr;
                curr = ts;
                stk.push(fid);
            } else {
                fid = stk.top();
                stk.pop();
                ans[fid] += ts - curr + 1;
                curr = ts + 1;
            }
        }
        return ans;
    }
};