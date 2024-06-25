impl Solution {
    #[allow(dead_code)]
    pub fn can_attend_meetings(intervals: Vec<Vec<i32>>) -> bool {
        if intervals.len() == 1 {
            return true;
        }

        let mut intervals = intervals;

        // Sort the intervals vector
        intervals.sort_by(|lhs, rhs| lhs[0].cmp(&rhs[0]));

        let mut end = -1;

        // Begin traverse
        for p in &intervals {
            if end == -1 {
                // This is the first pair
                end = p[1];
                continue;
            }
            if p[0] < end {
                return false;
            }
            end = p[1];
        }

        true
    }
}
