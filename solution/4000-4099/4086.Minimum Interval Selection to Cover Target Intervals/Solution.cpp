class Solution {
public:
    int minSelection(vector<vector<int>>& availableIntervals, vector<vector<int>>& targetIntervals) {
        sort(availableIntervals.begin(), availableIntervals.end(), [](auto& a, auto& b) { return a[0] < b[0]; });
        sort(targetIntervals.begin(), targetIntervals.end(), [](auto& a, auto& b) { return a[0] < b[0]; });
        int ans = 0, cur = 0, i = 0;
        for (auto& t : targetIntervals) {
            int l = t[0], r = t[1];
            while (cur < r) {
                int uncover = max(l, cur);
                int nxt = cur;
                while (i < (int) availableIntervals.size() && availableIntervals[i][0] <= uncover) {
                    nxt = max(nxt, availableIntervals[i][1]);
                    i++;
                }
                if (nxt < uncover || nxt == cur) {
                    return -1;
                }
                cur = nxt;
                ans++;
            }
        }
        return ans;
    }
};