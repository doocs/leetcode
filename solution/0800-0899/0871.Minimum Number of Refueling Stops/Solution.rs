use std::collections::BinaryHeap;

impl Solution {
    pub fn min_refuel_stops(target: i32, mut start_fuel: i32, mut stations: Vec<Vec<i32>>) -> i32 {
        let mut pq = BinaryHeap::new();
        let mut ans = 0;
        let mut pre = 0;

        stations.push(vec![target, 0]);

        for station in stations {
            let pos = station[0];
            let fuel = station[1];
            let dist = pos - pre;
            start_fuel -= dist;

            while start_fuel < 0 && !pq.is_empty() {
                start_fuel += pq.pop().unwrap();
                ans += 1;
            }

            if start_fuel < 0 {
                return -1;
            }

            pq.push(fuel);
            pre = pos;
        }

        ans
    }
}
