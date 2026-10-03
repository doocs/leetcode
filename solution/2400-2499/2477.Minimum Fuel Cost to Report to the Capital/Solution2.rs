impl Solution {
    pub fn minimum_fuel_cost(roads: Vec<Vec<i32>>, seats: i32) -> i64 {
        let n = roads.len() + 1;
        let mut g: Vec<Vec<usize>> = vec![vec![]; n];
        for road in roads.iter() {
            let a = road[0] as usize;
            let b = road[1] as usize;
            g[a].push(b);
            g[b].push(a);
        }
        let mut ans: i64 = 0;
        let mut sz = vec![1; n];
        let mut stk: Vec<(usize, i32, u8)> = vec![(0, -1, 0)];
        while let Some((a, fa, state)) = stk.pop() {
            if state == 0 {
                stk.push((a, fa, 1));
                for &b in &g[a] {
                    if b as i32 != fa {
                        stk.push((b, a as i32, 0));
                    }
                }
            } else {
                for &b in &g[a] {
                    if b as i32 != fa {
                        let t = sz[b];
                        ans += ((t + seats - 1) / seats) as i64;
                        sz[a] += t;
                    }
                }
            }
        }
        ans
    }
}
