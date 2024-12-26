class Solution {
public:
    int numTriplets(vector<int>& nums1, vector<int>& nums2) {
        auto cnt1 = count(nums1);
        auto cnt2 = count(nums2);
        return cal(cnt1, nums2) + cal(cnt2, nums1);
    }

    unordered_map<int, int> count(vector<int>& nums) {
        unordered_map<int, int> cnt;
        for (int x : nums) {
            ++cnt[x];
        }
        return cnt;
    }

    int cal(unordered_map<int, int>& cnt, vector<int>& nums) {
        long long ans = 0;
        for (int x : nums) {
            for (auto& [y, v1] : cnt) {
                int z = 1LL * x * x / y;
                if (1LL * y * z == 1LL * x * x) {
                    if (cnt.contains(z)) {
                        int v2 = cnt[z];
                        ans += 1LL * v1 * (y == z ? v2 - 1 : v2);
                    }
                }
            }
        }
        return ans / 2;
    }
};
