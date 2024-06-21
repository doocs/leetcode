impl Solution {
    pub fn num_subarray_product_less_than_k(nums: Vec<i32>, k: i32) -> i32 {
        if k <= 1 {
            return 0;
        }

        let mut res = 0;
        let mut product = 1;
        let mut i = 0;
        nums.iter().enumerate().for_each(|(j, v)| {
            product *= v;
            while product >= k {
                product /= nums[i];
                i += 1;
            }
            res += j - i + 1;
        });
        res as i32
    }
}
