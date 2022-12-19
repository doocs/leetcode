impl Solution {
    pub fn num_identical_pairs(nums: Vec<i32>) -> i32 {
        let mut count = [0; 101];
        let mut ans = 0;
        for &num in nums.iter() {
            ans += count[num as usize];
            count[num as usize] += 1;
        }
        ans
    }
}
