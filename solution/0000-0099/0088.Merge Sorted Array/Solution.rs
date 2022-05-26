impl Solution {
    pub fn merge(nums1: &mut Vec<i32>, m: i32, nums2: &mut Vec<i32>, n: i32) {
        let (mut m, mut n) = (m as usize, n as usize);
        for i in (0..m + n).rev() {
            nums1[i] = match (m == 0, n == 0) {
                (true, false) => {
                    n -= 1;
                    nums2[n]
                }
                (false, true) => {
                    m -= 1;
                    nums1[m]
                }
                (_, _) => {
                    if nums1[m - 1] > nums2[n - 1] {
                        m -= 1;
                        nums1[m]
                    } else {
                        n -= 1;
                        nums2[n]
                    }
                }
            }
        }
    }
}
