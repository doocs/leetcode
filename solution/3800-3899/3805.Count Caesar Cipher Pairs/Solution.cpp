class Solution {
public:
    long long countPairs(vector<string>& words) {
        unordered_map<string, int> cnt;
        long long ans = 0;
        for (auto& s : words) {
            string t = s;
            int k = 'z' - t[0];
            for (int i = 1; i < t.size(); i++) {
                t[i] = 'a' + (t[i] - 'a' + k) % 26;
            }
            t[0] = 'z';
            cnt[t]++;
        }
        for (auto& [key, v] : cnt) {
            ans += 1LL * v * (v - 1) / 2;
        }
        return ans;
    }
};
