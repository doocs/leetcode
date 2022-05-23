impl Solution {
    pub fn exchange(mut nums: Vec<i32>) -> Vec<i32> {
        if nums.len() == 0 {
            return nums;
        }
        let mut l = 0;
        let mut r = nums.len() - 1;
        while l < r {
            if nums[l] % 2 == 0 {
                nums.swap(l, r);
                r -= 1;
            } else {
                l += 1;
            }
        }
        nums
    }
}
