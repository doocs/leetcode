class Solution {
public:
    int maximumSetSize(vector<int>& nums1, vector<int>& nums2) {
        unordered_set<int> s1(nums1.begin(), nums1.end());
        unordered_set<int> s2(nums2.begin(), nums2.end());
        int n = nums1.size();
        int a = 0, b = 0, c = 0;
        for (int x : s1) {
            if (!s2.count(x)) {
                ++a;
            }
        }
        for (int x : s2) {
            if (!s1.count(x)) {
                ++b;
            } else {
                ++c;
            }
        }
        a = min(a, n / 2);
        b = min(b, n / 2);
        return min(a + b + c, n);
    }
};