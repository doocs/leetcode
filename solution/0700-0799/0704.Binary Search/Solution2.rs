use std::cmp::Ordering;

impl Solution {
    fn binary_search(nums: Vec<i32>, target: i32, l: usize, r: usize) -> i32 {
        if l == r {
            return if nums[l] == target { l as i32 } else { -1 };
        }
        let mid = (l + r) >> 1;
        match nums[mid].cmp(&target) {
            Ordering::Less => Self::binary_search(nums, target, mid + 1, r),
            Ordering::Greater => Self::binary_search(nums, target, l, mid),
            Ordering::Equal => mid as i32,
        }
    }

    pub fn search(nums: Vec<i32>, target: i32) -> i32 {
        let r = nums.len() - 1;
        Self::binary_search(nums, target, 0, r)
    }
}
