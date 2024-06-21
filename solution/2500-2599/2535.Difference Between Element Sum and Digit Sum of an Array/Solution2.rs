impl Solution {
    pub fn difference_of_sum(nums: Vec<i32>) -> i32 {
        let a: i32 = nums.iter().sum();
        let b: i32 = nums
            .iter()
            .map(|&n| {
                n.to_string()
                    .chars()
                    .map(|c| c.to_digit(10).unwrap() as i32)
                    .sum::<i32>()
            })
            .sum();
        (a - b).abs()
    }
}
