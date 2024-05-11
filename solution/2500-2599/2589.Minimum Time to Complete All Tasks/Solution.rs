impl Solution {
    pub fn find_minimum_time(tasks: Vec<Vec<i32>>) -> i32 {
        let mut tasks = tasks;
        tasks.sort_by(|a, b| a[1].cmp(&b[1]));
        let mut vis = vec![0; 2010];
        let mut ans = 0;

        for task in tasks {
            let start = task[0] as usize;
            let end = task[1] as usize;
            let mut duration = task[2] - vis[start..=end].iter().sum::<i32>();
            let mut i = end;

            while i >= start && duration > 0 {
                if vis[i] == 0 {
                    duration -= 1;
                    vis[i] = 1;
                    ans += 1;
                }
                i -= 1;
            }
        }

        ans
    }
}
