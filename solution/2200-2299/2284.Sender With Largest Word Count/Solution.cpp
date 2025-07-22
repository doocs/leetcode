class Solution {
public:
    string largestWordCount(vector<string>& messages, vector<string>& senders) {
        unordered_map<string, int> cnt;
        for (int i = 0; i < messages.size(); ++i) {
            int v = count(messages[i].begin(), messages[i].end(), ' ') + 1;
            cnt[senders[i]] += v;
        }
        string ans = senders[0];
        for (auto& [k, v] : cnt) {
            if (cnt[ans] < v || (cnt[ans] == v && ans < k)) {
                ans = k;
            }
        }
        return ans;
    }
};