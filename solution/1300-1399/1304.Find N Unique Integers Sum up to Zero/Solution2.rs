impl Solution {
    pub fn sum_zero(n: i32) -> Vec<i32> {
        let mut ans = vec![0; n as usize];
        for i in 1..n {
            ans[i as usize] = i;
        }
        ans[0] = -(n * (n - 1) / 2);
        ans
    }
}
