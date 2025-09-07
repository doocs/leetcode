impl Solution {
    pub fn sum_zero(n: i32) -> Vec<i32> {
        let mut ans = vec![0; n as usize];
        let mut j = 0;
        for i in 1..=n / 2 {
            ans[j] = i;
            j += 1;
            ans[j] = -i;
            j += 1;
        }
        ans
    }
}
