impl Solution {
    pub fn sort_colors(nums: &mut Vec<i32>) {
        let mut l = 0;
        let mut r = nums.len() - 1;
        let mut i = 0;
        while i <= r {
            match nums[i] {
                2 => {
                    nums.swap(i, r);
                    match r {
                        0 => return,
                        _ => r -= 1,
                    }
                }
                n => {
                    if n == 0 {
                        nums.swap(i, l);
                        l += 1;
                    }
                    i += 1;
                }
            }
        }
    }
}
