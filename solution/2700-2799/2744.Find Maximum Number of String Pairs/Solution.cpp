class Solution {
public:
    int maximumNumberOfStringPairs(vector<string>& words) {
        unordered_map<int, int> cnt;
        int ans = 0;
        for (auto& w : words) {
            int a = w[0] - 'a', b = w[1] - 'a';
            ans += cnt[b << 5 | a];
            cnt[a << 5 | b]++;
        }
        return ans;
    }
};