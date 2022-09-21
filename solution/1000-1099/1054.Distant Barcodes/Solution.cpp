using pii = pair<int, int>;

class Solution {
public:
    vector<int> rearrangeBarcodes(vector<int>& barcodes) {
        unordered_map<int, int> cnt;
        for (auto& v : barcodes) {
            ++cnt[v];
        }
        priority_queue<pii> pq;
        for (auto& [k, v] : cnt) {
            pq.push({v, k});
        }
        vector<int> ans;
        queue<pii> q;
        while (pq.size()) {
            auto p = pq.top();
            pq.pop();
            ans.push_back(p.second);
            p.first--;
            q.push(p);
            while (q.size() > 1) {
                p = q.front();
                q.pop();
                if (p.first) {
                    pq.push(p);
                }
            }
        }
        return ans;
    }
};