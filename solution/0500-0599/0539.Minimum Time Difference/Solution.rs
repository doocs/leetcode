impl Solution {
    pub fn find_min_difference(time_points: Vec<String>) -> i32 {
        if time_points.len() > 1440 {
            return 0;
        }

        let n = time_points.len();
        let mut nums: Vec<i32> = Vec::with_capacity(n + 1);

        for time in time_points.iter() {
            let parts: Vec<i32> = time.split(':').map(|s| s.parse().unwrap()).collect();
            let minutes = parts[0] * 60 + parts[1];
            nums.push(minutes);
        }

        nums.sort();
        nums.push(nums[0] + 1440);

        let mut ans = i32::MAX;
        for i in 1..=n {
            ans = ans.min(nums[i] - nums[i - 1]);
        }

        ans
    }
}
