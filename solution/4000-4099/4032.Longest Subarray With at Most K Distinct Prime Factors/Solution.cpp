class Solution {
public:
    int longestSubarray(vector<int>& nums, int k) {
        const int MX = 100001;

        static vector<vector<int>> primes(MX);

        static bool initialized = false;
        if (!initialized) {
            initialized = true;

            for (int i = 2; i < MX; i++) {
                if (primes[i].empty()) {
                    for (int j = i; j < MX; j += i) {
                        primes[j].push_back(i);
                    }
                }
            }
        }

        unordered_map<int, int> cnt;

        int ans = 0;
        int l = 0;

        for (int r = 0; r < nums.size(); r++) {
            for (int p : primes[nums[r]]) {
                cnt[p]++;
            }

            while (cnt.size() > k) {
                for (int p : primes[nums[l]]) {
                    if (--cnt[p] == 0) {
                        cnt.erase(p);
                    }
                }
                l++;
            }

            ans = max(ans, r - l + 1);
        }

        return ans;
    }
};