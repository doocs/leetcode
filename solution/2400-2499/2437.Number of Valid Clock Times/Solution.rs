impl Solution {
    pub fn count_time(time: String) -> i32 {
        let mut ans = 0;

        for i in 0..24 {
            for j in 0..60 {
                let mut ok = true;
                let t = format!("{:02}:{:02}", i, j);

                for (k, ch) in time.chars().enumerate() {
                    if ch != '?' && ch != t.chars().nth(k).unwrap() {
                        ok = false;
                    }
                }

                if ok {
                    ans += 1;
                }
            }
        }

        ans
    }
}
