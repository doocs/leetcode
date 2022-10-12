# [2264. Largest 3-Same-Digit Number in String](https://leetcode.com/problems/largest-3-same-digit-number-in-string)

[中文文档](/solution/2200-2299/2264.Largest%203-Same-Digit%20Number%20in%20String/README.md)

## Description

<p>You are given a string <code>num</code> representing a large integer. An integer is <strong>good</strong> if it meets the following conditions:</p>

<ul>
	<li>It is a <strong>substring</strong> of <code>num</code> with length <code>3</code>.</li>
	<li>It consists of only one unique digit.</li>
</ul>

<p>Return <em>the <strong>maximum good </strong>integer as a <strong>string</strong> or an empty string </em><code>&quot;&quot;</code><em> if no such integer exists</em>.</p>

<p>Note:</p>

<ul>
	<li>A <strong>substring</strong> is a contiguous sequence of characters within a string.</li>
	<li>There may be <strong>leading zeroes</strong> in <code>num</code> or a good integer.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;6<strong><u>777</u></strong>133339&quot;
<strong>Output:</strong> &quot;777&quot;
<strong>Explanation:</strong> There are two distinct good integers: &quot;777&quot; and &quot;333&quot;.
&quot;777&quot; is the largest, so we return &quot;777&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;23<strong><u>000</u></strong>19&quot;
<strong>Output:</strong> &quot;000&quot;
<strong>Explanation:</strong> &quot;000&quot; is the only good integer.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;42352338&quot;
<strong>Output:</strong> &quot;&quot;
<strong>Explanation:</strong> No substring of length 3 consists of only one unique digit. Therefore, there are no good integers.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= num.length &lt;= 1000</code></li>
	<li><code>num</code> only consists of digits.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def largestGoodInteger(self, num: str) -> str:
        for i in range(9, -1, -1):
            t = str(i) * 3
            if t in num:
                return t
        return ''
```

### **Java**

```java
class Solution {
    public String largestGoodInteger(String num) {
        for (int i = 9; i >= 0; i--) {
            String ret = String.valueOf(i).repeat(3);
            if (num.contains(ret)) {
                return ret;
            }
        }
        return "";
    }
}
```

### **TypeScript**

```ts
function largestGoodInteger(num: string): string {
    for (let i = 9; i >= 0; i--) {
        const c = String(i).repeat(3);
        if (num.includes(c)) return c;
    }
    return '';
}
```

### **C++**

```cpp
class Solution {
public:
    string largestGoodInteger(string num) {
        for (char i = '9'; i >= '0'; --i) {
            string t(3, i);
            if (num.find(t) != string::npos) return t;
        }
        return "";
    }
};
```

### **Go**

```go
func largestGoodInteger(num string) string {
	for c := '9'; c >= '0'; c-- {
		t := strings.Repeat(string(c), 3)
		if strings.Contains(num, t) {
			return t
		}
	}
	return ""
}
```

### **...**

```

```

<!-- tabs:end -->
