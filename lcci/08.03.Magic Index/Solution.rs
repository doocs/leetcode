impl Solution {
    fn find(nums: &Vec<i32>, l: usize, r: usize) -> i32 {
        if l >= r || nums[r - 1] < 0 {
            return -1;
        }
        let mid = l + (r - l) / 2;
        if nums[mid] >= (l as i32) {
            let res = Self::find(nums, l, mid);
            if res != -1 {
                return res;
            }
        }
        if nums[mid] == (mid as i32) {
            return mid as i32;
        }
        Self::find(nums, mid + 1, r)
    }

    pub fn find_magic_index(nums: Vec<i32>) -> i32 {
        Self::find(&nums, 0, nums.len())
    }
}
