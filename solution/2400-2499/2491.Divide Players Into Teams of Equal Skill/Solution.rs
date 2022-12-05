impl Solution {
    pub fn divide_players(mut skill: Vec<i32>) -> i64 {
        let n = skill.len();
        skill.sort();
        let target = skill[0] + skill[n - 1];
        let mut ans = 0;
        for i in 0..n >> 1 {
            if skill[i] + skill[n - 1 - i] != target {
                return -1;
            }
            ans += (skill[i] * skill[n - 1 - i]) as i64;
        }
        ans
    }
}
