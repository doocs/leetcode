use std::cell::RefCell;
use std::rc::Rc;

impl Solution {
    pub fn count_nodes(root: Option<Rc<RefCell<TreeNode>>>) -> i32 {
        if let Some(node) = root {
            let node = node.borrow();
            let left = Self::depth(&node.left);
            let right = Self::depth(&node.right);
            if left == right {
                Self::count_nodes(node.right.clone()) + (1 << left)
            } else {
                Self::count_nodes(node.left.clone()) + (1 << right)
            }
        } else {
            0
        }
    }

    fn depth(root: &Option<Rc<RefCell<TreeNode>>>) -> i32 {
        if let Some(node) = root {
            Self::depth(&node.borrow().left) + 1
        } else {
            0
        }
    }
}
