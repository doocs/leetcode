impl Solution {
    pub fn num_identical_pairs(nums: Vec<i32>) -> i32 {
        let mut cnt = [0; 101];
        for &num in nums.iter() {
            cnt[num as usize] += 1;
        }
        let mut ans = 0;
        for &v in cnt.iter() {
            ans += (v * (v - 1)) / 2;
        }
        ans
    }
}
