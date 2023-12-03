impl Solution {
    pub fn tour_of_knight(m: i32, n: i32, r: i32, c: i32) -> Vec<Vec<i32>> {
        let mut g: Vec<Vec<i32>> = vec![vec![-1; n as usize]; m as usize];
        g[r as usize][c as usize] = 0;
        let dirs: [i32; 9] = [-2, -1, 2, 1, -2, 1, 2, -1, -2];
        let mut ok = false;

        fn dfs(
            i: usize,
            j: usize,
            g: &mut Vec<Vec<i32>>,
            m: i32,
            n: i32,
            dirs: &[i32; 9],
            ok: &mut bool
        ) {
            if g[i][j] == m * n - 1 {
                *ok = true;
                return;
            }
            for k in 0..8 {
                let x = ((i as i32) + dirs[k]) as usize;
                let y = ((j as i32) + dirs[k + 1]) as usize;
                if x < (m as usize) && y < (n as usize) && g[x][y] == -1 {
                    g[x][y] = g[i][j] + 1;
                    dfs(x, y, g, m, n, dirs, ok);
                    if *ok {
                        return;
                    }
                    g[x][y] = -1;
                }
            }
        }

        dfs(r as usize, c as usize, &mut g, m, n, &dirs, &mut ok);
        g
    }
}
