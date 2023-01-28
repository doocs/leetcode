impl Solution {
    pub fn get_common(nums1: Vec<i32>, nums2: Vec<i32>) -> i32 {
        let m = nums1.len();
        let n = nums2.len();
        let mut i = 0;
        let mut j = 0;
        while i < m && j < n {
            if nums1[i] == nums2[j] {
                return nums1[i];
            }
            if nums1[i] < nums2[j] {
                i += 1;
            } else {
                j += 1;
            }
        }
        -1
    }
}
