# [6. Z 字形变换](https://leetcode-cn.com/problems/zigzag-conversion)

[English Version](/solution/0000-0099/0006.ZigZag%20Conversion/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>将一个给定字符串 <code>s</code> 根据给定的行数 <code>numRows</code> ，以从上往下、从左到右进行 Z 字形排列。</p>

<p>比如输入字符串为 <code>"PAYPALISHIRING"</code> 行数为 <code>3</code> 时，排列如下：</p>

<pre>
P   A   H   N
A P L S I I G
Y   I   R</pre>

<p>之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如：<code>"PAHNAPLSIIGYIR"</code>。</p>

<p>请你实现这个将字符串进行指定行数变换的函数：</p>

<pre>
string convert(string s, int numRows);</pre>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "PAYPALISHIRING", numRows = 3
<strong>输出：</strong>"PAHNAPLSIIGYIR"
</pre>

<strong>示例 2：</strong>

<pre>
<strong>输入：</strong>s = "PAYPALISHIRING", numRows = 4
<strong>输出：</strong>"PINALSIGYAHRPI"
<strong>解释：</strong>
P     I    N
A   L S  I G
Y A   H R
P     I
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "A", numRows = 1
<strong>输出：</strong>"A"
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <= 1000</code></li>
	<li><code>s</code> 由英文字母（小写和大写）、<code>','</code> 和 <code>'.'</code> 组成</li>
	<li><code>1 <= numRows <= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def convert(self, s, numRows):
        """
        :type s: str
        :type numRows: int
        :rtype: str
        """

        if numRows == 0:
            return ""
        elif numRows == 1:
            return s

        Ret = [[] for i in range(numRows)]
        i = 0
        while i < len(s):
            j = 0
            while i < len(s) and j < numRows:  # Vertical lines
                Ret[j].append(s[i])
                i += 1
                j += 1
            j -= 2
            while i < len(s) and j > 0:  # Diagonal lines
                Ret[j].append(s[i])
                j -= 1
                i += 1

        return "".join(["".join(row) for row in Ret])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        StringBuilder result = new StringBuilder();
        int group = 2 * numRows - 2;
        for (int i = 1; i <= numRows; i++) {
            int interval = 2 * numRows - 2 * i;
            if (i == numRows) interval = 2 * numRows - 2;
            int index = i;
            while (index <= s.length()) {
                result.append(s.charAt(index - 1));
                index += interval;
                interval = group - interval;
                if (interval == 0) interval = group;
            }
        }
        return result.toString();
    }
}
```

### **C++**

```cpp
// @ID:6. ZigZag Conversion
// @author:jxdeng3989

class Solution {
public:
    string convert(string s, int numRows) {
        string retstr;
        if(1==numRows)
            return s;
        for(int i=0; i<numRows; ++i)
        {
            retstr.push_back(s[i]);
            int maxspan = 2*(numRows-1);
            int span1 = maxspan-i*2;
            int span2 = maxspan - span1;
             int cntpos = i;
            if(span1==0)
                span1 = span2;
            if(span2==0)
                span2 = span1;
             while(1)
             {   
				 if(cntpos+span1>=s.size())
					 break;
                 cntpos += span1;
                 retstr.push_back(s[cntpos]);
                 
                 if(cntpos+span2>=s.size())
                     break;
                 cntpos += span2;
                 retstr.push_back(s[cntpos]);
             }
        }
        return retstr;
    }
};
```

### **C#**

```cs
using System.Collections.Generic;
using System.Linq;

public class Solution {
    public string Convert(string s, int numRows) {
        if (numRows == 1) return s;
        if (numRows > s.Length) numRows = s.Length;
        var rows = new List<char>[numRows];
        var i = 0;
        var j = 0;
        var down = true;
        while (i < s.Length)
        {
            if (rows[j] == null)
            {
                rows[j] = new List<char>();
            }
            rows[j].Add(s[i]);
            j = j + (down ? 1 : -1);
            if (j == numRows || j < 0)
            {
                down = !down;
                j = j + (down ? 2 : -2);
            }
            ++i;
        }
        return new string(rows.SelectMany(row => row).ToArray());
    }
}
```

### **Go**

```go
func convert(s string, numRows int) string {
	if numRows == 1 {
		return s
	}
	length := len(s)
	result := make([]byte, length)
	step := 2 * numRows - 2
	count := 0
	for i := 0; i < numRows; i++ {
		for j := 0; j + i < length; j += step {
			result[count] = s[i+j]
			count++
			if i != 0 && i != numRows - 1 && j + step - i < length {
				result[count] = s[j+step-i]
				count++
			}
		}
	}
	return string(result)
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @param {number} numRows
 * @return {string}
 */
var convert = function (s, numRows) {
    if (numRows == 1) return s;
    let arr = new Array(numRows);
    for (let i = 0; i < numRows; i++) arr[i] = [];
    let index = 0,
        len = s.length,
        mi = 0,
        isDown = true;
    while (index < len) {
        arr[mi].push(s[index]);
        index++;

        if (mi >= numRows - 1) isDown = false;
        else if (mi <= 0) isDown = true;

        if (isDown) mi++;
        else mi--;
    }
    let ans = [];
    for (let item of arr) {
        ans = ans.concat(item);
    }
    return ans.join('');
};

const s = 'AB',
    numRows = 1;

console.log(convert(s, numRows));
```

### **...**

```

```

<!-- tabs:end -->
