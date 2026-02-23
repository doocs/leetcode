use std::collections::{BTreeSet, VecDeque};

impl Solution {
    pub fn min_operations(s: String, k: i32) -> i32 {
        let n: i32 = s.len() as i32;
        let k: i32 = k;

        let mut ts: [BTreeSet<i32>; 2] = [BTreeSet::new(), BTreeSet::new()];
        for i in 0..=n {
            ts[(i % 2) as usize].insert(i);
        }

        let cnt0: i32 = s.bytes().filter(|&c| c == b'0').count() as i32;
        ts[(cnt0 % 2) as usize].remove(&cnt0);

        let mut q: VecDeque<i32> = VecDeque::new();
        q.push_back(cnt0);

        let mut ans: i32 = 0;

        while !q.is_empty() {
            let size = q.len();
            for _ in 0..size {
                let cur = q.pop_front().unwrap();
                if cur == 0 {
                    return ans;
                }

                let l = cur + k - 2 * cur.min(k);
                let r = cur + k - 2 * (k - n + cur).max(0);

                let parity = (l % 2) as usize;

                let vals: Vec<i32> = ts[parity]
                    .range(l..=r)
                    .cloned()
                    .collect();

                for v in vals {
                    q.push_back(v);
                    ts[parity].remove(&v);
                }
            }
            ans += 1;
        }

        -1
    }
}
