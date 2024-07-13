class Solution {
public:
    vector<int> occurrencesOfElement(vector<int>& nums, vector<int>& queries, int x) {
        vector<int> ids;
        for (int i = 0; i < nums.size(); ++i) {
            if (nums[i] == x) {
                ids.push_back(i);
            }
        }
        vector<int> ans;
        for (int& i : queries) {
            ans.push_back(i - 1 < ids.size() ? ids[i - 1] : -1);
        }
        return ans;
    }
};