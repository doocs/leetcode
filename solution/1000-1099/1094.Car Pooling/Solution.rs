impl Solution {
    pub fn car_pooling(trips: Vec<Vec<i32>>, capacity: i32) -> bool {
        let mx = trips.iter().map(|e| e[2]).max().unwrap_or(0) as usize;
        let mut d = vec![0; mx + 1];
        for trip in &trips {
            let (x, f, t) = (trip[0], trip[1] as usize, trip[2] as usize);
            d[f] += x;
            d[t] -= x;
        }
        d.iter()
            .scan(0, |acc, &x| {
                *acc += x;
                Some(*acc)
            })
            .all(|s| s <= capacity)
    }
}
