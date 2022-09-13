impl Solution {
    pub fn maximum_swap(mut num: i32) -> i32 {
        let mut list = {
            let mut res = Vec::new();
            while num != 0 {
                res.push(num % 10);
                num /= 10;
            }
            res
        };
        let n = list.len();
        let idx = {
            let mut i = 0;
            (0..n)
                .map(|j| {
                    if list[j] > list[i] {
                        i = j;
                    }
                    i
                })
                .collect::<Vec<usize>>()
        };
        for i in (0..n).rev() {
            if list[i] != list[idx[i]] {
                list.swap(i, idx[i]);
                break;
            }
        }
        let mut res = 0;
        for i in list.iter().rev() {
            res = res * 10 + i;
        }
        res
    }
}
