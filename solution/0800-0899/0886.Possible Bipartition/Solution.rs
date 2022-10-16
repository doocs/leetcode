impl Solution {
    fn dfs(i: usize, v: usize, color: &mut Vec<usize>, g: &Vec<Vec<usize>>) -> bool {
        color[i] = v;
        for &j in (*g[i]).iter() {
            if color[j] == color[i] || color[j] == 0 && Self::dfs(j, v ^ 3, color, g) {
                return true;
            }
        }
        false
    }

    pub fn possible_bipartition(n: i32, dislikes: Vec<Vec<i32>>) -> bool {
        let n = n as usize;
        let mut color = vec![0; n + 1];
        let mut g = vec![Vec::new(); n + 1];
        for d in dislikes.iter() {
            let (i, j) = (d[0] as usize, d[1] as usize);
            g[i].push(j);
            g[j].push(i);
        }
        for i in 1..=n {
            if color[i] == 0 && Self::dfs(i, 1, &mut color, &g) {
                return false;
            }
        }
        true
    }
}
