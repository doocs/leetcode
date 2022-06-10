impl Solution {
    pub fn search_range(nums: Vec<i32>, target: i32) -> Vec<i32> {
        let n = nums.len();
        let search = |target| {
            let mut left = 0;
            let mut right = n;
            while left < right {
                let mid = left + (right - left) / 2;
                if nums[mid] < target {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            left
        };
        let start = search(target);
        let end = search(target + 1) - 1;
        if start >= n || nums[start] != target {
            return vec![-1, -1];
        }
        vec![start as i32, end as i32]
    }
}
