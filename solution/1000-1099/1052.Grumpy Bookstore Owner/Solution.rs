impl Solution {
    pub fn max_satisfied(customers: Vec<i32>, grumpy: Vec<i32>, minutes: i32) -> i32 {
        let k = minutes as usize;
        let n = customers.len();

        let mut sum = 0;
        for i in 0..k {
            if grumpy[i] == 1 {
                sum += customers[i];
            }
        }
        let mut max = sum;
        for i in k..n {
            if grumpy[i - k] == 1 {
                sum -= customers[i - k];
            }
            if grumpy[i] == 1 {
                sum += customers[i];
            }
            max = max.max(sum);
        }

        sum = 0;
        for i in 0..n {
            if grumpy[i] == 0 {
                sum += customers[i];
            }
        }
        sum + max
    }
}
