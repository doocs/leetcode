impl Solution {
    pub fn separate_digits(nums: Vec<i32>) -> Vec<i32> {
        let mut ans = vec![];

        for n in nums {
            let mut t = vec![];
            let mut x = n;

            while x != 0 {
                t.push(x % 10);
                x /= 10;
            }

            for i in (0..t.len()).rev() {
                ans.push(t[i]);
            }
        }

        ans
    }
}
