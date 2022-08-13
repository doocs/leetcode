# [2067. Number of Equal Count Substrings](https://leetcode.com/problems/number-of-equal-count-substrings)

[中文文档](/solution/2000-2099/2067.Number%20of%20Equal%20Count%20Substrings/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> string <code>s</code> consisting of only lowercase English letters, and an integer <code>count</code>. A <strong>substring</strong> of <code>s</code> is said to be an <strong>equal count substring</strong> if, for each <strong>unique</strong> letter in the substring, it appears exactly <code>count</code> times in the substring.</p>

<p>Return <em>the number of <strong>equal count substrings</strong> in </em><code>s</code>.</p>

<p>A <strong>substring</strong> is a contiguous non-empty sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aaabcbbcc&quot;, count = 3
<strong>Output:</strong> 3
<strong>Explanation:</strong>
The substring that starts at index 0 and ends at index 2 is &quot;aaa&quot;.
The letter &#39;a&#39; in the substring appears exactly 3 times.
The substring that starts at index 3 and ends at index 8 is &quot;bcbbcc&quot;.
The letters &#39;b&#39; and &#39;c&#39; in the substring appear exactly 3 times.
The substring that starts at index 0 and ends at index 8 is &quot;aaabcbbcc&quot;.
The letters &#39;a&#39;, &#39;b&#39;, and &#39;c&#39; in the substring appear exactly 3 times.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcd&quot;, count = 2
<strong>Output:</strong> 0
<strong>Explanation:</strong>
The number of times each letter appears in s is less than count.
Therefore, no substrings in s are equal count substrings, so return 0.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;a&quot;, count = 5
<strong>Output:</strong> 0
<strong>Explanation:</strong>
The number of times each letter appears in s is less than count.
Therefore, no substrings in s are equal count substrings, so return 0</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= count &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def equalCountSubstrings(self, s: str, count: int) -> int:
        n = len(s)
        if count > n:
            return 0
        counter = [[0] * 26 for _ in range(n + 1)]

        def check(i, j):
            c1 = counter[i]
            c2 = counter[j + 1]
            for k in range(26):
                if c2[k] == 0 or c1[k] == c2[k]:
                    continue
                if c2[k] - c1[k] != count:
                    return False
            return True

        ans = 0
        for i, c in enumerate(s):
            idx = ord(c) - ord('a')
            for j in range(26):
                counter[i + 1][j] = counter[i][j]
            counter[i + 1][idx] = counter[i][idx] + 1
            l = 0
            for _ in range(26):
                l += count
                j = i - l + 1
                if j < 0:
                    break
                ans += check(j, i)
        return ans
```

### **Java**

```java
class Solution {
    public int equalCountSubstrings(String s, int count) {
        int n = s.length();
        if (count > n) {
            return 0;
        }
        int[][] counter = new int[n + 1][26];
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int idx = s.charAt(i) - 'a';
            for (int j = 0; j < 26; ++j) {
                counter[i + 1][j] = counter[i][j];
            }
            counter[i + 1][idx] = counter[i][idx] + 1;
            int l = 0;
            for (int k = 0; k < 26; ++k) {
                l += count;
                int j = i - l + 1;
                if (j < 0) {
                    break;
                }
                ans += check(j, i, count, counter) ? 1 : 0;
            }
        }
        return ans;
    }

    private boolean check(int i, int j, int count, int[][] counter) {
        int[] c1 = counter[i];
        int[] c2 = counter[j + 1];
        for (int k = 0; k < 26; ++k) {
            if (c2[k] == 0 || c1[k] == c2[k]) {
                continue;
            }
            if (c2[k] - c1[k] != count) {
                return false;
            }
        }
        return true;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int equalCountSubstrings(string s, int count) {
        int n = s.size();
        if (count > n) return 0;
        vector<vector<int>> counter(n + 1, vector<int>(26));
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            int idx = s[i] - 'a';
            for (int j = 0; j < 26; ++j) counter[i + 1][j] = counter[i][j];
            counter[i + 1][idx] = counter[i][idx] + 1;
            int l = 0;
            for (int k = 0; k < 26; ++k) {
                l += count;
                int j = i - l + 1;
                if (j < 0) break;
                ans += check(j, i, count, counter);
            }
        }
        return ans;
    }

    bool check(int i, int j, int count, vector<vector<int>>& counter) {
        auto& c1 = counter[i];
        auto& c2 = counter[j + 1];
        for (int k = 0; k < 26; ++k) {
            if (c2[k] == 0 || c1[k] == c2[k]) continue;
            if (c2[k] - c1[k] != count) return false;
        }
        return true;
    }
};
```

### **Go**

```go
func equalCountSubstrings(s string, count int) int {
	n := len(s)
	if count > n {
		return 0
	}
	counter := make([][]int, n+1)
	for i := range counter {
		counter[i] = make([]int, 26)
	}
	ans := 0
	check := func(i, j int) bool {
		c1, c2 := counter[i], counter[j+1]
		for k := 0; k < 26; k++ {
			if c2[k] == 0 || c1[k] == c2[k] {
				continue
			}
			if c2[k]-c1[k] != count {
				return false
			}
		}
		return true
	}
	for i, c := range s {
		idx := c - 'a'
		for j := 0; j < 26; j++ {
			counter[i+1][j] = counter[i][j]
		}
		counter[i+1][idx] = counter[i][idx] + 1
		l := 0
		for k := 0; k < 26; k++ {
			l += count
			j := i - l + 1
			if j < 0 {
				break
			}
			if check(j, i) {
				ans++
			}
		}
	}
	return ans
}
```

### **...**

```

```

<!-- tabs:end -->
