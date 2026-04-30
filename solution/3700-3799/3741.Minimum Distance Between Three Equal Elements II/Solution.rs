use std::collections::HashMap;

impl Solution {
    pub fn minimum_distance(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut g: HashMap<i32, Vec<usize>> = HashMap::new();
        for (i, &num) in nums.iter().enumerate() {
            g.entry(num).or_insert_with(Vec::new).push(i);
        }

        let inf = 1 << 30;
        let mut ans = inf;
        for ls in g.values() {
            let m = ls.len();
            if m < 3 {
                continue;
            }
            for h in 0..m - 2 {
                let i = ls[h];
                let k = ls[h + 2];
                let t = (k - i) as i32 * 2;
                ans = ans.min(t);
            }
        }

        if ans == inf { -1 } else { ans }
    }
}
