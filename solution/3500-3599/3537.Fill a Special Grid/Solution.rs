impl Solution {
    pub fn special_grid(n: i32) -> Vec<Vec<i32>> {
        fn dfs(x: usize, y: usize, k: usize, ans: &mut Vec<Vec<i32>>, val: &mut i32) {
            if k == 1 {
                ans[x][y] = *val;
                *val += 1;
                return;
            }

            let h = k / 2;
            dfs(x, y, h, ans, val);
            dfs(x + h, y, h, ans, val);
            dfs(x + h, y - h, h, ans, val);
            dfs(x, y - h, h, ans, val);
        }

        let m = 1usize << n;
        let mut ans = vec![vec![0; m]; m];
        let mut val = 0;
        dfs(0, m - 1, m, &mut ans, &mut val);
        ans
    }
}
