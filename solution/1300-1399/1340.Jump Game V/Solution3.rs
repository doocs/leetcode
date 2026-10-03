impl Solution {
    pub fn max_jumps(arr: Vec<i32>, d: i32) -> i32 {
        let n = arr.len();
        let d = d as usize;

        let mut idx: Vec<usize> = (0..n).collect();
        idx.sort_by_key(|&i| arr[i]);

        let mut f = vec![1; n];

        for &i in &idx {
            let mut j = i as i32 - 1;
            while j >= 0 {
                let k = j as usize;

                if i - k > d || arr[k] >= arr[i] {
                    break;
                }

                f[i] = f[i].max(1 + f[k]);
                j -= 1;
            }

            let mut j = i + 1;
            while j < n {
                if j - i > d || arr[j] >= arr[i] {
                    break;
                }

                f[i] = f[i].max(1 + f[j]);
                j += 1;
            }
        }

        *f.iter().max().unwrap()
    }
}
