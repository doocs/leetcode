use std::collections::HashMap;

impl Solution {
    pub fn maximum_total_damage(mut power: Vec<i32>) -> i64 {
        power.sort();
        let n = power.len();
        let mut cnt = HashMap::new();
        let mut nxt = vec![0; n];
        let mut f = vec![-1_i64; n];

        for i in 0..n {
            *cnt.entry(power[i]).or_insert(0) += 1;
            let j = match power[i + 1..].binary_search_by(|&x| x.cmp(&(power[i] + 2 + 1))) {
                Ok(pos) | Err(pos) => i + 1 + pos,
            };
            nxt[i] = j;
        }

        fn dfs(
            i: usize,
            n: usize,
            power: &Vec<i32>,
            nxt: &Vec<usize>,
            f: &mut Vec<i64>,
            cnt: &HashMap<i32, i32>,
        ) -> i64 {
            if i >= n {
                return 0;
            }
            if f[i] != -1 {
                return f[i];
            }
            let c = *cnt.get(&power[i]).unwrap();
            let a = dfs(i + c as usize, n, power, nxt, f, cnt);
            let b = power[i] as i64 * c as i64 + dfs(nxt[i], n, power, nxt, f, cnt);
            f[i] = a.max(b);
            f[i]
        }

        dfs(0, n, &power, &nxt, &mut f, &cnt)
    }
}
