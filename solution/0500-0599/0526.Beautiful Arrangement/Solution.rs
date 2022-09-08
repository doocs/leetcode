impl Solution {
    fn dfs(i: usize, n: usize, mat: &Vec<Vec<usize>>, vis: &mut Vec<bool>, res: &mut i32) {
        if i == n + 1 {
            *res += 1;
            return;
        }
        for &j in mat[i].iter() {
            if !vis[j] {
                vis[j] = true;
                Self::dfs(i + 1, n, mat, vis, res);
                vis[j] = false;
            }
        }
    }

    pub fn count_arrangement(n: i32) -> i32 {
        let n = n as usize;
        let mut vis = vec![false; n + 1];
        let mut mat = vec![Vec::new(); n + 1];
        for i in 1..=n {
            for j in 1..=n {
                if i % j == 0 || j % i == 0 {
                    mat[i].push(j);
                }
            }
        }

        let mut res = 0;
        Self::dfs(1, n, &mat, &mut vis, &mut res);
        res
    }
}
