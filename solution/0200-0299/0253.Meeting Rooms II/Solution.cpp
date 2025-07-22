class Solution {
public:
    int minMeetingRooms(vector<vector<int>>& intervals) {
        int m = 0;
        for (const auto& e : intervals) {
            m = max(m, e[1]);
        }
        vector<int> d(m + 1);
        for (const auto& e : intervals) {
            d[e[0]]++;
            d[e[1]]--;
        }
        int ans = 0, s = 0;
        for (int v : d) {
            s += v;
            ans = max(ans, s);
        }
        return ans;
    }
};
