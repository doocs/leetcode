class Solution {
public:
    vector<int> frequencySort(vector<int>& nums) {
        vector<int> cnt(201);
        for (int v : nums) {
            ++cnt[v + 100];
        }
        sort(nums.begin(), nums.end(), [&](const int a, const int b) {
            if (cnt[a + 100] == cnt[b + 100]) return a > b;
            return cnt[a + 100] < cnt[b + 100];
        });
        return nums;
    }
};