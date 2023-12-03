impl Solution {
    pub fn is_winner(player1: Vec<i32>, player2: Vec<i32>) -> i32 {
        let f = |arr: &Vec<i32>| -> i32 {
            let mut s = 0;
            for i in 0..arr.len() {
                let mut k = 1;
                if (i > 0 && arr[i - 1] == 10) || (i > 1 && arr[i - 2] == 10) {
                    k = 2;
                }
                s += k * arr[i];
            }
            s
        };

        let a = f(&player1);
        let b = f(&player2);
        if a > b {
            1
        } else if a < b {
            2
        } else {
            0
        }
    }
}
