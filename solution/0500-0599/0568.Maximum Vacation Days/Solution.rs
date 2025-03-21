impl Solution {
    pub fn max_vacation_days(flights: Vec<Vec<i32>>, days: Vec<Vec<i32>>) -> i32 {
        let n = flights.len();
        let k = days[0].len();
        let inf = i32::MIN;

        let mut f = vec![vec![inf; n]; k + 1];
        f[0][0] = 0;

        for step in 1..=k {
            for j in 0..n {
                f[step][j] = f[step - 1][j];
                for i in 0..n {
                    if flights[i][j] == 1 {
                        f[step][j] = f[step][j].max(f[step - 1][i]);
                    }
                }
                f[step][j] += days[j][step - 1];
            }
        }

        *f[k].iter().max().unwrap()
    }
}
