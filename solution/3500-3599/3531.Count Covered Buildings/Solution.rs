use std::collections::HashMap;

impl Solution {
    pub fn count_covered_buildings(_n: i32, buildings: Vec<Vec<i32>>) -> i32 {
        let mut g1: HashMap<i32, Vec<i32>> = HashMap::new();
        let mut g2: HashMap<i32, Vec<i32>> = HashMap::new();

        for b in &buildings {
            let x = b[0];
            let y = b[1];
            g1.entry(x).or_insert(Vec::new()).push(y);
            g2.entry(y).or_insert(Vec::new()).push(x);
        }

        for v in g1.values_mut() {
            v.sort();
        }
        for v in g2.values_mut() {
            v.sort();
        }

        let mut ans: i32 = 0;

        for b in &buildings {
            let x = b[0];
            let y = b[1];

            let l1 = g1.get(&x).unwrap();
            let l2 = g2.get(&y).unwrap();

            if l2[0] < x && x < l2[l2.len() - 1] && l1[0] < y && y < l1[l1.len() - 1] {
                ans += 1;
            }
        }

        ans
    }
}
