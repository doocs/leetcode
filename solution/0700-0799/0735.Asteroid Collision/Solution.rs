impl Solution {
    #[allow(dead_code)]
    pub fn asteroid_collision(asteroids: Vec<i32>) -> Vec<i32> {
        let mut ret_stack = Vec::new();

        for &a in &asteroids {
            if ret_stack.is_empty() {
                ret_stack.push(a);
                continue;
            }
            if a > 0 {
                ret_stack.push(a);
                continue;
            }
            // Otherwise, peek the top element in the current stack
            if a < 0 {
                if *ret_stack.last().unwrap() < 0 {
                    ret_stack.push(a);
                    continue;
                }
                let mut explode_flag = false;
                while !ret_stack.is_empty() && *ret_stack.last().unwrap() > 0 {
                    let cur_res = *ret_stack.last().unwrap() + a;
                    if cur_res < 0 {
                        // |a| > |top()|
                        assert_ne!(ret_stack.pop(), None);
                    } else if cur_res == 0 {
                        // |a| == |top()|
                        explode_flag = true;
                        assert_ne!(ret_stack.pop(), None);
                        break;
                    } else {
                        // |a| < |top()|
                        explode_flag = true;
                        break;
                    }
                }
                if !explode_flag {
                    ret_stack.push(a);
                }
                continue;
            }
            assert!(false); // This is impossible
        }

        ret_stack
    }
}