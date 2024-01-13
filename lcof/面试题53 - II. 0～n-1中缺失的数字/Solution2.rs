impl Solution {
    pub fn missing_number(nums: Vec<i32>) -> i32 {
        let n = nums.len() as i32;
        let mut sum = ((1 + n) * n) / 2;
        for num in nums.iter() {
            sum -= num;
        }
        sum
    }
}
