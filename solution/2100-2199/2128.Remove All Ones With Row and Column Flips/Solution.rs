use std::collections::HashSet;

impl Solution {
    pub fn remove_ones(grid: Vec<Vec<i32>>) -> bool {
        let n = grid[0].len();
        let mut set = HashSet::new();

        for row in grid.iter() {
            let mut pattern = String::with_capacity(n);
            for &x in row.iter() {
                pattern.push(((row[0] ^ x) as u8 + b'0') as char);
            }
            set.insert(pattern);
        }

        set.len() == 1
    }
}
