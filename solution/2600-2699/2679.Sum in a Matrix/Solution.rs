impl Solution {
    pub fn matrix_sum(nums: Vec<Vec<i32>>) -> i32 {
        let mut nums = nums;
        for row in nums.iter_mut() {
            row.sort();
        }
        let transposed: Vec<Vec<i32>> = (0..nums[0].len())
            .map(|i| {
                nums.iter()
                    .map(|row| row[i])
                    .collect()
            })
            .collect();

        transposed
            .iter()
            .map(|row| row.iter().max().unwrap())
            .sum()
    }
}
