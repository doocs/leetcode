impl Solution {
    pub fn stone_game_iii(stone_value: Vec<i32>) -> String {
        let n = stone_value.len();
        let mut f = vec![None; n];

        fn dfs(i: usize, stone_value: &Vec<i32>, f: &mut Vec<Option<i32>>) -> i32 {
            if i >= stone_value.len() {
                return 0;
            }

            if let Some(v) = f[i] {
                return v;
            }

            let mut ans = i32::MIN;
            let mut s = 0;

            for j in i..(i + 3).min(stone_value.len()) {
                s += stone_value[j];
                ans = ans.max(s - dfs(j + 1, stone_value, f));
            }

            f[i] = Some(ans);
            ans
        }

        let res = dfs(0, &stone_value, &mut f);

        if res == 0 {
            "Tie".to_string()
        } else if res > 0 {
            "Alice".to_string()
        } else {
            "Bob".to_string()
        }
    }
}
