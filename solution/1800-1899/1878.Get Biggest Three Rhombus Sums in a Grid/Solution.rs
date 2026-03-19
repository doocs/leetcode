use std::collections::BTreeSet;

impl Solution {
    pub fn get_biggest_three(grid: Vec<Vec<i32>>) -> Vec<i32> {
        let m = grid.len();
        let n = grid[0].len();

        let mut s1 = vec![vec![0; n + 2]; m + 1];
        let mut s2 = vec![vec![0; n + 2]; m + 1];

        for i in 1..=m {
            for j in 1..=n {
                s1[i][j] = s1[i - 1][j - 1] + grid[i - 1][j - 1];
                s2[i][j] = s2[i - 1][j + 1] + grid[i - 1][j - 1];
            }
        }

        let mut ss = BTreeSet::new();

        for i in 1..=m {
            for j in 1..=n {
                let l = (i - 1)
                    .min(m - i)
                    .min(j - 1)
                    .min(n - j);

                ss.insert(grid[i - 1][j - 1]);

                for k in 1..=l {
                    let a = s1[i + k][j] - s1[i][j - k];
                    let b = s1[i][j + k] - s1[i - k][j];
                    let c = s2[i][j - k] - s2[i - k][j];
                    let d = s2[i + k][j] - s2[i][j + k];

                    let v = a + b + c + d
                        - grid[i + k - 1][j - 1]
                        + grid[i - k - 1][j - 1];

                    ss.insert(v);
                }

                while ss.len() > 3 {
                    let first = *ss.iter().next().unwrap();
                    ss.remove(&first);
                }
            }
        }

        let mut ans = Vec::with_capacity(ss.len());
        while let Some(&v) = ss.iter().next_back() {
            ans.push(v);
            ss.remove(&v);
        }

        ans
    }
}
