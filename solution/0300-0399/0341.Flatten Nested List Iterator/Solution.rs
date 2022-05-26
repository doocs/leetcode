// #[derive(Debug, PartialEq, Eq)]
// pub enum NestedInteger {
//   Int(i32),
//   List(Vec<NestedInteger>)
// }
struct NestedIterator {
    index: usize,
    vals: Vec<i32>,
}


/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl NestedIterator {

    fn dfs(nestedList: &Vec<NestedInteger>, vals: &mut Vec<i32>) {
        for ele in nestedList.iter() {
            match ele {
                NestedInteger::Int(val) => vals.push(*val),
                NestedInteger::List(list) => Self::dfs(list, vals),
            }
        }
    }

    fn new(nestedList: Vec<NestedInteger>) -> Self {
        let mut vals = vec![];
        Self::dfs(&nestedList, &mut vals);
        Self {
            vals,
            index: 0,
        }
    }

    fn next(&mut self) -> i32 {
        let res = self.vals[self.index];
        self.index += 1;
        res
    }

    fn has_next(&self) -> bool {
        self.index < self.vals.len()
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * let obj = NestedIterator::new(nestedList);
 * let ret_1: i32 = obj.next();
 * let ret_2: bool = obj.has_next();
 */