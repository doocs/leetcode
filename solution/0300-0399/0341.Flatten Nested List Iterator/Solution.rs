// #[derive(Debug, PartialEq, Eq)]
// pub enum NestedInteger {
//   Int(i32),
//   List(Vec<NestedInteger>)
// }
struct NestedIterator {
    nums: Vec<i32>,
    i: usize,
}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl NestedIterator {
    fn new(nested_list: Vec<NestedInteger>) -> Self {
        let mut nums = Vec::new();
        Self::dfs(&nested_list, &mut nums);
        NestedIterator { nums, i: 0 }
    }

    fn next(&mut self) -> i32 {
        let result = self.nums[self.i];
        self.i += 1;
        result
    }

    fn has_next(&self) -> bool {
        self.i < self.nums.len()
    }

    fn dfs(nested_list: &Vec<NestedInteger>, nums: &mut Vec<i32>) {
        for ni in nested_list {
            match ni {
                NestedInteger::Int(x) => nums.push(*x),
                NestedInteger::List(list) => Self::dfs(list, nums),
            }
        }
    }
}
