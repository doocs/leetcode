impl Solution {
    pub fn average_waiting_time(customers: Vec<Vec<i32>>) -> f64 {
        let mut spent_time = 0;
        let mut time_waited = 0;
        for customer in &customers {
            let a = customer[0] as i64;
            let b = customer[1] as i64;
            time_waited = time_waited.max(a) + b;
            spent_time += time_waited - a;
        }
        spent_time as f64 / (customers.len() as f64)
    }
}
