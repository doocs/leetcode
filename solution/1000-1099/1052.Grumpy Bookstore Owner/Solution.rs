impl Solution {
    pub fn max_satisfied(customers: Vec<i32>, grumpy: Vec<i32>, minutes: i32) -> i32 {
        let mut cnt = 0;
        let mut tot = 0;
        let minutes = minutes as usize;
        for i in 0..minutes {
            cnt += customers[i] * grumpy[i];
            tot += customers[i] * (1 - grumpy[i]);
        }
        let mut mx = cnt;
        let n = customers.len();
        for i in minutes..n {
            cnt += customers[i] * grumpy[i];
            cnt -= customers[i - minutes] * grumpy[i - minutes];
            mx = mx.max(cnt);
            tot += customers[i] * (1 - grumpy[i]);
        }
        tot + mx
    }
}
