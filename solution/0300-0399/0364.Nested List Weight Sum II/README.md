# [364. 加权嵌套序列和 II](https://leetcode.cn/problems/nested-list-weight-sum-ii)

[English Version](/solution/0300-0399/0364.Nested%20List%20Weight%20Sum%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数嵌套列表&nbsp;<code>nestedList</code> ，每一个元素要么是一个整数，要么是一个列表（这个列表中的每个元素也同样是整数或列表）。</p>

<p>整数的 <strong>深度</strong> 取决于它位于多少个列表内部。例如，嵌套列表 <code>[1,[2,2],[[3],2],1]</code> 的每个整数的值都等于它的 <strong>深度</strong> 。令 <code>maxDepth</code> 是任意整数的 <strong>最大深度</strong> 。</p>

<p>整数的 <strong>权重</strong> 为 <code>maxDepth - (整数的深度) + 1</code> 。</p>

<p>将 <code>nestedList</code> 列表中每个整数先乘权重再求和，返回该加权和。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0364.Nested%20List%20Weight%20Sum%20II/images/nestedlistweightsumiiex1.png" style="width: 426px; height: 181px;" />
<pre>
<strong>输入：</strong>nestedList = [[1,1],2,[1,1]]
<strong>输出：</strong>8
<strong>解释：</strong>4<strong> </strong>个 1 在深度为 1 的位置， 一个 2 在深度为 2 的位置。
1*1 + 1*1 + 2*2 + 1*1 + 1*1 = 8
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0364.Nested%20List%20Weight%20Sum%20II/images/nestedlistweightsumiiex2.png" style="width: 349px; height: 192px;" />
<pre>
<strong>输入：</strong>nestedList = [1,[4,[6]]]
<strong>输出：</strong>17
<strong>解释：</strong>一个 1 在深度为 3 的位置， 一个 4 在深度为 2 的位置，一个 6 在深度为 1 的位置。 
1*3 + 4*2 + 6*1 = 17
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nestedList.length &lt;= 50</code></li>
	<li>嵌套列表中整数的值在范围 <code>[-100, 100]</code></li>
	<li>任意整数的最大 <strong>深度</strong> 小于等于 <code>50</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

先求序列的最大深度 `depth`，然后利用 DFS 累加求和。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
# """
# This is the interface that allows for creating nested lists.
# You should not implement it, or speculate about its implementation
# """
# class NestedInteger:
#    def __init__(self, value=None):
#        """
#        If value is not specified, initializes an empty list.
#        Otherwise initializes a single integer equal to value.
#        """
#
#    def isInteger(self):
#        """
#        @return True if this NestedInteger holds a single integer, rather than a nested list.
#        :rtype bool
#        """
#
#    def add(self, elem):
#        """
#        Set this NestedInteger to hold a nested list and adds a nested integer elem to it.
#        :rtype void
#        """
#
#    def setInteger(self, value):
#        """
#        Set this NestedInteger to hold a single integer equal to value.
#        :rtype void
#        """
#
#    def getInteger(self):
#        """
#        @return the single integer that this NestedInteger holds, if it holds a single integer
#        Return None if this NestedInteger holds a nested list
#        :rtype int
#        """
#
#    def getList(self):
#        """
#        @return the nested list that this NestedInteger holds, if it holds a nested list
#        Return None if this NestedInteger holds a single integer
#        :rtype List[NestedInteger]
#        """
class Solution:
    def depthSumInverse(self, nestedList: List[NestedInteger]) -> int:
        def max_depth(nestedList):
            depth = 1
            for item in nestedList:
                if item.isInteger():
                    continue
                depth = max(depth, max_depth(item.getList()) + 1)
            return depth

        def dfs(nestedList, max_depth):
            depth_sum = 0
            for item in nestedList:
                if item.isInteger():
                    depth_sum += item.getInteger() * max_depth
                else:
                    depth_sum += dfs(item.getList(), max_depth - 1)
            return depth_sum

        depth = max_depth(nestedList)
        return dfs(nestedList, depth)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        int depth = maxDepth(nestedList);
        return dfs(nestedList, depth);
    }

    private int maxDepth(List<NestedInteger> nestedList) {
        int depth = 1;
        for (NestedInteger item : nestedList) {
            if (item.isInteger()) {
                continue;
            }
            depth = Math.max(depth, 1 + maxDepth(item.getList()));
        }
        return depth;
    }

    private int dfs(List<NestedInteger> nestedList, int depth) {
        int depthSum = 0;
        for (NestedInteger item : nestedList) {
            if (item.isInteger()) {
                depthSum += item.getInteger() * depth;
            } else {
                depthSum += dfs(item.getList(), depth - 1);
            }
        }
        return depthSum;
    }
}
```

### **JavaScript**

```js
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * function NestedInteger() {
 *
 *     Return true if this NestedInteger holds a single integer, rather than a nested list.
 *     @return {boolean}
 *     this.isInteger = function() {
 *         ...
 *     };
 *
 *     Return the single integer that this NestedInteger holds, if it holds a single integer
 *     Return null if this NestedInteger holds a nested list
 *     @return {integer}
 *     this.getInteger = function() {
 *         ...
 *     };
 *
 *     Set this NestedInteger to hold a single integer equal to value.
 *     @return {void}
 *     this.setInteger = function(value) {
 *         ...
 *     };
 *
 *     Set this NestedInteger to hold a nested list and adds a nested integer elem to it.
 *     @return {void}
 *     this.add = function(elem) {
 *         ...
 *     };
 *
 *     Return the nested list that this NestedInteger holds, if it holds a nested list
 *     Return null if this NestedInteger holds a single integer
 *     @return {NestedInteger[]}
 *     this.getList = function() {
 *         ...
 *     };
 * };
 */
/**
 * @param {NestedInteger[]} nestedList
 * @return {number}
 */
var depthSumInverse = function (nestedList) {
    const maxDepth = nestedList => {
        let depth = 1;
        for (const item of nestedList) {
            if (item.isInteger()) {
                continue;
            }
            depth = Math.max(depth, 1 + maxDepth(item.getList()));
        }
        return depth;
    };
    const dfs = (nestedList, depth) => {
        let depthSum = 0;
        for (const item of nestedList) {
            if (item.isInteger()) {
                depthSum += item.getInteger() * depth;
            } else {
                depthSum += dfs(item.getList(), depth - 1);
            }
        }
        return depthSum;
    };
    const depth = maxDepth(nestedList);
    return dfs(nestedList, depth);
};
```

### **...**

```

```

<!-- tabs:end -->
