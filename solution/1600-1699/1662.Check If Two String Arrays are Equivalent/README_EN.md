---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1600-1699/1662.Check%20If%20Two%20String%20Arrays%20are%20Equivalent/README_EN.md
rating: 1217
source: Weekly Contest 216 Q1
tags:
    - Array
    - String
---

# [1662. Check If Two String Arrays are Equivalent](https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent)

[中文文档](/solution/1600-1699/1662.Check%20If%20Two%20String%20Arrays%20are%20Equivalent/README.md)

## Description

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

## Solutions

### Solution 1: String Concatenation

Concatenate the strings in the two arrays into two strings, then compare whether the two strings are equal.

The time complexity is $O(m)$, and the space complexity is $O(m)$. Here, $m$ is the total length of the strings in the arrays.

<!-- tabs:start -->

```python
class Solution:
    def arrayStringsAreEqual(self, word1: List[str], word2: List[str]) -> bool:
        return ''.join(word1) == ''.join(word2)
```

```java
class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        return String.join("", word1).equals(String.join("", word2));
    }
}
```

```cpp
class Solution {
public:
    bool arrayStringsAreEqual(vector<string>& word1, vector<string>& word2) {
        return reduce(word1.cbegin(), word1.cend()) == reduce(word2.cbegin(), word2.cend());
    }
};
```

```go
func arrayStringsAreEqual(word1 []string, word2 []string) bool {
	return strings.Join(word1, "") == strings.Join(word2, "")
}
```

```ts
function arrayStringsAreEqual(word1: string[], word2: string[]): boolean {
    return word1.join('') === word2.join('');
}
```

```rust
impl Solution {
    pub fn array_strings_are_equal(word1: Vec<String>, word2: Vec<String>) -> bool {
        word1.join("") == word2.join("")
    }
}
```

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

### Solution 2: Direct Traversal

In Solution 1, we concatenated the strings in the two arrays into two new strings, which has additional space overhead. We can also directly traverse the two arrays and compare the characters one by one.

We use two pointers $i$ and $j$ to point to the two string arrays, and another two pointers $x$ and $y$ to point to the corresponding characters in the strings. Initially, $i = j = x = y = 0$.

Each time we compare $word1[i][x]$ and $word2[j][y]$. If they are not equal, we directly return `false`. Otherwise, we increment $x$ and $y$ by $1$. If $x$ or $y$ exceeds the length of the corresponding string, we increment the corresponding string pointer $i$ or $j$ by $1$, and then reset $x$ and $y$ to $0$.

If both string arrays are traversed, we return `true`, otherwise, we return `false`.

The time complexity is $O(m)$, and the space complexity is $O(1)$. Here, $m$ is the total length of the strings in the arrays.

<!-- tabs:start -->

```python
class Solution:
    def arrayStringsAreEqual(self, word1: List[str], word2: List[str]) -> bool:
        i = j = x = y = 0
        while i < len(word1) and j < len(word2):
            if word1[i][x] != word2[j][y]:
                return False
            x, y = x + 1, y + 1
            if x == len(word1[i]):
                x, i = 0, i + 1
            if y == len(word2[j]):
                y, j = 0, j + 1
        return i == len(word1) and j == len(word2)
```

```java
class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        int i = 0, j = 0;
        int x = 0, y = 0;
        while (i < word1.length && j < word2.length) {
            if (word1[i].charAt(x++) != word2[j].charAt(y++)) {
                return false;
            }
            if (x == word1[i].length()) {
                x = 0;
                ++i;
            }
            if (y == word2[j].length()) {
                y = 0;
                ++j;
            }
        }
        return i == word1.length && j == word2.length;
    }
}
```

```cpp
class Solution {
public:
    bool arrayStringsAreEqual(vector<string>& word1, vector<string>& word2) {
        int i = 0, j = 0, x = 0, y = 0;
        while (i < word1.size() && j < word2.size()) {
            if (word1[i][x++] != word2[j][y++]) return false;
            if (x == word1[i].size()) x = 0, i++;
            if (y == word2[j].size()) y = 0, j++;
        }
        return i == word1.size() && j == word2.size();
    }
};
```

```go
func arrayStringsAreEqual(word1 []string, word2 []string) bool {
	var i, j, x, y int
	for i < len(word1) && j < len(word2) {
		if word1[i][x] != word2[j][y] {
			return false
		}
		x, y = x+1, y+1
		if x == len(word1[i]) {
			x, i = 0, i+1
		}
		if y == len(word2[j]) {
			y, j = 0, j+1
		}
	}
	return i == len(word1) && j == len(word2)
}
```

```ts
function arrayStringsAreEqual(word1: string[], word2: string[]): boolean {
    let [i, j, x, y] = [0, 0, 0, 0];
    while (i < word1.length && j < word2.length) {
        if (word1[i][x++] !== word2[j][y++]) {
            return false;
        }
        if (x === word1[i].length) {
            x = 0;
            ++i;
        }
        if (y === word2[j].length) {
            y = 0;
            ++j;
        }
    }
    return i === word1.length && j === word2.length;
}
```

```rust
impl Solution {
    pub fn array_strings_are_equal(word1: Vec<String>, word2: Vec<String>) -> bool {
        let (n, m) = (word1.len(), word2.len());
        let (mut i, mut j, mut x, mut y) = (0, 0, 0, 0);
        while i < n && j < m {
            if word1[i].as_bytes()[x] != word2[j].as_bytes()[y] {
                return false;
            }
            x += 1;
            y += 1;
            if x == word1[i].len() {
                x = 0;
                i += 1;
            }
            if y == word2[j].len() {
                y = 0;
                j += 1;
            }
        }
        i == n && j == m
    }
}
```

<!-- tabs:end -->

<!-- end -->
