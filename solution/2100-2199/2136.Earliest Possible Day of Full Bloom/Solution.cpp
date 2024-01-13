class Solution {
public:
    int earliestFullBloom(vector<int>& plantTime, vector<int>& growTime) {
        int n = plantTime.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) { return growTime[j] < growTime[i]; });
        int ans = 0, t = 0;
        for (int i : idx) {
            t += plantTime[i];
            ans = max(ans, t + growTime[i]);
        }
        return ans;
    }
};