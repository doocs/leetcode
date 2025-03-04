---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2011.Final%20Value%20of%20Variable%20After%20Performing%20Operations/README.md
rating: 1165
source: 第 259 场周赛 Q1
tags:
    - 数组
    - 字符串
    - 模拟
---

<!-- problem:start -->

# [2011. 执行操作后的变量值](https://leetcode.cn/problems/final-value-of-variable-after-performing-operations)

[English Version](/solution/2000-2099/2011.Final%20Value%20of%20Variable%20After%20Performing%20Operations/README_EN.md)

## 题目描述

<!-- description:start -->

<p>存在一种仅支持 4 种操作和 1 个变量 <code>X</code> 的编程语言：</p>

<ul>
	<li><code>++X</code> 和 <code>X++</code> 使变量 <code>X</code> 的值 <strong>加</strong> <code>1</code></li>
	<li><code>--X</code> 和 <code>X--</code> 使变量 <code>X</code> 的值 <strong>减</strong> <code>1</code></li>
</ul>

<p>最初，<code>X</code> 的值是 <code>0</code></p>

<p>给你一个字符串数组 <code>operations</code> ，这是由操作组成的一个列表，返回执行所有操作后，<em> </em><code>X</code> 的 <strong>最终值</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>operations = ["--X","X++","X++"]
<strong>输出：</strong>1
<strong>解释：</strong>操作按下述步骤执行：
最初，X = 0
--X：X 减 1 ，X =  0 - 1 = -1
X++：X 加 1 ，X = -1 + 1 =  0
X++：X 加 1 ，X =  0 + 1 =  1
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>operations = ["++X","++X","X++"]
<strong>输出：</strong>3
<strong>解释：</strong>操作按下述步骤执行： 
最初，X = 0
++X：X 加 1 ，X = 0 + 1 = 1
++X：X 加 1 ，X = 1 + 1 = 2
X++：X 加 1 ，X = 2 + 1 = 3
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>operations = ["X++","++X","--X","X--"]
<strong>输出：</strong>0
<strong>解释：</strong>操作按下述步骤执行：
最初，X = 0
X++：X 加 1 ，X = 0 + 1 = 1
++X：X 加 1 ，X = 1 + 1 = 2
--X：X 减 1 ，X = 2 - 1 = 1
X--：X 减 1 ，X = 1 - 1 = 0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= operations.length &lt;= 100</code></li>
	<li><code>operations[i]</code> 将会是 <code>"++X"</code>、<code>"X++"</code>、<code>"--X"</code> 或 <code>"X--"</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：计数

我们遍历数组 $\textit{operations}$，对于每个操作 $\textit{operations}[i]$，如果包含 `'+'`，那么答案加 $1$，否则答案减 $1$。

时间复杂度 $O(n)$，其中 $n$ 为数组 $\textit{operations}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def finalValueAfterOperations(self, operations: List[str]) -> int:
        return sum(1 if s[1] == '+' else -1 for s in operations)
```

#### Java

```java
class Solution {
    public int finalValueAfterOperations(String[] operations) {
        int ans = 0;
        for (var s : operations) {
            ans += (s.charAt(1) == '+' ? 1 : -1);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int finalValueAfterOperations(vector<string>& operations) {
        int ans = 0;
        for (auto& s : operations) {
            ans += s[1] == '+' ? 1 : -1;
        }
        return ans;
    }
};
```

#### Go

```go
func finalValueAfterOperations(operations []string) (ans int) {
	for _, s := range operations {
		if s[1] == '+' {
			ans += 1
		} else {
			ans -= 1
		}
	}
	return
}
```

#### TypeScript

```ts
function finalValueAfterOperations(operations: string[]): number {
    return operations.reduce((acc, op) => acc + (op[1] === '+' ? 1 : -1), 0);
}
```

#### Rust

```rust
impl Solution {
    pub fn final_value_after_operations(operations: Vec<String>) -> i32 {
        let mut ans = 0;
        for s in operations.iter() {
            ans += if s.as_bytes()[1] == b'+' { 1 } else { -1 };
        }
        ans
    }
}
```

#### JavaScript

```js
/**
 * @param {string[]} operations
 * @return {number}
 */
var finalValueAfterOperations = function (operations) {
    return operations.reduce((acc, op) => acc + (op[1] === '+' ? 1 : -1), 0);
};
```

#### C

```c
int finalValueAfterOperations(char** operations, int operationsSize) {
    int ans = 0;
    for (int i = 0; i < operationsSize; i++) {
        ans += operations[i][1] == '+' ? 1 : -1;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
