impl Solution {
    pub fn count_unguarded(m: i32, n: i32, guards: Vec<Vec<i32>>, walls: Vec<Vec<i32>>) -> i32 {
        let m = m as usize;
        let n = n as usize;
        let mut g = vec![vec![0; n]; m];
        for e in &guards {
            g[e[0] as usize][e[1] as usize] = 2;
        }
        for e in &walls {
            g[e[0] as usize][e[1] as usize] = 2;
        }
        let dirs = [-1, 0, 1, 0, -1];
        for e in &guards {
            let (x0, y0) = (e[0] as i32, e[1] as i32);
            for k in 0..4 {
                let (mut x, mut y) = (x0, y0);
                let (a, b) = (dirs[k], dirs[k + 1]);
                while x + a >= 0
                    && x + a < m as i32
                    && y + b >= 0
                    && y + b < n as i32
                    && g[(x + a) as usize][(y + b) as usize] < 2
                {
                    x += a;
                    y += b;
                    g[x as usize][y as usize] = 1;
                }
            }
        }
        let mut ans = 0;
        for row in g {
            for v in row {
                if v == 0 {
                    ans += 1;
                }
            }
        }
        ans
    }
}
