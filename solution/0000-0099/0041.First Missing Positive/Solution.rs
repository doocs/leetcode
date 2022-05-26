impl Solution {
    pub fn first_missing_positive(mut nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut i = 0;
        while i < n {
            let j = nums[i] - 1;
            if i as i32 == j || j < 0 || j >= n as i32 || nums[i] == nums[j as usize] {
                i += 1;
            } else {
                nums.swap(i, j as usize);
            }
        }
        nums.iter()
            .enumerate()
            .position(|(i, &v)| v as usize != i + 1)
            .unwrap_or(n) as i32
            + 1
    }
}
