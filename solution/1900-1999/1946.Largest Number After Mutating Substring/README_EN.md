# [1946. Largest Number After Mutating Substring](https://leetcode.com/problems/largest-number-after-mutating-substring)

[中文文档](/solution/1900-1999/1946.Largest%20Number%20After%20Mutating%20Substring/README.md)

## Description

<p>You are given a string <code>num</code>, which represents a large integer. You are also given a <strong>0-indexed</strong> integer array <code>change</code> of length <code>10</code> that maps each digit <code>0-9</code> to another digit. More formally, digit <code>d</code> maps to digit <code>change[d]</code>.</p>

<p>You may <strong>choose</strong> to <b>mutate a single substring</b> of <code>num</code>. To mutate a substring, replace each digit <code>num[i]</code> with the digit it maps to in <code>change</code> (i.e. replace <code>num[i]</code> with <code>change[num[i]]</code>).</p>

<p>Return <em>a string representing the <strong>largest</strong> possible integer after <strong>mutating</strong> (or choosing not to) a <strong>single substring</strong> of </em><code>num</code>.</p>

<p>A <strong>substring</strong> is a contiguous sequence of characters within the string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;<u>1</u>32&quot;, change = [9,8,5,0,3,6,4,2,6,8]
<strong>Output:</strong> &quot;<u>8</u>32&quot;
<strong>Explanation:</strong> Replace the substring &quot;1&quot;:
- 1 maps to change[1] = 8.
Thus, &quot;<u>1</u>32&quot; becomes &quot;<u>8</u>32&quot;.
&quot;832&quot; is the largest number that can be created, so return it.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;<u>021</u>&quot;, change = [9,4,3,5,7,2,1,9,0,6]
<strong>Output:</strong> &quot;<u>934</u>&quot;
<strong>Explanation:</strong> Replace the substring &quot;021&quot;:
- 0 maps to change[0] = 9.
- 2 maps to change[2] = 3.
- 1 maps to change[1] = 4.
Thus, &quot;<u>021</u>&quot; becomes &quot;<u>934</u>&quot;.
&quot;934&quot; is the largest number that can be created, so return it.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;5&quot;, change = [1,4,7,5,3,2,5,6,9,4]
<strong>Output:</strong> &quot;5&quot;
<strong>Explanation:</strong> &quot;5&quot; is already the largest number that can be created, so return it.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= num.length &lt;= 10<sup>5</sup></code></li>
	<li><code>num</code> consists of only digits <code>0-9</code>.</li>
	<li><code>change.length == 10</code></li>
	<li><code>0 &lt;= change[d] &lt;= 9</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumNumber(self, num: str, change: List[int]) -> str:
        s = list(num)
        for i, c in enumerate(s):
            if change[int(c)] > int(c):
                while i < len(s) and int(s[i]) <= change[int(s[i])]:
                    s[i] = str(change[int(s[i])])
                    i += 1
                break
        return ''.join(s)
```

### **Java**

```java
class Solution {
    public String maximumNumber(String num, int[] change) {
        char[] s = num.toCharArray();
        for (int i = 0; i < s.length; ++i) {
            if (change[s[i] - '0'] > s[i] - '0') {
                for (; i < s.length && s[i] - '0' <= change[s[i] - '0']; ++i) {
                    s[i] = (char) (change[s[i] - '0'] + '0');
                }
                break;
            }
        }
        return String.valueOf(s);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string maximumNumber(string num, vector<int>& change) {
        int n = num.size();
        for (int i = 0; i < n; ++i) {
            if (change[num[i] - '0'] > num[i] - '0') {
                for (; i < n && change[num[i] - '0'] >= num[i] - '0'; ++i) {
                    num[i] = change[num[i] - '0'] + '0';
                }
                break;
            }
        }
        return num;
    }
};
```

### **Go**

```go
func maximumNumber(num string, change []int) string {
	s := []byte(num)
	for i, c := range num {
		if change[c-'0'] > int(c-'0') {
			for ; i < len(s) && change[s[i]-'0'] >= int(s[i]-'0'); i++ {
				s[i] = byte(change[s[i]-'0']) + '0'
			}
			break
		}
	}
	return string(s)
}
```

### **...**

```

```

<!-- tabs:end -->
