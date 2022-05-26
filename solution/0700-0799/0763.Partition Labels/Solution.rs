impl Solution {
    pub fn partition_labels(s: String) -> Vec<i32> {
        let n = s.len();
        let bytes = s.as_bytes();
        let mut inx_arr = [0; 26];
        for i in 0..n {
            inx_arr[(bytes[i] - b'a') as usize] = i;
        }
        let mut res = vec![];
        let mut left = 0;
        let mut right = 0;
        for i in 0..n {
            right = right.max(inx_arr[(bytes[i] - b'a') as usize]);
            if right == i {
                res.push((right - left + 1) as i32);
                left = i + 1;
            }
        }
        res
    }
}
