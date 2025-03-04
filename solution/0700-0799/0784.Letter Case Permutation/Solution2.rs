impl Solution {
    pub fn letter_case_permutation(s: String) -> Vec<String> {
        let n = s.chars().filter(|&c| c.is_alphabetic()).count();
        let mut ans = Vec::new();
        for i in 0..(1 << n) {
            let mut j = 0;
            let mut t = String::new();
            for c in s.chars() {
                if c.is_alphabetic() {
                    if (i >> j) & 1 == 1 {
                        t.push(c.to_lowercase().next().unwrap());
                    } else {
                        t.push(c.to_uppercase().next().unwrap());
                    }
                    j += 1;
                } else {
                    t.push(c);
                }
            }
            ans.push(t);
        }
        ans
    }
}
