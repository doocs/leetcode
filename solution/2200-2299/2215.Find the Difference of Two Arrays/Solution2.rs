impl Solution {
    pub fn find_difference(nums1: Vec<i32>, nums2: Vec<i32>) -> Vec<Vec<i32>> {
        const N: usize = 2001;
        let to_index = |i| (i as usize) + 1000;

        let mut is_in_nums1 = [false; N];
        let mut is_in_nums2 = [false; N];
        let mut res1 = vec![];
        let mut res2 = vec![];
        for &num in nums1.iter() {
            is_in_nums1[to_index(num)] = true;
        }
        for &num in nums2.iter() {
            is_in_nums2[to_index(num)] = true;
            if !is_in_nums1[to_index(num)] {
                res2.push(num);
                is_in_nums1[to_index(num)] = true;
            }
        }
        for &num in nums1.iter() {
            if !is_in_nums2[to_index(num)] {
                res1.push(num);
                is_in_nums2[to_index(num)] = true;
            }
        }
        vec![res1, res2]
    }
}
