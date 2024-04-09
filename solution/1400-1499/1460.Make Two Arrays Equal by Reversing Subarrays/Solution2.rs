impl Solution {
    pub fn can_be_equal(mut target: Vec<i32>, mut arr: Vec<i32>) -> bool {
        let n = target.len();
        let mut cnt = [0; 1001];
        for i in 0..n {
            cnt[target[i] as usize] += 1;
            cnt[arr[i] as usize] -= 1;
        }
        cnt.iter().all(|v| *v == 0)
    }
}
