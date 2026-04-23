use std::collections::{HashMap, BTreeSet};
use std::cmp::min;

struct Hashing {
    p: Vec<i64>,
    h: Vec<i64>,
    mod_val: i64,
}

impl Hashing {
    fn new(word: &str, base: i64, mod_val: i64) -> Self {
        let n = word.len();
        let mut p = vec![0; n + 1];
        let mut h = vec![0; n + 1];
        p[0] = 1;
        let chars: Vec<u8> = word.bytes().collect();
        for i in 1..=n {
            p[i] = p[i - 1] * base % mod_val;
            h[i] = (h[i - 1] * base + chars[i - 1] as i64) % mod_val;
        }
        Hashing { p, h, mod_val }
    }

    fn query(&self, l: usize, r: usize) -> i64 {
        (self.h[r] - self.h[l - 1] * self.p[r - l + 1] % self.mod_val + self.mod_val) % self.mod_val
    }
}

impl Solution {
    pub fn minimum_cost(target: String, words: Vec<String>, costs: Vec<i32>) -> i32 {
        let base = 13331i64;
        let mod_val = 998244353i64;
        let inf = i32::MAX / 2;
        let n = target.len();
        let hashing = Hashing::new(&target, base, mod_val);

        let mut f = vec![inf; n + 1];
        f[0] = 0;

        let mut ss = BTreeSet::new();
        for w in &words {
            ss.insert(w.len());
        }

        let mut d = HashMap::new();
        for i in 0..words.len() {
            let mut x = 0i64;
            for c in words[i].bytes() {
                x = (x * base + c as i64) % mod_val;
            }
            let entry = d.entry(x).or_insert(inf);
            *entry = min(*entry, costs[i]);
        }

        for i in 1..=n {
            for &j in &ss {
                if j > i {
                    break;
                }
                let x = hashing.query(i - j + 1, i);
                if let Some(&cost) = d.get(&x) {
                    f[i] = min(f[i], f[i - j] + cost);
                }
            }
        }

        if f[n] >= inf { -1 } else { f[n] }
    }
}
