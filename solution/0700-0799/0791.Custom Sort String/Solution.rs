impl Solution {
    pub fn custom_sort_string(order: String, s: String) -> String {
        let mut count = [0; 26];
        for c in s.as_bytes() {
            count[(c - b'a') as usize] += 1;
        }
        let mut ans = String::new();
        for c in order.as_bytes() {
            for _ in 0..count[(c - b'a') as usize] {
                ans.push(char::from(*c));
            }
            count[(c - b'a') as usize] = 0;
        }
        for i in 0..count.len() {
            for _ in 0..count[i] {
                ans.push(char::from(b'a' + i as u8));
            }
        }
        ans
    }
}
