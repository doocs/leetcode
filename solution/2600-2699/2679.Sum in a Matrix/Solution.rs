impl Solution {
    pub fn matrix_sum(mut nums: Vec<Vec<i32>>) -> i32 {
        for row in &mut nums {
            row.sort();
        }
        (0..nums[0].len())
            .map(|col|
                nums
                    .iter()
                    .map(|row| row[col])
                    .max()
                    .unwrap()
            )
            .sum()
    }
}
