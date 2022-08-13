class Solution {
public:
    int earliestFullBloom(vector<int>& plantTime, vector<int>& growTime) {
        int n = plantTime.size();
        vector<pair<int, int>> arr;
        for (int i = 0; i < n; ++i) arr.push_back({-growTime[i], plantTime[i]});
        sort(arr.begin(), arr.end());
        int ans = 0, t = 0;
        for (auto [a, b] : arr) {
            t += b;
            ans = max(ans, t - a);
        }
        return ans;
    }
};