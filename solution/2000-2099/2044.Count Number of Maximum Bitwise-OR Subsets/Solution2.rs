impl Solution {
    pub fn count_max_or_subsets(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut ans = 0;
        let mut mx = 0;

        for mask in 0..(1 << n) {
            let mut t = 0;
            for i in 0..n {
                if (mask >> i) & 1 == 1 {
                    t |= nums[i];
                }
            }
            if mx < t {
                mx = t;
                ans = 1;
            } else if mx == t {
                ans += 1;
            }
        }

        ans
    }
}
