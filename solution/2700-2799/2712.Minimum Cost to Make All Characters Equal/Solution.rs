impl Solution {
    pub fn minimum_cost(s: String) -> i64 {
        let mut ans = 0;
        let n = s.len();
        let s = s.as_bytes();
        for i in 1..n {
            if s[i] != s[i - 1] {
                ans += i.min(n - i);
            }
        }
        ans as i64
    }
}
