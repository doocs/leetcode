/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode() : val(0), left(nullptr), right(nullptr) {}
 *     TreeNode(int x) : val(x), left(nullptr), right(nullptr) {}
 *     TreeNode(int x, TreeNode *left, TreeNode *right) : val(x), left(left), right(right) {}
 * };
 */
class Solution {
public:
    vector<vector<int>> verticalTraversal(TreeNode* root) {
        // Each tuple format: {tree node, row, column}.
        deque<tuple<TreeNode*, int, int>> queue = {{root, 0, 0}};

        // Each tuple format: {row, value}.
        deque<vector<tuple<int, int>>> columnsValues = {{}};

        int leftmostCol = 0, rightmostCol = 0;

        while (!queue.empty()) {
            auto [node, row, column] = queue.front();
            queue.pop_front();

            if (column < leftmostCol) {
                leftmostCol = column;
                columnsValues.push_front({});
            }

            if (column > rightmostCol) {
                rightmostCol = column;
                columnsValues.push_back({});
            }

            columnsValues[column - leftmostCol].push_back({row, node->val});

            if (node->left != nullptr)
                queue.push_back({node->left, row + 1, column - 1});

            if (node->right != nullptr)
                queue.push_back({node->right, row + 1, column + 1});
        }

        vector<vector<int>> verticalTraversal = {};

        for (auto columnValues : columnsValues) { // Need to sort so no const.
            verticalTraversal.push_back({});

            sort(columnValues.begin(), columnValues.end());
            for (const auto& [row, value] : columnValues)
                verticalTraversal.back().push_back(value);
        }

        return verticalTraversal;
    }
};