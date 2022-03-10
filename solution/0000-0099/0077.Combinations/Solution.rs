impl Solution {
    fn dfs(i: i32, n: i32, k: i32, t: &mut Vec<i32>, res: &mut Vec<Vec<i32>>) {
        if k == 0 {
            res.push(t.clone());
            return;
        }
        // 剪枝
        if n - i + 1 < k {
            return;
        }
        for j in i..=n {
            t.push(j);
            Self::dfs(j + 1, n, k - 1, t, res);
            t.pop();
        }
    }

    pub fn combine(n: i32, k: i32) -> Vec<Vec<i32>> {
        let mut res = vec![];
        Self::dfs(1, n, k, &mut vec![], &mut res);
        res
    }
}
