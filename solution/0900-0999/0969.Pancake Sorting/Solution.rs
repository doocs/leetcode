impl Solution {
    pub fn pancake_sort(mut arr: Vec<i32>) -> Vec<i32> {
        let mut res = vec![];
        for n in (1..arr.len()).rev() {
            let mut max_idx = 0;
            for idx in 0..=n {
                if arr[max_idx] < arr[idx] {
                    max_idx = idx;
                }
            }
            if max_idx != n {
                if max_idx != 0 {
                    arr[..=max_idx].reverse();
                    res.push(max_idx as i32 + 1);
                }
                arr[..=n].reverse();
                res.push(n as i32 + 1);
            }
        }
        res
    }
}
