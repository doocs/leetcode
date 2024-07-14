use std::{cmp::Reverse, collections::BinaryHeap};

impl Solution {
    #[allow(dead_code)]
    pub fn min_meeting_rooms(intervals: Vec<Vec<i32>>) -> i32 {
        // The min heap that stores the earliest ending time among all meeting rooms
        let mut pq = BinaryHeap::new();
        let mut intervals = intervals;
        let n = intervals.len();

        // Let's first sort the intervals vector
        intervals.sort_by(|lhs, rhs| lhs[0].cmp(&rhs[0]));

        // Push the first end time to the heap
        pq.push(Reverse(intervals[0][1]));

        // Traverse the intervals vector
        for i in 1..n {
            // Get the current top element from the heap
            if let Some(Reverse(end_time)) = pq.pop() {
                if end_time <= intervals[i][0] {
                    // If the end time is early than the current begin time
                    let new_end_time = intervals[i][1];
                    pq.push(Reverse(new_end_time));
                } else {
                    // Otherwise, push the end time back and we also need a new room
                    pq.push(Reverse(end_time));
                    pq.push(Reverse(intervals[i][1]));
                }
            }
        }

        pq.len() as i32
    }
}
