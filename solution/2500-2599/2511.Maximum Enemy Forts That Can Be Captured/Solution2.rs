impl Solution {
    pub fn capture_forts(forts: Vec<i32>) -> i32 {
        let mut ans = 0;
        let mut i = 0;

        while
            let Some((idx, &value)) = forts
                .iter()
                .enumerate()
                .skip(i)
                .find(|&(_, &x)| x != 0)
        {
            if
                let Some((jdx, _)) = forts
                    .iter()
                    .enumerate()
                    .skip(idx + 1)
                    .find(|&(_, &x)| x != 0)
            {
                if value + forts[jdx] == 0 {
                    ans = ans.max(jdx - idx - 1);
                }
            }
            i = idx + 1;
        }

        ans as i32
    }
}
