impl Solution {
    pub fn insert(intervals: Vec<Vec<i32>>, new_interval: Vec<i32>) -> Vec<Vec<i32>> {
        let mut merged_intervals = intervals.clone();
        merged_intervals.push(vec![new_interval[0], new_interval[1]]);
        // sort by elem[0]
        merged_intervals.sort_by_key(|elem| elem[0]);
        // merge interval
        let mut result = vec![];

        for interval in merged_intervals {
            if result.is_empty() {
                result.push(interval);
                continue;
            }

            let last_elem = result.last_mut().unwrap();
            if interval[0] > last_elem[1] {
                result.push(interval);
            } else {
                last_elem[1] = last_elem[1].max(interval[1]);
            }
        }
        result
    }
}
