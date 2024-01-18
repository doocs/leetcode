impl Solution {
    pub fn subarray_sum(mut nums: Vec<i32>, k: i32) -> i32 {
        let n = nums.len();
        let mut count = 0;
        for i in 0..n {
            let num = nums[i];
            if num == k {
                count += 1;
            }
            for j in 0..i {
                nums[j] += num;
                if nums[j] == k {
                    count += 1;
                }
            }
        }
        count
    }
}
