impl Solution {
    pub fn merge(a: &mut Vec<i32>, m: i32, b: &mut Vec<i32>, n: i32) {
        let mut m = m as usize;
        let mut n = n as usize;
        for i in (0..n + m).rev() {
            let x = if m != 0 { a[m - 1] } else { i32::MIN };
            let y = if n != 0 { b[n - 1] } else { i32::MIN };
            if x > y {
                a[i] = x;
                m -= 1;
            } else {
                a[i] = y;
                n -= 1;
            }
        }
    }
}
