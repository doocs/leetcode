class Solution {
public:
    long long maximumScore(vector<int>& nums, string s) {
        long long ans = 0;
        priority_queue<int> pq;
        for (int i = 0; i < nums.size(); i++) {
            int x = nums[i];
            char c = s[i];
            pq.push(x);
            if (c == '1') {
                ans += pq.top();
                pq.pop();
            }
        }
        return ans;
    }
};
