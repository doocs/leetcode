impl Solution {
    pub fn minimum_time(mut jobs: Vec<i32>, mut workers: Vec<i32>) -> i32 {
        jobs.sort();
        workers.sort();
        jobs.iter()
            .zip(workers.iter())
            .map(|(a, b)| (a + b - 1) / b)
            .max()
            .unwrap()
    }
}
