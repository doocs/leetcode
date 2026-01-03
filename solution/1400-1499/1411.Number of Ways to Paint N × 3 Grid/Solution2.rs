use std::collections::{HashSet, HashMap};

impl Solution {
    pub fn num_of_ways(n: i32) -> i32 {
        const MOD: i32 = 1_000_000_007;
        let m = 27;
        let mut valid = HashSet::new();
        let mut f = vec![0; m];

        for i in 0..m {
            if Self::f1(i as i32) {
                valid.insert(i as i32);
                f[i] = 1;
            }
        }

        let mut d: HashMap<i32, Vec<i32>> = HashMap::new();
        for &i in &valid {
            for &j in &valid {
                if Self::f2(i, j) {
                    d.entry(i).or_insert_with(Vec::new).push(j);
                }
            }
        }

        for _ in 1..n {
            let mut g = vec![0; m];
            for &i in &valid {
                if let Some(neighbors) = d.get(&i) {
                    for &j in neighbors {
                        g[j as usize] = (g[j as usize] + f[i as usize]) % MOD;
                    }
                }
            }
            f = g;
        }

        let mut ans = 0;
        for x in f {
            ans = (ans + x) % MOD;
        }
        ans
    }

    fn f1(mut x: i32) -> bool {
        let mut last = -1;
        for _ in 0..3 {
            if x % 3 == last {
                return false;
            }
            last = x % 3;
            x /= 3;
        }
        true
    }

    fn f2(mut x: i32, mut y: i32) -> bool {
        for _ in 0..3 {
            if x % 3 == y % 3 {
                return false;
            }
            x /= 3;
            y /= 3;
        }
        true
    }
}
