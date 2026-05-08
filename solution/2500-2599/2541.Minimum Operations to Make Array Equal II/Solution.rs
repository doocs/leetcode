impl Solution {
    pub fn min_operations(nums1: Vec<i32>, nums2: Vec<i32>, k: i32) -> i64 {
        let mut a: i64 = 0;
        let mut b: i64 = 0;
        for (&x, &y) in nums1.iter().zip(nums2.iter()) {
            if x == y {
                continue;
            }
            if k == 0 || (x - y) % k != 0 {
                return -1;
            }
            let t = (x - y) / k;
            if t < 0 {
                a += (-t) as i64;
            } else {
                b += t as i64;
            }
        }
        if a == b {
            a
        } else {
            -1
        }
    }
}
