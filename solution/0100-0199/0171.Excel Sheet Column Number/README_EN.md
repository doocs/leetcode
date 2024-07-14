---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0171.Excel%20Sheet%20Column%20Number/README_EN.md
tags:
    - Math
    - String
---

<!-- problem:start -->

# [171. Excel Sheet Column Number](https://leetcode.com/problems/excel-sheet-column-number)

[中文文档](/solution/0100-0199/0171.Excel%20Sheet%20Column%20Number/README.md)

## Description

<!-- description:start -->

<p>Given a string <code>columnTitle</code> that represents the column title as appears in an Excel sheet, return <em>its corresponding column number</em>.</p>

<p>For example:</p>

<pre>
A -&gt; 1
B -&gt; 2
C -&gt; 3
...
Z -&gt; 26
AA -&gt; 27
AB -&gt; 28 
...
</pre>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> columnTitle = &quot;A&quot;
<strong>Output:</strong> 1
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> columnTitle = &quot;AB&quot;
<strong>Output:</strong> 28
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> columnTitle = &quot;ZY&quot;
<strong>Output:</strong> 701
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= columnTitle.length &lt;= 7</code></li>
	<li><code>columnTitle</code> consists only of uppercase English letters.</li>
	<li><code>columnTitle</code> is in the range <code>[&quot;A&quot;, &quot;FXSHRXW&quot;]</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Base Conversion

The column name in Excel is a representation in base 26. For example, "AB" represents the column number $1 \times 26 + 2 = 28$.

Therefore, we can iterate through the string `columnTitle`, convert each character to its corresponding value, and then calculate the result.

The time complexity is $O(n)$, where $n$ is the length of the string `columnTitle`. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def titleToNumber(self, columnTitle: str) -> int:
        ans = 0
        for c in map(ord, columnTitle):
            ans = ans * 26 + c - ord("A") + 1
        return ans
```

#### Java

```java
class Solution {
    public int titleToNumber(String columnTitle) {
        int ans = 0;
        for (int i = 0; i < columnTitle.length(); ++i) {
            ans = ans * 26 + (columnTitle.charAt(i) - 'A' + 1);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int titleToNumber(string columnTitle) {
        int ans = 0;
        for (char& c : columnTitle) {
            ans = ans * 26 + (c - 'A' + 1);
        }
        return ans;
    }
};
```

#### Go

```go
func titleToNumber(columnTitle string) (ans int) {
	for _, c := range columnTitle {
		ans = ans*26 + int(c-'A'+1)
	}
	return
}
```

#### TypeScript

```ts
function titleToNumber(columnTitle: string): number {
    let ans: number = 0;
    for (const c of columnTitle) {
        ans = ans * 26 + (c.charCodeAt(0) - 'A'.charCodeAt(0) + 1);
    }
    return ans;
}
```

#### C#

```cs
public class Solution {
    public int TitleToNumber(string columnTitle) {
        int ans = 0;
        foreach (char c in columnTitle) {
            ans = ans * 26 + c - 'A' + 1;
        }
        return ans;
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
