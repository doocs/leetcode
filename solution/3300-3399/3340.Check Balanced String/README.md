---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3340.Check%20Balanced%20String/README.md
rating: 1190
source: 第 422 场周赛 Q1
tags:
    - 字符串
---

<!-- problem:start -->

# [3340. 检查平衡字符串](https://leetcode.cn/problems/check-balanced-string)

[English Version](/solution/3300-3399/3340.Check%20Balanced%20String/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个仅由数字 0 - 9 组成的字符串 <code>num</code>。如果偶数下标处的数字之和等于奇数下标处的数字之和，则认为该数字字符串是一个 <b>平衡字符串</b>。</p>

<p>如果 <code>num</code> 是一个 <strong>平衡字符串</strong>，则返回 <code>true</code>；否则，返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong>num<span class="example-io"> = "1234"</span></p>

<p><strong>输出：</strong><span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>偶数下标处的数字之和为 <code>1 + 3 = 4</code>，奇数下标处的数字之和为 <code>2 + 4 = 6</code>。</li>
	<li>由于 4 不等于 6，<code>num</code> 不是平衡字符串。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong>num<span class="example-io"> = "24123"</span></p>

<p><strong>输出：</strong>true</p>

<p><strong>解释：</strong></p>

<ul>
	<li>偶数下标处的数字之和为 <code>2 + 1 + 3 = 6</code>，奇数下标处的数字之和为 <code>4 + 2 = 6</code>。</li>
	<li>由于两者相等，<code>num</code> 是平衡字符串。</li>
</ul>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= num.length &lt;= 100</code></li>
	<li><code>num</code> 仅由数字 0 - 9 组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以用一个长度为 $2$ 的数组 $f$ 来记录偶数下标和奇数下标的数字之和，然后遍历字符串 $\textit{nums}$，根据下标的奇偶性将数字加到对应的位置上，最后判断 $f[0]$ 是否等于 $f[1]$ 即可。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $\textit{nums}$ 的长度。空间复杂度 $O(1)$。

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
