# [946. 验证栈序列](https://leetcode.cn/problems/validate-stack-sequences)

[English Version](/solution/0900-0999/0946.Validate%20Stack%20Sequences/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定&nbsp;<code>pushed</code>&nbsp;和&nbsp;<code>popped</code>&nbsp;两个序列，每个序列中的 <strong>值都不重复</strong>，只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 <code>true</code>；否则，返回 <code>false</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
<strong>输出：</strong>true
<strong>解释：</strong>我们可以按以下顺序执行：
push(1), push(2), push(3), push(4), pop() -&gt; 4,
push(5), pop() -&gt; 5, pop() -&gt; 3, pop() -&gt; 2, pop() -&gt; 1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
<strong>输出：</strong>false
<strong>解释：</strong>1 不能在 2 之前弹出。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= pushed.length &lt;= 1000</code></li>
	<li><code>0 &lt;= pushed[i] &lt;= 1000</code></li>
	<li><code>pushed</code> 的所有元素 <strong>互不相同</strong></li>
	<li><code>popped.length == pushed.length</code></li>
	<li><code>popped</code> 是 <code>pushed</code> 的一个排列</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

遍历 pushed 序列，将每个数依次压入栈中，压入后检查这个数是不是 popped 序列中下一个要 pop 的值，如果是就把它 pop 出来。

最后检查所有数是否都 pop 出来了。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def validateStackSequences(self, pushed: List[int], popped: List[int]) -> bool:
        stk, j, n = [], 0, len(popped)
        for x in pushed:
            stk.append(x)
            while stk and j < n and stk[-1] == popped[j]:
                stk.pop()
                j += 1
        return j == n
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stk = new ArrayDeque<>();
        int j = 0, n = popped.length;
        for (int x : pushed) {
            stk.push(x);
            while (!stk.isEmpty() && j < n && stk.peek() == popped[j]) {
                stk.pop();
                ++j;
            }
        }
        return j == n;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool validateStackSequences(vector<int>& pushed, vector<int>& popped) {
        int j = 0, n = popped.size();
        stack<int> stk;
        for (int x : pushed) {
            stk.push(x);
            while (!stk.empty() && j < n && stk.top() == popped[j]) {
                stk.pop();
                ++j;
            }
        }
        return j == n;
    }
};
```

### **Go**

```go
func validateStackSequences(pushed []int, popped []int) bool {
	j, n := 0, len(popped)
	var stk []int
	for _, x := range pushed {
		stk = append(stk, x)
		for len(stk) > 0 && j < n && stk[len(stk)-1] == popped[j] {
			stk = stk[:len(stk)-1]
			j++
		}
	}
	return j == n
}
```

### **...**

```

```

<!-- tabs:end -->
