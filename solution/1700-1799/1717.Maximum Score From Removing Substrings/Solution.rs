impl Solution {
    pub fn maximum_gain(s: String, mut x: i32, mut y: i32) -> i32 {
        let (mut a, mut b) = ('a', 'b');
        if x < y {
            std::mem::swap(&mut x, &mut y);
            std::mem::swap(&mut a, &mut b);
        }

        let mut ans = 0;
        let mut cnt1 = 0;
        let mut cnt2 = 0;

        for c in s.chars() {
            if c == a {
                cnt1 += 1;
            } else if c == b {
                if cnt1 > 0 {
                    ans += x;
                    cnt1 -= 1;
                } else {
                    cnt2 += 1;
                }
            } else {
                ans += cnt1.min(cnt2) * y;
                cnt1 = 0;
                cnt2 = 0;
            }
        }

        ans += cnt1.min(cnt2) * y;
        ans
    }
}
