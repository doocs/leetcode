impl Solution {
    pub fn smallest_palindrome(s: String) -> String {
        let mut cnt = vec![0; 26];
        for c in s.bytes() {
            cnt[(c - b'a') as usize] += 1;
        }

        let mut t = String::new();
        let mut ch = String::new();

        for i in 0..26 {
            let v = cnt[i] / 2;
            if v > 0 {
                t.extend(std::iter::repeat((b'a' + i as u8) as char).take(v as usize));
            }
            cnt[i] -= v * 2;
            if cnt[i] == 1 {
                ch.push((b'a' + i as u8) as char);
            }
        }

        let mut ans = t.clone();
        ans.push_str(&ch);

        let rev: String = t.chars().rev().collect();
        ans.push_str(&rev);

        ans
    }
}
