impl Solution {
    pub fn exchange(mut nums: Vec<i32>) -> Vec<i32> {
        if nums.len() == 0 {
            return nums;
        }
        let mut l = 0;
        let mut r = nums.len() - 1;
        while l < r {
            let num = nums[l];
            if num % 2 == 0 {
                nums[l] = nums[r];
                nums[r] = num;
                r -= 1;
            } else {
                l += 1;
            }
        }
        nums
    }
}
