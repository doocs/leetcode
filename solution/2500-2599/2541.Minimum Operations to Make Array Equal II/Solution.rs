impl Solution {
    pub fn min_operations(nums1: Vec<i32>, nums2: Vec<i32>, k: i32) -> i64 {
        let k = k as i64;
        let n = nums1.len();
        if k == 0 {
            return if nums1.iter().enumerate().all(|(i, &v)| v == nums2[i]) {
                0
            } else {
                -1
            };
        }
        let mut sum1 = 0;
        let mut sum2 = 0;
        for i in 0..n {
            let diff = (nums1[i] - nums2[i]) as i64;
            sum1 += diff;
            if diff % k != 0 {
                return -1;
            }
            sum2 += diff.abs();
        }
        if sum1 != 0 {
            return -1;
        }
        sum2 / (k * 2)
    }
}
