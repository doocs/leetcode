impl Solution {
    pub fn max_consecutive_answers(answer_key: String, k: i32) -> i32 {
        let s: Vec<char> = answer_key.chars().collect();
        let f = |c: char| -> i32 {
            let mut cnt = 0;
            let mut j = 0;
            let mut ans = 0;
            for i in 0..s.len() {
                cnt += if s[i] == c { 1 } else { 0 };
                while cnt > k {
                    cnt -= if s[j] == c { 1 } else { 0 };
                    j += 1;
                }
                ans = ans.max((i - j + 1) as i32);
            }
            ans
        };
        f('T').max(f('F'))
    }
}
