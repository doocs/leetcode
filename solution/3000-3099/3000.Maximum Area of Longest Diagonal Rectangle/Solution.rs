impl Solution {
    pub fn area_of_max_diagonal(dimensions: Vec<Vec<i32>>) -> i32 {
        let mut ans = 0;
        let mut mx = 0;
        for d in dimensions {
            let l = d[0];
            let w = d[1];
            let t = l * l + w * w;
            if mx < t {
                mx = t;
                ans = l * w;
            } else if mx == t {
                ans = ans.max(l * w);
            }
        }
        ans
    }
}
