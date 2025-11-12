impl Solution {
    pub fn climb_stairs(n: i32, costs: Vec<i32>) -> i32 {
        let n = n as usize;
        let inf = i32::MAX / 2;
        let mut f = vec![inf; n + 1];
        f[0] = 0;
        for i in 1..=n {
            let x = costs[i - 1];
            for j in (i.saturating_sub(3))..i {
                f[i] = f[i].min(f[j] + x + ((i - j) * (i - j)) as i32);
            }
        }
        f[n]
    }
}
