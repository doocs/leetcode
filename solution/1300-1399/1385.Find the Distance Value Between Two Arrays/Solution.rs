impl Solution {
    pub fn find_the_distance_value(arr1: Vec<i32>, mut arr2: Vec<i32>, d: i32) -> i32 {
        arr2.sort();
        let n = arr2.len();
        let mut res = 0;
        for &num in arr1.iter() {
            let mut left = 0;
            let mut right = n - 1;
            while left < right {
                let mid = left + (right - left) / 2;
                if arr2[mid] <= num {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            if i32::abs(num - arr2[left]) <= d || (left != 0 && i32::abs(num - arr2[left - 1]) <= d)
            {
                continue;
            }
            res += 1;
        }
        res
    }
}
