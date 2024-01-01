impl Solution {
    pub fn min_operations_max_profit(
        customers: Vec<i32>,
        boarding_cost: i32,
        running_cost: i32
    ) -> i32 {
        let mut ans = -1;
        let mut mx = 0;
        let mut t = 0;
        let mut wait = 0;
        let mut i = 0;

        while wait > 0 || i < customers.len() {
            wait += if i < customers.len() { customers[i] } else { 0 };
            let up = std::cmp::min(4, wait);
            wait -= up;
            i += 1;
            t += up * boarding_cost - running_cost;

            if t > mx {
                mx = t;
                ans = i as i32;
            }
        }

        ans
    }
}
