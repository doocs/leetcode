impl Solution {
    pub fn min_moves2(mut nums: Vec<i32>) -> i32 {
        nums.sort();
        let k = nums[nums.len() / 2];
        let mut ans = 0;
        for num in nums.iter() {
            ans += (num - k).abs();
        }
        ans
    }
}
