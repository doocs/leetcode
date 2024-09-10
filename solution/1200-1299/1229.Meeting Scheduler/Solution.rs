impl Solution {
    pub fn min_available_duration(
        mut slots1: Vec<Vec<i32>>,
        mut slots2: Vec<Vec<i32>>,
        duration: i32,
    ) -> Vec<i32> {
        slots1.sort_by_key(|slot| slot[0]);
        slots2.sort_by_key(|slot| slot[0]);

        let (mut i, mut j) = (0, 0);
        let (m, n) = (slots1.len(), slots2.len());

        while i < m && j < n {
            let (start1, end1) = (slots1[i][0], slots1[i][1]);
            let (start2, end2) = (slots2[j][0], slots2[j][1]);

            let start = start1.max(start2);
            let end = end1.min(end2);

            if end - start >= duration {
                return vec![start, start + duration];
            }

            if end1 < end2 {
                i += 1;
            } else {
                j += 1;
            }
        }

        vec![]
    }
}
