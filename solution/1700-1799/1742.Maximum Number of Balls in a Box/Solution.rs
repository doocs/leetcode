impl Solution {
    pub fn count_balls(low_limit: i32, high_limit: i32) -> i32 {
        let mut cnt = vec![0; 50];
        for x in low_limit..=high_limit {
            let mut y = 0;
            let mut n = x;
            while n > 0 {
                y += n % 10;
                n /= 10;
            }
            cnt[y as usize] += 1;
        }
        *cnt.iter().max().unwrap()
    }
}
