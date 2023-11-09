impl Solution {
    pub fn count_time(time: String) -> i32 {
        let f = |s: &str, m: usize| -> i32 {
            let mut cnt = 0;
            let first = s.chars().nth(0).unwrap();
            let second = s.chars().nth(1).unwrap();

            for i in 0..m {
                let a = first == '?' || (first.to_digit(10).unwrap() as usize) == i / 10;

                let b = second == '?' || (second.to_digit(10).unwrap() as usize) == i % 10;

                if a && b {
                    cnt += 1;
                }
            }

            cnt
        };

        f(&time[..2], 24) * f(&time[3..], 60)
    }
}
