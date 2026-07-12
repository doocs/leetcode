impl Solution {
    pub fn array_rank_transform(arr: Vec<i32>) -> Vec<i32> {
        let mut q1 = arr.clone();
        q1.sort_unstable();
        q1.dedup();
        arr.iter()
            .map(|q2| q1.binary_search(q2).unwrap() as i32 + 1)
            .collect()
    }
}
