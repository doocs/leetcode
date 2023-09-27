class Solution {
public:
    int earliestFullBloom(vector<int>& plantTime, vector<int>& growTime) {
        int n = plantTime.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) { return growTime[j] < growTime[i]; });
        int ans = 0, t = 0;
        for (int i : idx) {
            int pt = plantTime[i], gt = growTime[i];
            t += pt;
            ans = max(ans, t + gt);
        }
        return ans;
    }
};