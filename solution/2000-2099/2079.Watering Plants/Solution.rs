impl Solution {
    pub fn watering_plants(plants: Vec<i32>, capacity: i32) -> i32 {
        let n = plants.len();
        let mut ans = 0;
        let mut water = capacity;
        for i in 0..n {
            if water < plants[i] {
                ans += 2 * i + 1;
                water = capacity - plants[i];
            } else {
                ans += 1;
                water -= plants[i];
            }
        }
        ans as i32
    }
}
