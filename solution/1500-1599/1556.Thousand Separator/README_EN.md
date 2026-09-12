---
comments: true
difficulty: Easy
rating: 1271
source: Biweekly Contest 33 Q1
tags:
    - String
---

<!-- problem:start -->

# [1556. Thousand Separator](https://leetcode.com/problems/thousand-separator)

[中文文档](/solution/1500-1599/1556.Thousand%20Separator/README.md)

## Description

<!-- description:start -->

<p>Given an integer <code>n</code>, add a dot (&quot;.&quot;) as the thousands separator and return it in string format.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 987
<strong>Output:</strong> &quot;987&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1234
<strong>Output:</strong> &quot;1.234&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= n &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1

<!-- thinking:start -->

> **Thinking**
>
> Insert a dot every three digits from the right. Converting to a string first requires extra care when the length is a multiple of three; peeling remainders from the low end is simpler.
>
> Repeatedly take $n\bmod 10$ and count digits; after every third digit, if a higher place remains, append a dot. Reverse the collected characters at the end. The loop runs once per digit, $O(\log n)$.

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def thousandSeparator(self, n: int) -> str:
        cnt = 0
        ans = []
        while 1:
            n, v = divmod(n, 10)
            ans.append(str(v))
            cnt += 1
            if n == 0:
                break
            if cnt == 3:
                ans.append('.')
                cnt = 0
        return ''.join(ans[::-1])
```

#### Java

```java
class Solution {
    public String thousandSeparator(int n) {
        int cnt = 0;
        StringBuilder ans = new StringBuilder();
        while (true) {
            int v = n % 10;
            n /= 10;
            ans.append(v);
            ++cnt;
            if (n == 0) {
                break;
            }
            if (cnt == 3) {
                ans.append('.');
                cnt = 0;
            }
        }
        return ans.reverse().toString();
    }
}
```

#### C++

```cpp
class Solution {
public:
    string thousandSeparator(int n) {
        int cnt = 0;
        string ans;
        while (1) {
            int v = n % 10;
            n /= 10;
            ans += to_string(v);
            if (n == 0) break;
            if (++cnt == 3) {
                ans += '.';
                cnt = 0;
            }
        }
        reverse(ans.begin(), ans.end());
        return ans;
    }
};
```

#### Go

```go
func thousandSeparator(n int) string {
	cnt := 0
	ans := []byte{}
	for {
		v := n % 10
		n /= 10
		ans = append(ans, byte('0'+v))
		if n == 0 {
			break
		}
		cnt++
		if cnt == 3 {
			ans = append(ans, '.')
			cnt = 0
		}
	}
	for i, j := 0, len(ans)-1; i < j; i, j = i+1, j-1 {
		ans[i], ans[j] = ans[j], ans[i]
	}
	return string(ans)
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
