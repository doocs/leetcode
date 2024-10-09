---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0946.Validate%20Stack%20Sequences/README.md
tags:
    - 栈
    - 数组
    - 模拟
---

<!-- problem:start -->

# [946. 验证栈序列](https://leetcode.cn/problems/validate-stack-sequences)

[English Version](/solution/0900-0999/0946.Validate%20Stack%20Sequences/README_EN.md)

## 题目描述

<!-- description:start -->

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

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：栈模拟

我们遍历 $\textit{pushed}$ 数组，对于当前遍历到的元素 $x$，我们将其压入栈 $\textit{stk}$ 中，然后判断栈顶元素是否和 $\textit{popped}$ 数组中下一个要弹出的元素相等，如果相等，我们就将栈顶元素弹出并将 $\textit{popped}$ 数组中下一个要弹出的元素的索引 $i$ 加一。最后，如果要弹出的元素都能按照 $\textit{popped}$ 数组的顺序弹出，返回 $\textit{true}$，否则返回 $\textit{false}$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为 $\textit{pushed}$ 数组的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def validateStackSequences(self, pushed: List[int], popped: List[int]) -> bool:
        stk = []
        i = 0
        for x in pushed:
            stk.append(x)
            while stk and stk[-1] == popped[i]:
                stk.pop()
                i += 1
        return i == len(popped)
```

#### Java

```java
class Solution {
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stk = new ArrayDeque<>();
        int i = 0;
        for (int x : pushed) {
            stk.push(x);
            while (!stk.isEmpty() && stk.peek() == popped[i]) {
                stk.pop();
                ++i;
            }
        }
        return i == popped.length;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool validateStackSequences(vector<int>& pushed, vector<int>& popped) {
        stack<int> stk;
        int i = 0;
        for (int x : pushed) {
            stk.push(x);
            while (stk.size() && stk.top() == popped[i]) {
                stk.pop();
                ++i;
            }
        }
        return i == popped.size();
    }
};
```

#### Go

```go
func validateStackSequences(pushed []int, popped []int) bool {
	stk := []int{}
	i := 0
	for _, x := range pushed {
		stk = append(stk, x)
		for len(stk) > 0 && stk[len(stk)-1] == popped[i] {
			stk = stk[:len(stk)-1]
			i++
		}
	}
	return i == len(popped)
}
```

#### TypeScript

```ts
function validateStackSequences(pushed: number[], popped: number[]): boolean {
    const stk: number[] = [];
    let i = 0;
    for (const x of pushed) {
        stk.push(x);
        while (stk.length && stk.at(-1)! === popped[i]) {
            stk.pop();
            i++;
        }
    }
    return i === popped.length;
}
```

#### Rust

```rust
impl Solution {
    pub fn validate_stack_sequences(pushed: Vec<i32>, popped: Vec<i32>) -> bool {
        let mut stk: Vec<i32> = Vec::new();
        let mut i = 0;
        for &x in &pushed {
            stk.push(x);
            while !stk.is_empty() && *stk.last().unwrap() == popped[i] {
                stk.pop();
                i += 1;
            }
        }
        i == popped.len()
    }
}
```

#### JavaScript

```js
/**
 * @param {number[]} pushed
 * @param {number[]} popped
 * @return {boolean}
 */
var validateStackSequences = function (pushed, popped) {
    const stk = [];
    let i = 0;
    for (const x of pushed) {
        stk.push(x);
        while (stk.length && stk.at(-1) === popped[i]) {
            stk.pop();
            i++;
        }
    }
    return i === popped.length;
};
```

#### C#

```cs
public class Solution {
    public bool ValidateStackSequences(int[] pushed, int[] popped) {
        Stack<int> stk = new Stack<int>();
        int i = 0;

        foreach (int x in pushed) {
            stk.Push(x);
            while (stk.Count > 0 && stk.Peek() == popped[i]) {
                stk.Pop();
                i++;
            }
        }

        return i == popped.Length;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
