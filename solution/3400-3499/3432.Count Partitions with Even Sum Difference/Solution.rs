impl Solution {
    pub fn count_partitions(nums: Vec<i32>) -> i32 {
        let mut l: i64 = 0;
        let mut r: i64 = nums.iter().map(|&x| x as i64).sum();
        let mut ans: i32 = 0;

        for &x in nums[..nums.len() - 1].iter() {
            l += x as i64;
            r -= x as i64;
            if (l - r) % 2 == 0 {
                ans += 1;
            }
        }

        ans
    }
}
