impl Solution {
    pub fn judge_point24(cards: Vec<i32>) -> bool {
        fn dfs(nums: Vec<f64>) -> bool {
            let n = nums.len();
            if n == 1 {
                return (nums[0] - 24.0).abs() < 1e-6;
            }
            for i in 0..n {
                for j in 0..n {
                    if i == j {
                        continue;
                    }
                    let mut nxt = Vec::new();
                    for k in 0..n {
                        if k != i && k != j {
                            nxt.push(nums[k]);
                        }
                    }
                    for op in 0..4 {
                        let mut nxt2 = nxt.clone();
                        match op {
                            0 => {
                                nxt2.push(nums[i] + nums[j]);
                            }
                            1 => {
                                nxt2.push(nums[i] - nums[j]);
                            }
                            2 => {
                                nxt2.push(nums[i] * nums[j]);
                            }
                            3 => {
                                if nums[j].abs() < 1e-6 {
                                    continue;
                                }
                                nxt2.push(nums[i] / nums[j]);
                            }
                            _ => {}
                        }
                        if dfs(nxt2) {
                            return true;
                        }
                    }
                }
            }
            false
        }

        let nums: Vec<f64> = cards.into_iter().map(|x| x as f64).collect();
        dfs(nums)
    }
}
