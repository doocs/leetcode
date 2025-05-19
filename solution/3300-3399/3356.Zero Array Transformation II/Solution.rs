impl Solution {
    pub fn min_zero_array(nums: Vec<i32>, queries: Vec<Vec<i32>>) -> i32 {
        let n = nums.len();
        let m = queries.len();
        let mut d: Vec<i64> = vec![0; n + 1];
        let (mut l, mut r) = (0_usize, m + 1);

        let check = |k: usize, d: &mut Vec<i64>| -> bool {
            d.fill(0);
            for i in 0..k {
                let (l, r, val) = (
                    queries[i][0] as usize,
                    queries[i][1] as usize,
                    queries[i][2] as i64,
                );
                d[l] += val;
                d[r + 1] -= val;
            }
            let mut s: i64 = 0;
            for i in 0..n {
                s += d[i];
                if nums[i] as i64 > s {
                    return false;
                }
            }
            true
        };

        while l < r {
            let mid = (l + r) >> 1;
            if check(mid, &mut d) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        if l > m {
            -1
        } else {
            l as i32
        }
    }
}
