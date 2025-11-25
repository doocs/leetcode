use std::collections::BinaryHeap;

impl Solution {
    pub fn is_possible(target: Vec<i32>) -> bool {
        let mut pq = BinaryHeap::from(target.clone());
        let mut s: i64 = target.iter().map(|&x| x as i64).sum();

        while let Some(&mx) = pq.peek() {
            if mx == 1 {
                break;
            }
            let mx = pq.pop().unwrap() as i64;
            let t = s - mx;
            if t < 1 || mx - t < 1 {
                return false;
            }
            let x = if mx % t == 0 { t } else { mx % t };
            pq.push(x as i32);
            s = s - mx + x;
        }
        true
    }
}
