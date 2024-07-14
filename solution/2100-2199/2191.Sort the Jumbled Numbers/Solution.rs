impl Solution {
    pub fn sort_jumbled(mapping: Vec<i32>, nums: Vec<i32>) -> Vec<i32> {
        let f = |x: i32| -> i32 {
            if x == 0 {
                return mapping[0];
            }
            let mut y = 0;
            let mut k = 1;
            let mut num = x;
            while num != 0 {
                let v = mapping[(num % 10) as usize];
                y = k * v + y;
                k *= 10;
                num /= 10;
            }
            y
        };

        let n = nums.len();
        let mut arr: Vec<(i32, usize)> = Vec::with_capacity(n);
        for i in 0..n {
            arr.push((f(nums[i]), i));
        }
        arr.sort();

        let mut ans: Vec<i32> = Vec::with_capacity(n);
        for (_, i) in arr {
            ans.push(nums[i]);
        }
        ans
    }
}
