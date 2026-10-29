impl Solution {
    pub fn next_permutation(nums: &mut Vec<i32>) {
        let n = nums.len();
        let mut i = n;
        while i > 1 && nums[i - 2] >= nums[i - 1] {
            i -= 1;
        }
        // nums[i-1..] is the longest non-increasing suffix; pivot is nums[i-1]
        if i > 1 {
            let pivot = i - 2;
            let mut j = n - 1;
            while nums[j] <= nums[pivot] {
                j -= 1;
            }
            nums.swap(pivot, j);
        }
        nums[i - 1..].reverse();
    }
}
