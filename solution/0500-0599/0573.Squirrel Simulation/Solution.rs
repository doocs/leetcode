impl Solution {
    pub fn min_distance(
        height: i32,
        width: i32,
        tree: Vec<i32>,
        squirrel: Vec<i32>,
        nuts: Vec<Vec<i32>>,
    ) -> i32 {
        let (tr, tc) = (tree[0], tree[1]);
        let (sr, sc) = (squirrel[0], squirrel[1]);
        let s: i32 = nuts
            .iter()
            .map(|nut| (nut[0] - tr).abs() + (nut[1] - tc).abs())
            .sum::<i32>()
            * 2;

        let mut ans = i32::MAX;
        for nut in &nuts {
            let a = (nut[0] - tr).abs() + (nut[1] - tc).abs();
            let b = (nut[0] - sr).abs() + (nut[1] - sc).abs();
            ans = ans.min(s - a + b);
        }

        ans
    }
}
