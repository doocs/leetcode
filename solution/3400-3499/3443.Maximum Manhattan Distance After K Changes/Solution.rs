impl Solution {
    pub fn max_distance(s: String, k: i32) -> i32 {
        fn calc(s: &str, a: char, b: char, k: i32) -> i32 {
            let mut ans = 0;
            let mut mx = 0;
            let mut cnt = 0;
            for c in s.chars() {
                if c == a || c == b {
                    mx += 1;
                } else if cnt < k {
                    mx += 1;
                    cnt += 1;
                } else {
                    mx -= 1;
                }
                ans = ans.max(mx);
            }
            ans
        }

        let a = calc(&s, 'S', 'E', k);
        let b = calc(&s, 'S', 'W', k);
        let c = calc(&s, 'N', 'E', k);
        let d = calc(&s, 'N', 'W', k);
        a.max(b).max(c).max(d)
    }
}
