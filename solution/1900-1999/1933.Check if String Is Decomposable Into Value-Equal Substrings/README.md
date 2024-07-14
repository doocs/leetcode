---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1900-1999/1933.Check%20if%20String%20Is%20Decomposable%20Into%20Value-Equal%20Substrings/README.md
tags:
    - 字符串
---

<!-- problem:start -->

# [1933. 判断字符串是否可分解为值均等的子串 🔒](https://leetcode.cn/problems/check-if-string-is-decomposable-into-value-equal-substrings)

[English Version](/solution/1900-1999/1933.Check%20if%20String%20Is%20Decomposable%20Into%20Value-Equal%20Substrings/README_EN.md)

## 题目描述

<!-- description:start -->

<p>一个字符串的所有字符都是一样的，被称作等值字符串。</p>

<ul>
	<li>举例，<code>"1111"</code> 和 <code>"33" </code>就是等值字符串。</li>
	<li>相比之下，<code>"123"</code>就不是等值字符串。</li>
</ul>

<p>规则：给出一个数字字符串s，将字符串分解成一些等值字符串，如果有且仅有一个等值子字符串长度为2，其他的等值子字符串的长度都是3.</p>

<p>如果能够按照上面的规则分解字符串s，就返回真，否则返回假。</p>

<p>子串就是原字符串中连续的字符序列。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入:</strong> s = "000111000"
<strong>输出:</strong> false
<strong>解释: </strong> s只能被分解长度为3的等值子字符串。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入:</strong> s = "00011111222"
<strong>输出:</strong> true
<strong>解释: </strong>s 能被分解为 ["000","111","11","222"].
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入:</strong> s = "01110002223300"
<strong>输出:</strong> false
<strong>解释: </strong>一个不能被分解的原因是在开头有一个0.
</pre>

<p> </p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;</code><code>= 1000</code></li>
	<li><code>s</code> 仅包含数字。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

遍历字符串 $s$，用双指针 $i$ 和 $j$ 统计每个等值子字符串的长度。若长度模 $3$ 余 $1$，说明该子字符串长度不符合要求，返回 `false`；若长度模 $3$ 余 $2$，说明出现了长度为 $2$ 的子字符串，若此前已经出现过长度为 $2$ 的子字符串，返回 `false`，否则将 $j$ 的值赋给 $i$，继续遍历。

遍历结束后，判断是否出现过长度为 $2$ 的子字符串，若没有，返回 `false`，否则返回 `true`。

时间复杂度 $O(n)$，其中 $n$ 为字符串 $s$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isDecomposable(self, s: str) -> bool:
        i, n = 0, len(s)
        cnt2 = 0
        while i < n:
            j = i
            while j < n and s[j] == s[i]:
                j += 1
            if (j - i) % 3 == 1:
                return False
            cnt2 += (j - i) % 3 == 2
            if cnt2 > 1:
                return False
            i = j
        return cnt2 == 1
```

#### Java

```java
class Solution {
    public boolean isDecomposable(String s) {
        int i = 0, n = s.length();
        int cnt2 = 0;
        while (i < n) {
            int j = i;
            while (j < n && s.charAt(j) == s.charAt(i)) {
                ++j;
            }
            if ((j - i) % 3 == 1) {
                return false;
            }
            if ((j - i) % 3 == 2 && ++cnt2 > 1) {
                return false;
            }
            i = j;
        }
        return cnt2 == 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isDecomposable(string s) {
        int cnt2 = 0;
        for (int i = 0, n = s.size(); i < n;) {
            int j = i;
            while (j < n && s[j] == s[i]) {
                ++j;
            }
            if ((j - i) % 3 == 1) {
                return false;
            }
            cnt2 += (j - i) % 3 == 2;
            if (cnt2 > 1) {
                return false;
            }
            i = j;
        }
        return cnt2 == 1;
    }
};
```

#### Go

```go
func isDecomposable(s string) bool {
	i, n := 0, len(s)
	cnt2 := 0
	for i < n {
		j := i
		for j < n && s[j] == s[i] {
			j++
		}
		if (j-i)%3 == 1 {
			return false
		}
		if (j-i)%3 == 2 {
			cnt2++
			if cnt2 > 1 {
				return false
			}
		}
		i = j
	}
	return cnt2 == 1
}
```

#### TypeScript

```ts
function isDecomposable(s: string): boolean {
    const n = s.length;
    let cnt2 = 0;
    for (let i = 0; i < n; ) {
        let j = i;
        while (j < n && s[j] === s[i]) {
            ++j;
        }
        if ((j - i) % 3 === 1) {
            return false;
        }
        if ((j - i) % 3 === 2 && ++cnt2 > 1) {
            return false;
        }
        i = j;
    }
    return cnt2 === 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isDecomposable(self, s: str) -> bool:
        cnt2 = 0
        for _, g in groupby(s):
            m = len(list(g))
            if m % 3 == 1:
                return False
            cnt2 += m % 3 == 2
            if cnt2 > 1:
                return False
        return cnt2 == 1
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
