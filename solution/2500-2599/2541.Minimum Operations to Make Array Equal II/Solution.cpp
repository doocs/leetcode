class Solution {
public:
    long long minOperations(vector<int>& nums1, vector<int>& nums2, int k) {
        long long ans = 0, x = 0;
        for (int i = 0; i < nums1.size(); ++i) {
            int a = nums1[i], b = nums2[i];
            if (k == 0) {
                if (a != b) {
                    return -1;
                }
                continue;
            }
            if ((a - b) % k != 0) {
                return -1;
            }
            int y = (a - b) / k;
            ans += abs(y);
            x += y;
        }
        return x == 0 ? ans / 2 : -1;
    }
};