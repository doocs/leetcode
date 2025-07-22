use std::collections::HashMap;

struct RangeFreqQuery {
    g: HashMap<i32, Vec<usize>>,
}

impl RangeFreqQuery {
    fn new(arr: Vec<i32>) -> Self {
        let mut g = HashMap::new();
        for (i, &value) in arr.iter().enumerate() {
            g.entry(value).or_insert_with(Vec::new).push(i);
        }
        RangeFreqQuery { g }
    }

    fn query(&self, left: i32, right: i32, value: i32) -> i32 {
        if let Some(idx) = self.g.get(&value) {
            let l = idx.partition_point(|&x| x < left as usize);
            let r = idx.partition_point(|&x| x <= right as usize);
            return (r - l) as i32;
        }
        0
    }
}
