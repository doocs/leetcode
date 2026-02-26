impl Solution {
    pub fn rob(nums: Vec<i32>, colors: Vec<i32>) -> i64 {
        let n = nums.len();
        let mut f: i64 = 0;
        let mut g: i64 = nums[0] as i64;

        for i in 1..n {
            if colors[i - 1] == colors[i] {
                let gg = f + nums[i] as i64;
                f = f.max(g);
                g = gg;
            } else {
                let gg = f.max(g) + nums[i] as i64;
                f = f.max(g);
                g = gg;
            }
        }

        f.max(g)
    }
}
