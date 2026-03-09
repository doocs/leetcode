impl Solution {
    pub fn reverse_subarrays(mut nums: Vec<i32>, k: i32) -> Vec<i32> {
        let n = nums.len();
        let m = n / k as usize;

        for i in (0..n).step_by(m) {
            let mut l = i;
            let mut r = i + m - 1;
            while l < r {
                nums.swap(l, r);
                l += 1;
                r -= 1;
            }
        }

        nums
    }
}
