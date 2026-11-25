class Solution {
public:
    int smallestUniqueSubarray(vector<int>& nums) {
        int n = nums.size();
        vector<vector<int>> names{nums}; // names[k][i] is equal for equal windows of length 2^k starting at i
        for (int half = 1; 2 * half <= n; half *= 2) { // blocks of 2, 4, 8, ... while they fit
            unordered_map<long long, int> ids; // pair of halves -> number of the whole window
            vector<int> whole;
            for (auto [a, b] : views::zip(names.back(), names.back() | views::drop(half)))
                whole.push_back(ids.try_emplace((long long) a << 32 | b, ids.size()).first->second);
            names.push_back(std::move(whole));
        }

        auto found = [&](int length) { // is some window of this length unique?
            int k = bit_width((unsigned) length) - 1; // its first 2^k elements and its last 2^k cover it
            unordered_map<long long, int> count;
            for (auto [a, b] : views::zip(names[k], names[k] | views::drop(length - (1 << k))))
                count[(long long) a << 32 | b]++;
            return ranges::any_of(count, [](auto& entry) { return entry.second == 1; });
        };

        return *ranges::partition_point(views::iota(1, n), not_fn(found));
    }
};