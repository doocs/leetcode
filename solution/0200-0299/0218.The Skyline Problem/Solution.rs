impl Solution {
    pub fn get_skyline(buildings: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let mut skys: Vec<Vec<i32>> = vec![];
        let mut lines = vec![];
        for building in buildings.iter() {
            lines.push(building[0]);
            lines.push(building[1]);
        }
        lines.sort_unstable();
        let mut pq = std::collections::BinaryHeap::new();
        let (mut city, n) = (0, buildings.len());

        for line in lines {
            while city < n && buildings[city][0] <= line && buildings[city][1] > line {
                pq.push((buildings[city][2], buildings[city][1]));
                city += 1;
            }
            while !pq.is_empty() && pq.peek().unwrap().1 <= line {
                pq.pop();
            }
            let mut high = 0;
            if !pq.is_empty() {
                high = pq.peek().unwrap().0;
            }
            if !skys.is_empty() && skys.last().unwrap()[1] == high {
                continue;
            }
            skys.push(vec![line, high]);
        }
        skys
    }
}
