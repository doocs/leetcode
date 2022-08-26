# [1432. Max Difference You Can Get From Changing an Integer](https://leetcode.com/problems/max-difference-you-can-get-from-changing-an-integer)

[中文文档](/solution/1400-1499/1432.Max%20Difference%20You%20Can%20Get%20From%20Changing%20an%20Integer/README.md)

## Description

<p>You are given an integer <code>num</code>. You will apply the following steps exactly <strong>two</strong> times:</p>

<ul>
	<li>Pick a digit <code>x (0 &lt;= x &lt;= 9)</code>.</li>
	<li>Pick another digit <code>y (0 &lt;= y &lt;= 9)</code>. The digit <code>y</code> can be equal to <code>x</code>.</li>
	<li>Replace all the occurrences of <code>x</code> in the decimal representation of <code>num</code> by <code>y</code>.</li>
	<li>The new integer <strong>cannot</strong> have any leading zeros, also the new integer <strong>cannot</strong> be 0.</li>
</ul>

<p>Let <code>a</code> and <code>b</code> be the results of applying the operations to <code>num</code> the first and second times, respectively.</p>

<p>Return <em>the max difference</em> between <code>a</code> and <code>b</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 555
<strong>Output:</strong> 888
<strong>Explanation:</strong> The first time pick x = 5 and y = 9 and store the new integer in a.
The second time pick x = 5 and y = 1 and store the new integer in b.
We have now a = 999 and b = 111 and max difference = 888
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = 9
<strong>Output:</strong> 8
<strong>Explanation:</strong> The first time pick x = 9 and y = 9 and store the new integer in a.
The second time pick x = 9 and y = 1 and store the new integer in b.
We have now a = 9 and b = 1 and max difference = 8
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num &lt;= 10</code><sup>8</sup></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maxDiff(self, num: int) -> int:
        a, b = str(num), str(num)
        for c in a:
            if c != '9':
                a = a.replace(c, '9')
                break
        for i, c in enumerate(b):
            if i == 0:
                if c != '1':
                    b = b.replace(c, '1')
                    break
            else:
                if c != '0' and c != b[0]:
                    b = b.replace(c, '0')
                    break
        return int(a) - int(b)
```

### **Java**

```java
class Solution {
    public int maxDiff(int num) {
        String a = String.valueOf(num);
        String b = String.valueOf(num);
        for (char c : a.toCharArray()) {
            if (c != '9') {
                a = a.replaceAll(String.valueOf(c), "9");
                break;
            }
        }
        for (int i = 0; i < b.length(); ++i) {
            char c = b.charAt(i);
            if (i == 0) {
                if (c != '1') {
                    b = b.replaceAll(String.valueOf(c), "1");
                    break;
                }
            } else {
                if (c != '0' && c != b.charAt(0)) {
                    b = b.replaceAll(String.valueOf(c), "0");
                    break;
                }
            }
        }
        return Integer.parseInt(a) - Integer.parseInt(b);
    }
}
```

### **Go**

```go
func maxDiff(num int) int {
	a, b := num, num
	s := strconv.Itoa(num)
	for i := range s {
		if s[i] != '9' {
			a, _ = strconv.Atoi(strings.ReplaceAll(s, string(s[i]), "9"))
			break
		}
	}
	if s[0] > '1' {
		b, _ = strconv.Atoi(strings.ReplaceAll(s, string(s[0]), "1"))
	} else {
		for i := 1; i < len(s); i++ {
			if s[i] != '0' && s[i] != s[0] {
				b, _ = strconv.Atoi(strings.ReplaceAll(s, string(s[i]), "0"))
				break
			}
		}
	}
	return a - b
}
```

### **...**

```

```

<!-- tabs:end -->
