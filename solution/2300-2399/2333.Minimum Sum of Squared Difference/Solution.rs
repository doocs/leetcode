impl Solution {
    pub fn min_sum_square_diff(nums1: Vec<i32>, nums2: Vec<i32>, k1: i32, k2: i32) -> i64 {
        let mut k = i64::from(k1) + i64::from(k2);
        let mut s = 0i64;
        let mut cnt = [0i32; 100_001];
        let mut mx = 0usize;
        for (a, b) in nums1.iter().zip(&nums2) {
            let v = (a - b).unsigned_abs() as usize;
            cnt[v] += 1;
            s += v as i64;
            mx = mx.max(v);
        }
        if s <= k {
            return 0;
        }
        for v in (1..=mx).rev() {
            if k == 0 {
                break;
            }
            if cnt[v] == 0 {
                continue;
            }
            let take = i64::from(cnt[v]).min(k) as i32;
            k -= i64::from(take);
            cnt[v] -= take;
            cnt[v - 1] += take;
        }
        cnt[..=mx]
            .iter()
            .enumerate()
            .map(|(v, &c)| {
                let v = v as i64;
                v * v * i64::from(c)
            })
            .sum()
    }
}
