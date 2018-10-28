## 路径总和

### 问题描述

给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。

说明: 叶子节点是指没有子节点的节点。

示例: 
给定如下二叉树，以及目标和 sum = 22，
```
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \      \
        7    2      1
```
返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。


### 思路

题目要求有没有路径到**叶子节点**使和等于目标值

主要考察对叶子节点是否判断准确

这道题很简单，但是准确率不高，原因是的判断条件不明确，左空右不空返回什么什么，右空左不空返回什么什么，调试一直错

叶子节点唯一判断就是左右空

`root->left == NULL && root->right==NULL`

```CPP
/**
 * Definition for a binary tree node.
 * struct TreeNode {
 *     int val;
 *     TreeNode *left;
 *     TreeNode *right;
 *     TreeNode(int x) : val(x), left(NULL), right(NULL) {}
 * };
 */
class Solution {
public:
    bool hasPathSum(TreeNode* root, int sum) {
        
        if(root == NULL)return false;
        if(root->right == NULL && root->left == NULL && sum == root->val)return true;
        
        bool leftTrue = hasPathSum(root->left,sum - root->val);
        bool rightTrue = hasPathSum(root->right,sum - root->val);
        
        return (leftTrue || rightTrue);
    }
};
```