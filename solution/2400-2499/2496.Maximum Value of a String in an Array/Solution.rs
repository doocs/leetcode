impl Solution {
    pub fn maximum_value(strs: Vec<String>) -> i32 {
        let mut ans = 0;
        for s in strs.iter() {
            let num = s.parse().unwrap_or(s.len());
            ans = ans.max(num);
        }
        ans as i32
    }
}
