# [1208. Get Equal Substrings Within Budget](https://leetcode.com/problems/get-equal-substrings-within-budget)

[中文文档](/solution/1200-1299/1208.Get%20Equal%20Substrings%20Within%20Budget/README.md)

## Description

<p>You are given two strings <code>s</code> and <code>t</code> of the same length and an integer <code>maxCost</code>.</p>

<p>You want to change <code>s</code> to <code>t</code>. Changing the <code>i<sup>th</sup></code> character of <code>s</code> to <code>i<sup>th</sup></code> character of <code>t</code> costs <code>|s[i] - t[i]|</code> (i.e., the absolute difference between the ASCII values of the characters).</p>

<p>Return <em>the maximum length of a substring of </em><code>s</code><em> that can be changed to be the same as the corresponding substring of </em><code>t</code><em> with a cost less than or equal to </em><code>maxCost</code>. If there is no substring from <code>s</code> that can be changed to its corresponding substring from <code>t</code>, return <code>0</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcd&quot;, t = &quot;bcdf&quot;, maxCost = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong> &quot;abc&quot; of s can change to &quot;bcd&quot;.
That costs 3, so the maximum length is 3.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcd&quot;, t = &quot;cdef&quot;, maxCost = 3
<strong>Output:</strong> 1
<strong>Explanation:</strong> Each character in s costs 2 to change to character in t,  so the maximum length is 1.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcd&quot;, t = &quot;acde&quot;, maxCost = 0
<strong>Output:</strong> 1
<strong>Explanation:</strong> You cannot make any change, so the maximum length is 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>t.length == s.length</code></li>
	<li><code>0 &lt;= maxCost &lt;= 10<sup>6</sup></code></li>
	<li><code>s</code> and <code>t</code> consist of only lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def equalSubstring(self, s: str, t: str, maxCost: int) -> int:
        n = len(s)
        presum = [0] * (n + 1)
        for i in range(n):
            presum[i + 1] = presum[i] + abs(ord(s[i]) - ord(t[i]))
        left, right = 0, n

        def check(l):
            i = 0
            while i + l - 1 < n:
                j = i + l - 1
                if presum[j + 1] - presum[i] <= maxCost:
                    return True
                i += 1
            return False

        while left < right:
            mid = (left + right + 1) >> 1
            if check(mid):
                left = mid
            else:
                right = mid - 1
        return left
```

### **Java**

```java
class Solution {
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        int[] presum = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            presum[i + 1] = presum[i] + Math.abs(s.charAt(i) - t.charAt(i));
        }
        int left = 0, right = n;
        while (left < right) {
            int mid = (left + right + 1) >>> 1;
            if (check(mid, presum, maxCost, n)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private boolean check(int l, int[] s, int maxCost, int n) {
        int i = 0;
        while (i + l - 1 < n) {
            int j = i + l - 1;
            if (s[j + 1] - s[i] <= maxCost) {
                return true;
            }
            ++i;
        }
        return false;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int equalSubstring(string s, string t, int maxCost) {
        int n = s.size();
        vector<int> presum(n + 1);
        for (int i = 0; i < n; ++i) presum[i + 1] = presum[i] + abs(s[i] - t[i]);
        int left = 0, right = n;
        while (left < right) {
            int mid = left + right + 1 >> 1;
            if (check(mid, presum, maxCost, n))
                left = mid;
            else
                right = mid - 1;
        }
        return left;
    }

    bool check(int l, vector<int>& s, int maxCost, int n) {
        int i = 0;
        while (i + l - 1 < n) {
            int j = i + l - 1;
            if (s[j + 1] - s[i] <= maxCost) return true;
            ++i;
        }
        return false;
    }
};
```

### **Go**

```go
func equalSubstring(s string, t string, maxCost int) int {
	n := len(s)
	presum := make([]int, n+1)
	for i, c := range s {
		presum[i+1] = presum[i] + abs(int(c)-int(t[i]))
	}

	left, right := 0, n
	check := func(l int) bool {
		i := 0
		for i+l-1 < n {
			j := i + l - 1
			if presum[j+1]-presum[i] <= maxCost {
				return true
			}
			i++
		}
		return false
	}
	for left < right {
		mid := (left + right + 1) >> 1
		if check(mid) {
			left = mid
		} else {
			right = mid - 1
		}
	}
	return left
}

func abs(x int) int {
	if x > 0 {
		return x
	}
	return -x
}
```

### **...**

```

```

<!-- tabs:end -->
