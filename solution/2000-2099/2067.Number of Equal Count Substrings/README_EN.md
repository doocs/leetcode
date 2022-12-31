# [2067. Number of Equal Count Substrings](https://leetcode.com/problems/number-of-equal-count-substrings)

[中文文档](/solution/2000-2099/2067.Number%20of%20Equal%20Count%20Substrings/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> string <code>s</code> consisting of only lowercase English letters, and an integer <code>count</code>. A <strong>substring</strong> of <code>s</code> is said to be an <strong>equal count substring</strong> if, for each <strong>unique</strong> letter in the substring, it appears exactly <code>count</code> times in the substring.</p>

<p>Return <em>the number of <strong>equal count substrings</strong> in </em><code>s</code>.</p>

<p>A <strong>substring</strong> is a contiguous non-empty sequence of characters within a string.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

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

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;abcd&quot;, count = 2
<strong>Output:</strong> 0
<strong>Explanation:</strong>
The number of times each letter appears in s is less than count.
Therefore, no substrings in s are equal count substrings, so return 0.
</pre>

<p><strong class="example">Example 3:</strong></p>

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
        ans = 0
        for x in range(1, 27):
            m = count * x
            if m > len(s):
                break
            cnt = Counter()
            y = 0
            for i, c in enumerate(s):
                cnt[c] += 1
                y += cnt[c] == count
                y -= cnt[c] == count + 1
                j = i - m
                if j >= 0:
                    cnt[s[j]] -= 1
                    y += cnt[s[j]] == count
                    y -= cnt[s[j]] == count - 1
                ans += x == y
        return ans
```

### **Java**

```java
class Solution {
    public int equalCountSubstrings(String s, int count) {
        int ans = 0;
        int n = s.length();
        for (int x = 1; x < 27 && count * x <= n; ++x) {
            int m = count * x;
            int[] cnt = new int[26];
            int y = 0;
            for (int i = 0; i < n; ++i) {
                int a = s.charAt(i) - 'a';
                ++cnt[a];
                if (cnt[a] == count) {
                    ++y;
                }
                if (cnt[a] == count + 1) {
                    --y;
                }
                int j = i - m;
                if (j >= 0) {
                    int b = s.charAt(j) - 'a';
                    --cnt[b];
                    if (cnt[b] == count) {
                        ++y;
                    }
                    if (cnt[b] == count - 1) {
                        --y;
                    }
                }
                if (x == y) {
                    ++ans;
                }
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int equalCountSubstrings(string s, int count) {
        int ans = 0;
        int n = s.size();
        int cnt[26];
        for (int x = 1; x < 27 && count * x <= n; ++x) {
            int m = count * x;
            memset(cnt, 0, sizeof cnt);
            int y = 0;
            for (int i = 0; i < n; ++i) {
                int a = s[i] - 'a';
                ++cnt[a];
                y += cnt[a] == count;
                y -= cnt[a] == count + 1;
                int j = i - m;
                if (j >= 0) {
                    int b = s[j] - 'a';
                    --cnt[b];
                    y += cnt[b] == count;
                    y -= cnt[b] == count - 1;
                }
                ans += x == y;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func equalCountSubstrings(s string, count int) (ans int) {
	n := len(s)
	for x := 1; x < 27 && x*count <= n; x++ {
		m := x * count
		y := 0
		cnt := [26]int{}
		for i, c := range s {
			a := c - 'a'
			cnt[a]++
			if cnt[a] == count {
				y++
			}
			if cnt[a] == count+1 {
				y--
			}
			j := i - m
			if j >= 0 {
				b := s[j] - 'a'
				cnt[b]--
				if cnt[b] == count {
					y++
				}
				if cnt[b] == count-1 {
					y--
				}
			}
			if x == y {
				ans++
			}
		}
	}
	return
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @param {number} count
 * @return {number}
 */
var equalCountSubstrings = function (s, count) {
    let ans = 0;
    const n = s.length;
    for (let x = 1; x <= 26 && x * count <= n; ++x) {
        const m = x * count;
        const cnt = new Array(26).fill(0);
        let y = 0;
        for (let i = 0; i < n; ++i) {
            const a = s.charCodeAt(i) - 'a'.charCodeAt(0);
            ++cnt[a];
            y += cnt[a] == count;
            y -= cnt[a] == count + 1;
            const j = i - m;
            if (j >= 0) {
                const b = s.charCodeAt(j) - 'a'.charCodeAt(0);
                --cnt[b];
                y += cnt[b] == count;
                y -= cnt[b] == count - 1;
            }
            ans += x == y;
        }
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
