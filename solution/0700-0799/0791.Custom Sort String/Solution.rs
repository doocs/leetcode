impl Solution {
    pub fn custom_sort_string(order: String, s: String) -> String {
        let n = order.len();
        let mut d = [n; 26];
        for (i, c) in order.as_bytes().iter().enumerate() {
            d[(c - b'a') as usize] = i;
        }
        let mut ans = s.chars().collect::<Vec<_>>();
        ans.sort_by(|&a, &b|
            d[((a as u8) - ('a' as u8)) as usize].cmp(&d[((b as u8) - ('a' as u8)) as usize])
        );
        ans.into_iter().collect()
    }
}
