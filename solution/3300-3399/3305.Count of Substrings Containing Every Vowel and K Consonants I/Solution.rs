impl Solution {
    pub fn count_of_substrings(word: String, k: i32) -> i32 {
        fn f(word: &Vec<char>, k: i32) -> i32 {
            let mut ans = 0;
            let mut l = 0;
            let mut x = 0;
            let mut cnt = std::collections::HashMap::new();

            let is_vowel = |c: char| matches!(c, 'a' | 'e' | 'i' | 'o' | 'u');

            for (r, &c) in word.iter().enumerate() {
                if is_vowel(c) {
                    *cnt.entry(c).or_insert(0) += 1;
                } else {
                    x += 1;
                }

                while x >= k && cnt.len() == 5 {
                    let d = word[l];
                    l += 1;
                    if is_vowel(d) {
                        let count = cnt.entry(d).or_insert(0);
                        *count -= 1;
                        if *count == 0 {
                            cnt.remove(&d);
                        }
                    } else {
                        x -= 1;
                    }
                }
                ans += l as i32;
            }
            ans
        }

        let chars: Vec<char> = word.chars().collect();
        f(&chars, k) - f(&chars, k + 1)
    }
}
