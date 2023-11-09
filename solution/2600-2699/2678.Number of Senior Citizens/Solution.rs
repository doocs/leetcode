impl Solution {
    pub fn count_seniors(details: Vec<String>) -> i32 {
        let mut ans = 0;

        for s in details.iter() {
            if let Ok(age) = s[11..13].parse::<i32>() {
                if age > 60 {
                    ans += 1;
                }
            }
        }

        ans
    }
}
