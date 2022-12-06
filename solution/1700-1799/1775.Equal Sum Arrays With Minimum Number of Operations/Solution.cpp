class Solution {
public:
    int minOperations(vector<int>& nums1, vector<int>& nums2) {
        int s1 = accumulate(nums1.begin(), nums1.end(), 0);
        int s2 = accumulate(nums2.begin(), nums2.end(), 0);
        if (s1 == s2) return 0;
        if (s1 > s2) return minOperations(nums2, nums1);
        int d = s2 - s1;
        int arr[nums1.size() + nums2.size()];
        int k = 0;
        for (int& v : nums1) arr[k++] = 6 - v;
        for (int& v : nums2) arr[k++] = v - 1;
        sort(arr, arr + k, greater<>());
        for (int i = 0; i < k; ++i) {
            d -= arr[i];
            if (d <= 0) return i + 1;
        }
        return -1;
    }
};