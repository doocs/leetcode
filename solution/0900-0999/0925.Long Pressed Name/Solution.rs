impl Solution {
    pub fn is_long_pressed_name(name: String, typed: String) -> bool {
        let (m, n) = (name.len(), typed.len());
        let (mut i, mut j) = (0, 0);
        let s: Vec<char> = name.chars().collect();
        let t: Vec<char> = typed.chars().collect();

        while i < m && j < n {
            if s[i] != t[j] {
                return false;
            }
            let mut x = i + 1;
            while x < m && s[x] == s[i] {
                x += 1;
            }
            let mut y = j + 1;
            while y < n && t[y] == t[j] {
                y += 1;
            }
            if x - i > y - j {
                return false;
            }
            i = x;
            j = y;
        }

        i == m && j == n
    }
}
