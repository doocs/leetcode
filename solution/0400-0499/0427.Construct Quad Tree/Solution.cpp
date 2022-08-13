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
    
    Node() {
        val = false;
        isLeaf = false;
        topLeft = NULL;
        topRight = NULL;
        bottomLeft = NULL;
        bottomRight = NULL;
    }
    
    Node(bool _val, bool _isLeaf) {
        val = _val;
        isLeaf = _isLeaf;
        topLeft = NULL;
        topRight = NULL;
        bottomLeft = NULL;
        bottomRight = NULL;
    }
    
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
        return dfs(0, 0, grid.size() - 1, grid[0].size() - 1, grid);
    }

    Node* dfs(int a, int b, int c, int d, vector<vector<int>>& grid) {
        int zero = 0, one = 0;
        for (int i = a; i <= c; ++i) {
            for (int j = b; j <= d; ++j) {
                if (grid[i][j])
                    one = 1;
                else
                    zero = 1;
            }
        }
        bool isLeaf = zero + one == 1;
        bool val = isLeaf && one;
        Node* node = new Node(val, isLeaf);
        if (isLeaf) return node;
        node->topLeft = dfs(a, b, (a + c) / 2, (b + d) / 2, grid);
        node->topRight = dfs(a, (b + d) / 2 + 1, (a + c) / 2, d, grid);
        node->bottomLeft = dfs((a + c) / 2 + 1, b, c, (b + d) / 2, grid);
        node->bottomRight = dfs((a + c) / 2 + 1, (b + d) / 2 + 1, c, d, grid);
        return node;
    }
};