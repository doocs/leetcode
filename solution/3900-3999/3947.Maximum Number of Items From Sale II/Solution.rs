use std::collections::BTreeMap;

impl Solution {
    pub fn maximum_sale_items(items: Vec<Vec<i32>>, budget: i32) -> i32 {
        let n = items.len();
        let mut freq = vec![0i64; n + 1];
        let mut min_price = i64::MAX;

        for item in &items {
            freq[item[0] as usize] += 1;
            min_price = min_price.min(item[1] as i64);
        }

        let mut cnt = vec![0i64; n + 1];

        for f in 1..=n {
            for j in (f..=n).step_by(f) {
                cnt[f] += freq[j];
            }
            cnt[f] -= 1;
        }

        let mut deals = BTreeMap::<i64, i64>::new();

        for item in &items {
            let f = item[0] as usize;
            let p = item[1] as i64;

            if cnt[f] > 0 && p <= 2 * min_price {
                *deals.entry(p).or_insert(0) += cnt[f];
            }
        }

        let mut budget = budget as i64;
        let mut ans = 0i64;

        for (price, count) in deals {
            let take = count.min(budget / price);
            ans += take * 2;
            budget -= take * price;
        }

        (ans + budget / min_price) as i32
    }
}
