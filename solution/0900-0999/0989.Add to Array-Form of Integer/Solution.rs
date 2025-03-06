impl Solution {
    pub fn add_to_array_form(num: Vec<i32>, k: i32) -> Vec<i32> {
        let mut ans = Vec::new();
        let mut k = k;
        let mut i = num.len() as i32 - 1;

        while i >= 0 || k > 0 {
            if i >= 0 {
                k += num[i as usize];
            }
            ans.push(k % 10);
            k /= 10;
            i -= 1;
        }

        ans.reverse();
        ans
    }
}
