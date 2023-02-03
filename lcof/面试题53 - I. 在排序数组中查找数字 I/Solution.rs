impl Solution {
    pub fn search(nums: Vec<i32>, target: i32) -> i32 {
        let search = |x| {
            let mut l = 0;
            let mut r = nums.len();
            while l < r  {
                let mid = l + (r - l) / 2;
                if nums[mid] >= x {
                    r = mid;
                } else {
                    l = mid + 1
                }
            }
            l as i32
        };
        search(target + 1) - search(target)
    }
}