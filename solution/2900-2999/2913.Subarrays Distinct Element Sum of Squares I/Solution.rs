impl Solution {
    pub fn sum_counts(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        for i in 0..nums.len() {
            let mut s = [false; 101];
            let mut cnt = 0;
            for &x in &nums[i..] {
                if !s[x as usize] {
                    s[x as usize] = true;
                    cnt += 1;
                }
                ans += cnt * cnt;
            }
        }
        ans
    }
}
