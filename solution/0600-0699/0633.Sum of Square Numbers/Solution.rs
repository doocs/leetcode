use std::cmp::Ordering;

impl Solution {
    pub fn judge_square_sum(c: i32) -> bool {
        let mut a: i64 = 0;
        let mut b: i64 = (c as f64).sqrt() as i64;
        while a <= b {
            let s = a * a + b * b;
            match s.cmp(&(c as i64)) {
                Ordering::Equal => {
                    return true;
                }
                Ordering::Less => {
                    a += 1;
                }
                Ordering::Greater => {
                    b -= 1;
                }
            }
        }
        false
    }
}
