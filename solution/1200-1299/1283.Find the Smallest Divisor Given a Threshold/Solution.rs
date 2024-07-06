impl Solution {
    pub fn smallest_divisor(nums: Vec<i32>, threshold: i32) -> i32 {
        let mut l = 1;
        let mut r = *nums.iter().max().unwrap();
        while l < r {
            let mid = (l + r) / 2;
            let s: i32 = nums.iter().map(|&x| (x + mid - 1) / mid).sum();
            if s <= threshold {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        l
    }
}
