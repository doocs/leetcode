/**
 *  Author: Moriarty12138
 */
class Solution {
public:
    vector<int> dailyTemperatures(vector<int>& T) {
        int n = T.size();
        vector<int> ans(n);
        stack<int> s;
        for(int i = 0; i < n; ++i) {
            while(!s.empty() && T[s.top()] < T[i]) {
                int pre = s.top();
                s.pop();
                ans[pre] = i - pre;
            }
            s.push(i);
        }
        return ans;
    }
};
