impl Solution {
    pub fn sum_of_squares(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        nums.iter()
            .enumerate()
            .filter(|&(i, _)| n % (i + 1) == 0)
            .map(|(_, &x)| x * x)
            .sum()
    }
}
