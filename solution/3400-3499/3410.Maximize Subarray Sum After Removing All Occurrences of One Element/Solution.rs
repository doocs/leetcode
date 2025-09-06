use std::collections::HashMap;

impl Solution {
    pub fn max_subarray_sum(nums: Vec<i32>) -> i64 {
        let mut ans: i64 = *nums.iter().max().unwrap() as i64;
        let mut prefix: i64 = 0;
        let mut min_prefix: i64 = 0;

        let mut modified_min_prefix: i64 = 0;
        let mut count: HashMap<i32, i64> = HashMap::new();
        let mut min_prefix_plus_removal: HashMap<i32, i64> = HashMap::new();

        for &num in nums.iter() {
            let n64 = num as i64;
            prefix += n64;
            ans = ans.max(prefix - modified_min_prefix);

            if n64 < 0 {
                let entry = count.entry(num).or_insert(0);
                *entry += 1;

                let prev = *min_prefix_plus_removal.get(&num).unwrap_or(&0);
                min_prefix_plus_removal.insert(num, prev.min(min_prefix) + n64);

                modified_min_prefix = modified_min_prefix.min(*entry * n64);
                modified_min_prefix =
                    modified_min_prefix.min(*min_prefix_plus_removal.get(&num).unwrap());
            }

            min_prefix = min_prefix.min(prefix);
            modified_min_prefix = modified_min_prefix.min(min_prefix);
        }

        ans
    }
}
