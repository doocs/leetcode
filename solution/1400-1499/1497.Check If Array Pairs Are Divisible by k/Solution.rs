impl Solution {
    pub fn can_arrange(arr: Vec<i32>, k: i32) -> bool {
        let k = k as usize;
        let mut cnt = vec![0; k];
        for &x in &arr {
            cnt[((x % k as i32 + k as i32) % k as i32) as usize] += 1;
        }
        for i in 1..k {
            if cnt[i] != cnt[k - i] {
                return false;
            }
        }
        cnt[0] % 2 == 0
    }
}
