impl Solution {
    pub fn exchange(mut nums: Vec<i32>) -> Vec<i32> {
        let mut j = 0;
        for i in 0..nums.len() {
            if nums[i] % 2 == 1 {
                nums.swap(i, j);
                j += 1;
            }
        }
        nums
    }
}