impl Solution {
    pub fn watering_plants(plants: Vec<i32>, capacity: i32) -> i32 {
        let mut ans = 0;
        let mut water = capacity;
        for (i, &p) in plants.iter().enumerate() {
            if water >= p {
                water -= p;
                ans += 1;
            } else {
                water = capacity - p;
                ans += (i as i32) * 2 + 1;
            }
        }
        ans
    }
}
