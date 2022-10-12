# [171. Excel Sheet Column Number](https://leetcode.com/problems/excel-sheet-column-number)

[中文文档](/solution/0100-0199/0171.Excel%20Sheet%20Column%20Number/README.md)

## Description

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

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def titleToNumber(self, columnTitle: str) -> int:
        res = 0
        for c in columnTitle:
            res = res * 26 + (ord(c) - ord('A') + 1)
        return res
```

### **Java**

```java
class Solution {
    public int titleToNumber(String columnTitle) {
        int res = 0;
        for (char c : columnTitle.toCharArray()) {
            res = res * 26 + (c - 'A' + 1);
        }
        return res;
    }
}
```

### **TypeScript**

```ts
function titleToNumber(columnTitle: string): number {
    let res: number = 0;
    for (let char of columnTitle) {
        res = res * 26 + char.charCodeAt(0) - 64;
    }
    return res;
}
```

### **C++**

```cpp
class Solution {
public:
    int titleToNumber(string columnTitle) {
        int res = 0;
        for (char c : columnTitle) {
            res = res * 26 + (c - 'A' + 1);
        }
        return res;
    }
};
```

### **Go**

```go
func titleToNumber(columnTitle string) int {
	res := 0
	for _, c := range columnTitle {
		res = res*26 + int(c-'A'+1)
	}
	return res
}
```

### **...**

```

```

<!-- tabs:end -->
