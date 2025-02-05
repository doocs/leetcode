impl Solution {
    pub fn maximum_number(num: String, change: Vec<i32>) -> String {
        let mut s: Vec<char> = num.chars().collect();
        let mut changed = false;
        for i in 0..s.len() {
            let d = (change[s[i] as usize - '0' as usize] + '0' as i32) as u8 as char;
            if changed && d < s[i] {
                break;
            }
            if d > s[i] {
                changed = true;
                s[i] = d;
            }
        }
        s.into_iter().collect()
    }
}
