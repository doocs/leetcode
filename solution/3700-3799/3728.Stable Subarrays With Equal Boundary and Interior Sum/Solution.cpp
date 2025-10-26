struct PairHash {
    size_t operator()(const pair<int, long long>& p) const {
        return hash<int>()(p.first) ^ (hash<long long>()(p.second) << 1);
    }
};

class Solution {
public:
    long long countStableSubarrays(vector<int>& capacity) {
        int n = capacity.size();
        vector<long long> s(n + 1, 0);
        for (int i = 1; i <= n; ++i) {
            s[i] = s[i - 1] + capacity[i - 1];
        }

        unordered_map<pair<int, long long>, int, PairHash> cnt;
        long long ans = 0;

        for (int r = 2; r < n; ++r) {
            int l = r - 2;
            pair<int, long long> keyL = {capacity[l], capacity[l] + s[l + 1]};
            cnt[keyL] += 1;

            pair<int, long long> keyR = {capacity[r], s[r]};
            ans += cnt.count(keyR) ? cnt[keyR] : 0;
        }

        return ans;
    }
};
