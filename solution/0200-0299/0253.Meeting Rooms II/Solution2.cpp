class Solution {
public:
    int minMeetingRooms(vector<vector<int>>& intervals) {
        map<int, int> d;
        for (const auto& e : intervals) {
            d[e[0]]++;
            d[e[1]]--;
        }
        int ans = 0, s = 0;
        for (auto& [_, v] : d) {
            s += v;
            ans = max(ans, s);
        }
        return ans;
    }
};
