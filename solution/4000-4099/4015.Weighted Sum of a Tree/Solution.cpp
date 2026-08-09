class Solution {
public:
    long long weightedSum(vector<int>& parent, vector<int>& nums) {
        int n = nums.size();

        vector<vector<int>> g(n);

        for (int i = 1; i < n; i++) {
            g[parent[i]].push_back(i);
        }

        long long ans = 0;

        vector<int> q = {0};

        int d = 0;

        while (!q.empty()) {
            d++;

            vector<int> nq;

            for (int i : q) {
                ans += 1LL * nums[i] * (1 - d);
                for (int son : g[i]) {
                    nq.push_back(son);
                }
            }

            q = move(nq);
        }

        long long sum = 0;
        for (int x : nums) {
            sum += x;
        }

        ans += 1LL * d * sum;

        return ans;
    }
};