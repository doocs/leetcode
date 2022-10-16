impl Solution {
    pub fn count_subarrays(nums: Vec<i32>, min_k: i32, max_k: i32) -> i64 {
        let mut res = 0;
        let mut min_index = -1;
        let mut max_index = -1;
        let mut k = -1;
        for i in 0..nums.len() {
            let num = nums[i];
            let i = i as i64;
            if num == min_k {
                min_index = i;
            }
            if num == max_k {
                max_index = i;
            }
            if num < min_k || num > max_k {
                k = i;
            }
            res += 0.max(min_index.min(max_index) - k);
        }
        res
    }
}
