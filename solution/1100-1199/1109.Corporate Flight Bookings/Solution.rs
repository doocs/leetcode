impl Solution {
    #[allow(dead_code)]
    pub fn corp_flight_bookings(bookings: Vec<Vec<i32>>, n: i32) -> Vec<i32> {
        let mut ans = vec![0; n as usize];

        // Build the difference vector first
        for b in &bookings {
            let (l, r) = ((b[0] as usize) - 1, (b[1] as usize) - 1);
            ans[l] += b[2];
            if r < (n as usize) - 1 {
                ans[r + 1] -= b[2];
            }
        }

        // Build the prefix sum vector based on the difference vector
        for i in 1..n as usize {
            ans[i] += ans[i - 1];
        }

        ans
    }
}
