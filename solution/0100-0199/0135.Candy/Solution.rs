impl Solution {
    pub fn candy(ratings: Vec<i32>) -> i32 {
        let n = ratings.len();
        let mut left = vec![1; n];
        let mut right = vec![1; n];

        for i in 1..n {
            if ratings[i] > ratings[i - 1] {
                left[i] = left[i - 1] + 1;
            }
        }

        for i in (0..n - 1).rev() {
            if ratings[i] > ratings[i + 1] {
                right[i] = right[i + 1] + 1;
            }
        }

        ratings
            .iter()
            .enumerate()
            .map(|(i, _)| left[i].max(right[i]) as i32)
            .sum()
    }
}
