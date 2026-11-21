---
comments: true
difficulty: 简单
rating: 1217
source: 第 216 场周赛 Q1
tags:
    - 数组
    - 字符串
---

<!-- problem:start -->

# [1662. 检查两个字符串数组是否相等](https://leetcode.cn/problems/check-if-two-string-arrays-are-equivalent)

[English Version](/solution/1600-1699/1662.Check%20If%20Two%20String%20Arrays%20are%20Equivalent/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个字符串数组 <code>word1</code> 和 <code>word2</code> 。如果两个数组表示的字符串相同，返回<em> </em><code>true</code><em> </em>；否则，返回 <code>false</code><em> 。</em></p>

<p><strong>数组表示的字符串</strong> 是由数组中的所有元素 <strong>按顺序</strong> 连接形成的字符串。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>word1 = ["ab", "c"], word2 = ["a", "bc"]
<strong>输出：</strong>true
<strong>解释：</strong>
word1 表示的字符串为 "ab" + "c" -> "abc"
word2 表示的字符串为 "a" + "bc" -> "abc"
两个字符串相同，返回 true</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>word1 = ["a", "cb"], word2 = ["ab", "c"]
<strong>输出：</strong>false
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>word1  = ["abc", "d", "defg"], word2 = ["abcddefg"]
<strong>输出：</strong>true
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= word1.length, word2.length <= 10<sup>3</sup></code></li>
	<li><code>1 <= word1[i].length, word2[i].length <= 10<sup>3</sup></code></li>
	<li><code>1 <= sum(word1[i].length), sum(word2[i].length) <= 10<sup>3</sup></code></li>
	<li><code>word1[i]</code> 和 <code>word2[i]</code> 由小写字母组成</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：字符串拼接

<!-- thinking:start -->

> **思考**
>
> 两个数组拼接后是否相等。总长很小，直接 $\texttt{join}$ 再比较即可。

<!-- thinking:end -->

将两个数组中的字符串拼接成两个字符串，然后比较两个字符串是否相等。

时间复杂度 $O(m)$，空间复杂度 $O(m)$。其中 $m$ 为数组中字符串的总长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def arrayStringsAreEqual(self, word1: list[str], word2: list[str]) -> bool:
        return ''.join(word1) == ''.join(word2)
```

#### Java

```java
class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        return String.join("", word1).equals(String.join("", word2));
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool arrayStringsAreEqual(vector<string>& word1, vector<string>& word2) {
        return reduce(word1.cbegin(), word1.cend()) == reduce(word2.cbegin(), word2.cend());
    }
};
```

#### Go

```go
func arrayStringsAreEqual(word1 []string, word2 []string) bool {
	return strings.Join(word1, "") == strings.Join(word2, "")
}
```

#### TypeScript

```ts
function arrayStringsAreEqual(word1: string[], word2: string[]): boolean {
    return word1.join('') === word2.join('');
}
```

#### Rust

```rust
impl Solution {
    pub fn array_strings_are_equal(word1: Vec<String>, word2: Vec<String>) -> bool {
        word1.join("") == word2.join("")
    }
}
```

#### C

```c
bool arrayStringsAreEqual(char** word1, int word1Size, char** word2, int word2Size) {
    int i = 0;
    int j = 0;
    int x = 0;
    int y = 0;
    while (i < word1Size && j < word2Size) {
        if (word1[i][x++] != word2[j][y++]) {
            return 0;
        }

        if (word1[i][x] == '\0') {
            x = 0;
            i++;
        }
        if (word2[j][y] == '\0') {
            y = 0;
            j++;
        }
    }
    return i == word1Size && j == word2Size;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：直接遍历

<!-- thinking:start -->

> **思考**
>
> 方法一生成了两个新串。用四指针在原数组上逐字符比较，空间降为 $O(1)$：字符不等立即失败，走到串尾则切到下一单词。

<!-- thinking:end -->

方法一中，我们是将两个数组中的字符串拼接成两个新的字符串，有额外的空间开销。我们也可以直接遍历两个数组，逐个字符比较。

我们用 $\textit{i1}$ 和 $\textit{j1}$ 表示 $\textit{word1}$ 当前单词与字符下标，用 $\textit{i2}$ 和 $\textit{j2}$ 表示 $\textit{word2}$ 的对应位置。初始时 $\textit{i1}=\textit{j1}=\textit{i2}=\textit{j2}=0$。

每次比较 $\textit{word1}[\textit{i1}][\textit{j1}]$ 和 $\textit{word2}[\textit{i2}][\textit{j2}]$。若不相等，直接返回 `false`。否则将 $\textit{j1}$ 加 $1$，如果它到达当前单词末尾，就把 $\textit{i1}$ 加 $1$ 并将 $\textit{j1}$ 置为 $0$。 $\textit{j2}$ 与 $\textit{i2}$ 按同样的方式前进。

两个数组都遍历完时返回 `true`，否则返回 `false`。

时间复杂度 $O(m)$，空间复杂度 $O(1)$。其中 $m$ 为数组中字符串的总长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def arrayStringsAreEqual(self, word1: list[str], word2: list[str]) -> bool:
        i1 = j1 = i2 = j2 = 0
        while i1 < len(word1) and i2 < len(word2):
            if word1[i1][j1] != word2[i2][j2]:
                return False
            j1 += 1
            if j1 == len(word1[i1]):
                i1, j1 = i1 + 1, 0
            j2 += 1
            if j2 == len(word2[i2]):
                i2, j2 = i2 + 1, 0
        return i1 == len(word1) and i2 == len(word2)
```

#### Java

```java
class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int i1 = 0, j1 = 0, i2 = 0, j2 = 0;
        while (i1 < word1.length && i2 < word2.length) {
            if (word1[i1].charAt(j1) != word2[i2].charAt(j2)) {
                return false;
            }
            ++j1;
            if (j1 == word1[i1].length()) {
                ++i1;
                j1 = 0;
            }
            ++j2;
            if (j2 == word2[i2].length()) {
                ++i2;
                j2 = 0;
            }
        }
        return i1 == word1.length && i2 == word2.length;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool arrayStringsAreEqual(vector<string>& word1, vector<string>& word2) {
        int i1 = 0, j1 = 0, i2 = 0, j2 = 0;
        while (i1 < word1.size() && i2 < word2.size()) {
            if (word1[i1][j1] != word2[i2][j2]) {
                return false;
            }
            ++j1;
            if (j1 == word1[i1].size()) {
                ++i1;
                j1 = 0;
            }
            ++j2;
            if (j2 == word2[i2].size()) {
                ++i2;
                j2 = 0;
            }
        }
        return i1 == word1.size() && i2 == word2.size();
    }
};
```

#### Go

```go
func arrayStringsAreEqual(word1 []string, word2 []string) bool {
	i1, j1, i2, j2 := 0, 0, 0, 0
	for i1 < len(word1) && i2 < len(word2) {
		if word1[i1][j1] != word2[i2][j2] {
			return false
		}
		j1++
		if j1 == len(word1[i1]) {
			i1, j1 = i1+1, 0
		}
		j2++
		if j2 == len(word2[i2]) {
			i2, j2 = i2+1, 0
		}
	}
	return i1 == len(word1) && i2 == len(word2)
}
```

#### TypeScript

```ts
function arrayStringsAreEqual(word1: string[], word2: string[]): boolean {
    let i1 = 0;
    let j1 = 0;
    let i2 = 0;
    let j2 = 0;
    while (i1 < word1.length && i2 < word2.length) {
        if (word1[i1][j1] !== word2[i2][j2]) {
            return false;
        }
        j1++;
        if (j1 === word1[i1].length) {
            i1++;
            j1 = 0;
        }
        j2++;
        if (j2 === word2[i2].length) {
            i2++;
            j2 = 0;
        }
    }
    return i1 === word1.length && i2 === word2.length;
}
```

#### Rust

```rust
impl Solution {
    pub fn array_strings_are_equal(word1: Vec<String>, word2: Vec<String>) -> bool {
        let (mut i1, mut j1, mut i2, mut j2) = (0, 0, 0, 0);
        while i1 < word1.len() && i2 < word2.len() {
            if word1[i1].as_bytes()[j1] != word2[i2].as_bytes()[j2] {
                return false;
            }
            j1 += 1;
            if j1 == word1[i1].len() {
                i1 += 1;
                j1 = 0;
            }
            j2 += 1;
            if j2 == word2[i2].len() {
                i2 += 1;
                j2 = 0;
            }
        }
        i1 == word1.len() && i2 == word2.len()
    }
}
```

#### C

```c
bool arrayStringsAreEqual(char** word1, int word1Size, char** word2, int word2Size) {
    int i1 = 0, j1 = 0, i2 = 0, j2 = 0;
    while (i1 < word1Size && i2 < word2Size) {
        if (word1[i1][j1] != word2[i2][j2]) {
            return 0;
        }
        j1++;
        if (word1[i1][j1] == '\0') {
            i1++;
            j1 = 0;
        }
        j2++;
        if (word2[i2][j2] == '\0') {
            i2++;
            j2 = 0;
        }
    }
    return i1 == word1Size && i2 == word2Size;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
