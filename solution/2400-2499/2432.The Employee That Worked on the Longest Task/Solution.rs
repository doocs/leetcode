impl Solution {
    pub fn hardest_worker(n: i32, logs: Vec<Vec<i32>>) -> i32 {
        let mut res = 0;
        let mut max = 0;
        let mut pre = 0;
        for log in logs.iter() {
            let t = log[1] - pre;
            if t > max || t == max && res > log[0] {
                res = log[0];
                max = t;
            }
            pre = log[1];
        }
        res
    }
}
