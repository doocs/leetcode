# [38. 外观数列](https://leetcode.cn/problems/count-and-say)

[English Version](/solution/0000-0099/0038.Count%20and%20Say/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个正整数 <code>n</code> ，输出外观数列的第 <code>n</code> 项。</p>

<p>「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。</p>

<p>你可以将其视作是由递归公式定义的数字字符串序列：</p>

<ul>
	<li><code>countAndSay(1) = "1"</code></li>
	<li><code>countAndSay(n)</code> 是对 <code>countAndSay(n-1)</code> 的描述，然后转换成另一个数字字符串。</li>
</ul>

<p>前五项如下：</p>

<pre>
1.     1
2.     11
3.     21
4.     1211
5.     111221
第一项是数字 1 
描述前一项，这个数是 <code>1</code> 即 “ 一 个 1 ”，记作 <code>"11"
</code>描述前一项，这个数是 <code>11</code> 即 “ 二 个 1 ” ，记作 <code>"21"
</code>描述前一项，这个数是 <code>21</code> 即 “ 一 个 2 + 一 个 1 ” ，记作 "<code>1211"
</code>描述前一项，这个数是 <code>1211</code> 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "<code>111221"</code>
</pre>

<p>要 <strong>描述</strong> 一个数字字符串，首先要将字符串分割为 <strong>最小</strong> 数量的组，每个组都由连续的最多 <strong>相同字符</strong> 组成。然后对于每个组，先描述字符的数量，然后描述字符，形成一个描述组。要将描述转换为数字字符串，先将每组中的字符数量用数字替换，再将所有描述组连接起来。</p>

<p>例如，数字字符串 <code>"3322251"</code> 的描述如下图：</p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/0000-0099/0038.Count%20and%20Say/images/1629874763-TGmKUh-image.png" style="width: 581px; height: 172px;" />
<ul>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>n = 1
<strong>输出：</strong>"1"
<strong>解释：</strong>这是一个基本样例。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>n = 4
<strong>输出：</strong>"1211"
<strong>解释：</strong>
countAndSay(1) = "1"
countAndSay(2) = 读 "1" = 一 个 1 = "11"
countAndSay(3) = 读 "11" = 二 个 1 = "21"
countAndSay(4) = 读 "21" = 一 个 2 + 一 个 1 = "12" + "11" = "1211"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 30</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countAndSay(self, n: int) -> str:
        s = '1'
        for _ in range(n - 1):
            i = 0
            t = []
            while i < len(s):
                j = i
                while j < len(s) and s[j] == s[i]:
                    j += 1
                t.append(str(j - i))
                t.append(str(s[i]))
                i = j
            s = ''.join(t)
        return s
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String countAndSay(int n) {
        String s = "1";
        while (--n > 0) {
            StringBuilder t = new StringBuilder();
            for (int i = 0; i < s.length();) {
                int j = i;
                while (j < s.length() && s.charAt(j) == s.charAt(i)) {
                    ++j;
                }
                t.append((j - i) + "");
                t.append(s.charAt(i));
                i = j;
            }
            s = t.toString();
        }
        return s;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string countAndSay(int n) {
        string s = "1";
        while (--n) {
            string t = "";
            for (int i = 0; i < s.size();) {
                int j = i;
                while (j < s.size() && s[j] == s[i]) ++j;
                t += to_string(j - i);
                t += s[i];
                i = j;
            }
            s = t;
        }
        return s;
    }
};
```

### **Go**

```go
func countAndSay(n int) string {
	s := "1"
	for k := 0; k < n-1; k++ {
		t := &strings.Builder{}
		i := 0
		for i < len(s) {
			j := i
			for j < len(s) && s[j] == s[i] {
				j++
			}
			t.WriteString(strconv.Itoa(j - i))
			t.WriteByte(s[i])
			i = j
		}
		s = t.String()
	}
	return s
}
```

### **C#**

```cs
using System.Text;
public class Solution {
    public string CountAndSay(int n) {
        var s = "1";
        while (n > 1)
        {
            var sb = new StringBuilder();
            var lastChar = '1';
            var count = 0;
            foreach (var ch in s)
            {
                if (count > 0 && lastChar == ch)
                {
                    ++count;
                }
                else
                {
                    if (count > 0)
                    {
                        sb.Append(count);
                        sb.Append(lastChar);
                    }
                    lastChar = ch;
                    count = 1;
                }
            }
            if (count > 0)
            {
                sb.Append(count);
                sb.Append(lastChar);
            }
            s = sb.ToString();
            --n;
        }
        return s;
    }
}
```

### **JavaScript**

```js
const countAndSay = function (n) {
    let s = '1';

    for (let i = 2; i <= n; i++) {
        let count = 1,
            str = '',
            len = s.length;

        for (let j = 0; j < len; j++) {
            if (j < len - 1 && s[j] === s[j + 1]) {
                count++;
            } else {
                str += `${count}${s[j]}`;
                count = 1;
            }
        }
        s = str;
    }
    return s;
};
```

### **TypeScript**

```ts
function countAndSay(n: number): string {
    let s = '1';
    for (let i = 1; i < n; i++) {
        let t = '';
        let cur = s[0];
        let count = 1;
        for (let j = 1; j < s.length; j++) {
            if (s[j] !== cur) {
                t += `${count}${cur}`;
                cur = s[j];
                count = 0;
            }
            count++;
        }
        t += `${count}${cur}`;
        s = t;
    }
    return s;
}
```

### **...**

```

```

<!-- tabs:end -->
