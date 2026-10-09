impl Solution {
    pub fn num_submat(mat: Vec<Vec<i32>>) -> i32 {
        let m = mat.len();
        let n = mat[0].len();
        let mut g = vec![vec![0; n]; m];
        for i in 0..m {
            for j in 0..n {
                if mat[i][j] == 1 {
                    g[i][j] = if j == 0 { 1 } else { 1 + g[i][j - 1] };
                }
            }
        }
        let mut ans = 0;
        for j in 0..n {
            let mut stk: Vec<(i32, i32, i32)> = Vec::new();
            for i in 0..m {
                let cur = g[i][j];
                while !stk.is_empty() && stk.last().unwrap().0 >= cur {
                    stk.pop();
                }
                let cnt = if stk.is_empty() {
                    cur * (i as i32 + 1)
                } else {
                    let t = stk.last().unwrap();
                    t.2 + cur * (i as i32 - t.1)
                };
                ans += cnt;
                stk.push((cur, i as i32, cnt));
            }
        }
        ans
    }
}
