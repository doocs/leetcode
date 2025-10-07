use std::collections::{BTreeSet, HashMap};

impl Solution {
    pub fn avoid_flood(rains: Vec<i32>) -> Vec<i32> {
        let n = rains.len();
        let mut ans = vec![-1; n];
        let mut sunny = BTreeSet::new();
        let mut rainy = HashMap::new();

        for (i, &v) in rains.iter().enumerate() {
            if v > 0 {
                if let Some(&last) = rainy.get(&v) {
                    if let Some(&t) = sunny.range((last + 1) as usize..).next() {
                        ans[t] = v;
                        sunny.remove(&t);
                    } else {
                        return vec![];
                    }
                }
                rainy.insert(v, i as i32);
            } else {
                sunny.insert(i);
                ans[i] = 1;
            }
        }
        ans
    }
}
