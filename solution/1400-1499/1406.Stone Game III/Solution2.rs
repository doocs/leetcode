impl Solution {
    pub fn stone_game_iii(stone_value: Vec<i32>) -> String {
        let n = stone_value.len();
        let mut f = vec![0; n + 1];
        for i in (0..n).rev() {
            let mut ans = i32::MIN;
            let mut s = 0;
            for j in i..(i + 3).min(n) {
                s += stone_value[j];
                ans = ans.max(s - f[j + 1]);
            }
            f[i] = ans;
        }
        let res = f[0];
        if res == 0 {
            "Tie".to_string()
        } else if res > 0 {
            "Alice".to_string()
        } else {
            "Bob".to_string()
        }
    }
}
