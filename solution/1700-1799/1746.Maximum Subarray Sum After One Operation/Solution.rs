impl Solution {
    #[allow(dead_code)]
    pub fn max_sum_after_operation(nums: Vec<i32>) -> i32 {
        // Here f[i] represents the value of max sub-array that ends with nums[i] with no substitution
        let mut f = 0;
        // g[i] represents the case with exact one substitution
        let mut g = 0;
        let mut ret = 1 << 31;

        // Begin the actual dp process
        for e in &nums {
            // f[i] = MAX(f[i - 1], 0) + nums[i]
            let new_f = std::cmp::max(f, 0) + *e;
            // g[i] = MAX(MAX(f[i - 1], 0) + nums[i] * nums[i], g[i - 1] + nums[i])
            let new_g = std::cmp::max(std::cmp::max(f, 0) + *e * *e, g + *e);
            // Update f[i] & g[i]
            f = new_f;
            g = new_g;
            // Since we start at 0, update answer after updating f[i] & g[i]
            ret = std::cmp::max(ret, g);
        }

        ret
    }
}
