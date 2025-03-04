impl Solution {
    pub fn can_be_increasing(nums: Vec<i32>) -> bool {
        let check = |k: usize| -> bool {
            let mut pre = 0;
            for (i, &x) in nums.iter().enumerate() {
                if i == k {
                    continue;
                }
                if pre >= x {
                    return false;
                }
                pre = x;
            }
            true
        };

        let mut i = 0;
        while i + 1 < nums.len() && nums[i] < nums[i + 1] {
            i += 1;
        }
        check(i) || check(i + 1)
    }
}
