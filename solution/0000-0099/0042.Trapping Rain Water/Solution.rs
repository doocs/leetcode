impl Solution {
    #[allow(dead_code)]
    pub fn trap(height: Vec<i32>) -> i32 {
        let n = height.len();
        let mut left_vec: Vec<i32> = vec![0; n];
        let mut right_vec: Vec<i32> = vec![0; n];

        left_vec[0] = height[0];
        right_vec[n - 1] = height[n - 1];

        // Initialize the left & right vector
        for i in 1..n {
            left_vec[i] = std::cmp::max(left_vec[i - 1], height[i]);
            right_vec[n - i - 1] = std::cmp::max(right_vec[n - i], height[n - i - 1]);
        }

        let mut ret = 0;

        // Calculate the ans
        for i in 0..n {
            ret += std::cmp::min(left_vec[i], right_vec[i]) - height[i];
        }

        ret
    }
}