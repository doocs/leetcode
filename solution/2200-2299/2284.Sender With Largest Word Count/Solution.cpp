class Solution {
public:
    string largestWordCount(vector<string>& messages, vector<string>& senders) {
        unordered_map<string, int> cnt;
        int n = senders.size();
        for (int i = 0; i < n; ++i) {
            int v = 0;
            for (char& c : messages[i]) {
                if (c == ' ') ++v;
            }
            cnt[senders[i]] += v + 1;
        }
        string ans = senders[0];
        for (auto& [u, v] : cnt) {
            if (v > cnt[ans] || (v == cnt[ans] && u > ans)) ans = u;
        }
        return ans;
    }
};