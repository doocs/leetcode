impl Solution {
    pub fn earliest_full_bloom(plant_time: Vec<i32>, grow_time: Vec<i32>) -> i32 {
        let mut idx: Vec<usize> = (0..plant_time.len()).collect();
        idx.sort_by_key(|&i| -&grow_time[i]);
        let mut ans = 0;
        let mut t = 0;
        for &i in &idx {
            t += plant_time[i];
            ans = ans.max(t + grow_time[i]);
        }
        ans
    }
}