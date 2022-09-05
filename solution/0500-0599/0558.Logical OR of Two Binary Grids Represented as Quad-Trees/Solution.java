/*
// Definition for a QuadTree node.
class Node {
    public boolean val;
    public boolean isLeaf;
    public Node topLeft;
    public Node topRight;
    public Node bottomLeft;
    public Node bottomRight;

    public Node() {}

    public Node(boolean _val,boolean _isLeaf,Node _topLeft,Node _topRight,Node _bottomLeft,Node
_bottomRight) { val = _val; isLeaf = _isLeaf; topLeft = _topLeft; topRight = _topRight; bottomLeft =
_bottomLeft; bottomRight = _bottomRight;
    }
};
*/

class Solution {
    public Node intersect(Node quadTree1, Node quadTree2) {
        return dfs(quadTree1, quadTree2);
    }

    private Node dfs(Node t1, Node t2) {
        if (t1.isLeaf && t2.isLeaf) {
            return new Node(t1.val || t2.val, true);
        }
        if (t1.isLeaf) {
            return t1.val ? t1 : t2;
        }
        if (t2.isLeaf) {
            return t2.val ? t2 : t1;
        }
        Node res = new Node();
        res.topLeft = dfs(t1.topLeft, t2.topLeft);
        res.topRight = dfs(t1.topRight, t2.topRight);
        res.bottomLeft = dfs(t1.bottomLeft, t2.bottomLeft);
        res.bottomRight = dfs(t1.bottomRight, t2.bottomRight);
        boolean isLeaf = res.topLeft.isLeaf && res.topRight.isLeaf && res.bottomLeft.isLeaf
            && res.bottomRight.isLeaf;
        boolean sameVal = res.topLeft.val == res.topRight.val
            && res.topRight.val == res.bottomLeft.val && res.bottomLeft.val == res.bottomRight.val;
        if (isLeaf && sameVal) {
            res = res.topLeft;
        }
        return res;
    }
}