impl Solution {
    pub fn average_waiting_time(customers: Vec<Vec<i32>>) -> f64 {
        let mut tot = 0.0;
        let mut t = 0;

        for e in customers.iter() {
            let a = e[0];
            let b = e[1];
            t = t.max(a) + b;
            tot += (t - a) as f64;
        }

        tot / customers.len() as f64
    }
}
