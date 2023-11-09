impl Solution {
    #[allow(dead_code)]
    pub fn min_taps(n: i32, ranges: Vec<i32>) -> i32 {
        let mut last = vec![0; (n + 1) as usize];
        let mut ans = 0;
        let mut mx = 0;
        let mut pre = 0;

        // Initialize the last vector
        for (i, &r) in ranges.iter().enumerate() {
            if (i as i32) - r >= 0 {
                last[((i as i32) - r) as usize] = std::cmp::max(
                    last[((i as i32) - r) as usize],
                    (i as i32) + r
                );
            } else {
                last[0] = std::cmp::max(last[0], (i as i32) + r);
            }
        }

        for i in 0..n as usize {
            mx = std::cmp::max(mx, last[i]);
            if mx <= (i as i32) {
                return -1;
            }
            if pre == (i as i32) {
                ans += 1;
                pre = mx;
            }
        }

        ans
    }
}
