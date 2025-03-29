use std::collections::HashSet;

impl Solution {
    pub fn is_escape_possible(blocked: Vec<Vec<i32>>, source: Vec<i32>, target: Vec<i32>) -> bool {
        const N: i64 = 1_000_000;
        let m = (blocked.len() * blocked.len()) as i64 / 2;

        let f = |i: i64, j: i64| -> i64 { i * N + j };

        let mut s: HashSet<i64> = HashSet::new();
        for b in &blocked {
            s.insert(f(b[0] as i64, b[1] as i64));
        }

        fn dfs(
            sx: i64,
            sy: i64,
            tx: i64,
            ty: i64,
            s: &HashSet<i64>,
            m: i64,
            vis: &mut HashSet<i64>,
        ) -> bool {
            static DIRS: [i64; 5] = [-1, 0, 1, 0, -1];
            let key = sx * 1_000_000 + sy;
            vis.insert(key);
            if vis.len() as i64 > m {
                return true;
            }
            for k in 0..4 {
                let x = sx + DIRS[k];
                let y = sy + DIRS[k + 1];
                let key = x * 1_000_000 + y;
                if x >= 0 && x < 1_000_000 && y >= 0 && y < 1_000_000 {
                    if x == tx && y == ty {
                        return true;
                    }
                    if !s.contains(&key) && vis.insert(key) && dfs(x, y, tx, ty, s, m, vis) {
                        return true;
                    }
                }
            }
            false
        }

        dfs(
            source[0] as i64,
            source[1] as i64,
            target[0] as i64,
            target[1] as i64,
            &s,
            m,
            &mut HashSet::new(),
        ) && dfs(
            target[0] as i64,
            target[1] as i64,
            source[0] as i64,
            source[1] as i64,
            &s,
            m,
            &mut HashSet::new(),
        )
    }
}
