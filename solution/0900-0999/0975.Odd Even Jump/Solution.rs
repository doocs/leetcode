use std::collections::BTreeMap;

impl Solution {
    pub fn odd_even_jumps(arr: Vec<i32>) -> i32 {
        let n: usize = arr.len();
        let mut f: Vec<Vec<Option<i32>>> = vec![vec![None; 2]; n];
        let mut g: Vec<Vec<i32>> = vec![vec![-1; 2]; n];
        let mut tm: BTreeMap<i32, usize> = BTreeMap::new();

        for i in (0..n).rev() {
            if let Some((_, &v)) = tm.range(arr[i]..).next() {
                g[i][1] = v as i32;
            }
            if let Some((_, &v)) = tm.range(..=arr[i]).next_back() {
                g[i][0] = v as i32;
            }
            tm.insert(arr[i], i);
        }

        fn dfs(
            i: usize,
            k: usize,
            n: usize,
            f: &mut Vec<Vec<Option<i32>>>,
            g: &Vec<Vec<i32>>,
        ) -> i32 {
            if i == n - 1 {
                return 1;
            }
            if g[i][k] == -1 {
                return 0;
            }
            if let Some(v) = f[i][k] {
                return v;
            }
            let res = dfs(g[i][k] as usize, k ^ 1, n, f, g);
            f[i][k] = Some(res);
            res
        }

        let mut ans: i32 = 0;
        for i in 0..n {
            ans += dfs(i, 1, n, &mut f, &g);
        }
        ans
    }
}
