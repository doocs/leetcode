impl Solution {
    pub fn temperature_trend(temperature_a: Vec<i32>, temperature_b: Vec<i32>) -> i32 {
        let mut ans = 0;
        let mut f = 0;

        for i in 0..temperature_a.len() - 1 {
            let x = temperature_a[i + 1] - temperature_a[i];
            let y = temperature_b[i + 1] - temperature_b[i];

            if (x == 0 && y == 0) || (x > 0 && y > 0) || (x < 0 && y < 0) {
                f += 1;
                if f > ans {
                    ans = f;
                }
            } else {
                f = 0;
            }
        }

        ans
    }
}
