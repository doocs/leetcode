impl Solution {
    pub fn count_pairs(nums1: Vec<i32>, nums2: Vec<i32>) -> i64 {
        let mut nums: Vec<i32> = nums1.iter().zip(nums2.iter()).map(|(a, b)| a - b).collect();
        nums.sort();
        let mut l = 0;
        let mut r = nums.len() - 1;
        let mut ans = 0;
        while l < r {
            while l < r && nums[l] + nums[r] <= 0 {
                l += 1;
            }
            ans += (r - l) as i64;
            r -= 1;
        }
        ans
    }
}
