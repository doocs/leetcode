impl Solution {
    pub fn find_length_of_lcis(nums: Vec<i32>) -> i32 {
        let mut ans = 1;
        let n = nums.len();
        let mut i = 0;
        while i < n {
            let mut j = i + 1;
            while j < n && nums[j - 1] < nums[j] {
                j += 1;
            }
            ans = ans.max(j - i);
            i = j;
        }
        ans as i32
    }
}
