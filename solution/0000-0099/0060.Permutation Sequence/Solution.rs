impl Solution {
    pub fn get_permutation(n: i32, k: i32) -> String {
        let mut k = k;
        let mut result = String::new();
        let mut factorial = vec![1; n as usize];
        for i in 1..n as usize {
            factorial[i] = factorial[i - 1] * (i as i32);
        }
        let mut vis = vec![false; n as usize + 1];

        for i in 0..n as usize {
            let cnt = factorial[(n as usize) - i - 1];
            for j in 1..=n {
                if vis[j as usize] {
                    continue;
                }
                if k > cnt {
                    k -= cnt;
                } else {
                    result.push_str(&j.to_string());
                    vis[j as usize] = true;
                    break;
                }
            }
        }

        result
    }
}
