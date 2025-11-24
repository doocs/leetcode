impl Solution {
    pub fn query_string(s: String, n: i32) -> bool {
        if n > 1000 {
            return false;
        }
        for i in (n / 2 + 1..=n).rev() {
            if !s.contains(&format!("{:b}", i)) {
                return false;
            }
        }
        true
    }
}
