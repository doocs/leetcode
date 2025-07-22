impl Solution {
    pub fn is_sum_equal(first_word: String, second_word: String, target_word: String) -> bool {
        fn f(s: &str) -> i64 {
            let mut ans = 0;
            let a = 'a' as i64;
            for c in s.chars() {
                let x = c as i64 - a;
                ans = ans * 10 + x;
            }
            ans
        }
        f(&first_word) + f(&second_word) == f(&target_word)
    }
}
