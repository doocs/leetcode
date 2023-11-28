impl Solution {
    pub fn maximize_square_hole_area(n: i32, m: i32, h_bars: Vec<i32>, v_bars: Vec<i32>) -> i32 {
        let f = |nums: &mut Vec<i32>| -> i32 {
            let mut ans = 1;
            let mut cnt = 1;
            nums.sort();
            for i in 1..nums.len() {
                if nums[i] == nums[i - 1] + 1 {
                    cnt += 1;
                    ans = ans.max(cnt);
                } else {
                    cnt = 1;
                }
            }
            ans + 1
        };

        let mut h_bars = h_bars;
        let mut v_bars = v_bars;
        let x = f(&mut h_bars).min(f(&mut v_bars));
        x * x
    }
}
