# [339. Nested List Weight Sum](https://leetcode.com/problems/nested-list-weight-sum)

[中文文档](/solution/0300-0399/0339.Nested%20List%20Weight%20Sum/README.md)

## Description

<p>You are given a nested list of integers <code>nestedList</code>. Each element is either an integer or a list whose elements may also be integers or other lists.</p>

<p>The <strong>depth</strong> of an integer is the number of lists that it is inside of. For example, the nested list <code>[1,[2,2],[[3],2],1]</code> has each integer&#39;s value set to its <strong>depth</strong>.</p>

<p>Return <em>the sum of each integer in </em><code>nestedList</code><em> multiplied by its <strong>depth</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0339.Nested%20List%20Weight%20Sum/images/nestedlistweightsumex1.png" style="width: 405px; height: 99px;" />
<pre>
<strong>Input:</strong> nestedList = [[1,1],2,[1,1]]
<strong>Output:</strong> 10
<strong>Explanation:</strong> Four 1&#39;s at depth 2, one 2 at depth 1. 1*2 + 1*2 + 2*1 + 1*2 + 1*2 = 10.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0300-0399/0339.Nested%20List%20Weight%20Sum/images/nestedlistweightsumex2.png" style="width: 315px; height: 106px;" />
<pre>
<strong>Input:</strong> nestedList = [1,[4,[6]]]
<strong>Output:</strong> 27
<strong>Explanation:</strong> One 1 at depth 1, one 4 at depth 2, and one 6 at depth 3. 1*1 + 4*2 + 6*3 = 27.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nestedList = [0]
<strong>Output:</strong> 0
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nestedList.length &lt;= 50</code></li>
	<li>The values of the integers in the nested list is in the range <code>[-100, 100]</code>.</li>
	<li>The maximum <strong>depth</strong> of any integer is less than or equal to <code>50</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
    def depthSum(self, nestedList: List[NestedInteger]) -> int:
        def dfs(nestedList, depth):
            depth_sum = 0
            for item in nestedList:
                if item.isInteger():
                    depth_sum += item.getInteger() * depth
                else:
                    depth_sum += dfs(item.getList(), depth + 1)
            return depth_sum

        return dfs(nestedList, 1)
```

### **Java**

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
    public int depthSum(List<NestedInteger> nestedList) {
        return dfs(nestedList, 1);
    }

    private int dfs(List<NestedInteger> nestedList, int depth) {
        int depthSum = 0;
        for (NestedInteger item : nestedList) {
            if (item.isInteger()) {
                depthSum += item.getInteger() * depth;
            } else {
                depthSum += dfs(item.getList(), depth + 1);
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
var depthSum = function (nestedList) {
    const dfs = (nestedList, depth) => {
        let depthSum = 0;
        for (const item of nestedList) {
            if (item.isInteger()) {
                depthSum += item.getInteger() * depth;
            } else {
                depthSum += dfs(item.getList(), depth + 1);
            }
        }
        return depthSum;
    };
    return dfs(nestedList, 1);
};
```

### **...**

```

```

<!-- tabs:end -->
