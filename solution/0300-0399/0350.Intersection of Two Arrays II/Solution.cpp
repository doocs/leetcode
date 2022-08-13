class Solution {
public:
    vector<int> intersect(vector<int>& nums1, vector<int>& nums2) {
        unordered_map<int, int> counter;
        for (int num : nums1) ++counter[num];
        vector<int> res;
        for (int num : nums2) {
            if (counter[num] > 0) {
                --counter[num];
                res.push_back(num);
            }
        }
        return res;
    }
};