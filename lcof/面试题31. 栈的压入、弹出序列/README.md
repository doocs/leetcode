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

借助一个辅助栈实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def validateStackSequences(self, pushed: List[int], popped: List[int]) -> bool:
        s = []
        q = 0
        for num in pushed:
            s.append(num)
            while s and s[-1] == popped[q]:
                s.pop()
                q += 1
        return not s
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> s = new ArrayDeque<>();
        int q = 0;
        for (int num : pushed) {
            s.push(num);
            while (!s.isEmpty() && s.peek() == popped[q]) {
                s.pop();
                ++q;
            }
        }
        return s.isEmpty();
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
    let s = [];
    let q = 0;
    for (let num of pushed) {
        s.push(num);
        while (s.length > 0 && s[s.length - 1] == popped[q]) {
            ++q;
            s.pop();
        }
    }
    return s.length == 0;
};
```

### **C++**

```cpp
class Solution {
public:
    bool validateStackSequences(vector<int>& pushed, vector<int>& popped) {
        stack<int> s;
        int i = 0;
        for (int x : pushed) {
            s.push(x);
            while (!s.empty() && s.top() == popped[i]) {
                s.pop();
                ++i;
            }
        }
        return s.empty();
    }
};
```

### **TypeScript**

```ts
function validateStackSequences(pushed: number[], popped: number[]): boolean {
    const stack = [];
    let i = 0;
    for (const num of pushed) {
        stack.push(num);
        while (stack.length !== 0 && stack[stack.length - 1] === popped[i]) {
            stack.pop();
            i++;
        }
    }
    return stack.length === 0;
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

### **C#**

```cs
public class Solution {
    public bool ValidateStackSequences(int[] pushed, int[] popped) {
        Stack<int> ans = new Stack<int>();
        int q = 0;
        foreach (int x in pushed)
        {
            ans.Push(pushed[x]);
            while (ans.Count != 0 && ans.Peek() == popped[q]) {
                ans.Pop();
                q += 1;
            }
        }
        return ans.Count == 0;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
