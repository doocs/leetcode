impl Solution {
    #[allow(dead_code)]
    pub fn max_run_time(n: i32, batteries: Vec<i32>) -> i64 {
        // First sort the batteries
        let mut batteries = batteries;
        let m = batteries.len() as i32;
        batteries.sort();

        let mut extra_sum: i64 = 0;
        for i in 0..(m - n) as usize {
            extra_sum += batteries[i] as i64;
        }

        let mut i = (m - n) as usize;
        let mut cur_height = batteries[i];
        let mut ret = cur_height as i64;
        while extra_sum != 0 {
            if i + 1 == (m as usize) {
                assert!(cur_height == *batteries.last().unwrap());
                ret += extra_sum / (n as i64);
                break;
            }

            if batteries[i] == batteries[i + 1] {
                i += 1;
                continue;
            }

            let diff = extra_sum / ((i - ((m - n) as usize) + 1) as i64);

            if (cur_height as i64) + diff <= (batteries[i + 1] as i64) {
                ret = (cur_height as i64) + diff;
                break;
            } else {
                extra_sum -=
                    ((batteries[i + 1] - batteries[i]) as i64) *
                    ((i - ((m - n) as usize) + 1) as i64);
                ret = batteries[i + 1] as i64;
            }

            i += 1;
            if i != (m as usize) {
                cur_height = batteries[i];
            }
        }

        ret
    }
}
