//non-recursive
var inorderTraversal = function (root) {
    let res = [], stk = [];
    let cur = root;
    while (cur || stk.length !== 0) {
        while (cur) {
            stk.push(cur);
            cur = cur.left;
        } 
        let top = stk.pop();
        res.push(top.val);
        cur = top.right;

    }
    return res;
};

//recursive
var inorderTraversal = function(root) {
    let res = [];
    function inorder(root){
        if(root){
        inorder(root.left);
        res.push(root.val);
        inorder(root.right);
        }
    }
    inorder(root);
    return res;
};
