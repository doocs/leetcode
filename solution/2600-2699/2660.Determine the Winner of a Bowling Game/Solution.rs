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

        let p1 = f(&player1);
        let p2 = f(&player2);
        if p1 > p2 {
            return 1;
        } else if p1 < p2 {
            return 2;
        } else {
            0
        }
    }
}
