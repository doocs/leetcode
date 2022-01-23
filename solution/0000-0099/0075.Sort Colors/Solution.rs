impl Solution {
    pub fn sort_colors(nums: &mut Vec<i32>) {
        let len = nums.len();
        if len < 2 {
            return;
        }

        let mut l = 0;
        let mut r = len - 1;
        let mut i = 0;
        while i <= r {
            match nums[i] {
                0 => {
                    nums.swap(i, l);
                    l += 1;
                    i += 1;
                }
                2 => {
                    nums.swap(i, r);
                    match r {
                        0 => return,
                        _ => r -= 1,
                    }
                }
                _ => i += 1,
            }
        }
    }
}
