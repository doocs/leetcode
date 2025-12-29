impl Solution {
    pub fn num_magic_squares_inside(grid: Vec<Vec<i32>>) -> i32 {
        let m = grid.len();
        let n = grid[0].len();
        let mut ans: i32 = 0;

        let check = |i: usize, j: usize, grid: &Vec<Vec<i32>>| -> i32 {
            if i + 3 > m || j + 3 > n {
                return 0;
            }

            let mut cnt = vec![0; 16];
            let mut row = vec![0; 3];
            let mut col = vec![0; 3];
            let mut a = 0;
            let mut b = 0;

            for x in i..i + 3 {
                for y in j..j + 3 {
                    let v = grid[x][y] as usize;
                    if v < 1 || v > 9 {
                        return 0;
                    }
                    cnt[v] += 1;
                    if cnt[v] > 1 {
                        return 0;
                    }

                    let vv = grid[x][y];
                    row[x - i] += vv;
                    col[y - j] += vv;

                    if x - i == y - j {
                        a += vv;
                    }
                    if x - i + y - j == 2 {
                        b += vv;
                    }
                }
            }

            if a != b {
                return 0;
            }

            for k in 0..3 {
                if row[k] != a || col[k] != a {
                    return 0;
                }
            }

            1
        };

        for i in 0..m {
            for j in 0..n {
                ans += check(i, j, &grid);
            }
        }

        ans
    }
}
