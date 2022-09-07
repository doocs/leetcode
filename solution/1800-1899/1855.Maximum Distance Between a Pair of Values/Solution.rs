impl Solution {
    pub fn max_distance(nums1: Vec<i32>, nums2: Vec<i32>) -> i32 {
        let m = nums1.len();
        let n = nums2.len();
        let mut res = 0;
        let mut j = 0;
        for i in 0..m {
            while j < n && nums1[i] <= nums2[j] {
                j += 1
            }
            res = res.max((j - i - 1) as i32)
        }
        res
    }
}