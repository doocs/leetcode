impl Solution {
    pub fn sort_vowels(s: String) -> String {
        fn is_vowel(c: char) -> bool {
            matches!(c.to_ascii_lowercase(), 'a' | 'e' | 'i' | 'o' | 'u')
        }

        let mut vs: Vec<char> = s.chars().filter(|&c| is_vowel(c)).collect();
        vs.sort_unstable();

        let mut cs: Vec<char> = s.chars().collect();
        let mut j = 0;

        for (i, c) in cs.clone().into_iter().enumerate() {
            if is_vowel(c) {
                cs[i] = vs[j];
                j += 1;
            }
        }

        cs.into_iter().collect()
    }
}
