impl Solution {
    pub fn smallest_range(nums: Vec<Vec<i32>>) -> Vec<i32> {
        let mut t = vec![];
        for (i, x) in nums.iter().enumerate() {
            for &v in x {
                t.push((v, i));
            }
        }
        t.sort_unstable();
        let (mut ans, n) = (vec![-1000000, 1000000], nums.len());
        let mut j = 0;
        let mut cnt = std::collections::HashMap::new();

        for (b, v) in t.iter() {
            let (b, v) = (*b, *v);
            if let Some(x) = cnt.get_mut(&v) {
                *x += 1;
            } else {
                cnt.insert(v, 1);
            }
            while cnt.len() == n {
                let (a, w) = t[j];
                let x = b - a - (ans[1] - ans[0]);
                if x < 0 || (x == 0 && a < ans[0]) {
                    ans = vec![a, b];
                }
                if let Some(x) = cnt.get_mut(&w) {
                    *x -= 1;
                }
                if cnt[&w] == 0 {
                    cnt.remove(&w);
                }
                j += 1;
            }
        }
        ans
    }
}
