impl Solution {
    pub fn min_operations(s: String) -> i32 {
        let mut cnt: i32 = 0;
        let n: i32 = s.len() as i32;
        let bytes = s.as_bytes();

        for i in 0..n as usize {
            if bytes[i] != b"01"[i & 1] {
                cnt += 1;
            }
        }

        cnt.min(n - cnt)
    }
}
