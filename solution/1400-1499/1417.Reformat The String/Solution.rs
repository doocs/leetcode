impl Solution {
    pub fn reformat(s: String) -> String {
        let mut a: Vec<char> = Vec::new();
        let mut b: Vec<char> = Vec::new();

        for c in s.chars() {
            if c.is_ascii_lowercase() {
                a.push(c);
            } else if c.is_ascii_digit() {
                b.push(c);
            }
        }

        if (a.len() as i32 - b.len() as i32).abs() > 1 {
            return String::new();
        }

        if a.len() < b.len() {
            std::mem::swap(&mut a, &mut b);
        }

        let mut ans = String::new();

        for i in 0..b.len() {
            ans.push(a[i]);
            ans.push(b[i]);
        }

        if a.len() > b.len() {
            ans.push(a[a.len() - 1]);
        }

        ans
    }
}
