use std::collections::HashMap;

impl Solution {
    pub fn min_operations(nums: Vec<i32>) -> i32 {
        let mut f: HashMap<i32, i32> = HashMap::new();
        f.insert(nums[0], 0);

        for i in 1..nums.len() {
            let x = nums[i];
            let mut g: HashMap<i32, i32> = HashMap::new();

            for (&pre, &s) in f.iter() {
                let mut cur = ((x + pre - 1) / pre) * pre;
                while cur <= 100 {
                    let val = s + (cur - x);
                    match g.get(&cur) {
                        None => {
                            g.insert(cur, val);
                        }
                        Some(&old) => {
                            if val < old {
                                g.insert(cur, val);
                            }
                        }
                    }
                    cur += pre;
                }
            }
            f = g;
        }

        *f.values().min().unwrap()
    }
}
