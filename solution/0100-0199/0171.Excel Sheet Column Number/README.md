---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0100-0199/0171.Excel%20Sheet%20Column%20Number/README.md
tags:
    - 数学
    - 字符串
---

# [171. Excel 表列序号](https://leetcode.cn/problems/excel-sheet-column-number)

[English Version](/solution/0100-0199/0171.Excel%20Sheet%20Column%20Number/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>columnTitle</code> ，表示 Excel 表格中的列名称。返回 <em>该列名称对应的列序号</em>&nbsp;。</p>

<p>例如：</p>

<pre>
A -&gt; 1
B -&gt; 2
C -&gt; 3
...
Z -&gt; 26
AA -&gt; 27
AB -&gt; 28 
...</pre>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> columnTitle = "A"
<strong>输出:</strong> 1
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入: </strong>columnTitle = "AB"
<strong>输出:</strong> 28
</pre>

<p><strong>示例&nbsp;3:</strong></p>

<pre>
<strong>输入: </strong>columnTitle = "ZY"
<strong>输出:</strong> 701</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= columnTitle.length &lt;= 7</code></li>
	<li><code>columnTitle</code> 仅由大写英文组成</li>
	<li><code>columnTitle</code> 在范围 <code>["A", "FXSHRXW"]</code> 内</li>
</ul>

## 解法

### 方法一：进制转换

Excel 表格中的列名称是一种 26 进制的表示方法。例如，"AB" 表示的列序号是 $1 \times 26 + 2 = 28$。

因此，我们可以遍历字符串 `columnTitle`，将每个字符转换为对应的数值，然后计算结果即可。

时间复杂度 $O(n)$，其中 $n$ 是字符串 `columnTitle` 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def titleToNumber(self, columnTitle: str) -> int:
        ans = 0
        for c in map(ord, columnTitle):
            ans = ans * 26 + c - ord("A") + 1
        return ans
```

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

```go
func titleToNumber(columnTitle string) (ans int) {
	for _, c := range columnTitle {
		ans = ans*26 + int(c-'A'+1)
	}
	return
}
```

```ts
function titleToNumber(columnTitle: string): number {
    let ans: number = 0;
    for (const c of columnTitle) {
        ans = ans * 26 + (c.charCodeAt(0) - 'A'.charCodeAt(0) + 1);
    }
    return ans;
}
```

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

<!-- end -->
