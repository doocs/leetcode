use std::collections::{HashMap, HashSet};

impl Solution {
    pub fn minimum_teachings(n: i32, languages: Vec<Vec<i32>>, friendships: Vec<Vec<i32>>) -> i32 {
        fn check(u: usize, v: usize, languages: &Vec<Vec<i32>>) -> bool {
            for &x in &languages[u - 1] {
                for &y in &languages[v - 1] {
                    if x == y {
                        return true;
                    }
                }
            }
            false
        }

        let mut s: HashSet<usize> = HashSet::new();
        for edge in friendships.iter() {
            let u = edge[0] as usize;
            let v = edge[1] as usize;
            if !check(u, v, &languages) {
                s.insert(u);
                s.insert(v);
            }
        }

        let mut cnt: HashMap<i32, i32> = HashMap::new();
        for &u in s.iter() {
            for &l in &languages[u - 1] {
                *cnt.entry(l).or_insert(0) += 1;
            }
        }

        let mx = cnt.values().cloned().max().unwrap_or(0);
        (s.len() as i32) - mx
    }
}
