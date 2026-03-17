impl Solution {
    pub fn result_array(nums: Vec<i32>) -> Vec<i32> {
        let n = nums.len();
        let mut arr1 = vec![nums[0]];
        let mut arr2 = vec![nums[1]];
        for k in 2..n {
            if arr1[arr1.len() - 1] > arr2[arr2.len() - 1] {
                arr1.push(nums[k]);
            } else {
                arr2.push(nums[k]);
            }
        }
        arr1.extend(arr2);
        arr1
    }
}
