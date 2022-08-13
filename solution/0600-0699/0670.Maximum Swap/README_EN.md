# [670. Maximum Swap](https://leetcode.com/problems/maximum-swap)

[中文文档](/solution/0600-0699/0670.Maximum%20Swap/README.md)

## Description

<p>You are given an integer <code>num</code>. You can swap two digits at most once to get the maximum valued number.</p>

<p>Return <em>the maximum valued number you can get</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = 2736
<strong>Output:</strong> 7236
<strong>Explanation:</strong> Swap the number 2 and the number 7.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = 9973
<strong>Output:</strong> 9973
<strong>Explanation:</strong> No swap.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= num &lt;= 10<sup>8</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumSwap(self, num: int) -> int:
        chars = list(str(num))
        n = len(chars)
        for i in range(n - 1):
            mx = i + 1
            for j in range(i + 1, n):
                if ord(chars[j]) >= ord(chars[mx]):
                    mx = j
            if ord(chars[i]) < ord(chars[mx]):
                chars[i], chars[mx] = chars[mx], chars[i]
                break
        return int(''.join(chars))
```

### **Java**

```java
class Solution {
    public int maximumSwap(int num) {
        char[] chars = String.valueOf(num).toCharArray();
        int n = chars.length;
        for (int i = 0; i < n - 1; ++i) {
            int mx = i + 1;
            for (int j = i + 1; j < n; ++j) {
                if (chars[j] >= chars[mx]) {
                    mx = j;
                }
            }
            if (chars[i] < chars[mx]) {
                char t = chars[i];
                chars[i] = chars[mx];
                chars[mx] = t;
                break;
            }
        }
        return Integer.parseInt(String.valueOf(chars));
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumSwap(int num) {
        string s = to_string(num);
        int n = s.size();
        for (int i = 0; i < n - 1; ++i) {
            int mx = i + 1;
            for (int j = i + 1; j < n; ++j) {
                if (s[j] >= s[mx]) mx = j;
            }
            if (s[i] < s[mx]) {
                swap(s[i], s[mx]);
                break;
            }
        }
        return stoi(s);
    }
};
```

### **Go**

```go
func maximumSwap(num int) int {
	s := strconv.Itoa(num)
	chars := []byte(s)
	n := len(chars)
	for i := range chars[:n-1] {
		mx := i + 1
		for j := i + 1; j < n; j++ {
			if chars[j] >= chars[mx] {
				mx = j
			}
		}
		if chars[i] < chars[mx] {
			chars[i], chars[mx] = chars[mx], chars[i]
			break
		}
	}
	ans, _ := strconv.Atoi(string(chars))
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
