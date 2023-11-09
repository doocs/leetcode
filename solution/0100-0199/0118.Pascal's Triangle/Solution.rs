impl Solution {
    #[allow(dead_code)]
    pub fn generate(num_rows: i32) -> Vec<Vec<i32>> {
        let mut ret_vec: Vec<Vec<i32>> = Vec::new();
        let mut cur_vec: Vec<i32> = Vec::new();

        for i in 0..num_rows as usize {
            let mut new_vec: Vec<i32> = vec![1; i + 1];
            for j in 1..i {
                new_vec[j] = cur_vec[j - 1] + cur_vec[j];
            }
            cur_vec = new_vec.clone();
            ret_vec.push(new_vec);
        }

        ret_vec
    }
}
