impl Solution {
    pub fn min_deletion_size(strs: Vec<String>) -> i32 {
        let n = strs.len();
        let m = strs[0].len();
        let mut st = vec![false; n - 1];
        let mut ans = 0;

        for j in 0..m {
            let mut must_del = false;
            for i in 0..n - 1 {
                if !st[i] && strs[i].as_bytes()[j] > strs[i + 1].as_bytes()[j] {
                    must_del = true;
                    break;
                }
            }
            if must_del {
                ans += 1;
            } else {
                for i in 0..n - 1 {
                    if !st[i] && strs[i].as_bytes()[j] < strs[i + 1].as_bytes()[j] {
                        st[i] = true;
                    }
                }
            }
        }

        ans
    }
}
