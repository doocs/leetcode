use std::collections::{BTreeSet, VecDeque};

impl Solution {
    pub fn min_reverse_operations(n: i32, p: i32, banned: Vec<i32>, k: i32) -> Vec<i32> {
        let mut ans = vec![-1; n as usize];
        let mut ts = [BTreeSet::new(), BTreeSet::new()];

        for i in 0..n {
            ts[(i % 2) as usize].insert(i);
        }
        ans[p as usize] = 0;
        ts[(p % 2) as usize].remove(&p);

        for &b in &banned {
            ts[(b % 2) as usize].remove(&b);
        }

        ts[0].insert(n);
        ts[1].insert(n);
        let mut q = VecDeque::new();
        q.push_back(p);

        while let Some(i) = q.pop_front() {
            let mi = (i - k + 1).max(k - i - 1);
            let mx = (i + k - 1).min(2 * n - k - i - 1);
            let s = &mut ts[(mi % 2) as usize];

            while let Some(&j) = s.range(mi..=mx).next() {
                q.push_back(j);
                ans[j as usize] = ans[i as usize] + 1;
                s.remove(&j);
            }
        }

        ans
    }
}
