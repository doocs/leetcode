/*
// Definition for a QuadTree node.
class Node {
public:
    bool val;
    bool isLeaf;
    Node* topLeft;
    Node* topRight;
    Node* bottomLeft;
    Node* bottomRight;

    Node() {}

    Node(bool _val, bool _isLeaf, Node* _topLeft, Node* _topRight, Node* _bottomLeft, Node* _bottomRight) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = _topLeft;
        topRight = _topRight;
        bottomLeft = _bottomLeft;
        bottomRight = _bottomRight;
    }
};
*/
class Solution {
public:
    Node* construct(vector<vector<int>>& grid) {
        return build(grid, 0, grid.size(), 0, grid.size()) ;
    }
    Node *build(vector<vector<int>> &g, int l, int r, int t, int b) 
    {
        Node *node = new Node ;
        node->topLeft = NULL ;
        node->topRight = NULL ;
        node->bottomLeft = NULL ;
        node->bottomRight = NULL ;
        node->isLeaf = false ;
        
        bool tl, tr, bl, br ;
        if (l + 1 == r)
        {
            node->val = g[t][l] ;
            node->isLeaf = true ;
            return node ;
        }
        
        int vmid = (l+r)>>1 ;
        int hmid = (t+b)>>1 ;
        node->topLeft = build(g, l, vmid, t, hmid) ;
        node->topRight = build(g, vmid, r, t, hmid) ;
        node->bottomLeft = build(g, l, vmid, hmid, b) ;
        node->bottomRight = build(g, vmid, r, hmid, b) ;
        
        if (node->topLeft->isLeaf && node->topRight->isLeaf && node->bottomLeft->isLeaf && node->bottomRight->isLeaf)
        {
            if (node->topLeft->val && node->topRight->val && node->bottomLeft->val && node->bottomRight->val
               || !(node->topLeft->val || node->topRight->val || node->bottomLeft->val || node->bottomRight->val))
            {
                node->val = node->topLeft->val ;
                node->isLeaf = true ;
                
                delete(node->topLeft) ;
                delete(node->topRight) ;
                delete(node->bottomLeft) ;
                delete(node->bottomRight) ;
                
                node->topLeft = NULL ;
                node->topRight = NULL ;
                node->bottomLeft = NULL ;
                node->bottomRight = NULL ;
            }
        }
        return node ;
    }
};