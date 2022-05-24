impl Solution {
    pub fn maximum_bags(capacity: Vec<i32>, rocks: Vec<i32>, mut additional_rocks: i32) -> i32 {
        let n = capacity.len();
        let mut diffs = vec![0; n];
        for i in 0..n {
            diffs[i] = capacity[i] - rocks[i];
        }
        diffs.sort();
        for i in 0..n {
            if diffs[i] > additional_rocks {
                return i as i32;
            }
            additional_rocks -= diffs[i];
        }
        n as i32
    }
}
