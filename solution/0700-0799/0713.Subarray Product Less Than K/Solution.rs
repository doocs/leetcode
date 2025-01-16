impl Solution {
    pub fn num_subarray_product_less_than_k(nums: Vec<i32>, k: i32) -> i32 {
        let mut ans = 0;
        let mut l = 0;
        let mut p = 1;

        for (r, &x) in nums.iter().enumerate() {
            p *= x;
            while l <= r && p >= k {
                p /= nums[l];
                l += 1;
            }
            ans += (r - l + 1) as i32;
        }

        ans
    }
}
