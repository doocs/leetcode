class Solution {
public:
    int minOperations(vector<int>& nums) {
        unordered_set<int> st;
        for (int i = nums.size() - 1; ~i; --i) {
            if (st.contains(nums[i])) {
                return i / 3 + 1;
            }
            st.insert(nums[i]);
        }
        return 0;
    }
};
