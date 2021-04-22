# [968. 监控二叉树](https://leetcode-cn.com/problems/binary-tree-cameras)

[English Version](/solution/0900-0999/0968.Binary%20Tree%20Cameras/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二叉树，我们在树的节点上安装摄像头。</p>

<p>节点上的每个摄影头都可以监视<strong>其父对象、自身及其直接子对象。</strong></p>

<p>计算监控树的所有节点所需的最小摄像头数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0968.Binary%20Tree%20Cameras/images/bst_cameras_01.png" style="height: 163px; width: 138px;"></p>

<pre><strong>输入：</strong>[0,0,null,0,0]
<strong>输出：</strong>1
<strong>解释：</strong>如图所示，一台摄像头足以监控所有节点。
</pre>

<p><strong>示例 2：</strong></p>

<p><img alt="" src="https://cdn.jsdelivr.net/gh/doocs/leetcode@main/solution/0900-0999/0968.Binary%20Tree%20Cameras/images/bst_cameras_02.png" style="height: 312px; width: 139px;"></p>

<pre><strong>输入：</strong>[0,0,null,0,null,0,null,null,0]
<strong>输出：</strong>2
<strong>解释：</strong>需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
</pre>

<p><br>
<strong>提示：</strong></p>

<ol>
	<li>给定树的节点数的范围是&nbsp;<code>[1, 1000]</code>。</li>
	<li>每个节点的值都是 0。</li>
</ol>


## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **Go**

```go
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

var res int
func minCameraCover(root *TreeNode) int {
    res = 0
    //三种状态，后序遍历
    if root == nil {
        return 0
    }
    if dfs(root) == 0 {
        res++
    }
    return res
}
//0:待覆盖，1：已覆盖，2：安装

func dfs(root *TreeNode) int {
    if root == nil {
        return 1
    }
    l := dfs(root.Left)
    r := dfs(root.Right)
    //左右子节点存在待覆盖状态，当前节点要安装
    if l == 0 || r == 0 {
        res++
        return 2
    } else if l == 1 && r == 1 { //左右节点均为已覆盖，则当前节点为待覆盖
        return 0
    }
    //除上述情况外，左右子节点中至少有一个安装了监控，当前节点为已覆盖
    return 1
}
```

<!-- tabs:end -->
