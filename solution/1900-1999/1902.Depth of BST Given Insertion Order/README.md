---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1902.Depth%20of%20BST%20Given%20Insertion%20Order/README.md
tags:
    - 树
    - 二叉搜索树
    - 二叉树
    - 有序集合
---

# [1902. 给定二叉搜索树的插入顺序求深度 🔒](https://leetcode.cn/problems/depth-of-bst-given-insertion-order)

[English Version](/solution/1900-1999/1902.Depth%20of%20BST%20Given%20Insertion%20Order/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个<strong>从 0 开始索引</strong>的整数类型数组 <code>order</code> ，其长度为 <code>n</code>，是从 <code>1</code> 到 <code>n</code> 的所有整数的一个排列，表示插入到一棵二叉搜索树的顺序。</p>

<p>二叉搜索树的定义如下：</p>

<ul>
	<li>一个节点的左子树只包含键值<strong>小于</strong>该节点键值的节点。</li>
	<li>一个节点的右子树只包含键值<strong>大于</strong>该节点键值的节点。</li>
	<li>左子树和右子树须均为二叉搜索树。</li>
</ul>

<p>该二叉搜索树的构造方式如下：</p>

<ul>
	<li><code>order[0]</code> 将成为该二叉搜索树的根。</li>
	<li>所有后续的元素均在维持二叉搜索树性质的前提下作为<strong>任何</strong>已存在节点的<strong>子节点</strong>插入。</li>
</ul>

<p>返回该二叉搜索树的<strong>深度</strong>。</p>

<p>一棵二叉树的<strong>深度</strong>是从根节点到最远叶节点的<strong>最长路径</strong>所经<strong>节点</strong>的个数。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1902.Depth%20of%20BST%20Given%20Insertion%20Order/images/1.png" style="width: 624px; height: 154px;" />
<pre>
<strong>输入:</strong> order = [2,1,4,3]
<strong>输出:</strong> 3
<strong>解释: </strong>该二叉搜索树的深度为 3，路径为 2-&gt;4-&gt;3。
</pre>

<p><strong>示例 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1902.Depth%20of%20BST%20Given%20Insertion%20Order/images/2.png" style="width: 624px; height: 146px;" />
<pre>
<strong>输入:</strong> order = [2,1,3,4]
<strong>输出:</strong> 3
<strong>解释: </strong>该二叉搜索树的深度为 3，路径为 2-&gt;3-&gt;4。
</pre>

<p><strong>示例 3:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1900-1999/1902.Depth%20of%20BST%20Given%20Insertion%20Order/images/3.png" style="width: 624px; height: 225px;" />
<pre>
<strong>输入:</strong> order = [1,2,3,4]
<strong>输出:</strong> 4
<strong>解释: </strong>该二叉搜索树的深度为 4，路径为 1-&gt;2-&gt;3-&gt;4。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == order.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>order</code> 是从 <code>1</code> 到 <code>n</code> 的整数的一个排列。</li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
from sortedcontainers import SortedDict


class Solution:
    def maxDepthBST(self, order: List[int]) -> int:
        sd = SortedDict({0: 0, inf: 0, order[0]: 1})
        ans = 1
        for v in order[1:]:
            lower = sd.bisect_left(v) - 1
            higher = lower + 1
            depth = 1 + max(sd.values()[lower], sd.values()[higher])
            ans = max(ans, depth)
            sd[v] = depth
        return ans
```

```java
class Solution {
    public int maxDepthBST(int[] order) {
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        tm.put(0, 0);
        tm.put(Integer.MAX_VALUE, 0);
        tm.put(order[0], 1);
        int ans = 1;
        for (int i = 1; i < order.length; ++i) {
            int v = order[i];
            Map.Entry<Integer, Integer> lower = tm.lowerEntry(v);
            Map.Entry<Integer, Integer> higher = tm.higherEntry(v);
            int depth = 1 + Math.max(lower.getValue(), higher.getValue());
            ans = Math.max(ans, depth);
            tm.put(v, depth);
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- end -->
