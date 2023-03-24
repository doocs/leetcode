class Solution {
public:
    int makePrefSumNonNegative(vector<int>& nums) {
        priority_queue<int, vector<int>, greater<int>> pq;
        int ans = 0;
        long long s = 0;
        for (int& x : nums) {
            s += x;
            if (x < 0) {
                pq.push(x);
            }
            while (s < 0) {
                s -= pq.top();
                pq.pop();
                ++ans;
            }
        }
        return ans;
    }
};