use std::collections::HashMap;
impl Solution {
    pub fn can_make_arithmetic_progression(arr: Vec<i32>) -> bool {
        let n = arr.len() as i32;
        let mut min = i32::MAX;
        let mut max = i32::MIN;
        let mut map = HashMap::new();
        for &num in arr.iter() {
            *map.entry(num).or_insert(0) += 1;
            min = min.min(num);
            max = max.max(num);
        }
        if min == max {
            return true;
        }
        if (max - min) % (n - 1) != 0 {
            return false;
        }
        let diff = (max - min) / (n - 1);
        let mut k = min;
        while k <= max {
            if *map.get(&k).unwrap_or(&0) != 1 {
                return false;
            }
            k += diff;
        }
        true
    }
}
