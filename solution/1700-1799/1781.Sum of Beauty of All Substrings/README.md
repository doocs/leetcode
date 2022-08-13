# [1781. 所有子字符串美丽值之和](https://leetcode.cn/problems/sum-of-beauty-of-all-substrings)

[English Version](/solution/1700-1799/1781.Sum%20of%20Beauty%20of%20All%20Substrings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个字符串的 <strong>美丽值</strong> 定义为：出现频率最高字符与出现频率最低字符的出现次数之差。</p>

<ul>
	<li>比方说，<code>"abaacc"</code> 的美丽值为 <code>3 - 1 = 2</code> 。</li>
</ul>

<p>给你一个字符串 <code>s</code> ，请你返回它所有子字符串的 <strong>美丽值</strong> 之和。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>s = "aabcb"
<b>输出：</b>5
<strong>解释：</strong>美丽值不为零的字符串包括 ["aab","aabc","aabcb","abcb","bcb"] ，每一个字符串的美丽值都为 1 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>s = "aabcbaa"
<b>输出：</b>17
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s.length <=<sup> </sup>500</code></li>
	<li><code>s</code> 只包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

计数器实现。`s[i, j]` 的 counter 可用于计算 `s[i, j + 1]`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def beautySum(self, s: str) -> int:
        ans, n = 0, len(s)
        for i in range(n):
            counter = Counter()
            for j in range(i, n):
                counter[s[j]] += 1
                t = [v for v in counter.values() if v]
                ans += max(t) - min(t)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {

    public int beautySum(String s) {
        int ans = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            int[] counter = new int[26];
            for (int j = i; j < n; ++j) {
                ++counter[s.charAt(j) - 'a'];
                int mi = 1000;
                int mx = 0;
                for (int v : counter) {
                    if (v > 0) {
                        mi = Math.min(mi, v);
                        mx = Math.max(mx, v);
                    }
                }
                ans += mx - mi;
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
    int beautySum(string s) {
        int ans = 0;
        int n = s.size();
        for (int i = 0; i < n; ++i) {
            vector<int> counter(26);
            for (int j = i; j < n; ++j) {
                ++counter[s[j] - 'a'];
                int mi = 1000;
                int mx = 0;
                for (int v : counter) {
                    if (v) {
                        mi = min(mi, v);
                        mx = max(mx, v);
                    }
                }
                ans += mx - mi;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func beautySum(s string) int {
	ans, n := 0, len(s)
	for i := 0; i < n; i++ {
		counter := make([]int, 26)
		for j := i; j < n; j++ {
			counter[s[j]-'a']++
			mi, mx := 1000, 0
			for _, v := range counter {
				if v > 0 {
					mi = min(mi, v)
					mx = max(mx, v)
				}
			}
			ans += mx - mi
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
