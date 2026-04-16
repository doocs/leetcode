use std::collections::HashMap;

impl Solution {
    pub fn min_mirror_pair_distance(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut ans = n as i32 + 1;
        let mut pos: HashMap<i32, i32> = HashMap::new();

        fn reverse(mut x: i32) -> i32 {
            let mut y = 0;
            while x > 0 {
                y = y * 10 + x % 10;
                x /= 10;
            }
            y
        }

        for (i, &v) in nums.iter().enumerate() {
            if let Some(&j) = pos.get(&v) {
                ans = ans.min(i as i32 - j);
            }
            pos.insert(reverse(v), i as i32);
        }

        if ans > n as i32 { -1 } else { ans }
    }
}
