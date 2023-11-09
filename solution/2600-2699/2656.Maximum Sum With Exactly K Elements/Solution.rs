impl Solution {
    pub fn maximize_sum(nums: Vec<i32>, k: i32) -> i32 {
        let mut mx = 0;

        for &n in &nums {
            if n > mx {
                mx = n;
            }
        }

        ((0 + k - 1) * k) / 2 + k * mx
    }
}
