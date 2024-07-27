impl Solution {
    pub fn digit_count(num: String) -> bool {
        let mut cnt = vec![0; 10];
        for c in num.chars() {
            let x = c.to_digit(10).unwrap() as usize;
            cnt[x] += 1;
        }
        for (i, c) in num.chars().enumerate() {
            let x = c.to_digit(10).unwrap() as usize;
            if cnt[i] != x {
                return false;
            }
        }
        true
    }
}
