impl Solution {
    pub fn valid_path(n: i32, edges: Vec<Vec<i32>>, source: i32, destination: i32) -> bool {
        let mut disjoint_set: Vec<i32> = vec![0; n as usize];
        // Initialize the set
        for i in 0..n {
            disjoint_set[i as usize] = i;
        }

        // Traverse the edges
        for p_vec in &edges {
            let parent_one = Solution::find(p_vec[0], &mut disjoint_set);
            let parent_two = Solution::find(p_vec[1], &mut disjoint_set);
            disjoint_set[parent_one as usize] = parent_two;
        }

        let p_s = Solution::find(source, &mut disjoint_set);
        let p_d = Solution::find(destination, &mut disjoint_set);

        p_s == p_d
    }

    pub fn find(x: i32, d_set: &mut Vec<i32>) -> i32 {
        if d_set[x as usize] != x {
            d_set[x as usize] = Solution::find(d_set[x as usize], d_set);
        }
        d_set[x as usize]
    }
}
