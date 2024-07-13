class Solution {
public:
    long long kthSmallestProduct(vector<int>& nums1, vector<int>& nums2, long long k) {
        int m = nums1.size(), n = nums2.size();
        int a = max(abs(nums1[0]), abs(nums1[m - 1]));
        int b = max(abs(nums2[0]), abs(nums2[n - 1]));
        long long r = 1LL * a * b;
        long long l = -r;
        auto count = [&](long long p) {
            long long cnt = 0;
            for (int x : nums1) {
                if (x > 0) {
                    int l = 0, r = n;
                    while (l < r) {
                        int mid = (l + r) >> 1;
                        if (1LL * x * nums2[mid] > p) {
                            r = mid;
                        } else {
                            l = mid + 1;
                        }
                    }
                    cnt += l;
                } else if (x < 0) {
                    int l = 0, r = n;
                    while (l < r) {
                        int mid = (l + r) >> 1;
                        if (1LL * x * nums2[mid] <= p) {
                            r = mid;
                        } else {
                            l = mid + 1;
                        }
                    }
                    cnt += n - l;
                } else if (p >= 0) {
                    cnt += n;
                }
            }
            return cnt;
        };
        while (l < r) {
            long long mid = (l + r) >> 1;
            if (count(mid) >= k) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
};