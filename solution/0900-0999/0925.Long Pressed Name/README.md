---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0925.Long%20Pressed%20Name/README.md
tags:
    - 双指针
    - 字符串
---

<!-- problem:start -->

# [925. 长按键入](https://leetcode.cn/problems/long-pressed-name)

[English Version](/solution/0900-0999/0925.Long%20Pressed%20Name/README_EN.md)

## 题目描述

<!-- description:start -->

<p>你的朋友正在使用键盘输入他的名字&nbsp;<code>name</code>。偶尔，在键入字符&nbsp;<code>c</code>&nbsp;时，按键可能会被<em>长按</em>，而字符可能被输入 1 次或多次。</p>

<p>你将会检查键盘输入的字符&nbsp;<code>typed</code>。如果它对应的可能是你的朋友的名字（其中一些字符可能被长按），那么就返回&nbsp;<code>True</code>。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>name = "alex", typed = "aaleex"
<strong>输出：</strong>true
<strong>解释：</strong>'alex' 中的 'a' 和 'e' 被长按。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>name = "saeed", typed = "ssaaedd"
<strong>输出：</strong>false
<strong>解释：</strong>'e' 一定需要被键入两次，但在 typed 的输出中不是这样。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= name.length, typed.length &lt;= 1000</code></li>
	<li><code>name</code> 和&nbsp;<code>typed</code>&nbsp;的字符都是小写字母</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

我们利用两个指针 $i$ 和 $j$ 分别指向字符串 $\textit{typed}$ 和 $\textit{name}$ 的第一个字符，然后开始遍历，如果 $\textit{typed}[j] \neq \textit{name}[i]$，说明两个字符串不匹配，直接返回 $\textit{False}$。否则，我们找到连续相同的字符的下一个位置，分别记为 $x$ 和 $y$，如果 $x - i > y - j$，说明 $\textit{typed}$ 中的字符个数小于 $\textit{name}$ 中的字符个数，直接返回 $\textit{False}$。否则，我们将 $i$ 和 $j$ 更新为 $x$ 和 $y$，继续遍历，直到 $i$ 和 $j$ 分别遍历完 $\textit{name}$ 和 $\textit{typed}$，返回 $\textit{True}$。

时间复杂度 $O(m + n)$，其中 $m$ 和 $n$ 分别是字符串 $\textit{name}$ 和 $\textit{typed}$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isLongPressedName(self, name: str, typed: str) -> bool:
        m, n = len(name), len(typed)
        i = j = 0
        while i < m and j < n:
            if name[i] != typed[j]:
                return False
            x = i + 1
            while x < m and name[x] == name[i]:
                x += 1
            y = j + 1
            while y < n and typed[y] == typed[j]:
                y += 1
            if x - i > y - j:
                return False
            i, j = x, y
        return i == m and j == n
```

#### Java

```java
class Solution {
    public boolean isLongPressedName(String name, String typed) {
        int m = name.length(), n = typed.length();
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (name.charAt(i) != typed.charAt(j)) {
                return false;
            }
            int x = i + 1;
            while (x < m && name.charAt(x) == name.charAt(i)) {
                ++x;
            }
            int y = j + 1;
            while (y < n && typed.charAt(y) == typed.charAt(j)) {
                ++y;
            }
            if (x - i > y - j) {
                return false;
            }
            i = x;
            j = y;
        }
        return i == m && j == n;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isLongPressedName(string name, string typed) {
        int m = name.length(), n = typed.length();
        int i = 0, j = 0;
        while (i < m && j < n) {
            if (name[i] != typed[j]) {
                return false;
            }
            int x = i + 1;
            while (x < m && name[x] == name[i]) {
                ++x;
            }
            int y = j + 1;
            while (y < n && typed[y] == typed[j]) {
                ++y;
            }
            if (x - i > y - j) {
                return false;
            }
            i = x;
            j = y;
        }
        return i == m && j == n;
    }
};
```

#### Go

```go
func isLongPressedName(name string, typed string) bool {
	m, n := len(name), len(typed)
	i, j := 0, 0

	for i < m && j < n {
		if name[i] != typed[j] {
			return false
		}
		x, y := i+1, j+1

		for x < m && name[x] == name[i] {
			x++
		}

		for y < n && typed[y] == typed[j] {
			y++
		}

		if x-i > y-j {
			return false
		}

		i, j = x, y
	}

	return i == m && j == n
}
```

#### TypeScript

```ts
function isLongPressedName(name: string, typed: string): boolean {
    const [m, n] = [name.length, typed.length];
    let i = 0;
    let j = 0;
    while (i < m && j < n) {
        if (name[i] !== typed[j]) {
            return false;
        }
        let x = i + 1;
        while (x < m && name[x] === name[i]) {
            x++;
        }
        let y = j + 1;
        while (y < n && typed[y] === typed[j]) {
            y++;
        }
        if (x - i > y - j) {
            return false;
        }
        i = x;
        j = y;
    }
    return i === m && j === n;
}
```

#### Rust

```rust
impl Solution {
    pub fn is_long_pressed_name(name: String, typed: String) -> bool {
        let (m, n) = (name.len(), typed.len());
        let (mut i, mut j) = (0, 0);
        let s: Vec<char> = name.chars().collect();
        let t: Vec<char> = typed.chars().collect();

        while i < m && j < n {
            if s[i] != t[j] {
                return false;
            }
            let mut x = i + 1;
            while x < m && s[x] == s[i] {
                x += 1;
            }
            let mut y = j + 1;
            while y < n && t[y] == t[j] {
                y += 1;
            }
            if x - i > y - j {
                return false;
            }
            i = x;
            j = y;
        }

        i == m && j == n
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
