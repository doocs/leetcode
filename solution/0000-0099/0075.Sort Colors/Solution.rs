impl Solution {
    pub fn sort_colors(nums: &mut Vec<i32>) {
        let mut i = -1;
        let mut j = nums.len();
        let mut k = 0;
        while k < j {
            if nums[k] == 0 {
                i += 1;
                nums.swap(i as usize, k as usize);
                k += 1;
            } else if nums[k] == 2 {
                j -= 1;
                nums.swap(j, k);
            } else {
                k += 1;
            }
        }
    }
}
