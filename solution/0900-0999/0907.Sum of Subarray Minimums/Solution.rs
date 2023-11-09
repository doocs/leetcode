const MOD: i64 = (1e9 as i64) + 7;

impl Solution {
    pub fn sum_subarray_mins(arr: Vec<i32>) -> i32 {
        let n: usize = arr.len();
        let mut ret: i64 = 0;
        let mut left: Vec<i32> = vec![-1; n];
        let mut right: Vec<i32> = vec![n as i32; n];
        // Index stack, store the index of the value in the given array
        let mut stack: Vec<i32> = Vec::new();

        // Find the first element that's less than the current value for the left side
        // The default value of which is -1
        for i in 0..n {
            while !stack.is_empty() && arr[*stack.last().unwrap() as usize] >= arr[i] {
                stack.pop();
            }
            if !stack.is_empty() {
                left[i] = *stack.last().unwrap();
            }
            stack.push(i as i32);
        }

        stack.clear();

        // Find the first element that's less or equal than the current value for the right side
        // The default value of which is n
        for i in (0..n).rev() {
            while !stack.is_empty() && arr[*stack.last().unwrap() as usize] > arr[i] {
                stack.pop();
            }
            if !stack.is_empty() {
                right[i] = *stack.last().unwrap();
            }
            stack.push(i as i32);
        }

        // Traverse the array, to find the sum
        for i in 0..n {
            ret +=
                ((((right[i] - (i as i32)) * ((i as i32) - left[i])) as i64) * (arr[i] as i64)) %
                MOD;
            ret %= MOD;
        }

        (ret % (MOD as i64)) as i32
    }
}
