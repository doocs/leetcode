---
comments: true
difficulty: Easy
rating: 1217
source: Weekly Contest 216 Q1
tags:
    - Array
    - String
---

<!-- problem:start -->

# [1662. Check If Two String Arrays are Equivalent](https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent)

[中文文档](/solution/1600-1699/1662.Check%20If%20Two%20String%20Arrays%20are%20Equivalent/README.md)

## Description

<!-- description:start -->

<p>Given two string arrays <code>word1</code> and <code>word2</code>, return<em> </em><code>true</code><em> if the two arrays <strong>represent</strong> the same string, and </em><code>false</code><em> otherwise.</em></p>

<p>A string is <strong>represented</strong> by an array if the array elements concatenated <strong>in order</strong> forms the string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> word1 = [&quot;ab&quot;, &quot;c&quot;], word2 = [&quot;a&quot;, &quot;bc&quot;]
<strong>Output:</strong> true
<strong>Explanation:</strong>
word1 represents string &quot;ab&quot; + &quot;c&quot; -&gt; &quot;abc&quot;
word2 represents string &quot;a&quot; + &quot;bc&quot; -&gt; &quot;abc&quot;
The strings are the same, so return true.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> word1 = [&quot;a&quot;, &quot;cb&quot;], word2 = [&quot;ab&quot;, &quot;c&quot;]
<strong>Output:</strong> false
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> word1  = [&quot;abc&quot;, &quot;d&quot;, &quot;defg&quot;], word2 = [&quot;abcddefg&quot;]
<strong>Output:</strong> true
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word1.length, word2.length &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= word1[i].length, word2[i].length &lt;= 10<sup>3</sup></code></li>
	<li><code>1 &lt;= sum(word1[i].length), sum(word2[i].length) &lt;= 10<sup>3</sup></code></li>
	<li><code>word1[i]</code> and <code>word2[i]</code> consist of lowercase letters.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: String Concatenation

<!-- thinking:start -->

> **Thinking**
>
> We ask whether concatenating the two arrays yields the same string. The total length is tiny, so $\texttt{join}$ and compare.

<!-- thinking:end -->

Concatenate the strings in the two arrays into two strings, then compare whether the two strings are equal.

The time complexity is $O(m)$, and the space complexity is $O(m)$, where $m$ is the total length of the strings in the arrays.

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

### Solution 2: Direct Traversal

<!-- thinking:start -->

> **Thinking**
>
> Solution 1 builds two new strings. Four pointers walk the original arrays character by character in $O(1)$ extra space: mismatch fails, and finishing a word advances to the next piece.

<!-- thinking:end -->

In Solution 1, we concatenated the strings in the two arrays into two new strings, which has additional space overhead. We can also directly traverse the two arrays and compare the characters one by one.

Use $\textit{i1}$ and $\textit{j1}$ for the current word and character of $\textit{word1}$, and $\textit{i2}$ and $\textit{j2}$ for the corresponding position in $\textit{word2}$. Initially, $\textit{i1}=\textit{j1}=\textit{i2}=\textit{j2}=0$.

Compare $\textit{word1}[\textit{i1}][\textit{j1}]$ with $\textit{word2}[\textit{i2}][\textit{j2}]$. If they differ, return `false`. Otherwise increment $\textit{j1}$. When it reaches the end of the current word, increment $\textit{i1}$ and set $\textit{j1}$ to $0$. Advance $\textit{j2}$ and $\textit{i2}$ in the same way.

Return `true` when both arrays have been fully traversed, and `false` otherwise.

The time complexity is $O(m)$, and the space complexity is $O(1)$, where $m$ is the total length of the strings in the arrays.

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
