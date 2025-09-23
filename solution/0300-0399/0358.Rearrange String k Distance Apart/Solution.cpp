class Solution {
public:
    string rearrangeString(string s, int k) {
        vector<int> cnt(26, 0);
        for (char c : s) {
            ++cnt[c - 'a'];
        }

        priority_queue<pair<int, int>> pq;
        for (int i = 0; i < 26; ++i) {
            if (cnt[i] > 0) {
                pq.emplace(cnt[i], i);
            }
        }

        queue<pair<int, int>> q;
        string ans;
        while (!pq.empty()) {
            auto p = pq.top();
            pq.pop();
            p.first -= 1;
            ans.push_back('a' + p.second);
            q.push(p);
            if (q.size() >= k) {
                p = q.front();
                q.pop();
                if (p.first > 0) {
                    pq.push(p);
                }
            }
        }

        return ans.size() < s.size() ? "" : ans;
    }
};
