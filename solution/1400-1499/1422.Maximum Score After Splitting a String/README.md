# [1422. 分割字符串的最大得分](https://leetcode.cn/problems/maximum-score-after-splitting-a-string)

[English Version](/solution/1400-1499/1422.Maximum%20Score%20After%20Splitting%20a%20String/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个由若干 0 和 1 组成的字符串 <code>s</code> ，请你计算并返回将该字符串分割成两个 <strong>非空</strong> 子字符串（即&nbsp;<strong>左</strong> 子字符串和 <strong>右</strong> 子字符串）所能获得的最大得分。</p>

<p>「分割字符串的得分」为 <strong>左</strong> 子字符串中 <strong>0</strong> 的数量加上 <strong>右</strong> 子字符串中 <strong>1</strong> 的数量。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>s = &quot;011101&quot;
<strong>输出：</strong>5 
<strong>解释：</strong>
将字符串 s 划分为两个非空子字符串的可行方案有：
左子字符串 = &quot;0&quot; 且 右子字符串 = &quot;11101&quot;，得分 = 1 + 4 = 5 
左子字符串 = &quot;01&quot; 且 右子字符串 = &quot;1101&quot;，得分 = 1 + 3 = 4 
左子字符串 = &quot;011&quot; 且 右子字符串 = &quot;101&quot;，得分 = 1 + 2 = 3 
左子字符串 = &quot;0111&quot; 且 右子字符串 = &quot;01&quot;，得分 = 1 + 1 = 2 
左子字符串 = &quot;01110&quot; 且 右子字符串 = &quot;1&quot;，得分 = 2 + 1 = 3
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>s = &quot;00111&quot;
<strong>输出：</strong>5
<strong>解释：</strong>当 左子字符串 = &quot;00&quot; 且 右子字符串 = &quot;111&quot; 时，我们得到最大得分 = 2 + 3 = 5
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>s = &quot;1111&quot;
<strong>输出：</strong>3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= s.length &lt;= 500</code></li>
	<li>字符串 <code>s</code> 仅由字符 <code>&#39;0&#39;</code> 和 <code>&#39;1&#39;</code> 组成。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxScore(self, s: str) -> int:
        return max(s[:i].count('0') + s[i:].count('1') for i in range(1, len(s)))
```

```python
class Solution:
    def maxScore(self, s: str) -> int:
        ans = t = (s[0] == '0') + s[1:].count('1')
        for i in range(1, len(s) - 1):
            t += 1 if s[i] == '0' else -1
            ans = max(ans, t)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxScore(String s) {
        int ans = 0;
        for (int i = 1; i < s.length(); ++i) {
            int t = 0;
            for (int j = 0; j < i; ++j) {
                if (s.charAt(j) == '0') {
                    ++t;
                }
            }
            for (int j = i; j < s.length(); ++j) {
                if (s.charAt(j) == '1') {
                    ++t;
                }
            }
            ans = Math.max(ans, t);
        }
        return ans;
    }
}
```

```java
class Solution {
    public int maxScore(String s) {
        int t = 0;
        if (s.charAt(0) == '0') {
            t++;
        }
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) == '1') {
                t++;
            }
        }
        int ans = t;
        for (int i = 1; i < s.length() - 1; ++i) {
            t += s.charAt(i) == '0' ? 1 : -1;
            ans = Math.max(ans, t);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxScore(string s) {
        int ans = 0;
        for (int i = 1, n = s.size(); i < n; ++i) {
            int t = 0;
            for (int j = 0; j < i; ++j) {
                t += s[j] == '0';
            }
            for (int j = i; j < n; ++j) {
                t += s[j] == '1';
            }
            ans = max(ans, t);
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int maxScore(string s) {
        int t = 0;
        if (s[0] == '0') ++t;
        for (int i = 1; i < s.size(); ++i) t += s[i] == '1';
        int ans = t;
        for (int i = 1; i < s.size() - 1; ++i) {
            t += s[i] == '0' ? 1 : -1;
            ans = max(ans, t);
        }
        return ans;
    }
};
```

### **Go**

```go
func maxScore(s string) int {
	ans := 0
	for i, n := 1, len(s); i < n; i++ {
		t := 0
		for j := 0; j < i; j++ {
			if s[j] == '0' {
				t++
			}
		}
		for j := i; j < n; j++ {
			if s[j] == '1' {
				t++
			}
		}
		ans = max(ans, t)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

```go
func maxScore(s string) int {
	t := 0
	if s[0] == '0' {
		t++
	}
	n := len(s)
	for i := 1; i < n; i++ {
		if s[i] == '1' {
			t++
		}
	}
	ans := t
	for i := 1; i < n-1; i++ {
		if s[i] == '0' {
			t++
		} else {
			t--
		}
		ans = max(ans, t)
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function maxScore(s: string): number {
    const n = s.length;
    let res = 0;
    let score = 0;
    if (s[0] === '0') {
        score++;
    }
    for (let i = 1; i < n; i++) {
        if (s[i] === '1') {
            score++;
        }
    }
    res = Math.max(res, score);
    for (let i = 1; i < n - 1; i++) {
        if (s[i] === '0') {
            score++;
        } else if (s[i] === '1') {
            score--;
        }
        res = Math.max(res, score);
    }
    return res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn max_score(s: String) -> i32 {
        let n = s.len();
        let mut res = 0;
        let mut score = 0;
        let bs = s.as_bytes();
        if bs[0] == b'0' {
            score += 1;
        }
        for i in 1..n {
            if bs[i] == b'1' {
                score += 1;
            }
        }
        res = res.max(score);
        for i in 1..n - 1 {
            if bs[i] == b'0' {
                score += 1;
            } else if bs[i] == b'1' {
                score -= 1;
            }
            res = res.max(score);
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
