impl Solution {
    pub fn count_negatives(grid: Vec<Vec<i32>>) -> i32 {
        let n = grid[0].len();
        grid.into_iter()
            .map(|nums| {
                let mut left = 0;
                let mut right = n;
                while left < right {
                    let mid = left + (right - left) / 2;
                    if nums[mid] >= 0 {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                (n - left) as i32
            })
            .sum()
    }
}
