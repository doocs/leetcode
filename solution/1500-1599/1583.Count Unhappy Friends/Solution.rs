impl Solution {
    pub fn unhappy_friends(n: i32, preferences: Vec<Vec<i32>>, pairs: Vec<Vec<i32>>) -> i32 {
        let n = n as usize;
        let mut d = vec![vec![0usize; n]; n];
        for i in 0..n {
            for j in 0..(n - 1) {
                let friend = preferences[i][j] as usize;
                d[i][friend] = j;
            }
        }

        let mut p = vec![0usize; n];
        for e in pairs {
            let x = e[0] as usize;
            let y = e[1] as usize;
            p[x] = y;
            p[y] = x;
        }

        let mut ans = 0;

        for x in 0..n {
            let y = p[x];
            for i in 0..d[x][y] {
                let u = preferences[x][i] as usize;
                let v = p[u];
                if d[u][x] < d[u][v] {
                    ans += 1;
                    break;
                }
            }
        }

        ans
    }
}
