class Solution {
public:
    vector<vector<int>> kSmallestPairs(vector<int>& nums1, vector<int>& nums2, int k) {
        using pii = pair<int, int>;
        auto cmp = [](pii p1, pii p2) { return p1.first + p1.second < p2.first + p2.second; };
        priority_queue<pii, vector<pii>, decltype(cmp)> pq(cmp);
        for (int i = 0; i < nums1.size() && i < k; ++i) {
            for (int j = 0; j < nums2.size() && j < k; ++j) {
                pq.push({nums1[i], nums2[j]});
                if (pq.size() > k) pq.pop();
            }
        }
        vector<vector<int>> ans;
        while (!pq.empty()) {
            pii p = pq.top();
            pq.pop();
            ans.push_back({p.first, p.second});
        }
        return ans;
    }
};
