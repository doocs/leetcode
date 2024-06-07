class Solution {
public:
    int countDays(int days, vector<vector<int>>& meetings) {
        sort(meetings.begin(), meetings.end());
        int ans = 0, last = 0;
        for (auto& e : meetings) {
            int st = e[0], ed = e[1];
            if (last < st) {
                ans += st - last - 1;
            }
            last = max(last, ed);
        }
        ans += days - last;
        return ans;
    }
};