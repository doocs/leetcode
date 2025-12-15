impl Solution {
    pub fn best_closing_time(customers: String) -> i32 {
        let bytes = customers.as_bytes();

        let mut cost: i32 = bytes.iter().filter(|&&c| c == b'Y').count() as i32;
        let mut mn = cost;
        let mut ans: i32 = 0;

        for j in 1..=bytes.len() {
            let c = bytes[j - 1];
            if c == b'N' {
                cost += 1;
            } else {
                cost -= 1;
            }
            if cost < mn {
                mn = cost;
                ans = j as i32;
            }
        }
        ans
    }
}
