impl Solution {
    #[allow(dead_code)]
    pub fn invert_tree(root: Option<Rc<RefCell<TreeNode>>>) -> Option<Rc<RefCell<TreeNode>>> {
        if root.is_none() {
            return root;
        }
        let left = root.as_ref().unwrap().borrow().left.clone();
        let right = root.as_ref().unwrap().borrow().right.clone();
        // Invert the subtree
        let inverted_left = Self::invert_tree(right);
        let inverted_right = Self::invert_tree(left);
        // Update the left & right
        root.as_ref().unwrap().borrow_mut().left = inverted_left;
        root.as_ref().unwrap().borrow_mut().right = inverted_right;
        // Return the root
        root
    }
}