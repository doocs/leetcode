class Solution {
public:
    vector<int> intersection(vector<int>& nums1, vector<int>& nums2) {
        unordered_set<int> s;
        for (int num : nums1) s.insert(num);
        unordered_set<int> t;
        vector<int> res;
        for (int num : nums2) {
            if (s.count(num) && !t.count(num)) {
                t.insert(num);
                res.push_back(num);
            }
        }
        return res;
    }
};