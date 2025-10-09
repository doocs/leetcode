impl Solution {
    pub fn maximum_energy(energy: Vec<i32>, k: i32) -> i32 {
        let n = energy.len();
        let mut ans = i32::MIN;
        for i in n - k as usize..n {
            let mut s = 0;
            let mut j = i as i32;
            while j >= 0 {
                s += energy[j as usize];
                ans = ans.max(s);
                j -= k;
            }
        }
        ans
    }
}
