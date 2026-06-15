class Solution {
public:
    int getLength(vector<int>& nums) {
        int n = nums.size();
        int ans = 1;

        for (int l = 0; l < n; ++l) {
            unordered_map<int, int> cnt;
            unordered_map<int, int> freq;

            for (int r = l; r < n; ++r) {
                int x = nums[r];
                int c = cnt[x];

                if (freq.contains(c)) {
                    if (--freq[c] == 0) {
                        freq.erase(c);
                    }
                }

                ++cnt[x];
                ++freq[cnt[x]];

                if (cnt.size() == 1 || (freq.size() == 2 && (freq.contains(cnt[x] * 2) || (cnt[x] % 2 == 0 && freq.contains(cnt[x] / 2))))) {
                    ans = max(ans, r - l + 1);
                }
            }
        }

        return ans;
    }
};