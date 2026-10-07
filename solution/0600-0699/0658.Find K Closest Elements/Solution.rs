impl Solution {
    pub fn find_closest_elements(arr: Vec<i32>, k: i32, x: i32) -> Vec<i32> {
        let mut arr = arr;
        arr.sort_by_key(|&v| ((v - x).abs(), v));
        arr.truncate(k as usize);
        arr.sort();
        arr
    }
}
