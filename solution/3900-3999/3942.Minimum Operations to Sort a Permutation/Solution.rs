impl Solution {
    pub fn min_operations(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let zero = nums.iter().position(|&x| x == 0).unwrap();
        let mut ans = usize::MAX;
        if nums[zero..].iter().chain(&nums[..zero]).is_sorted() {
            ans = zero.min(n - zero + 2);
        }
        if nums[..=zero]
            .iter()
            .rev()
            .chain(nums[zero + 1..].iter().rev())
            .is_sorted()
        {
            ans = ans.min((zero + 2).min(n - zero));
        }
        if ans == usize::MAX {
            -1
        } else {
            ans as i32
        }
    }
}
