impl Solution {
    pub fn possible_bipartition(n: i32, dislikes: Vec<Vec<i32>>) -> bool {
        let n = n as usize;
        let mut g = vec![Vec::new(); n];
        for d in dislikes.iter() {
            let a = d[0] as usize - 1;
            let b = d[1] as usize - 1;
            g[a].push(b);
            g[b].push(a);
        }
        let mut color = vec![0; n];
        for start in 0..n {
            if color[start] != 0 {
                continue;
            }
            color[start] = 1;
            let mut stk = vec![start];
            while let Some(i) = stk.pop() {
                for &j in &g[i] {
                    if color[j] == color[i] {
                        return false;
                    }
                    if color[j] == 0 {
                        color[j] = 3 - color[i];
                        stk.push(j);
                    }
                }
            }
        }
        true
    }
}
