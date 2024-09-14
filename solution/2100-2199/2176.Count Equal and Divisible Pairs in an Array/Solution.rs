impl Solution {
    pub fn count_pairs(nums: Vec<i32>, k: i32) -> i32 {
        let mut ans = 0;
        for j in 1..nums.len() {
            for (i, &x) in nums[..j].iter().enumerate() {
                if x == nums[j] && (i * j) as i32 % k == 0 {
                    ans += 1;
                }
            }
        }
        ans
    }
}
