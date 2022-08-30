# [面试题 31. 栈的压入、弹出序列](https://leetcode.cn/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/)

## 题目描述

<!-- 这里写题目描述 -->

<p>输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
<strong>输出：</strong>true
<strong>解释：</strong>我们可以按以下顺序执行：
push(1), push(2), push(3), push(4), pop() -&gt; 4,
push(5), pop() -&gt; 5, pop() -&gt; 3, pop() -&gt; 2, pop() -&gt; 1
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
<strong>输出：</strong>false
<strong>解释：</strong>1 不能在 2 之前弹出。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ol>
	<li><code>0 &lt;= pushed.length == popped.length &lt;= 1000</code></li>
	<li><code>0 &lt;= pushed[i], popped[i] &lt; 1000</code></li>
	<li><code>pushed</code>&nbsp;是&nbsp;<code>popped</code>&nbsp;的排列。</li>
</ol>

<p>注意：本题与主站 946 题相同：<a href="https://leetcode.cn/problems/validate-stack-sequences/">https://leetcode.cn/problems/validate-stack-sequences/</a></p>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：栈模拟**

遍历 `pushed` 序列，将每个数 `v` 依次压入栈中，压入后检查这个数是不是 `popped` 序列中下一个要弹出的值，如果是就循环把栈顶元素弹出。

遍历结束，如果 `popped` 序列已经到末尾，说明是一个合法的序列，否则不是。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是 `pushed` 序列的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def validateStackSequences(self, pushed: List[int], popped: List[int]) -> bool:
        j, stk = 0, []
        for v in pushed:
            stk.append(v)
            while stk and stk[-1] == popped[j]:
                stk.pop()
                j += 1
        return j == len(pushed)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stk = new ArrayDeque<>();
        int j = 0;
        for (int v : pushed) {
            stk.push(v);
            while (!stk.isEmpty() && stk.peek() == popped[j]) {
                stk.pop();
                ++j;
            }
        }
        return j == pushed.length;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    bool validateStackSequences(vector<int>& pushed, vector<int>& popped) {
        stack<int> stk;
        int j = 0;
        for (int v : pushed) {
            stk.push(v);
            while (!stk.empty() && stk.top() == popped[j]) {
                stk.pop();
                ++j;
            }
        }
        return j == pushed.size();
    }
};
```

### **Go**

```go
func validateStackSequences(pushed []int, popped []int) bool {
	stk := []int{}
	j := 0
	for _, v := range pushed {
		stk = append(stk, v)
		for len(stk) > 0 && stk[len(stk)-1] == popped[j] {
			stk = stk[:len(stk)-1]
			j++
		}
	}
	return j == len(pushed)
}
```

### **TypeScript**

```ts
function validateStackSequences(pushed: number[], popped: number[]): boolean {
    const stk = [];
    let j = 0;
    for (const v of pushed) {
        stk.push(v);
        while (stk.length && stk[stk.length - 1] == popped[j]) {
            stk.pop();
            ++j;
        }
    }
    return j == pushed.length;
}
```

### **C#**

```cs
public class Solution {
    public bool ValidateStackSequences(int[] pushed, int[] popped) {
        Stack<int> stk = new Stack<int>();
        int j = 0;
        foreach (int x in pushed)
        {
            stk.Push(x);
            while (stk.Count != 0 && stk.Peek() == popped[j]) {
                stk.Pop();
                ++j;
            }
        }
        return stk.Count == 0;
    }
}
```

### **Rust**

```rust
impl Solution {
    pub fn validate_stack_sequences(pushed: Vec<i32>, popped: Vec<i32>) -> bool {
        let mut stack = Vec::new();
        let mut i = 0;
        for &num in pushed.iter() {
            stack.push(num);
            while !stack.is_empty() && *stack.last().unwrap() == popped[i] {
                stack.pop();
                i += 1;
            }
        }
        stack.len() == 0
    }
}
```

### **JavaScript**

```js
/**
 * @param {number[]} pushed
 * @param {number[]} popped
 * @return {boolean}
 */
var validateStackSequences = function (pushed, popped) {
    let stk = [];
    let j = 0;
    for (const v of pushed) {
        stk.push(v);
        while (stk.length && stk[stk.length - 1] == popped[j]) {
            stk.pop();
            ++j;
        }
    }
    return j == pushed.length;
};
```

### **...**

```

```

<!-- tabs:end -->
