impl Solution {
    pub fn maximum_bags(mut capacity: Vec<i32>, rocks: Vec<i32>, mut additional_rocks: i32) -> i32 {
        for i in 0..rocks.len() {
            capacity[i] -= rocks[i];
        }
        capacity.sort();
        for i in 0..capacity.len() {
            additional_rocks -= capacity[i];
            if additional_rocks < 0 {
                return i as i32;
            }
        }
        capacity.len() as i32
    }
}
