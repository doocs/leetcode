impl Solution {
    pub fn sort_array_by_parity_ii(mut nums: Vec<i32>) -> Vec<i32> {
        let n = nums.len();
        let mut j = 1;
        for i in (0..n).step_by(2) {
            if nums[i] % 2 != 0 {
                while nums[j] % 2 != 0 {
                    j += 2;
                }
                nums.swap(i, j);
            }
        }
        nums
    }
}
