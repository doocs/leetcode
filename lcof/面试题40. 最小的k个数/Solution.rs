impl Solution {
    pub fn get_least_numbers(mut arr: Vec<i32>, k: i32) -> Vec<i32> {
        let k = k as usize;
        let mut start = 0;
        let mut end = arr.len();
        while start < end && end > k {
            let num = arr[start];
            let mut mark = start;
            for i in (start + 1)..end {
                if arr[i] < num {
                    mark += 1;
                    arr.swap(i, mark);
                }
            }
            arr.swap(start, mark);

            if mark <= k {
                start = mark + 1;
            } else {
                end = mark
            }
        }
        arr[0..k].to_vec()
    }
}
