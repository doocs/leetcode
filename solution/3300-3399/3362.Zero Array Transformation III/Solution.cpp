class Solution {
public:
    int maxRemoval(vector<int>& nums, vector<vector<int>>& queries) {
        sort(queries.begin(), queries.end());
        priority_queue<int> pq;
        int n = nums.size();
        vector<int> d(n + 1, 0);
        int s = 0, j = 0;
        for (int i = 0; i < n; ++i) {
            s += d[i];
            while (j < queries.size() && queries[j][0] <= i) {
                pq.push(queries[j][1]);
                ++j;
            }
            while (s < nums[i] && !pq.empty() && pq.top() >= i) {
                ++s;
                int end = pq.top();
                pq.pop();
                --d[end + 1];
            }
            if (s < nums[i]) {
                return -1;
            }
        }
        return pq.size();
    }
};
