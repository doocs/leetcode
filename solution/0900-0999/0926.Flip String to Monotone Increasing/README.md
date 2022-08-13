# [926. 将字符串翻转到单调递增](https://leetcode.cn/problems/flip-string-to-monotone-increasing)

[English Version](/solution/0900-0999/0926.Flip%20String%20to%20Monotone%20Increasing/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>如果一个二进制字符串，是以一些 <code>0</code>（可能没有 <code>0</code>）后面跟着一些 <code>1</code>（也可能没有 <code>1</code>）的形式组成的，那么该字符串是 <strong>单调递增 </strong>的。</p>

<p>给你一个二进制字符串 <code>s</code>，你可以将任何 <code>0</code> 翻转为 <code>1</code> 或者将 <code>1</code> 翻转为 <code>0</code> 。</p>

<p>返回使 <code>s</code> 单调递增的最小翻转次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s = "00110"
<strong>输出：</strong>1
<strong>解释：</strong>翻转最后一位得到 00111.
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s = "010110"
<strong>输出：</strong>2
<strong>解释：</strong>翻转得到 011111，或者是 000111。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>s = "00011000"
<strong>输出：</strong>2
<strong>解释：</strong>翻转得到 00000000。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code> 为 <code>'0'</code> 或 <code>'1'</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：前缀和**

我们需要找到一个分界点 `i`，使 `[:i]` 全为 0，`[i:]` 全为 1，并且翻转次数最少，问题就转换成计算 `i` 的左右两侧的翻转次数，可以用前缀和进行优化。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minFlipsMonoIncr(self, s: str) -> int:
        n = len(s)
        left, right = [0] * (n + 1), [0] * (n + 1)
        ans = 0x3F3F3F3F
        for i in range(1, n + 1):
            left[i] = left[i - 1] + (1 if s[i - 1] == '1' else 0)
        for i in range(n - 1, -1, -1):
            right[i] = right[i + 1] + (1 if s[i] == '0' else 0)
        for i in range(0, n + 1):
            ans = min(ans, left[i] + right[i])
        return ans
```

```python
class Solution:
    def minFlipsMonoIncr(self, s: str) -> int:
        n = len(s)
        presum = [0] * (n + 1)
        for i, c in enumerate(s):
            presum[i + 1] = presum[i] + int(c)
        ans = presum[-1]
        for i in range(n):
            ans = min(ans, presum[i] + n - i - (presum[-1] - presum[i]))
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minFlipsMonoIncr(String s) {
        int n = s.length();
        int[] left = new int[n + 1];
        int[] right = new int[n + 1];
        int ans = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            left[i] = left[i - 1] + (s.charAt(i - 1) == '1' ? 1 : 0);
        }
        for (int i = n - 1; i >= 0; i--) {
            right[i] = right[i + 1] + (s.charAt(i) == '0' ? 1 : 0);
        }
        for (int i = 0; i <= n; i++) {
            ans = Math.min(ans, left[i] + right[i]);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int minFlipsMonoIncr(String s) {
        int n = s.length();
        int[] presum = new int[n + 1];
        for (int i = 0; i < n; ++i) {
            presum[i + 1] = presum[i] + (s.charAt(i) - '0');
        }
        int ans = presum[n];
        for (int i = 0; i < n; ++i) {
            ans = Math.min(ans, presum[i] + n - i - (presum[n] - presum[i]));
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minFlipsMonoIncr(string s) {
        int n = s.size();
        vector<int> left(n + 1, 0), right(n + 1, 0);
        int ans = INT_MAX;
        for (int i = 1; i <= n; ++i) {
            left[i] = left[i - 1] + (s[i - 1] == '1');
        }
        for (int i = n - 1; i >= 0; --i) {
            right[i] = right[i + 1] + (s[i] == '0');
        }
        for (int i = 0; i <= n; i++) {
            ans = min(ans, left[i] + right[i]);
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int minFlipsMonoIncr(string s) {
        int n = s.size();
        vector<int> presum(n + 1);
        for (int i = 0; i < n; ++i) presum[i + 1] = presum[i] + (s[i] == '1');
        int ans = presum[n];
        for (int i = 0; i < n; ++i) ans = min(ans, presum[i] + n - i - (presum[n] - presum[i]));
        return ans;
    }
};
```

### **Go**

```go
func minFlipsMonoIncr(s string) int {
	n := len(s)
	left, right := make([]int, n+1), make([]int, n+1)
	ans := math.MaxInt32
	for i := 1; i <= n; i++ {
		left[i] = left[i-1]
		if s[i-1] == '1' {
			left[i]++
		}
	}
	for i := n - 1; i >= 0; i-- {
		right[i] = right[i+1]
		if s[i] == '0' {
			right[i]++
		}
	}
	for i := 0; i <= n; i++ {
		ans = min(ans, left[i]+right[i])
	}
	return ans
}

func min(x, y int) int {
	if x < y {
		return x
	}
	return y
}
```

```go
func minFlipsMonoIncr(s string) int {
	n := len(s)
	presum := make([]int, n+1)
	for i, c := range s {
		presum[i+1] = presum[i] + int(c-'0')
	}
	ans := presum[n]
	for i := range s {
		ans = min(ans, presum[i]+n-i-(presum[n]-presum[i]))
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **JavaScript**

```js
/**
 * @param {string} s
 * @return {number}
 */
var minFlipsMonoIncr = function (s) {
    const n = s.length;
    let presum = new Array(n + 1).fill(0);
    for (let i = 0; i < n; ++i) {
        presum[i + 1] = presum[i] + (s[i] == '1');
    }
    let ans = presum[n];
    for (let i = 0; i < n; ++i) {
        ans = Math.min(ans, presum[i] + n - i - (presum[n] - presum[i]));
    }
    return ans;
};
```

### **...**

```

```

<!-- tabs:end -->
