# [1653. 使字符串平衡的最少删除次数](https://leetcode.cn/problems/minimum-deletions-to-make-string-balanced)

[English Version](/solution/1600-1699/1653.Minimum%20Deletions%20to%20Make%20String%20Balanced/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个字符串&nbsp;<code>s</code>&nbsp;，它仅包含字符&nbsp;<code>'a'</code> 和&nbsp;<code>'b'</code>​​​​ 。</p>

<p>你可以删除&nbsp;<code>s</code>&nbsp;中任意数目的字符，使得&nbsp;<code>s</code> <strong>平衡</strong>&nbsp;。当不存在下标对&nbsp;<code>(i,j)</code>&nbsp;满足&nbsp;<code>i &lt; j</code> ，且&nbsp;<code>s[i] = 'b'</code> 的同时&nbsp;<code>s[j]= 'a'</code> ，此时认为 <code>s</code> 是 <strong>平衡 </strong>的。</p>

<p>请你返回使 <code>s</code>&nbsp;<strong>平衡</strong>&nbsp;的 <strong>最少</strong>&nbsp;删除次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "aababbab"
<b>输出：</b>2
<b>解释：</b>你可以选择以下任意一种方案：
下标从 0 开始，删除第 2 和第 6 个字符（"aa<strong>b</strong>abb<strong>a</strong>b" -&gt; "aaabbb"），
下标从 0 开始，删除第 3 和第 6 个字符（"aab<strong>a</strong>bb<strong>a</strong>b" -&gt; "aabbbb"）。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "bbaaaaabb"
<b>输出：</b>2
<b>解释：</b>唯一的最优解是删除最前面两个字符。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s[i]</code>&nbsp;要么是&nbsp;<code>'a'</code> 要么是&nbsp;<code>'b'</code>​<strong>&nbsp;</strong>。​</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：动态规划**

我们定义 $f[i]$ 表示前 $i$ 个字符中，删除最少的字符数，使得字符串平衡。初始时 $f[0]=0$。答案为 $f[n]$。

我们遍历字符串 $s$，维护变量 $b$，表示当前遍历到的位置之前的字符中，字符 $b$ 的个数。

-   如果当前字符为 `'b'`，此时不影响前 $i$ 个字符的平衡性，因此 $f[i]=f[i-1]$，然后我们更新 $b \leftarrow b+1$。
-   如果当前字符为 `'a'`，此时我们可以选择删除当前字符，那么有 $f[i]=f[i-1]+1$；也可以选择删除之前的字符 $b$，那么有 $f[i]=b$。因此我们取两者的最小值，即 $f[i]=\min(f[i-1]+1,b)$。

综上，我们可以得到状态转移方程：

$$
f[i]=\begin{cases}
f[i-1], & s[i-1]='b'\\
\min(f[i-1]+1,b), & s[i-1]='a'
\end{cases}
$$

最终答案为 $f[n]$。

我们注意到，状态转移方程中只与前一个状态以及变量 $b$ 有关，因此我们可以仅用一个答案变量 $ans$ 维护当前的 $f[i]$，并不需要开辟数组 $f$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为字符串 $s$ 的长度。

**方法二：枚举 + 前缀和**

我们可以枚举字符串 $s$ 中的每一个位置 $i$，将字符串 $s$ 分成两部分，分别为 $s[0,..,i-1]$ 和 $s[i+1,..n-1]$，要使得字符串平衡，我们在当前位置 $i$ 需要删除的字符数为 $s[0,..,i-1]$ 中字符 $b$ 的个数加上 $s[i+1,..n-1]$ 中字符 $a$ 的个数。

因此，我们维护两个变量 $lb$ 和 $ra$ 分别表示 $s[0,..,i-1]$ 中字符 $b$ 的个数以及 $s[i+1,..n-1]$ 中字符 $a$ 的个数，那么我们需要删除的字符数为 $lb+ra$。枚举过程中，更新变量 $lb$ 和 $ra$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为字符串 $s$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimumDeletions(self, s: str) -> int:
        n = len(s)
        f = [0] * (n + 1)
        b = 0
        for i, c in enumerate(s, 1):
            if c == 'b':
                f[i] = f[i - 1]
                b += 1
            else:
                f[i] = min(f[i - 1] + 1, b)
        return f[n]
```

```python
class Solution:
    def minimumDeletions(self, s: str) -> int:
        ans = b = 0
        for c in s:
            if c == 'b':
                b += 1
            else:
                ans = min(ans + 1, b)
        return ans
```

```python
class Solution:
    def minimumDeletions(self, s: str) -> int:
        lb, ra = 0, s.count('a')
        ans = len(s)
        for c in s:
            ra -= c == 'a'
            ans = min(ans, lb + ra)
            lb += c == 'b'
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimumDeletions(String s) {
        int n = s.length();
        int[] f = new int[n + 1];
        int b = 0;
        for (int i = 1; i <= n; ++i) {
            if (s.charAt(i - 1) == 'b') {
                f[i] = f[i - 1];
                ++b;
            } else {
                f[i] = Math.min(f[i - 1] + 1, b);
            }
        }
        return f[n];
    }
}
```

```java
class Solution {
    public int minimumDeletions(String s) {
        int n = s.length();
        int ans = 0, b = 0;
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == 'b') {
                ++b;
            } else {
                ans = Math.min(ans + 1, b);
            }
        }
        return ans;
    }
}
```

```java
class Solution {
    public int minimumDeletions(String s) {
        int lb = 0, ra = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (s.charAt(i) == 'a') {
                ++ra;
            }
        }
        int ans = n;
        for (int i = 0; i < n; ++i) {
            ra -= (s.charAt(i) == 'a' ? 1 : 0);
            ans = Math.min(ans, lb + ra);
            lb += (s.charAt(i) == 'b' ? 1 : 0);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimumDeletions(string s) {
        int n = s.size();
        int f[n + 1];
        memset(f, 0, sizeof(f));
        int b = 0;
        for (int i = 1; i <= n; ++i) {
            if (s[i - 1] == 'b') {
                f[i] = f[i - 1];
                ++b;
            } else {
                f[i] = min(f[i - 1] + 1, b);
            }
        }
        return f[n];
    }
};
```

```cpp
class Solution {
public:
    int minimumDeletions(string s) {
        int ans = 0, b = 0;
        for (char& c : s) {
            if (c == 'b') {
                ++b;
            } else {
                ans = min(ans + 1, b);
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int minimumDeletions(string s) {
        int lb = 0, ra = count(s.begin(), s.end(), 'a');
        int ans = ra;
        for (char& c : s) {
            ra -= c == 'a';
            ans = min(ans, lb + ra);
            lb += c == 'b';
        }
        return ans;
    }
};
```

### **Go**

```go
func minimumDeletions(s string) int {
	n := len(s)
	f := make([]int, n+1)
	b := 0
	for i, c := range s {
		i++
		if c == 'b' {
			f[i] = f[i-1]
			b++
		} else {
			f[i] = min(f[i-1]+1, b)
		}
	}
	return f[n]
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

```go
func minimumDeletions(s string) int {
	ans, b := 0, 0
	for _, c := range s {
		if c == 'b' {
			b++
		} else {
			ans = min(ans+1, b)
		}
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

```go
func minimumDeletions(s string) int {
	lb, ra := 0, strings.Count(s, "a")
	ans := ra
	for _, c := range s {
		if c == 'a' {
			ra--
		}
		if t := lb + ra; ans > t {
			ans = t
		}
		if c == 'b' {
			lb++
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function minimumDeletions(s: string): number {
    const n = s.length;
    const f = new Array(n + 1).fill(0);
    let b = 0;
    for (let i = 1; i <= n; ++i) {
        if (s.charAt(i - 1) === 'b') {
            f[i] = f[i - 1];
            ++b;
        } else {
            f[i] = Math.min(f[i - 1] + 1, b);
        }
    }
    return f[n];
}
```

```ts
function minimumDeletions(s: string): number {
    const n = s.length;
    let ans = 0,
        b = 0;
    for (let i = 0; i < n; ++i) {
        if (s.charAt(i) === 'b') {
            ++b;
        } else {
            ans = Math.min(ans + 1, b);
        }
    }
    return ans;
}
```

```ts
function minimumDeletions(s: string): number {
    let lb = 0,
        ra = 0;
    const n = s.length;
    for (let i = 0; i < n; ++i) {
        if (s.charAt(i) === 'a') {
            ++ra;
        }
    }
    let ans = n;
    for (let i = 0; i < n; ++i) {
        ra -= s.charAt(i) === 'a' ? 1 : 0;
        ans = Math.min(ans, lb + ra);
        lb += s.charAt(i) === 'b' ? 1 : 0;
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
