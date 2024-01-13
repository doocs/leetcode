impl Solution {
    pub fn single_number(nums: Vec<i32>) -> i32 {
        let mut a = 0;
        let mut b = 0;

        for c in nums {
            let aa = (!a & b & c) | (a & !b & !c);
            let bb = !a & (b ^ c);
            a = aa;
            b = bb;
        }

        return b;
    }
}
