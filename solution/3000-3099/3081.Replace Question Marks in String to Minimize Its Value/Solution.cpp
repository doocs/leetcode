class Solution {
public:
    string minimizeStringValue(string s) {
        int cnt[26]{};
        int k = 0;
        for (char& c : s) {
            if (c == '?') {
                ++k;
            } else {
                ++cnt[c - 'a'];
            }
        }
        priority_queue<pair<int, int>, vector<pair<int, int>>, greater<>> pq;
        for (int i = 0; i < 26; ++i) {
            pq.push({cnt[i], i});
        }
        vector<int> t(k);
        for (int i = 0; i < k; ++i) {
            auto [v, c] = pq.top();
            pq.pop();
            t[i] = c;
            pq.push({v + 1, c});
        }
        sort(t.begin(), t.end());
        int j = 0;
        for (char& c : s) {
            if (c == '?') {
                c = t[j++] + 'a';
            }
        }
        return s;
    }
};