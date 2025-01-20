class Solution {
public:
    int maximumProduct(vector<int>& nums, int k) {
        priority_queue<int, vector<int>, greater<int>> pq;
        for (int x : nums) {
            pq.push(x);
        }
        while (k-- > 0) {
            int smallest = pq.top();
            pq.pop();
            pq.push(smallest + 1);
        }
        const int mod = 1e9 + 7;
        long long ans = 1;
        while (!pq.empty()) {
            ans = (ans * pq.top()) % mod;
            pq.pop();
        }
        return static_cast<int>(ans);
    }
};
