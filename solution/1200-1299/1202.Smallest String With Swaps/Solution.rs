impl Solution {
    #[allow(dead_code)]
    pub fn smallest_string_with_swaps(s: String, pairs: Vec<Vec<i32>>) -> String {
        let n = s.as_bytes().len();
        let s = s.as_bytes();
        let mut disjoint_set: Vec<usize> = vec![0; n];
        let mut str_vec: Vec<Vec<u8>> = vec![Vec::new(); n];
        let mut ret_str = String::new();

        // Initialize the disjoint set
        for i in 0..n {
            disjoint_set[i] = i;
        }

        // Union the pairs
        for pair in pairs {
            Self::union(pair[0] as usize, pair[1] as usize, &mut disjoint_set);
        }

        // Initialize the return vector
        for (i, c) in s.iter().enumerate() {
            let p_c = Self::find(i, &mut disjoint_set);
            str_vec[p_c].push(*c);
        }

        // Sort the return vector in reverse order
        for cur_vec in &mut str_vec {
            cur_vec.sort();
            cur_vec.reverse();
        }

        // Construct the return string
        for i in 0..n {
            let index = Self::find(i, &mut disjoint_set);
            ret_str.push(str_vec[index].last().unwrap().clone() as char);
            str_vec[index].pop();
        }

        ret_str
    }

    #[allow(dead_code)]
    fn find(x: usize, d_set: &mut Vec<usize>) -> usize {
        if d_set[x] != x {
            d_set[x] = Self::find(d_set[x], d_set);
        }
        d_set[x]
    }

    #[allow(dead_code)]
    fn union(x: usize, y: usize, d_set: &mut Vec<usize>) {
        let p_x = Self::find(x, d_set);
        let p_y = Self::find(y, d_set);
        d_set[p_x] = p_y;
    }
}
