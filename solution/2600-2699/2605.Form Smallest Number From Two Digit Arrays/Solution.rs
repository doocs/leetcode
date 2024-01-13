impl Solution {
    pub fn min_number(nums1: Vec<i32>, nums2: Vec<i32>) -> i32 {
        let mut ans = 100;

        for &a in &nums1 {
            for &b in &nums2 {
                if a == b {
                    ans = std::cmp::min(ans, a);
                } else {
                    ans = std::cmp::min(ans, std::cmp::min(a * 10 + b, b * 10 + a));
                }
            }
        }

        ans
    }
}
