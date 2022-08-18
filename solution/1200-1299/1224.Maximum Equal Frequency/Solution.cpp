class Solution {
public:
    int maxEqualFreq(vector<int>& nums) {
        unordered_map<int, int> cnt;
        unordered_map<int, int> ccnt;
        int ans = 0, mx = 0;
        for (int i = 1; i <= nums.size(); ++i) {
            int v = nums[i - 1];
            if (cnt[v]) --ccnt[cnt[v]];
            ++cnt[v];
            mx = max(mx, cnt[v]);
            ++ccnt[cnt[v]];
            if (mx == 1)
                ans = i;
            else if (ccnt[mx] * mx + ccnt[mx - 1] * (mx - 1) == i && ccnt[mx] == 1)
                ans = i;
            else if (ccnt[mx] * mx + 1 == i && ccnt[1] == 1)
                ans = i;
        }
        return ans;
    }
};