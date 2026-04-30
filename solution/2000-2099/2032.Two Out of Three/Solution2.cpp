class Solution {
public:
    vector<int> twoOutOfThree(vector<int>& nums1, vector<int>& nums2, vector<int>& nums3) {
        unordered_map<int, int> mask;
        vector<vector<int>*> all = {&nums1, &nums2, &nums3};

        for (int i = 0; i < 3; ++i) {
            for (int x : *all[i]) {
                mask[x] |= (1 << i);
            }
        }

        vector<int> ans;
        for (auto& [x, m] : mask) {
            if (m & (m - 1)) {
                ans.push_back(x);
            }
        }
        return ans;
    }
};
