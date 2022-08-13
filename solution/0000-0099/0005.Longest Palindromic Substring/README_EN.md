# [5. Longest Palindromic Substring](https://leetcode.com/problems/longest-palindromic-substring)

[中文文档](/solution/0000-0099/0005.Longest%20Palindromic%20Substring/README.md)

## Description

<p>Given a string <code>s</code>, return <em>the longest palindromic substring</em> in <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;babad&quot;
<strong>Output:</strong> &quot;bab&quot;
<strong>Explanation:</strong> &quot;aba&quot; is also a valid answer.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;cbbd&quot;
<strong>Output:</strong> &quot;bb&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code> consist of only digits and English letters.</li>
</ul>

## Solutions

Dynamic programming.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def longestPalindrome(self, s: str) -> str:
        n = len(s)
        dp = [[False] * n for _ in range(n)]
        start, mx = 0, 1
        for j in range(n):
            for i in range(j + 1):
                if j - i < 2:
                    dp[i][j] = s[i] == s[j]
                else:
                    dp[i][j] = dp[i + 1][j - 1] and s[i] == s[j]
                if dp[i][j] and mx < j - i + 1:
                    start, mx = i, j - i + 1
        return s[start : start + mx]
```

### **Java**

```java
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int mx = 1, start = 0;
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i <= j; ++i) {
                if (j - i < 2) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                }
                if (dp[i][j] && mx < j - i + 1) {
                    mx = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + mx);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string longestPalindrome(string s) {
        int n = s.size();
        vector<vector<bool>> dp(n, vector<bool>(n, false));
        int start = 0, mx = 1;
        for (int j = 0; j < n; ++j) {
            for (int i = 0; i <= j; ++i) {
                if (j - i < 2) {
                    dp[i][j] = s[i] == s[j];
                } else {
                    dp[i][j] = dp[i + 1][j - 1] && s[i] == s[j];
                }
                if (dp[i][j] && mx < j - i + 1) {
                    start = i;
                    mx = j - i + 1;
                }
            }
        }
        return s.substr(start, mx);
    }
};
```

### **Go**

```go
func longestPalindrome(s string) string {
	n := len(s)
	dp := make([][]bool, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]bool, n)
	}
	mx, start := 1, 0
	for j := 0; j < n; j++ {
		for i := 0; i <= j; i++ {
			if j-i < 2 {
				dp[i][j] = s[i] == s[j]
			} else {
				dp[i][j] = dp[i+1][j-1] && s[i] == s[j]
			}
			if dp[i][j] && mx < j-i+1 {
				mx, start = j-i+1, i
			}
		}
	}
	return s[start : start+mx]
}
```

### **C#**

```cs
public class Solution{
    public string LongestPalindrome(string s) {
        int n = s.Length;
        bool[,] dp = new bool[n, n];
        int mx = 1, start = 0;
        for (int j = 0; j < n; ++j)
        {
            for (int i = 0; i <= j; ++i)
            {
                if (j - i < 2)
                {
                    dp[i, j] = s[i] == s[j];
                }
                else
                {
                    dp[i, j] = dp[i + 1, j - 1] && s[i] == s[j];
                }
                if (dp[i, j] && mx < j - i + 1)
                {
                    mx = j - i + 1;
                    start = i;
                }
            }
        }
        return s.Substring(start, mx);
    }
}
```

### **Nim**

```nim
import std/sequtils

proc longestPalindrome(s: string): string =
  let n: int = s.len()
  var
    dp = newSeqWith[bool](n, newSeqWith[bool](n, false))
    start: int = 0
    mx: int = 1

  for j in 0 ..< n:
    for i in 0 .. j:
      if j - i < 2:
        dp[i][j] = s[i] == s[j]
      else:
        dp[i][j] = dp[i + 1][j - 1] and s[i] == s[j]

      if dp[i][j] and mx < j - i + 1:
        start = i
        mx = j - i + 1

  result = s[start ..< start+mx]
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {string}
 */
var longestPalindrome = function (s) {
    let maxLength = 0,
        left = 0,
        right = 0;
    for (let i = 0; i < s.length; i++) {
        let singleCharLength = getPalLenByCenterChar(s, i, i);
        let doubleCharLength = getPalLenByCenterChar(s, i, i + 1);
        let max = Math.max(singleCharLength, doubleCharLength);
        if (max > maxLength) {
            maxLength = max;
            left = i - parseInt((max - 1) / 2);
            right = i + parseInt(max / 2);
        }
    }
    return s.slice(left, right + 1);
};

function getPalLenByCenterChar(s, left, right) {
    // 中间值为两个字符，确保两个字符相等
    if (s[left] != s[right]) {
        return right - left; // 不相等返回为1个字符串
    }
    while (left > 0 && right < s.length - 1) {
        // 先加减再判断
        left--;
        right++;
        if (s[left] != s[right]) {
            return right - left - 1;
        }
    }
    return right - left + 1;
}
```

### **TypeScript**

```ts
function longestPalindrome(s: string): string {
    const n = s.length;
    const isPass = (l: number, r: number) => {
        while (l < r) {
            if (s[l++] !== s[r--]) {
                return false;
            }
        }
        return true;
    };
    let res = s[0];
    for (let i = 0; i < n - 1; i++) {
        for (let j = n - 1; j > i; j--) {
            if (j - i < res.length) {
                break;
            }
            if (isPass(i, j)) {
                res = s.slice(i, j + 1);
            }
        }
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn longest_palindrome(s: String) -> String {
        let n = s.len();
        let s = s.as_bytes();
        let is_pass = |mut l, mut r| {
            while l < r {
                if s[l] != s[r] {
                    return false;
                }
                l += 1;
                r -= 1;
            }
            true
        };
        let mut res = &s[0..1];
        for i in 0..n - 1 {
            for j in (i + 1..n).rev() {
                if res.len() > j - i {
                    break;
                }
                if is_pass(i, j) {
                    res = &s[i..=j];
                }
            }
        }
        res.into_iter().map(|c| char::from(*c)).collect()
    }
}
```

### **...**

```

```

<!-- tabs:end -->
