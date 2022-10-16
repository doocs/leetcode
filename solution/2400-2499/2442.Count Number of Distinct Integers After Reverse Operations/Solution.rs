use std::collections::HashSet;
impl Solution {
    pub fn count_distinct_integers(nums: Vec<i32>) -> i32 {
        let mut set = HashSet::new();
        for i in 0..nums.len() {
            let mut num = nums[i];
            set.insert(num);
            set.insert({
                let mut item = 0;
                while num > 0 {
                    item = item * 10 + num % 10;
                    num /= 10;
                }
                item
            });
        }
        set.len() as i32
    }
}
