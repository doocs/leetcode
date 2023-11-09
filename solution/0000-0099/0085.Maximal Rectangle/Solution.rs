impl Solution {
    #[allow(dead_code)]
    pub fn maximal_rectangle(matrix: Vec<Vec<char>>) -> i32 {
        let n = matrix[0].len();
        let mut heights = vec![0; n];
        let mut ret = -1;

        for row in &matrix {
            Self::array_builder(row, &mut heights);
            ret = std::cmp::max(ret, Self::largest_rectangle_area(heights.clone()));
        }

        ret
    }

    /// Helper function, build the heights array according to the input
    #[allow(dead_code)]
    fn array_builder(input: &Vec<char>, heights: &mut Vec<i32>) {
        for (i, &c) in input.iter().enumerate() {
            heights[i] += match c {
                '1' => 1,
                '0' => {
                    heights[i] = 0;
                    0
                }
                _ => panic!("This is impossible"),
            };
        }
    }

    /// Helper function, see: https://leetcode.com/problems/largest-rectangle-in-histogram/ for details
    #[allow(dead_code)]
    fn largest_rectangle_area(heights: Vec<i32>) -> i32 {
        let n = heights.len();
        let mut left = vec![-1; n];
        let mut right = vec![-1; n];
        let mut stack: Vec<(usize, i32)> = Vec::new();
        let mut ret = -1;

        // Build left vector
        for (i, h) in heights.iter().enumerate() {
            while !stack.is_empty() && stack.last().unwrap().1 >= *h {
                stack.pop();
            }
            if stack.is_empty() {
                left[i] = -1;
            } else {
                left[i] = stack.last().unwrap().0 as i32;
            }
            stack.push((i, *h));
        }

        stack.clear();

        // Build right vector
        for (i, h) in heights.iter().enumerate().rev() {
            while !stack.is_empty() && stack.last().unwrap().1 >= *h {
                stack.pop();
            }
            if stack.is_empty() {
                right[i] = n as i32;
            } else {
                right[i] = stack.last().unwrap().0 as i32;
            }
            stack.push((i, *h));
        }

        // Calculate the max area
        for (i, h) in heights.iter().enumerate() {
            ret = std::cmp::max(ret, (right[i] - left[i] - 1) * *h);
        }

        ret
    }
}
