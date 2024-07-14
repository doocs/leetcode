class Solution {
public:
    vector<long long> mostFrequentIDs(vector<int>& nums, vector<int>& freq) {
        unordered_map<int, long long> cnt;
        unordered_map<long long, int> lazy;
        int n = nums.size();
        vector<long long> ans(n);
        priority_queue<long long> pq;

        for (int i = 0; i < n; ++i) {
            int x = nums[i], f = freq[i];
            lazy[cnt[x]]++;
            cnt[x] += f;
            pq.push(cnt[x]);
            while (!pq.empty() && lazy[pq.top()] > 0) {
                lazy[pq.top()]--;
                pq.pop();
            }
            ans[i] = pq.empty() ? 0 : pq.top();
        }

        return ans;
    }
};