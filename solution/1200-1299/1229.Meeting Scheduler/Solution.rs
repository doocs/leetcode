impl Solution {
    #[allow(dead_code)]
    pub fn min_available_duration(
        slots1: Vec<Vec<i32>>,
        slots2: Vec<Vec<i32>>,
        duration: i32,
    ) -> Vec<i32> {
        let mut slots1 = slots1;
        let mut slots2 = slots2;

        // First sort the two vectors based on the beginning time
        slots1.sort_by(|lhs, rhs| lhs[0].cmp(&rhs[0]));
        slots2.sort_by(|lhs, rhs| lhs[0].cmp(&rhs[0]));

        // Then traverse the two vector
        let mut i: usize = 0;
        let mut j: usize = 0;
        let N = slots1.len();
        let M = slots2.len();

        while i < N && j < M {
            let (start, end) = (slots1[i][0], slots1[i][1]);
            while j < M && slots2[j][0] < end {
                // If still in the scope
                let (cur_x, cur_y) = (
                    std::cmp::max(start, slots2[j][0]),
                    std::cmp::min(end, slots2[j][1]),
                );
                if cur_y - cur_x >= duration {
                    return vec![cur_x, cur_x + duration];
                }
                // Otherwise, keep iterating
                if slots1[i][1] < slots2[j][1] {
                    // Update i then
                    break;
                }
                j += 1;
            }
            i += 1;
        }

        // The default is an empty vector
        vec![]
    }
}
