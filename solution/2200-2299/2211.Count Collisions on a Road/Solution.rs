impl Solution {
    pub fn count_collisions(directions: String) -> i32 {
        let s = directions.trim_start_matches('L').trim_end_matches('R');
        (s.len() as i32) - (s.matches('S').count() as i32)
    }
}
