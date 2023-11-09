impl Solution {
    pub fn can_be_typed_words(text: String, broken_letters: String) -> i32 {
        let mut s = vec![false; 26];
        for c in broken_letters.chars() {
            s[(c as usize) - ('a' as usize)] = true;
        }
        let mut ans = 0;
        let words = text.split_whitespace();
        for w in words {
            for c in w.chars() {
                if s[(c as usize) - ('a' as usize)] {
                    ans -= 1;
                    break;
                }
            }
            ans += 1;
        }
        ans
    }
}
