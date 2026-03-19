impl Solution {
    pub fn num_pairs_divisible_by60(time: Vec<i32>) -> i32 {
        let mut cnt = [0i32; 60];
        let mut ans: i32 = 0;
        for mut x in time {
            x %= 60;
            let y = (60 - x) % 60;
            ans += cnt[y as usize];
            cnt[x as usize] += 1;
        }
        ans
    }
}
