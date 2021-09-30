class Solution {
public:
    vector<int> processQueries(vector<int>& queries, int m) {
        vector<int> nums(m);
        iota(nums.begin(), nums.end(), 1);
        vector<int> res;
        for (int num : queries)
        {
            int idx = -1;
            for (int i = 0; i < m; ++i)
            {
                if (nums[i] == num) {
                    idx = i;
                    break;
                }
            }
            res.push_back(idx);
            nums.erase(nums.begin() + idx);
            nums.insert(nums.begin(), num);
        }
        return res;
    }
};