impl Solution {
    pub fn count_symmetric_integers(low: i32, high: i32) -> i32 {
        let mut ans = 0;
        for x in low..=high {
            ans += Self::f(x);
        }
        ans
    }

    fn f(x: i32) -> i32 {
        let s = x.to_string();
        let n = s.len();
        if n % 2 == 1 {
            return 0;
        }
        let bytes = s.as_bytes();
        let mut a = 0;
        let mut b = 0;
        for i in 0..n / 2 {
            a += (bytes[i] - b'0') as i32;
        }
        for i in n / 2..n {
            b += (bytes[i] - b'0') as i32;
        }
        if a == b {
            1
        } else {
            0
        }
    }
}
