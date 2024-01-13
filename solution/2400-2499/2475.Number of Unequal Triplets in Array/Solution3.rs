use std::collections::HashMap;

impl Solution {
    pub fn unequal_triplets(nums: Vec<i32>) -> i32 {
        let cnt = nums.iter().fold(HashMap::new(), |mut map, &n| {
            *map.entry(n).or_insert(0) += 1;
            map
        });

        let mut ans = 0;
        let n = nums.len();
        let mut a = 0;
        for &b in cnt.values() {
            let c = n - a - b;
            ans += a * b * c;
            a += b;
        }

        ans as i32
    }
}
