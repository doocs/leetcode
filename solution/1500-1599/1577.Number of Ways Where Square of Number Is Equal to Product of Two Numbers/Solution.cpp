class Solution {
public:
    int numTriplets(vector<int>& nums1, vector<int>& nums2) {
        auto cnt1 = count(nums1);
        auto cnt2 = count(nums2);
        return cal(cnt1, nums2) + cal(cnt2, nums1);
    }

    unordered_map<long long, int> count(vector<int>& nums) {
        unordered_map<long long, int> cnt;
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                cnt[(long long) nums[i] * nums[j]]++;
            }
        }
        return cnt;
    }

    int cal(unordered_map<long long, int>& cnt, vector<int>& nums) {
        int ans = 0;
        for (int x : nums) {
            ans += cnt[(long long) x * x];
        }
        return ans;
    }
};
