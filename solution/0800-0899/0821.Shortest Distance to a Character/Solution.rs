impl Solution {
    pub fn shortest_to_char(s: String, c: char) -> Vec<i32> {
        let c = c as u8;
        let s = s.as_bytes();
        let n = s.len();
        let mut res = vec![i32::MAX; n];
        let mut pre = i32::MAX;
        for i in 0..n {
            if s[i] == c {
                pre = i as i32;
            }
            res[i] = i32::abs(i as i32 - pre);
        }
        pre = i32::MAX;
        for i in (0..n).rev() {
            if s[i] == c {
                pre = i as i32;
            }
            res[i] = res[i].min(i32::abs(i as i32 - pre));
        }
        res
    }
}
