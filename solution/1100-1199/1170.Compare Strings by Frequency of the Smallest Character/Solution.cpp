class Solution {
public:
    vector<int> numSmallerByFrequency(vector<string>& queries, vector<string>& words) {
        auto f = [](string s) {
            int cnt[26] = {0};
            for (char c : s) {
                cnt[c - 'a']++;
            }
            for (int x : cnt) {
                if (x) {
                    return x;
                }
            }
            return 0;
        };
        int n = words.size();
        int nums[n];
        for (int i = 0; i < n; i++) {
            nums[i] = f(words[i]);
        }
        sort(nums, nums + n);
        vector<int> ans;
        for (auto& q : queries) {
            int x = f(q);
            ans.push_back(n - (upper_bound(nums, nums + n, x) - nums));
        }
        return ans;
    }
};