impl Solution {
    pub fn next_greater_element(nums1: Vec<i32>, nums2: Vec<i32>) -> Vec<i32> {
        nums1
            .iter()
            .map(|target| {
                let mut res = -1;
                for num in nums2.iter().rev() {
                    if num == target {
                        break;
                    }
                    if num > target {
                        res = *num;
                    }
                }
                res
            })
            .collect::<Vec<i32>>()
    }
}
