impl Solution {
    pub fn find_judge(n: i32, trust: Vec<Vec<i32>>) -> i32 {
        let mut cnt1 = vec![0; (n + 1) as usize];
        let mut cnt2 = vec![0; (n + 1) as usize];

        for t in trust.iter() {
            let a = t[0] as usize;
            let b = t[1] as usize;
            cnt1[a] += 1;
            cnt2[b] += 1;
        }

        for i in 1..=n as usize {
            if cnt1[i] == 0 && cnt2[i] == (n as usize) - 1 {
                return i as i32;
            }
        }

        -1
    }
}
