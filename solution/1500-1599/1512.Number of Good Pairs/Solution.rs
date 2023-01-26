impl Solution {
    pub fn num_identical_pairs(nums: Vec<i32>) -> i32 {
        let mut cnt = [0; 101];
        let mut ans = 0;
        for &num in nums.iter() {
            ans += cnt[num as usize];
            cnt[num as usize] += 1;
        }
        ans
    }
}
