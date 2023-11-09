impl Solution {
    #[allow(dead_code)]
    pub fn best_closing_time(customers: String) -> i32 {
        let n = customers.len();
        let mut penalty = i32::MAX;
        let mut ret = -1;
        let mut prefix_sum = vec![0; n + 1];

        // Initialize the vector
        for (i, c) in customers.chars().enumerate() {
            prefix_sum[i + 1] = prefix_sum[i] + (if c == 'Y' { 1 } else { 0 });
        }

        // Calculate the answer
        for i in 0..=n {
            if penalty > ((prefix_sum[n] - prefix_sum[i]) as i32) + ((i - prefix_sum[i]) as i32) {
                penalty = ((prefix_sum[n] - prefix_sum[i]) as i32) + ((i - prefix_sum[i]) as i32);
                ret = i as i32;
            }
        }

        ret
    }
}
