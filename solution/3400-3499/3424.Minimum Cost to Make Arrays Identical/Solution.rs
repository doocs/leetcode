impl Solution {
    pub fn min_cost(mut arr: Vec<i32>, mut brr: Vec<i32>, k: i64) -> i64 {
        let c1: i64 = arr
            .iter()
            .zip(&brr)
            .map(|(a, b)| (*a - *b).abs() as i64)
            .sum();

        arr.sort_unstable();
        brr.sort_unstable();

        let c2: i64 = k + arr
            .iter()
            .zip(&brr)
            .map(|(a, b)| (*a - *b).abs() as i64)
            .sum::<i64>();

        c1.min(c2)
    }
}
