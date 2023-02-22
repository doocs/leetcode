impl Solution {
    pub fn single_number(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        for i in 0..32 {
            let count = nums.iter().map(|v| v >> i & 1).sum::<i32>();
            ans |= count % 3 << i;
        }
        ans
    }
}
