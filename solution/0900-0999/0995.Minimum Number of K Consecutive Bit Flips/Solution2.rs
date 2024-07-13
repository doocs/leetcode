impl Solution {
    pub fn min_k_bit_flips(mut nums: Vec<i32>, k: i32) -> i32 {
        let mut ans = 0;
        let mut flipped = 0;
        let k = k as usize;

        for i in 0..nums.len() {
            if i >= k && nums[i - k] == -1 {
                flipped ^= 1;
            }
            if flipped == nums[i] {
                if i + k > nums.len() {
                    return -1;
                }
                flipped ^= 1;
                ans += 1;
                nums[i] = -1;
            }
        }

        ans
    }
}
