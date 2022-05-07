use std::collections::VecDeque;
impl Solution {
    pub fn min_mutation(start: String, end: String, mut bank: Vec<String>) -> i32 {
        let mut queue = vec![start].into_iter().collect::<VecDeque<String>>();
        let mut res = 0;
        while !queue.is_empty() {
            let n = queue.len();
            for _ in 0..n {
                let s1 = queue.pop_front().unwrap();
                if s1 == end {
                    return res;
                }

                for i in (0..bank.len()).rev() {
                    let s1 = s1.as_bytes();
                    let s2 = bank[i].as_bytes();
                    let mut count = 0;
                    for j in 0..8 {
                        if s1[j] != s2[j] {
                            count += 1;
                        }
                    }
                    if count <= 1 {
                        queue.push_back(bank.remove(i));
                    }
                }
            }
            res += 1;
        }
        -1
    }
}
