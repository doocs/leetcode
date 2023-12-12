use std::collections::HashSet;

impl Solution {
    pub fn dig_artifacts(n: i32, artifacts: Vec<Vec<i32>>, dig: Vec<Vec<i32>>) -> i32 {
        let mut s: HashSet<i32> = HashSet::new();
        for p in dig {
            s.insert(p[0] * n + p[1]);
        }
        let check = |a: &[i32]| -> i32 {
            let x1 = a[0];
            let y1 = a[1];
            let x2 = a[2];
            let y2 = a[3];
            for x in x1..=x2 {
                for y in y1..=y2 {
                    if !s.contains(&(x * n + y)) {
                        return 0;
                    }
                }
            }
            1
        };
        let mut ans = 0;
        for a in artifacts {
            ans += check(&a);
        }
        ans
    }
}
