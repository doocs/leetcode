class Solution {
public:
    int minMeetingRooms(vector<vector<int>>& intervals) {
        int n = 1000010;
        vector<int> delta(n);
        for (auto e : intervals) {
            ++delta[e[0]];
            --delta[e[1]];
        }
        for (int i = 0; i < n - 1; ++i) {
            delta[i + 1] += delta[i];
        }
        return *max_element(delta.begin(), delta.end());
    }
};