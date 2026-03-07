impl Solution {
    pub fn min_flips(s: String) -> i32 {
        let n: usize = s.len();
        let bytes = s.as_bytes();
        let target = b"01";
        let mut cnt: i32 = 0;

        for i in 0..n {
            if bytes[i] != target[i & 1] {
                cnt += 1;
            }
        }

        let mut ans = cnt.min(n as i32 - cnt);

        for i in 0..n {
            if bytes[i] != target[i & 1] {
                cnt -= 1;
            }
            if bytes[i] != target[(i + n) & 1] {
                cnt += 1;
            }
            ans = ans.min(cnt).min(n as i32 - cnt);
        }

        ans
    }
}
