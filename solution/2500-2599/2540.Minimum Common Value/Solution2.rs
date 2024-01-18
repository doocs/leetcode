impl Solution {
    pub fn get_common(nums1: Vec<i32>, nums2: Vec<i32>) -> i32 {
        let mut iter1 = nums1.iter();
        let mut iter2 = nums2.iter();
        let mut num1 = iter1.next();
        let mut num2 = iter2.next();

        while let (Some(n1), Some(n2)) = (num1, num2) {
            if n1 == n2 {
                return *n1;
            } else if n1 < n2 {
                num1 = iter1.next();
            } else {
                num2 = iter2.next();
            }
        }

        -1
    }
}
