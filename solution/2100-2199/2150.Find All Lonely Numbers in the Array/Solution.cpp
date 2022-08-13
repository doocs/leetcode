class Solution {
public:
    vector<int> findLonely(vector<int>& nums) {
        unordered_map<int, int> counter;
        for (int num : nums) ++counter[num];
        vector<int> ans;
        for (auto& e : counter) {
            int k = e.first, v = e.second;
            if (v == 1 && !counter.count(k - 1) && !counter.count(k + 1)) ans.push_back(k);
        }
        return ans;
    }
};