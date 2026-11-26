class Solution {
public:
    int smallestUniqueSubarray(vector<int>& nums) {
        int n = nums.size();
        vector<vector<int>> names{nums}; // names[k][i] is equal for equal windows of length 2^k starting at i
        for (int half = 1; 2 * half <= n; half *= 2) { // blocks of 2, 4, 8, ... while they fit
            unordered_map<long long, int> ids; // pair of halves -> number of the whole window
            vector<int>& block = names.back();
            vector<int> whole;
            for (int i = half; i < (int) block.size(); ++i) {
                long long key = (long long) block[i - half] << 32 | block[i];
                whole.push_back(ids.try_emplace(key, (int) ids.size()).first->second);
            }
            names.push_back(std::move(whole));
        }

        auto found = [&](int length) { // is some window of this length unique?
            int k = 31 - __builtin_clz(length); // its first 2^k elements and its last 2^k cover it
            vector<int>& block = names[k];
            int shift = length - (1 << k);
            unordered_map<long long, int> count;
            for (int i = shift; i < (int) block.size(); ++i) {
                long long key = (long long) block[i - shift] << 32 | block[i];
                ++count[key];
            }
            for (auto& entry : count) {
                if (entry.second == 1) {
                    return true;
                }
            }
            return false;
        };

        int lo = 1, hi = n; // the first length that works is in lo..hi
        while (lo < hi) {
            int mid = (lo + hi) >> 1;
            if (found(mid)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }
};
