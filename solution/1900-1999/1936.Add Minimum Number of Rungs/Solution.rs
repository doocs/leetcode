impl Solution {
    pub fn add_rungs(rungs: Vec<i32>, dist: i32) -> i32 {
        let mut ans = 0;
        let mut prev = 0;

        for &x in rungs.iter() {
            ans += (x - prev - 1) / dist;
            prev = x;
        }

        ans
    }
}
