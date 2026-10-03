use std::collections::HashMap;

impl Solution {
    pub fn maximum_total_damage(mut power: Vec<i32>) -> i64 {
        power.sort();
        let n = power.len();
        let mut cnt = HashMap::new();
        let mut nxt = vec![0; n];

        for i in 0..n {
            *cnt.entry(power[i]).or_insert(0) += 1;
            let j = match power[i + 1..].binary_search_by(|&x| x.cmp(&(power[i] + 2 + 1))) {
                Ok(pos) | Err(pos) => i + 1 + pos,
            };
            nxt[i] = j;
        }

        let mut f = vec![0_i64; n + 1];
        for i in (0..n).rev() {
            let c = *cnt.get(&power[i]).unwrap();
            let j = i + c as usize;
            let a = if j <= n { f[j] } else { 0 };
            let b = power[i] as i64 * c as i64 + f[nxt[i]];
            f[i] = a.max(b);
        }
        f[0]
    }
}
