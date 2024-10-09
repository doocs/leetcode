impl Solution {
    pub fn min_operations(k: i32) -> i32 {
        let mut ans = k;
        for a in 0..k {
            let x = a + 1;
            let b = (k + x - 1) / x - 1;
            ans = ans.min(a + b);
        }
        ans
    }
}
