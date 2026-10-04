impl Solution {
    pub fn minimum_score(nums: Vec<i32>, edges: Vec<Vec<i32>>) -> i32 {
        let n = nums.len();
        let mut g = vec![vec![]; n];
        for e in edges.iter() {
            let a = e[0] as usize;
            let b = e[1] as usize;
            g[a].push(b);
            g[b].push(a);
        }
        let s = nums.iter().fold(0, |acc, &x| acc ^ x);

        fn component_xor(root: usize, ban: usize, g: &[Vec<usize>], nums: &[i32]) -> i32 {
            let n = nums.len();
            let mut sub = vec![0; n];
            let mut stk = vec![(root, ban, 0)];
            while let Some((i, fa, state)) = stk.pop() {
                if state == 0 {
                    stk.push((i, fa, 1));
                    for &j in &g[i] {
                        if j != fa {
                            stk.push((j, i, 0));
                        }
                    }
                } else {
                    let mut res = nums[i];
                    for &j in &g[i] {
                        if j != fa {
                            res ^= sub[j];
                        }
                    }
                    sub[i] = res;
                }
            }
            sub[root]
        }

        fn collect(
            root: usize,
            ban: usize,
            g: &[Vec<usize>],
            nums: &[i32],
            s: i32,
            s1: i32,
        ) -> i32 {
            let n = nums.len();
            let mut ans = i32::MAX;
            let mut sub = vec![0; n];
            let mut stk = vec![(root, ban, 0)];
            while let Some((i, fa, state)) = stk.pop() {
                if state == 0 {
                    stk.push((i, fa, 1));
                    for &j in &g[i] {
                        if j != fa {
                            stk.push((j, i, 0));
                        }
                    }
                } else {
                    let mut res = nums[i];
                    for &j in &g[i] {
                        if j != fa {
                            let s2 = sub[j];
                            res ^= s2;
                            let mx = (s ^ s1).max(s2).max(s1 ^ s2);
                            let mn = (s ^ s1).min(s2).min(s1 ^ s2);
                            ans = ans.min(mx - mn);
                        }
                    }
                    sub[i] = res;
                }
            }
            ans
        }

        let mut ans = i32::MAX;
        for i in 0..n {
            for &j in &g[i] {
                let s1 = component_xor(i, j, &g, &nums);
                ans = ans.min(collect(i, j, &g, &nums, s, s1));
            }
        }
        ans
    }
}
