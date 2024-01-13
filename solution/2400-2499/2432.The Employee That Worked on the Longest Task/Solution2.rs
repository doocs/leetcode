impl Solution {
    pub fn hardest_worker(n: i32, logs: Vec<Vec<i32>>) -> i32 {
        let mut ans = 0;
        let mut mx = 0;
        let mut last = 0;

        for log in logs {
            let uid = log[0];
            let t = log[1];

            let diff = t - last;
            last = t;

            if diff > mx || (diff == mx && uid < ans) {
                ans = uid;
                mx = diff;
            }
        }

        ans
    }
}
