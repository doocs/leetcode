impl Solution {
    pub fn pass_the_pillow(n: i32, time: i32) -> i32 {
        let mut ans = 1;
        let mut k = 1;

        for i in 1..=time {
            ans += k;

            if ans == 1 || ans == n {
                k *= -1;
            }
        }

        ans
    }
}
