impl Solution {
    pub fn max_product(nums: Vec<i32>) -> i32 {
        let mut max = 0;
        let mut submax = 0;
        for &num in nums.iter() {
            if num > max {
                submax = max;
                max = num;
            } else if num > submax {
                submax = num;
            }
        }
        (max - 1) * (submax - 1)
    }
}
