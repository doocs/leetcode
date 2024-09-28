impl Solution {
    pub fn num_identical_pairs(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        let mut cnt = [0; 101];
        for &x in nums.iter() {
            ans += cnt[x as usize];
            cnt[x as usize] += 1;
        }
        ans
    }
}
