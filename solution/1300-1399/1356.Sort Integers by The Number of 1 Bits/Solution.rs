impl Solution {
    pub fn sort_by_bits(mut arr: Vec<i32>) -> Vec<i32> {
        let n = arr.len();

        for i in 0..n {
            arr[i] += arr[i].count_ones() as i32 * 100000;
        }

        arr.sort();

        for i in 0..n {
            arr[i] %= 100000;
        }

        arr
    }
}
