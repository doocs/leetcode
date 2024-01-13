impl Solution {
    pub fn find_magic_index(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut i = 0 as i32;
        while (i as usize) < n {
            let num = nums[i as usize];
            if num == i {
                return i;
            }
            i = num.max(i + 1);
        }
        -1
    }
}
