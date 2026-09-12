---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/04.09.BST%20Sequences/README.md
---

<!-- problem:start -->

# [面试题 04.09. 二叉搜索树序列](https://leetcode.cn/problems/bst-sequences-lcci)

[English Version](/lcci/04.09.BST%20Sequences/README_EN.md)

## 题目描述

<!-- description:start -->

<p>从左向右遍历一个数组，通过不断将其中的元素插入树中可以逐步地生成一棵二叉搜索树。给定一个由不同节点组成的二叉树，输出所有可能生成此树的数组。</p>
<p><strong>示例:</strong><br>
给定如下二叉树</p>
<pre>        2
       / \
      1   3
</pre>
<p>返回:</p>
<pre>[
   [2,1,3],
   [2,3,1]
]
</pre>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：递归交织子序列

<!-- thinking:start -->

> **思考**
>
> 若枚举节点值的全排列再模拟插入，则需验证每一种排列是否得到同一棵树，排列数为 $n!$，在节点稍多时不可行。根必须最先插入，因此合法序列都以根开头；左右子树的插入序列彼此独立，只需保持各自内部的相对次序。为此先递归求出左右子树的全部合法序列，再以根为前缀做交织：每一步从某一侧尚未用完的序列取下一个值。空树对应空序列。

<!-- thinking:end -->

<!-- tabs:start -->

#### Swift

```swift
/* class TreeNode {
*    var val: Int
*    var left: TreeNode?
*    var right: TreeNode?
*
*    init(_ val: Int, _ left: TreeNode? = nil, _ right: TreeNode? = nil) {
*        self.val = val
*        self.left = left
*        self.right = right
*    }
* }
*/

class Solution {
    func BSTSequences(_ root: TreeNode?) -> [[Int]] {
        guard let root = root else { return [[]] }

        var result = [[Int]]()
        let prefix = [root.val]
        let leftSeq = BSTSequences(root.left)
        let rightSeq = BSTSequences(root.right)

        for left in leftSeq {
            for right in rightSeq {
                var weaved = [[Int]]()
                weaveLists(left, right, &weaved, prefix)
                result.append(contentsOf: weaved)
            }
        }
        return result
    }

    private func weaveLists(_ first: [Int], _ second: [Int], _ results: inout [[Int]], _ prefix: [Int]) {
        if first.isEmpty || second.isEmpty {
            var result = prefix
            result.append(contentsOf: first)
            result.append(contentsOf: second)
            results.append(result)
            return
        }

        var prefixWithFirst = prefix
        prefixWithFirst.append(first.first!)
        weaveLists(Array(first.dropFirst()), second, &results, prefixWithFirst)

        var prefixWithSecond = prefix
        prefixWithSecond.append(second.first!)
        weaveLists(first, Array(second.dropFirst()), &results, prefixWithSecond)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
