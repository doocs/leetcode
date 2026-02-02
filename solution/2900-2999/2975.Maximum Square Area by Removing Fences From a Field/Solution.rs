use std::collections::HashSet;

impl Solution {
    pub fn maximize_square_area(m: i32, n: i32, h_fences: Vec<i32>, v_fences: Vec<i32>) -> i32 {
        fn calc(mut nums: Vec<i32>, k: i32) -> HashSet<i32> {
            nums.push(k);
            nums.push(1);
            nums.sort_unstable();
            let mut s = HashSet::new();
            let len = nums.len();
            for i in 0..len {
                for j in 0..i {
                    s.insert(nums[i] - nums[j]);
                }
            }
            s
        }

        let hs = calc(h_fences, m);
        let vs = calc(v_fences, n);

        let mut ans = 0i64;
        for &h in hs.iter() {
            if vs.contains(&h) {
                ans = ans.max(h as i64);
            }
        }

        if ans > 0 {
            let modv = 1_000_000_007i64;
            ((ans * ans) % modv) as i32
        } else {
            -1
        }
    }
}
