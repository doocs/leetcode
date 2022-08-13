class Solution {
public:
    int maxSatisfaction(vector<int>& satisfaction) {
        sort(rbegin(satisfaction), rend(satisfaction));
        int ans = 0, presum = 0;
        for (int v : satisfaction) {
            presum += v;
            if (presum > 0)
                ans += presum;
            else
                break;
        }
        return ans;
    }
};