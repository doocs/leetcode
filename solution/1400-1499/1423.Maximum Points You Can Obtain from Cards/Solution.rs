impl Solution {
    pub fn max_score(card_points: Vec<i32>, k: i32) -> i32 {
        let (k, n) = (k as usize, card_points.len());
        let mut sum = card_points.iter().take(n - k).sum::<i32>();
        let mut min = sum;
        for i in 0..k {
            sum += card_points[n - k + i] - card_points[i];
            min = min.min(sum);
        }
        card_points.iter().sum::<i32>() - min
    }
}
