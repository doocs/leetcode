use std::collections::HashMap;
impl Solution {
    pub fn top_k_frequent(nums: Vec<i32>, k: i32) -> Vec<i32> {
        let mut map = HashMap::new();
        let mut max_count = 0;
        for &num in nums.iter() {
            let val = map.get(&num).unwrap_or(&0) + 1;
            map.insert(num, val);
            max_count = max_count.max(val);
        }
        let mut k = k as usize;
        let mut res = vec![0; k];
        while k > 0 {
            let mut next = 0;
            for key in map.keys() {
                let val = map[key];
                if val == max_count {
                    res[k - 1] = *key;
                    k -= 1;
                } else if val < max_count {
                    next = next.max(val);
                }
            }
            max_count = next;
        }
        res
    }
}
