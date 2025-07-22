impl Solution {
    pub fn max_compatibility_sum(students: Vec<Vec<i32>>, mentors: Vec<Vec<i32>>) -> i32 {
        let mut ans = 0;
        let m = students.len();
        let mut vis = vec![false; m];
        let mut g = vec![vec![0; m]; m];

        for i in 0..m {
            for j in 0..m {
                for k in 0..students[i].len() {
                    if students[i][k] == mentors[j][k] {
                        g[i][j] += 1;
                    }
                }
            }
        }

        fn dfs(i: usize, s: i32, m: usize, g: &Vec<Vec<i32>>, vis: &mut Vec<bool>, ans: &mut i32) {
            if i >= m {
                *ans = (*ans).max(s);
                return;
            }
            for j in 0..m {
                if !vis[j] {
                    vis[j] = true;
                    dfs(i + 1, s + g[i][j], m, g, vis, ans);
                    vis[j] = false;
                }
            }
        }

        dfs(0, 0, m, &g, &mut vis, &mut ans);
        ans
    }
}
