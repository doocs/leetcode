impl Solution {
    pub fn unequal_triplets(mut nums: Vec<i32>) -> i32 {
        nums.sort_unstable();
        let n = nums.len();
        let mut ans = 0;
        for j in 1..n - 1 {
            let i = nums[..j].partition_point(|&x| x < nums[j]) as i32 - 1;
            let k = j + 1 + nums[j + 1..].partition_point(|&x| x <= nums[j]);
            if i >= 0 && k < n {
                ans += (i + 1) * (n - k) as i32;
            }
        }
        ans
    }
}
