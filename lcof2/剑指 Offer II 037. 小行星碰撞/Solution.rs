impl Solution {
    #[allow(dead_code)]
    pub fn asteroid_collision(asteroids: Vec<i32>) -> Vec<i32> {
        let mut stk = Vec::new();
        for &x in &asteroids {
            if x > 0 {
                stk.push(x);
            } else {
                while !stk.is_empty() && *stk.last().unwrap() > 0 && *stk.last().unwrap() < -x {
                    stk.pop();
                }
                if !stk.is_empty() && *stk.last().unwrap() == -x {
                    stk.pop();
                } else if stk.is_empty() || *stk.last().unwrap() < 0 {
                    stk.push(x);
                }
            }
        }
        stk
    }
}
