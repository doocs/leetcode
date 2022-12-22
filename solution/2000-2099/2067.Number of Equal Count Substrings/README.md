# [2067. 等计数子串的数量](https://leetcode.cn/problems/number-of-equal-count-substrings)

[English Version](/solution/2000-2099/2067.Number%20of%20Equal%20Count%20Substrings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的字符串 <code>s</code>，只包含小写英文字母和一个整数 <code>count</code>。如果&nbsp;<code>s</code>&nbsp;的&nbsp;<strong>子串 </strong>中的每种字母在子串中恰好出现 <code>count</code> 次，这个子串就被称为&nbsp;<strong>等计数子串</strong>。</p>

<p>返回<em> <code>s</code> 中&nbsp;<strong>等计数子串&nbsp;</strong>的个数。</em></p>

<p><strong>子串&nbsp;</strong>是字符串中连续的非空字符序列。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> s = "aaabcbbcc", count = 3
<strong>输出:</strong> 3
<strong>解释:</strong>
从下标 0 开始到下标 2 结束的子串是 "aaa"。
字母 “a” 在子串中恰好出现了 3 次。
从下标 3 开始到下标 8 结束的子串是 "bcbbcc"。
字母 “b” 和 “c” 在子串中恰好出现了 3 次。
从下标 0 开始到下标 8 结束的子串是 "aaabcbbcc"。
字母 “a”、“b” 和 “c” 在子串中恰好出现了 3 次。
</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> s = "abcd", count = 2
<strong>输出:</strong> 0
<strong>解释:</strong>
每种字母在 s 中出现的次数小于 count。
因此，s 中没有子串是等计数子串，返回 0。
</pre>

<p><strong>示例 3:</strong></p>

<pre>
<strong>输入:</strong> s = "a", count = 5
<strong>输出:</strong> 0
<strong>解释:</strong>
每种字母在 s 中出现的次数小于 count。
因此，s 中没有子串是等计数子串，返回 0。</pre>

<p>&nbsp;</p>

<p><strong>提示:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= count &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>s</code> 只由小写英文字母组成。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

前缀和统计每个位置各个字母出现的次数。然后根据 count 枚举子字符串左右端点，check 是否满足条件，是则 ans 加 1。

最后返回 ans。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
