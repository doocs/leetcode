// Definition for a binary tree node.
// #[derive(Debug, PartialEq, Eq)]
// pub struct TreeNode {
//   pub val: i32,
//   pub left: Option<Rc<RefCell<TreeNode>>>,
//   pub right: Option<Rc<RefCell<TreeNode>>>,
// }
//
// impl TreeNode {
//   #[inline]
//   pub fn new(val: i32) -> Self {
//     TreeNode {
//       val,
//       left: None,
//       right: None
//     }
//   }
// }
use std::rc::Rc;
use std::cell::RefCell;
struct Codec {

}

/**
 * `&self` means the method takes an immutable reference.
 * If you need a mutable reference, change it to `&mut self` instead.
 */
impl Codec {
    fn new() -> Self {
        Codec {}
    }

    fn serialize(&self, root: Option<Rc<RefCell<TreeNode>>>) -> String {
        if root.is_none() {
            return String::from("#");
        }
        let mut node = root.as_ref().unwrap().borrow_mut();
        let left = node.left.take();
        let right = node.right.take();
        format!(
            "{},{},{}",
            self.serialize(right),
            self.serialize(left),
            node.val
        )
    }

    fn deserialize(&self, data: String) -> Option<Rc<RefCell<TreeNode>>> {
        if data.len() == 1 {
            return None;
        }
        Self::renew(&mut data.split(",").collect())
    }

    fn renew(vals: &mut Vec<&str>) -> Option<Rc<RefCell<TreeNode>>> {
        let val = vals.pop().unwrap_or("#");
        if val == "#" {
            return None;
        }
        Some(Rc::new(RefCell::new(TreeNode {
            val: val.parse().unwrap(),
            left: Self::renew(vals),
            right: Self::renew(vals),
        })))
    }
}

/**
 * Your Codec object will be instantiated and called as such:
 * let obj = Codec::new();
 * let data: String = obj.serialize(strs);
 * let ans: Option<Rc<RefCell<TreeNode>>> = obj.deserialize(data);
 */