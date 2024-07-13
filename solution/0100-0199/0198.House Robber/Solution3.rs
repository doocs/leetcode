impl Solution {
    pub fn rob(nums: Vec<i32>) -> i32 {
        let mut f = [0, 0];
        for x in nums {
            f = [f[0].max(f[1]), f[0] + x];
        }
        f[0].max(f[1])
    }
}
