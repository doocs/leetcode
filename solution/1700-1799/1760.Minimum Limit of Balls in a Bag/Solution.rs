impl Solution {
    pub fn minimum_size(nums: Vec<i32>, max_operations: i32) -> i32 {
        let mut l = 1;
        let mut r = *nums.iter().max().unwrap();

        while l < r {
            let mid = (l + r) / 2;
            let mut s: i64 = 0;

            for &x in &nums {
                s += ((x - 1) / mid) as i64;
            }

            if s <= max_operations as i64 {
                r = mid;
            } else {
                l = mid + 1;
            }
        }

        l
    }
}
