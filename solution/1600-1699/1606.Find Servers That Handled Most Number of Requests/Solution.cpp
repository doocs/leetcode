class Solution {
public:
    vector<int> busiestServers(int k, vector<int>& arrival, vector<int>& load) {
        set<int> free;
        for (int i = 0; i < k; ++i) free.insert(i);
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> busy;
        vector<int> cnt(k);
        for (int i = 0; i < arrival.size(); ++i) {
            int start = arrival[i], end = start + load[i];
            while (!busy.empty() && busy.top().first <= start) {
                free.insert(busy.top().second);
                busy.pop();
            }
            if (free.empty()) continue;
            auto p = free.lower_bound(i % k);
            if (p == free.end()) p = free.begin();
            int server = *p;
            ++cnt[server];
            busy.emplace(end, server);
            free.erase(server);
        }
        int mx = *max_element(cnt.begin(), cnt.end());
        vector<int> ans;
        for (int i = 0; i < k; ++i)
            if (cnt[i] == mx)
                ans.push_back(i);
        return ans;
    }
};