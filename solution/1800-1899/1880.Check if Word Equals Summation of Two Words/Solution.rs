impl Solution {
    fn calc(s: &String) -> i32 {
        let mut res = 0;
        for c in s.as_bytes() {
            res = res * 10 + (c - b'a') as i32;
        }
        res
    }

    pub fn is_sum_equal(first_word: String, second_word: String, target_word: String) -> bool {
        Self::calc(&first_word) + Self::calc(&second_word) == Self::calc(&target_word)
    }
}
