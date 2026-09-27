class Solution {
public:
    int longestSubarray(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> p(n + 1);
        for (int i = 0; i < n; ++i) {
            p[i + 1] = (p[i] + nums[i]) % k;
            if (p[i + 1] < 0) {
                p[i + 1] += k;
            }
        }

        vector<int> first(k, -1);
        for (int i = 0; i <= n; ++i) {
            if (first[p[i]] == -1) {
                first[p[i]] = i;
            }
        }

        vector<int> order;
        for (int q = 0; q < k; ++q) {
            if (first[q] != -1) {
                order.push_back(q);
            }
        }

        ranges::sort(order, [&](int a, int b) {
            return first[a] < first[b];
        });

        vector<int> pos(k);
        vector<int> best(k, INT_MAX);
        for (int q = 0; q < k; ++q) {
            best[q] = first[q] == -1 ? INT_MAX : first[q];
        }

        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int a = nums[i] % k;
            if (a < 0) {
                a += k;
            }

            while (pos[a] < order.size() && first[order[pos[a]]] <= i) {
                int q = order[pos[a]++];
                int t = (q + 2 * a) % k;
                best[t] = min(best[t], first[q]);
            }

            int s = p[i + 1];
            if (best[s] != INT_MAX) {
                ans = max(ans, i + 1 - best[s]);
            }
        }
        return ans;
    }
};