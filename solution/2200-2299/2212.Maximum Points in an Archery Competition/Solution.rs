impl Solution {
    fn dfs(alice_arrows: &Vec<i32>, mut res: Vec<i32>, count: i32, i: usize) -> Vec<i32> {
        if i == 0 || count == 0 {
            res[0] += count;
            return res;
        }
        let r1 = Self::dfs(alice_arrows, res.clone(), count, i - 1);
        if count > alice_arrows[i] {
            res[i] = alice_arrows[i] + 1;
            let r2 = Self::dfs(alice_arrows, res, count - alice_arrows[i] - 1, i - 1);
            if r2
                .iter()
                .enumerate()
                .map(|(i, v)| if v > &0 { i } else { 0 })
                .sum::<usize>()
                > r1.iter()
                    .enumerate()
                    .map(|(i, v)| if v > &0 { i } else { 0 })
                    .sum::<usize>()
            {
                return r2;
            }
        }
        r1
    }

    pub fn maximum_bob_points(num_arrows: i32, alice_arrows: Vec<i32>) -> Vec<i32> {
        Self::dfs(&alice_arrows, vec![0; 12], num_arrows, 11)
    }
}
