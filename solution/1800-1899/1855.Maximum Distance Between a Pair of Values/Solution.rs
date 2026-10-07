impl Solution {
    pub fn max_distance(nums1: Vec<i32>, nums2: Vec<i32>) -> i32 {
        let m = nums1.len();
        let n = nums2.len();
        let mut res = 0;
        for i in 0..m {
            let mut left = i;
            let mut right = n;
            while left < right {
                let mid = left + (right - left) / 2;
                if nums2[mid] >= nums1[i] {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            res = res.max(left as i32 - i as i32 - 1);
        }
        res
    }
}
