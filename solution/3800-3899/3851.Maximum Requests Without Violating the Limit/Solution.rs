use std::collections::{HashMap, VecDeque};

impl Solution {
    pub fn max_requests(requests: Vec<Vec<i32>>, k: i32, window: i32) -> i32 {
        let mut g: HashMap<i32, Vec<i32>> = HashMap::new();
        for r in &requests {
            let u: i32 = r[0];
            let t: i32 = r[1];
            g.entry(u).or_insert_with(Vec::new).push(t);
        }

        let mut ans: i32 = requests.len() as i32;
        let mut kept: VecDeque<i32> = VecDeque::new();

        for ts in g.values_mut() {
            ts.sort();
            kept.clear();

            for &t in ts.iter() {
                while let Some(&front) = kept.front() {
                    if t - front > window {
                        kept.pop_front();
                    } else {
                        break;
                    }
                }

                if kept.len() < k as usize {
                    kept.push_back(t);
                } else {
                    ans -= 1;
                }
            }
        }

        ans
    }
}
