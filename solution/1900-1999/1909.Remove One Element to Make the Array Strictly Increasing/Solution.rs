impl Solution {
    pub fn can_be_increasing(nums: Vec<i32>) -> bool {
        let check = |p: usize| -> bool {
            let mut prev = None;
            for j in 0..nums.len() {
                if p != j {
                    if let Some(value) = prev {
                        if value >= nums[j] {
                            return false;
                        }
                    }
                    prev = Some(nums[j]);
                }
            }
            true
        };
        for i in 1..nums.len() {
            if nums[i - 1] >= nums[i] {
                return check(i - 1) || check(i);
            }
        }
        true
    }
}
