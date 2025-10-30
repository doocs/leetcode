impl Solution {
    pub fn get_sneaky_numbers(nums: Vec<i32>) -> Vec<i32> {
        let n = nums.len() as i32 - 2;
        let mut xx = nums[n as usize] ^ nums[(n + 1) as usize];
        for i in 0..n {
            xx ^= i ^ nums[i as usize];
        }
        let k = xx.trailing_zeros();
        let mut ans = vec![0, 0];
        for &x in &nums {
            ans[((x >> k) & 1) as usize] ^= x;
        }
        for i in 0..n {
            ans[((i >> k) & 1) as usize] ^= i;
        }
        ans
    }
}
