impl Solution {
    pub fn max_sum_of_squares(num: i32, sum: i32) -> String {
        if num * 9 < sum {
            return String::new();
        }
        let k = sum / 9;
        let s = sum % 9;
        let mut ans = "9".repeat(k as usize);
        if s > 0 {
            ans.push((b'0' + s as u8) as char);
        }
        if ans.len() < num as usize {
            ans.push_str(&"0".repeat(num as usize - ans.len()));
        }
        ans
    }
}
