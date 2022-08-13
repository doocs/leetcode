# [255. 验证前序遍历序列二叉搜索树](https://leetcode.cn/problems/verify-preorder-sequence-in-binary-search-tree)

[English Version](/solution/0200-0299/0255.Verify%20Preorder%20Sequence%20in%20Binary%20Search%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个&nbsp;<b>无重复元素</b>&nbsp;的整数数组&nbsp;<code>preorder</code>&nbsp;，&nbsp;<em>如果它是以二叉搜索树的<strong>先序遍历</strong>排列</em><em>&nbsp;</em>，返回 <code>true</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0200-0299/0255.Verify%20Preorder%20Sequence%20in%20Binary%20Search%20Tree/images/preorder-tree.jpg" /></p>

<pre>
<strong>输入: </strong>preorder = [5,2,1,3,6]
<strong>输出: </strong>true</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入: </strong>preorder = [5,2,6,1,3]
<strong>输出: </strong>false</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= preorder.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= preorder[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>preorder</code>&nbsp;中&nbsp;<strong>无重复元素</strong></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>您能否使用恒定的空间复杂度来完成此题？</p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

二叉搜索树先序遍历时，每次移向左子树时，值递减，移向右子树时，值递增。

因此，可以维护一个单调递减栈。遍历序列，若当前值大于栈顶元素，说明开始要进入右子树的遍历。只要栈顶元素比当前值小，就表示还是左子树，要移除，也就是从栈中弹出，直至栈顶元素大于当前值，或者栈为空。此过程要记录弹出栈的最后一个元素 last。

接下来继续往后遍历，之后右子树的每个节点，都要比 last 大，才能满足二叉搜索树，否则直接返回 false。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def verifyPreorder(self, preorder: List[int]) -> bool:
        stk = []
        last = -inf
        for x in preorder:
            if x < last:
                return False
            while stk and stk[-1] < x:
                last = stk.pop()
            stk.append(x)
        return True
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean verifyPreorder(int[] preorder) {
        Deque<Integer> stk = new ArrayDeque<>();
        int last = Integer.MIN_VALUE;
        for (int x : preorder) {
            if (x < last) {
                return false;
            }
            while (!stk.isEmpty() && stk.peek() < x) {
                last = stk.poll();
            }
            stk.push(x);
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool verifyPreorder(vector<int>& preorder) {
        stack<int> stk;
        int last = INT_MIN;
        for (int x : preorder) {
            if (x < last) return false;
            while (!stk.empty() && stk.top() < x) {
                last = stk.top();
                stk.pop();
            }
            stk.push(x);
        }
        return true;
    }
};
```

### **Go**

```go
func verifyPreorder(preorder []int) bool {
	var stk []int
	last := math.MinInt32
	for _, x := range preorder {
		if x < last {
			return false
		}
		for len(stk) > 0 && stk[len(stk)-1] < x {
			last = stk[len(stk)-1]
			stk = stk[0 : len(stk)-1]
		}
		stk = append(stk, x)
	}
	return true
}
```

### **...**

```

```

<!-- tabs:end -->
