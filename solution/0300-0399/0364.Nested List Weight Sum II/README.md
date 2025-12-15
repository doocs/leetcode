---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0300-0399/0364.Nested%20List%20Weight%20Sum%20II/README.md
tags:
    - 栈
    - 深度优先搜索
    - 广度优先搜索
---

<!-- problem:start -->

# [364. 嵌套列表加权和 II 🔒](https://leetcode.cn/problems/nested-list-weight-sum-ii)

[English Version](/solution/0300-0399/0364.Nested%20List%20Weight%20Sum%20II/README_EN.md)

## 题目描述

<!-- description:start -->

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
	<li>没有空列表</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：DFS

我们不妨假设整数分别为 $a_1, a_2, \cdots, a_n$，它们的深度分别为 $d_1, d_2, \cdots, d_n$，最大深度为 $\textit{maxDepth}$，那么答案就是：

$$
a_1 \times \textit{maxDepth} - a_1 \times d_1 + a_1 + a_2 \times \textit{maxDepth} - a_2 \times d_2 + a_2 + \cdots + a_n \times \textit{maxDepth} - a_n \times d_n + a_n
$$

即：

$$
(\textit{maxDepth} + 1) \times (a_1 + a_2 + \cdots + a_n) - (a_1 \times d_1 + a_2 \times d_2 + \cdots + a_n \times d_n)
$$

如果我们记所有整数的和为 $s$，所有整数乘以深度的和为 $ws$，那么答案就是：

$$
(\textit{maxDepth} + 1) \times s - ws
$$

因此，我们设计一个函数 $dfs(x, d)$，表示从 $x$ 开始，深度为 $d$ 开始搜索，函数 $dfs(x, d)$ 的执行过程如下：

- 我们先更新 $\textit{maxDepth} = \max(\textit{maxDepth}, d)$；
- 如果 $x$ 是一个整数，那么我们更新 $s = s + x$, $ws = ws + x \times d$；
- 否则，我们递归地遍历 $x$ 的每一个元素 $y$，调用 $dfs(y, d + 1)$。

我们遍历整个列表，对于每一个元素 $x$，我们调用 $dfs(x, 1)$，最终返回 $(\textit{maxDepth} + 1) \times s - ws$ 即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为整数的个数。

<!-- tabs:start -->

#### Python3

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
        def dfs(x, d):
            nonlocal maxDepth, s, ws
            maxDepth = max(maxDepth, d)
            if x.isInteger():
                s += x.getInteger()
                ws += x.getInteger() * d
            else:
                for y in x.getList():
                    dfs(y, d + 1)

        maxDepth = s = ws = 0
        for x in nestedList:
            dfs(x, 1)
        return (maxDepth + 1) * s - ws
```

#### Java

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
    private int maxDepth;
    private int ws;
    private int s;

    public int depthSumInverse(List<NestedInteger> nestedList) {
        for (NestedInteger x : nestedList) {
            dfs(x, 1);
        }
        return (maxDepth + 1) * s - ws;
    }

    private void dfs(NestedInteger x, int d) {
        maxDepth = Math.max(maxDepth, d);
        if (x.isInteger()) {
            ws += x.getInteger() * d;
            s += x.getInteger();
        } else {
            for (NestedInteger y : x.getList()) {
                dfs(y, d + 1);
            }
        }
    }
}
```

#### C++

```cpp
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * class NestedInteger {
 *   public:
 *     // Constructor initializes an empty nested list.
 *     NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     NestedInteger(int value);
 *
 *     // Return true if this NestedInteger holds a single integer, rather than a nested list.
 *     bool isInteger() const;
 *
 *     // Return the single integer that this NestedInteger holds, if it holds a single integer
 *     // The result is undefined if this NestedInteger holds a nested list
 *     int getInteger() const;
 *
 *     // Set this NestedInteger to hold a single integer.
 *     void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     void add(const NestedInteger &ni);
 *
 *     // Return the nested list that this NestedInteger holds, if it holds a nested list
 *     // The result is undefined if this NestedInteger holds a single integer
 *     const vector<NestedInteger> &getList() const;
 * };
 */
class Solution {
public:
    int depthSumInverse(vector<NestedInteger>& nestedList) {
        int maxDepth = 0, ws = 0, s = 0;
        function<void(NestedInteger&, int)> dfs = [&](NestedInteger& x, int d) {
            maxDepth = max(maxDepth, d);
            if (x.isInteger()) {
                ws += x.getInteger() * d;
                s += x.getInteger();
            } else {
                for (auto& y : x.getList()) {
                    dfs(y, d + 1);
                }
            }
        };
        for (auto& x : nestedList) {
            dfs(x, 1);
        }
        return (maxDepth + 1) * s - ws;
    }
};
```

#### Go

```go
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * type NestedInteger struct {
 * }
 *
 * // Return true if this NestedInteger holds a single integer, rather than a nested list.
 * func (n NestedInteger) IsInteger() bool {}
 *
 * // Return the single integer that this NestedInteger holds, if it holds a single integer
 * // The result is undefined if this NestedInteger holds a nested list
 * // So before calling this method, you should have a check
 * func (n NestedInteger) GetInteger() int {}
 *
 * // Set this NestedInteger to hold a single integer.
 * func (n *NestedInteger) SetInteger(value int) {}
 *
 * // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 * func (n *NestedInteger) Add(elem NestedInteger) {}
 *
 * // Return the nested list that this NestedInteger holds, if it holds a nested list
 * // The list length is zero if this NestedInteger holds a single integer
 * // You can access NestedInteger's List element directly if you want to modify it
 * func (n NestedInteger) GetList() []*NestedInteger {}
 */
func depthSumInverse(nestedList []*NestedInteger) int {
	var maxDepth, ws, s int
	var dfs func(*NestedInteger, int)
	dfs = func(x *NestedInteger, d int) {
		maxDepth = max(maxDepth, d)
		if x.IsInteger() {
			ws += x.GetInteger() * d
			s += x.GetInteger()
		} else {
			for _, y := range x.GetList() {
				dfs(y, d+1)
			}
		}
	}
	for _, x := range nestedList {
		dfs(x, 1)
	}
	return (maxDepth+1)*s - ws
}
```

#### TypeScript

```ts
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * class NestedInteger {
 *     If value is provided, then it holds a single integer
 *     Otherwise it holds an empty nested list
 *     constructor(value?: number) {
 *         ...
 *     };
 *
 *     Return true if this NestedInteger holds a single integer, rather than a nested list.
 *     isInteger(): boolean {
 *         ...
 *     };
 *
 *     Return the single integer that this NestedInteger holds, if it holds a single integer
 *     Return null if this NestedInteger holds a nested list
 *     getInteger(): number | null {
 *         ...
 *     };
 *
 *     Set this NestedInteger to hold a single integer equal to value.
 *     setInteger(value: number) {
 *         ...
 *     };
 *
 *     Set this NestedInteger to hold a nested list and adds a nested integer elem to it.
 *     add(elem: NestedInteger) {
 *         ...
 *     };
 *
 *     Return the nested list that this NestedInteger holds,
 *     or an empty list if this NestedInteger holds a single integer
 *     getList(): NestedInteger[] {
 *         ...
 *     };
 * };
 */

function depthSumInverse(nestedList: NestedInteger[]): number {
    let [maxDepth, ws, s] = [0, 0, 0];
    const dfs = (x: NestedInteger, d: number) => {
        maxDepth = Math.max(maxDepth, d);
        if (x.isInteger()) {
            ws += x.getInteger() * d;
            s += x.getInteger();
        } else {
            for (const y of x.getList()) {
                dfs(y, d + 1);
            }
        }
    };
    for (const x of nestedList) {
        dfs(x, 1);
    }
    return (maxDepth + 1) * s - ws;
}
```

#### JavaScript

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
    let [maxDepth, ws, s] = [0, 0, 0];
    const dfs = (x, d) => {
        maxDepth = Math.max(maxDepth, d);
        if (x.isInteger()) {
            ws += x.getInteger() * d;
            s += x.getInteger();
        } else {
            for (const y of x.getList()) {
                dfs(y, d + 1);
            }
        }
    };
    for (const x of nestedList) {
        dfs(x, 1);
    }
    return (maxDepth + 1) * s - ws;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
