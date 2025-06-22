class Solution {
public:
    int minSwaps(vector<int>& nums) {
        vector<int> pos[2];
        for (int i = 0; i < nums.size(); ++i) {
            pos[nums[i] & 1].push_back(i);
        }
        if (abs(int(pos[0].size() - pos[1].size())) > 1) {
            return -1;
        }
        auto calc = [&](int k) {
            int res = 0;
            for (int i = 0; i < nums.size(); i += 2) {
                res += abs(pos[k][i / 2] - i);
            }
            return res;
        };
        if (pos[0].size() > pos[1].size()) {
            return calc(0);
        }
        if (pos[0].size() < pos[1].size()) {
            return calc(1);
        }
        return min(calc(0), calc(1));
    }
};
