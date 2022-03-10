use std::cmp::Ordering;

impl Solution {
    pub fn good_days_to_rob_bank(security: Vec<i32>, time: i32) -> Vec<i32> {
        let time = time as usize;
        let n = security.len();
        if time * 2 >= n {
            return vec![];
        }
        let mut g = vec![0; n];
        for i in 1..n {
            g[i] = match security[i].cmp(&security[i - 1]) {
                Ordering::Less => -1,
                Ordering::Greater => 1,
                Ordering::Equal => 0,
            }
        }
        let (mut a, mut b) = (vec![0; n + 1], vec![0; n + 1]);
        for i in 1..=n {
            a[i] = a[i - 1] + if g[i - 1] == 1 { 1 } else { 0 };
            b[i] = b[i - 1] + if g[i - 1] == -1 { 1 } else { 0 };
        }
        let mut res = vec![];
        for i in time..n - time {
            if a[i + 1] - a[i + 1 - time] == 0 && b[i + 1 + time] - b[i + 1] == 0 {
                res.push((i) as i32);
            }
        }
        res
    }
}
