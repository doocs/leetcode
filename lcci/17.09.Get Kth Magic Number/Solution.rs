impl Solution {
    pub fn get_kth_magic_number(k: i32) -> i32 {
        let k = k as usize;
        let mut dp = vec![1];
        let mut index = [0, 0, 0];
        for _ in 1..k {
            let a = dp[index[0]] * 3;
            let b = dp[index[1]] * 5;
            let c = dp[index[2]] * 7;
            let num = a.min(b.min(c));
            dp.push(num);
            if a == num {
                index[0] += 1;
            }
            if b == num {
                index[1] += 1;
            }
            if c == num {
                index[2] += 1;
            }
        }
        dp[k - 1]
    }
}
