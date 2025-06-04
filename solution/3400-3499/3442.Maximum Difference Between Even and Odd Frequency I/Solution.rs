impl Solution {
    pub fn max_difference(s: String) -> i32 {
        let mut cnt = [0; 26];
        for c in s.bytes() {
            cnt[(c - b'a') as usize] += 1;
        }
        let mut a = 0;
        let mut b = 1 << 30;
        for &v in cnt.iter() {
            if v % 2 == 1 {
                a = a.max(v);
            } else if v > 0 {
                b = b.min(v);
            }
        }
        a - b
    }
}
