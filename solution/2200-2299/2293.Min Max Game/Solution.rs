impl Solution {
    pub fn min_max_game(mut nums: Vec<i32>) -> i32 {
        let mut n = nums.len();
        while n != 1 {
            n >>= 1;
            for i in 0..n {
                nums[i] = (if i & 1 == 1 {
                    i32::max
                } else {
                    i32::min
                })(nums[i << 1], nums[i << 1 | 1])
            }
        }
        nums[0]
    }
}
