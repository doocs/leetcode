impl Solution {
    pub fn maximum_happiness_sum(mut happiness: Vec<i32>, k: i32) -> i64 {
        happiness.sort_unstable_by(|a, b| b.cmp(a));

        let mut ans: i64 = 0;
        for i in 0..(k as usize) {
            let x = happiness[i] as i64 - i as i64;
            if x > 0 {
                ans += x;
            }
        }
        ans
    }
}
