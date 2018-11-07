## 从前序与中序遍历序列构造二叉树

### 问题描述
根据一棵树的前序遍历与中序遍历构造二叉树。

注意:
你可以假设树中没有重复的元素。

例如，给出
```
前序遍历 preorder = [3,9,20,15,7]
中序遍历 inorder = [9,3,15,20,7]
```

返回如下的二叉树：
```
    3
   / \
  9  20
    /  \
   15   7
```
---------------

### 思路

利用树的前序遍历和中序遍历特性+递归实现

对树进行前序遍历，每一个元素，在树的中序遍历中找到概元素；在中序遍历中，该元素的左边是它的左子树的全部元素，右边是它的右子树的全部元素，以此为递归条件，确定左右子树的范围

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
    TreeNode* buildTree(vector<int>& preorder, vector<int>& inorder) {
        int pRight = preorder.size()-1;
        int iRight = inorder.size()-1;
        return build(preorder,inorder,0,pRight,0,iRight);
    }
    
    
    TreeNode* build(vector<int>& preorder,vector<int>& inorder,int pLeft,int pRight,int iLeft,int iRight){
        if(pLeft > pRight)return NULL;
        
        TreeNode *node = new TreeNode(preorder[pLeft]);
        int idx = iLeft;
        
        while(inorder[idx] != preorder[pLeft])idx++;
        node->left = build(preorder,inorder,pLeft+1,pLeft+idx-iLeft,iLeft,idx-1);
        node->right = build(preorder,inorder,pLeft+idx-iLeft+1,pRight,idx+1,iRight);  
        return node;
    }
};

```


