impl Solution {
    pub fn minimum_cost(
        source: String,
        target: String,
        original: Vec<char>,
        changed: Vec<char>,
        cost: Vec<i32>,
    ) -> i64 {
        let inf: i64 = i64::MAX / 4;
        let mut g = vec![vec![inf; 26]; 26];

        for i in 0..26 {
            g[i][i] = 0;
        }

        for i in 0..original.len() {
            let x = (original[i] as u8 - b'a') as usize;
            let y = (changed[i] as u8 - b'a') as usize;
            g[x][y] = g[x][y].min(cost[i] as i64);
        }

        for k in 0..26 {
            for i in 0..26 {
                for j in 0..26 {
                    let v = g[i][k] + g[k][j];
                    if v < g[i][j] {
                        g[i][j] = v;
                    }
                }
            }
        }

        let mut ans: i64 = 0;
        for (a, b) in source.bytes().zip(target.bytes()) {
            if a != b {
                let x = (a - b'a') as usize;
                let y = (b - b'a') as usize;
                if g[x][y] >= inf {
                    return -1;
                }
                ans += g[x][y];
            }
        }

        ans
    }
}
