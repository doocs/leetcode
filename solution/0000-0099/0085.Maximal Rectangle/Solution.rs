impl Solution {
    pub fn maximal_rectangle(matrix: Vec<Vec<char>>) -> i32 {
        let n = matrix[0].len();
        let mut heights = vec![0; n];
        let mut ans = 0;

        for row in matrix {
            for j in 0..n {
                if row[j] == '1' {
                    heights[j] += 1;
                } else {
                    heights[j] = 0;
                }
            }
            ans = ans.max(Self::largest_rectangle_area(&heights));
        }

        ans
    }

    fn largest_rectangle_area(heights: &Vec<i32>) -> i32 {
        let mut res = 0;
        let n = heights.len();
        let mut stk: Vec<usize> = Vec::new();
        let mut left = vec![0; n];
        let mut right = vec![n; n];

        for i in 0..n {
            while let Some(&top) = stk.last() {
                if heights[top] >= heights[i] {
                    right[top] = i;
                    stk.pop();
                } else {
                    break;
                }
            }
            left[i] = if stk.is_empty() { -1 } else { stk[stk.len() - 1] as i32 };
            stk.push(i);
        }

        for i in 0..n {
            res = res.max(heights[i] * (right[i] as i32 - left[i] - 1));
        }

        res
    }
}
