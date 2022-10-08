impl Solution {
    pub fn advantage_count(mut nums1: Vec<i32>, nums2: Vec<i32>) -> Vec<i32> {
        let n = nums1.len();
        let mut idx = (0..n).collect::<Vec<usize>>();
        idx.sort_by(|&i, &j| nums2[i].cmp(&nums2[j]));
        nums1.sort();
        let mut res = vec![0; n];
        let mut left = 0;
        let mut right = n - 1;
        for &num in nums1.iter() {
            if num > nums2[idx[left]] {
                res[idx[left]] = num;
                left += 1;
            } else {
                res[idx[right]] = num;
                right -= 1;
            }
        }
        res
    }
}
