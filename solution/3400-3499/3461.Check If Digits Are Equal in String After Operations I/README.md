---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3400-3499/3461.Check%20If%20Digits%20Are%20Equal%20in%20String%20After%20Operations%20I/README.md
tags:
    - 数学
    - 字符串
    - 组合数学
    - 数论
    - 模拟
---

<!-- problem:start -->

# [3461. 判断操作后字符串中的数字是否相等 I](https://leetcode.cn/problems/check-if-digits-are-equal-in-string-after-operations-i)

[English Version](/solution/3400-3499/3461.Check%20If%20Digits%20Are%20Equal%20in%20String%20After%20Operations%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个由数字组成的字符串 <code>s</code>&nbsp;。重复执行以下操作，直到字符串恰好包含&nbsp;<strong>两个&nbsp;</strong>数字：</p>

<ul>
	<li>从第一个数字开始，对于 <code>s</code> 中的每一对连续数字，计算这两个数字的和&nbsp;<strong>模&nbsp;</strong>10。</li>
	<li>用计算得到的新数字依次替换 <code>s</code>&nbsp;的每一个字符，并保持原本的顺序。</li>
</ul>

<p>如果 <code>s</code>&nbsp;最后剩下的两个数字 <strong>相同</strong> ，返回 <code>true</code>&nbsp;。否则，返回 <code>false</code>。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "3902"</span></p>

<p><strong>输出：</strong> <span class="example-io">true</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>一开始，<code>s = "3902"</code></li>
	<li>第一次操作：
	<ul>
		<li><code>(s[0] + s[1]) % 10 = (3 + 9) % 10 = 2</code></li>
		<li><code>(s[1] + s[2]) % 10 = (9 + 0) % 10 = 9</code></li>
		<li><code>(s[2] + s[3]) % 10 = (0 + 2) % 10 = 2</code></li>
		<li><code>s</code> 变为 <code>"292"</code></li>
	</ul>
	</li>
	<li>第二次操作：
	<ul>
		<li><code>(s[0] + s[1]) % 10 = (2 + 9) % 10 = 1</code></li>
		<li><code>(s[1] + s[2]) % 10 = (9 + 2) % 10 = 1</code></li>
		<li><code>s</code> 变为 <code>"11"</code></li>
	</ul>
	</li>
	<li>由于 <code>"11"</code> 中的数字相同，输出为 <code>true</code>。</li>
</ul>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">s = "34789"</span></p>

<p><strong>输出：</strong> <span class="example-io">false</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li>一开始，<code>s = "34789"</code>。</li>
	<li>第一次操作后，<code>s = "7157"</code>。</li>
	<li>第二次操作后，<code>s = "862"</code>。</li>
	<li>第三次操作后，<code>s = "48"</code>。</li>
	<li>由于 <code>'4' != '8'</code>，输出为 <code>false</code>。</li>
</ul>

<p>&nbsp;</p>
</div>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= s.length &lt;= 100</code></li>
	<li><code>s</code> 仅由数字组成。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：模拟

我们可以模拟题目描述的操作，直到字符串 $s$ 中只剩下两个数字为止，判断这两个数字是否相同。

时间复杂度 $O(n^2)$，空间复杂度 $O(n)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def hasSameDigits(self, s: str) -> bool:
        t = list(map(int, s))
        n = len(t)
        for k in range(n - 1, 1, -1):
            for i in range(k):
                t[i] = (t[i] + t[i + 1]) % 10
        return t[0] == t[1]
```

#### Java

```java
class Solution {
    public boolean hasSameDigits(String s) {
        char[] t = s.toCharArray();
        int n = t.length;
        for (int k = n - 1; k > 1; --k) {
            for (int i = 0; i < k; ++i) {
                t[i] = (char) ((t[i] - '0' + t[i + 1] - '0') % 10 + '0');
            }
        }
        return t[0] == t[1];
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool hasSameDigits(string s) {
        int n = s.size();
        string t = s;
        for (int k = n - 1; k > 1; --k) {
            for (int i = 0; i < k; ++i) {
                t[i] = (t[i] - '0' + t[i + 1] - '0') % 10 + '0';
            }
        }
        return t[0] == t[1];
    }
};
```

#### Go

```go
func hasSameDigits(s string) bool {
	t := []byte(s)
	n := len(t)
	for k := n - 1; k > 1; k-- {
		for i := 0; i < k; i++ {
			t[i] = (t[i]-'0'+t[i+1]-'0')%10 + '0'
		}
	}
	return t[0] == t[1]
}
```

#### TypeScript

```ts
function hasSameDigits(s: string): boolean {
    const t = s.split('').map(Number);
    const n = t.length;
    for (let k = n - 1; k > 1; --k) {
        for (let i = 0; i < k; ++i) {
            t[i] = (t[i] + t[i + 1]) % 10;
        }
    }
    return t[0] === t[1];
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
