impl Solution {
    fn dfs(nums: &Vec<i32>, i: usize, j: usize) -> i32 {
        if i >= j || nums[j - 1] < 0 {
            return -1;
        }
        let mid = (i + j) >> 1;
        if nums[mid] >= (i as i32) {
            let l = Self::dfs(nums, i, mid);
            if l != -1 {
                return l;
            }
        }
        if nums[mid] == (mid as i32) {
            return mid as i32;
        }
        Self::dfs(nums, mid + 1, j)
    }

    pub fn find_magic_index(nums: Vec<i32>) -> i32 {
        Self::dfs(&nums, 0, nums.len())
    }
}
