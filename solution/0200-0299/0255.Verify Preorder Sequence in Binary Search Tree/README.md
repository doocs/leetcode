# [255. 验证前序遍历序列二叉搜索树](https://leetcode-cn.com/problems/verify-preorder-sequence-in-binary-search-tree)

[English Version](/solution/0200-0299/0255.Verify%20Preorder%20Sequence%20in%20Binary%20Search%20Tree/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个整数数组，你需要验证它是否是一个二叉搜索树正确的先序遍历序列。</p>

<p>你可以假定该序列中的数都是不相同的。</p>

<p>参考以下这颗二叉搜索树：</p>

<pre>     5
    / \
   2   6
  / \
 1   3</pre>

<p><strong>示例 1：</strong></p>

<pre><strong>输入: </strong>[5,2,6,1,3]
<strong>输出: </strong>false</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入: </strong>[5,2,1,3,6]
<strong>输出: </strong>true</pre>

<p><strong>进阶挑战：</strong></p>

<p>您能否使用恒定的空间复杂度来完成此题？</p>

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
        last = float('-inf')
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
        for (int x : preorder)
        {
            if (x < last) return false;
            while (!stk.empty() && stk.top() < x)
            {
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
