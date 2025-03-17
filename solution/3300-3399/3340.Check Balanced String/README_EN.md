---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3340.Check%20Balanced%20String/README_EN.md
rating: 1190
source: Weekly Contest 422 Q1
tags:
    - String
---

<!-- problem:start -->

# [3340. Check Balanced String](https://leetcode.com/problems/check-balanced-string)

[中文文档](/solution/3300-3399/3340.Check%20Balanced%20String/README.md)

## Description

<!-- description:start -->

<p>You are given a string <code>num</code> consisting of only digits. A string of digits is called <b>balanced </b>if the sum of the digits at even indices is equal to the sum of digits at odd indices.</p>

<p>Return <code>true</code> if <code>num</code> is <strong>balanced</strong>, otherwise return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> num<span class="example-io"> = &quot;1234&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The sum of digits at even indices is <code>1 + 3 == 4</code>, and the sum of digits at odd indices is <code>2 + 4 == 6</code>.</li>
	<li>Since 4 is not equal to 6, <code>num</code> is not balanced.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> num<span class="example-io"> = &quot;24123&quot;</span></p>

<p><strong>Output:</strong> true</p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>The sum of digits at even indices is <code>2 + 1 + 3 == 6</code>, and the sum of digits at odd indices is <code>4 + 2 == 6</code>.</li>
	<li>Since both are equal the <code>num</code> is balanced.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= num.length &lt;= 100</code></li>
	<li><code><font face="monospace">num</font></code> consists of digits only</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We can use an array $f$ of length $2$ to record the sum of numbers at even indices and odd indices. Then, we traverse the string $\textit{nums}$ and add the numbers to the corresponding positions based on the parity of the indices. Finally, we check whether $f[0]$ is equal to $f[1]$.

The time complexity is $O(n)$, where $n$ is the length of the string $\textit{nums}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isBalanced(self, num: str) -> bool:
        f = [0, 0]
        for i, x in enumerate(map(int, num)):
            f[i & 1] += x
        return f[0] == f[1]
```

#### Java

```java
class Solution {
    public boolean isBalanced(String num) {
        int[] f = new int[2];
        for (int i = 0; i < num.length(); ++i) {
            f[i & 1] += num.charAt(i) - '0';
        }
        return f[0] == f[1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isBalanced(string num) {
        int f[2]{};
        for (int i = 0; i < num.size(); ++i) {
            f[i & 1] += num[i] - '0';
        }
        return f[0] == f[1];
    }
};
```

#### Go

```go
func isBalanced(num string) bool {
	f := [2]int{}
	for i, c := range num {
		f[i&1] += int(c - '0')
	}
	return f[0] == f[1]
}
```

#### TypeScript

```ts
function isBalanced(num: string): boolean {
    const f = [0, 0];
    for (let i = 0; i < num.length; ++i) {
        f[i & 1] += +num[i];
    }
    return f[0] === f[1];
}
```

#### Rust

```rust
impl Solution {
    pub fn is_balanced(num: String) -> bool {
        let mut f = [0; 2];
        for (i, x) in num.as_bytes().iter().enumerate() {
            f[i & 1] += (x - b'0') as i32;
        }
        f[0] == f[1]
    }
}
```

#### JavaScript

```js
/**
 * @param {string} num
 * @return {boolean}
 */
var isBalanced = function (num) {
    const f = [0, 0];
    for (let i = 0; i < num.length; ++i) {
        f[i & 1] += +num[i];
    }
    return f[0] === f[1];
};
```

#### C#

```cs
public class Solution {
    public bool IsBalanced(string num) {
        int[] f = new int[2];
        for (int i = 0; i < num.Length; ++i) {
            f[i & 1] += num[i] - '0';
        }
        return f[0] == f[1];
    }
}
```

#### PHP

```php
class Solution {
    /**
     * @param String $num
     * @return Boolean
     */
    function isBalanced($num) {
        $f = [0, 0];
        foreach (str_split($num) as $i => $ch) {
            $f[$i & 1] += ord($ch) - 48;
        }
        return $f[0] == $f[1];
    }
}
```

#### Scala

```scala
object Solution {
    def isBalanced(num: String): Boolean = {
        val f = Array(0, 0)
        for (i <- num.indices) {
            f(i & 1) += num(i) - '0'
        }
        f(0) == f(1)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
