use std::collections::BTreeMap;

impl Solution {
    #[allow(dead_code)]
    pub fn full_bloom_flowers(flowers: Vec<Vec<i32>>, people: Vec<i32>) -> Vec<i32> {
        let n = people.len();

        // First sort the people vector based on the first item
        let mut people: Vec<(usize, i32)> = people
            .into_iter()
            .enumerate()
            .map(|x| x)
            .collect();

        people.sort_by(|lhs, rhs| {
            lhs.1.cmp(&rhs.1)
        });

        // Initialize the difference vector
        let mut diff = BTreeMap::new();
        let mut ret = vec![0; n];

        for f in flowers {
            let (left, right) = (f[0], f[1]);
            diff
                .entry(left)
                .and_modify(|x| *x += 1)
                .or_insert(1);

            diff
                .entry(right + 1)
                .and_modify(|x| *x -= 1)
                .or_insert(-1);
        }

        let mut sum = 0;
        let mut i = 0;
        for (k, v) in diff {
            while i < n && people[i].1 < k {
                ret[people[i].0] += sum;
                i += 1;
            }
            sum += v;
        }

        ret
    }
}