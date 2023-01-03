using pii = pair<int, int>;

class Solution {
public:
    vector<int> topKFrequent(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        for (int v : nums) ++cnt[v];
        priority_queue<pii, vector<pii>, greater<pii>> pq;
        for (auto& [num, freq] : cnt) {
            pq.push({freq, num});
            if (pq.size() > k) {
                pq.pop();
            }
        }
        vector<int> ans(k);
        for (int i = 0; i < k; ++i) {
            ans[i] = pq.top().second;
            pq.pop();
        }
        return ans;
    }
};