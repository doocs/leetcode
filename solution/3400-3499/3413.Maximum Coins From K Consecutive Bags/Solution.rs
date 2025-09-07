use std::cmp::{max, min};
fn f(coins: &[(i32, i32, i32)], k: i32) -> i64 {
    let mut result = 0;
    let mut sum = 0;
    let mut r = 0;

    for l in 0..coins.len() {
        while r < coins.len() && coins[l].0 + k > coins[r].1 {
            sum += (coins[r].1 - coins[r].0 + 1) as i64 * coins[r].2 as i64;
            r += 1;
        }
        result = max(result, sum);
        if r == coins.len() {
            break;
        }
        let cnt = coins[l].0 + k - coins[r].0;
        result = max(result, sum + max(cnt, 0) as i64 * coins[r].2 as i64);
        sum -= (coins[l].1 - coins[l].0 + 1) as i64 * coins[l].2 as i64;
    }

    result
}

impl Solution {
    pub fn maximum_coins(mut coins: Vec<Vec<i32>>, k: i32) -> i64 {
        let mut coins: Vec<_> = coins.into_iter().map(|xs| (xs[0], xs[1], xs[2])).collect();
        coins.sort();
        let a = f(&coins, k);
        coins.reverse();
        for i in 0..coins.len() {
            (coins[i].0, coins[i].1) = (-coins[i].1, -coins[i].0);
        }
        let b = f(&coins, k);

        max(a, b)
    }
}
