impl Solution {
    pub fn lexical_order(n: i32) -> Vec<i32> {
        let mut ans = Vec::with_capacity(n as usize);
        let mut v = 1;
        for _ in 0..n {
            ans.push(v);
            if v * 10 <= n {
                v *= 10;
            } else {
                while v % 10 == 9 || v + 1 > n {
                    v /= 10;
                }
                v += 1;
            }
        }
        ans
    }
}
