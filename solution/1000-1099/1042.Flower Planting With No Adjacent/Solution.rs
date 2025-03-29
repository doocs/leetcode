impl Solution {
    pub fn garden_no_adj(n: i32, paths: Vec<Vec<i32>>) -> Vec<i32> {
        let n = n as usize;
        let mut g = vec![vec![]; n];

        for path in paths {
            let (x, y) = (path[0] as usize - 1, path[1] as usize - 1);
            g[x].push(y);
            g[y].push(x);
        }

        let mut ans = vec![0; n];
        for x in 0..n {
            let mut used = [false; 5];
            for &y in &g[x] {
                used[ans[y] as usize] = true;
            }
            for c in 1..5 {
                if !used[c] {
                    ans[x] = c as i32;
                    break;
                }
            }
        }
        ans
    }
}
