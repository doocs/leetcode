class Solution {
public:
    vector<int> getFinalState(vector<int>& nums, int k, int multiplier) {
        auto cmp = [&nums](int i, int j) {
            return nums[i] == nums[j] ? i > j : nums[i] > nums[j];
        };
        priority_queue<int, vector<int>, decltype(cmp)> pq(cmp);

        for (int i = 0; i < nums.size(); ++i) {
            pq.push(i);
        }

        while (k--) {
            int i = pq.top();
            pq.pop();
            nums[i] *= multiplier;
            pq.push(i);
        }

        return nums;
    }
};
