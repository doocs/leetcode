# [168. Excel 表列名称](https://leetcode-cn.com/problems/excel-sheet-column-title)

[English Version](/solution/0100-0199/0168.Excel%20Sheet%20Column%20Title/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个正整数，返回它在 Excel 表中相对应的列名称。</p>

<p>例如，</p>

<pre>    1 -&gt; A
    2 -&gt; B
    3 -&gt; C
    ...
    26 -&gt; Z
    27 -&gt; AA
    28 -&gt; AB 
    ...
</pre>

<p><strong>示例 1:</strong></p>

<pre><strong>输入:</strong> 1
<strong>输出:</strong> &quot;A&quot;
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre><strong>输入:</strong> 28
<strong>输出:</strong> &quot;AB&quot;
</pre>

<p><strong>示例&nbsp;3:</strong></p>

<pre><strong>输入:</strong> 701
<strong>输出:</strong> &quot;ZY&quot;
</pre>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def convertToTitle(self, columnNumber: int) -> str:
        res = []
        while columnNumber:
            columnNumber -= 1
            res.append(chr(ord('A') + columnNumber % 26))
            columnNumber //= 26
        return ''.join(res[::-1])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String convertToTitle(int columnNumber) {
        StringBuilder res = new StringBuilder();
        while (columnNumber != 0) {
            --columnNumber;
            res.append((char) ('A' + columnNumber % 26));
            columnNumber /= 26;
        }
        return res.reverse().toString();
    }
}
```

### **TypeScript**

```ts
function convertToTitle(columnNumber: number): string {
    let res: string[] = [];
    while (columnNumber > 0) {
        --columnNumber;
        let num: number = columnNumber % 26;
        res.unshift(String.fromCharCode(num + 65));
        columnNumber = Math.floor(columnNumber / 26);
    }
    return res.join("");
}
```

### **C#**

```cs
public class Solution {
    public string ConvertToTitle(int columnNumber) {
        StringBuilder res = new StringBuilder();
        while (columnNumber != 0) {
            --columnNumber;
            res.Append((char) ('A' + columnNumber % 26));
            columnNumber /= 26;
        }
        return new string(res.ToString().Reverse().ToArray());
    }
}
```

### **...**

```

```

<!-- tabs:end -->
