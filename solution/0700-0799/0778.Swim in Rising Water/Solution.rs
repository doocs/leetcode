const DIR: [(i32, i32); 4] = [
    (-1, 0),
    (1, 0),
    (0, -1),
    (0, 1),
];

impl Solution {
    #[allow(dead_code)]
    pub fn swim_in_water(grid: Vec<Vec<i32>>) -> i32 {
        let n = grid.len();
        let m = grid[0].len();
        let mut ret_time = 0;
        let mut disjoint_set: Vec<usize> = vec![0; n * m];

        // Initialize the disjoint set
        for i in 0..n * m {
            disjoint_set[i] = i;
        }

        loop {
            if Self::check_and_union(&grid, &mut disjoint_set, ret_time) {
                break;
            }
            // Otherwise, keep checking
            ret_time += 1;
        }

        ret_time
    }

    #[allow(dead_code)]
    fn check_and_union(grid: &Vec<Vec<i32>>, d_set: &mut Vec<usize>, cur_time: i32) -> bool {
        let n = grid.len();
        let m = grid[0].len();

        for i in 0..n {
            for j in 0..m {
                if grid[i][j] != cur_time {
                    continue;
                }
                // Otherwise, let's union the square with its neighbors
                for (dx, dy) in DIR {
                    let x = dx + (i as i32);
                    let y = dy + (j as i32);
                    if
                        Self::check_bounds(x, y, n as i32, m as i32) &&
                        grid[x as usize][y as usize] <= cur_time
                    {
                        Self::union(i * m + j, (x as usize) * m + (y as usize), d_set);
                    }
                }
            }
        }

        Self::find(0, d_set) == Self::find(n * m - 1, d_set)
    }

    #[allow(dead_code)]
    fn find(x: usize, d_set: &mut Vec<usize>) -> usize {
        if d_set[x] != x {
            d_set[x] = Self::find(d_set[x], d_set);
        }
        d_set[x]
    }

    #[allow(dead_code)]
    fn union(x: usize, y: usize, d_set: &mut Vec<usize>) {
        let p_x = Self::find(x, d_set);
        let p_y = Self::find(y, d_set);
        d_set[p_x] = p_y;
    }

    #[allow(dead_code)]
    fn check_bounds(i: i32, j: i32, n: i32, m: i32) -> bool {
        i >= 0 && i < n && j >= 0 && j < m
    }
}
