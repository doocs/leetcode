---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/lcci/04.09.BST%20Sequences/README.md
---

# [面试题 04.09. 二叉搜索树序列](https://leetcode.cn/problems/bst-sequences-lcci)

[English Version](/lcci/04.09.BST%20Sequences/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->
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

## 解法

<!-- tabs:start -->

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

<!-- end -->
