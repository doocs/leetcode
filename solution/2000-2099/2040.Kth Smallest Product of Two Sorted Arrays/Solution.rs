impl Solution {
    pub fn kth_smallest_product(nums1: Vec<i32>, nums2: Vec<i32>, k: i64) -> i64 {
        let m = nums1.len();
        let n = nums2.len();
        let a = nums1[0].abs().max(nums1[m - 1].abs()) as i64;
        let b = nums2[0].abs().max(nums2[n - 1].abs()) as i64;
        let mut l = -a * b;
        let mut r = a * b;

        let count = |p: i64| -> i64 {
            let mut cnt = 0i64;
            for &x in &nums1 {
                if x > 0 {
                    let mut left = 0;
                    let mut right = n;
                    while left < right {
                        let mid = (left + right) / 2;
                        if (x as i64) * (nums2[mid] as i64) > p {
                            right = mid;
                        } else {
                            left = mid + 1;
                        }
                    }
                    cnt += left as i64;
                } else if x < 0 {
                    let mut left = 0;
                    let mut right = n;
                    while left < right {
                        let mid = (left + right) / 2;
                        if (x as i64) * (nums2[mid] as i64) <= p {
                            right = mid;
                        } else {
                            left = mid + 1;
                        }
                    }
                    cnt += (n - left) as i64;
                } else if p >= 0 {
                    cnt += n as i64;
                }
            }
            cnt
        };

        while l < r {
            let mid = l + (r - l) / 2;
            if count(mid) >= k {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        l
    }
}
