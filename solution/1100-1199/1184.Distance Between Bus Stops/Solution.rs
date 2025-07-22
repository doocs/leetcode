impl Solution {
    pub fn distance_between_bus_stops(distance: Vec<i32>, start: i32, destination: i32) -> i32 {
        let s: i32 = distance.iter().sum();
        let mut t = 0;
        let n = distance.len();
        let mut start = start as usize;
        let destination = destination as usize;

        while start != destination {
            t += distance[start];
            start = (start + 1) % n;
        }

        t.min(s - t)
    }
}
