# [2168. 每个数字的频率都相同的独特子字符串的数量](https://leetcode.cn/problems/unique-substrings-with-equal-digit-frequency)

[English Version](/solution/2100-2199/2168.Unique%20Substrings%20With%20Equal%20Digit%20Frequency/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

给你一个由数字组成的字符串&nbsp;<code>s</code>，返回<em>&nbsp;</em><code>s</code><em>&nbsp;</em>中<strong>独特子字符串数量</strong>，其中的每一个数字出现的频率都相同<i>。</i>

<p>&nbsp;</p>

<p><strong>示例1:</strong></p>

<pre>
<strong>输入:</strong> s = "1212"
<strong>输出:</strong> 5
<strong>解释:</strong> 符合要求的子串有 "1", "2", "12", "21", "1212".
要注意，尽管"12"在s中出现了两次，但在计数的时候只计算一次。
</pre>

<p><strong>示例&nbsp;2:</strong></p>

<pre>
<strong>输入:</strong> s = "12321"
<strong>输出:</strong> 9
<strong>解释:</strong> 符合要求的子串有 "1", "2", "3", "12", "23", "32", "21", "123", "321".
</pre>

<p>&nbsp;</p>

<p><strong>解释:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 1000</code></li>
	<li><code>s</code>&nbsp;只包含阿拉伯数字.</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def equalDigitFrequency(self, s: str) -> int:
        def check(i, j):
            v = set()
            for k in range(10):
                cnt = presum[j + 1][k] - presum[i][k]
                if cnt > 0:
                    v.add(cnt)
                if len(v) > 1:
                    return False
            return True

        n = len(s)
        presum = [[0] * 10 for _ in range(n + 1)]
        for i, c in enumerate(s):
            presum[i + 1][int(c)] += 1
            for j in range(10):
                presum[i + 1][j] += presum[i][j]
        vis = set(s[i : j + 1] for i in range(n) for j in range(i, n) if check(i, j))
        return len(vis)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int equalDigitFrequency(String s) {
        int n = s.length();
        int[][] presum = new int[n + 1][10];
        for (int i = 0; i < n; ++i) {
            ++presum[i + 1][s.charAt(i) - '0'];
            for (int j = 0; j < 10; ++j) {
                presum[i + 1][j] += presum[i][j];
            }
        }
        Set<String> vis = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            for (int j = i; j < n; ++j) {
                if (check(i, j, presum)) {
                    vis.add(s.substring(i, j + 1));
                }
            }
        }
        return vis.size();
    }

    private boolean check(int i, int j, int[][] presum) {
        Set<Integer> v = new HashSet<>();
        for (int k = 0; k < 10; ++k) {
            int cnt = presum[j + 1][k] - presum[i][k];
            if (cnt > 0) {
                v.add(cnt);
            }
            if (v.size() > 1) {
                return false;
            }
        }
        return true;
    }
}
```

### **Go**

```go
func equalDigitFrequency(s string) int {
	n := len(s)
	presum := make([][]int, n+1)
	for i := range presum {
		presum[i] = make([]int, 10)
	}
	for i, c := range s {
		presum[i+1][c-'0']++
		for j := 0; j < 10; j++ {
			presum[i+1][j] += presum[i][j]
		}
	}
	check := func(i, j int) bool {
		v := make(map[int]bool)
		for k := 0; k < 10; k++ {
			cnt := presum[j+1][k] - presum[i][k]
			if cnt > 0 {
				v[cnt] = true
			}
			if len(v) > 1 {
				return false
			}
		}
		return true
	}
	vis := make(map[string]bool)
	for i := 0; i < n; i++ {
		for j := i; j < n; j++ {
			if check(i, j) {
				vis[s[i:j+1]] = true
			}
		}
	}
	return len(vis)
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
