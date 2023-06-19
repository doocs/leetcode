impl Solution {
    pub fn rob(nums: Vec<i32>) -> i32 {
        let mut f = [0, 0];
        for x in nums {
            f = [f[1], f[1].max(f[0] + x)]
        }
        f[1]
    }
}
