impl Solution {
    pub fn merge_similar_items(items1: Vec<Vec<i32>>, items2: Vec<Vec<i32>>) -> Vec<Vec<i32>> {
        let mut count = [0; 1001];
        for item in items1.iter() {
            count[item[0] as usize] += item[1];
        }
        for item in items2.iter() {
            count[item[0] as usize] += item[1];
        }
        count
            .iter()
            .enumerate()
            .filter_map(|(i, &v)| {
                if v == 0 {
                    return None;
                }
                Some(vec![i as i32, v])
            })
            .collect()
    }
}
