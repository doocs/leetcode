impl Solution {
    pub fn asteroids_destroyed(mass: i32, mut asteroids: Vec<i32>) -> bool {
        let mut mass = mass as i64;
        asteroids.sort_unstable();
        for &x in &asteroids {
            if mass < x as i64 {
                return false;
            }
            mass += x as i64;
        }
        true
    }
}
