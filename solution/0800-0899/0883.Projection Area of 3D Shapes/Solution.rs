impl Solution {
    pub fn projection_area(grid: Vec<Vec<i32>>) -> i32 {
        let xy: i32 = grid
            .iter()
            .map(
                |row|
                    row
                        .iter()
                        .filter(|&&v| v > 0)
                        .count() as i32
            )
            .sum();
        let yz: i32 = grid
            .iter()
            .map(|row| *row.iter().max().unwrap_or(&0))
            .sum();
        let zx: i32 = (0..grid[0].len())
            .map(|i|
                grid
                    .iter()
                    .map(|row| row[i])
                    .max()
                    .unwrap_or(0)
            )
            .sum();
        xy + yz + zx
    }
}
