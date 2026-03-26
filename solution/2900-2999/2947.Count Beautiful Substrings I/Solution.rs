impl Solution {
    pub fn beautiful_substrings(s: String, k: i32) -> i32 {
        let n = s.len();
        let s = s.as_bytes();

        let mut vs = [0; 26];
        for &c in b"aeiou" {
            vs[(c - b'a') as usize] = 1;
        }

        let mut ans = 0;

        for i in 0..n {
            let mut vowels = 0;
            for j in i..n {
                vowels += vs[(s[j] - b'a') as usize];
                let consonants = (j - i + 1) as i32 - vowels;
                if vowels == consonants && (vowels * consonants) % k == 0 {
                    ans += 1;
                }
            }
        }

        ans
    }
}
