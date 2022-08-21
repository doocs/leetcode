# [2381. 字母移位 II](https://leetcode.cn/problems/shifting-letters-ii)

[English Version](/solution/2300-2399/2381.Shifting%20Letters%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个小写英文字母组成的字符串&nbsp;<code>s</code>&nbsp;和一个二维整数数组&nbsp;<code>shifts</code>&nbsp;，其中&nbsp;<code>shifts[i] = [start<sub>i</sub>, end<sub>i</sub>, direction<sub>i</sub>]</code>&nbsp;。对于每个&nbsp;<code>i</code>&nbsp;，将&nbsp;<code>s</code>&nbsp;中从下标&nbsp;<code>start<sub>i</sub></code>&nbsp;到下标&nbsp;<code>end<sub>i</sub></code>&nbsp;（两者都包含）所有字符都进行移位运算，如果&nbsp;<code>direction<sub>i</sub> = 1</code>&nbsp;将字符向后移位，如果&nbsp;<code>direction<sub>i</sub> = 0</code>&nbsp;将字符向前移位。</p>

<p>将一个字符 <strong>向后</strong>&nbsp;移位的意思是将这个字符用字母表中 <strong>下一个</strong>&nbsp;字母替换（字母表视为环绕的，所以&nbsp;<code>'z'</code>&nbsp;变成&nbsp;<code>'a'</code>）。类似的，将一个字符 <strong>向前</strong>&nbsp;移位的意思是将这个字符用字母表中 <strong>前一个</strong>&nbsp;字母替换（字母表是环绕的，所以&nbsp;<code>'a'</code>&nbsp;变成&nbsp;<code>'z'</code>&nbsp;）。</p>

<p>请你返回对 <code>s</code>&nbsp;进行所有移位操作以后得到的最终字符串。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>s = "abc", shifts = [[0,1,0],[1,2,1],[0,2,1]]
<b>输出：</b>"ace"
<b>解释：</b>首先，将下标从 0 到 1 的字母向前移位，得到 s = "zac" 。
然后，将下标从 1 到 2 的字母向后移位，得到 s = "zbd" 。
最后，将下标从 0 到 2 的字符向后移位，得到 s = "ace" 。</pre>

<p><strong>示例 2:</strong></p>

<pre><b>输入：</b>s = "dztz", shifts = [[0,0,0],[1,1,1]]
<b>输出：</b>"catz"
<b>解释：</b>首先，将下标从 0 到 0 的字母向前移位，得到 s = "cztz" 。
最后，将下标从 1 到 1 的字符向后移位，得到 s = "catz" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= s.length, shifts.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>shifts[i].length == 3</code></li>
	<li><code>0 &lt;= start<sub>i</sub> &lt;= end<sub>i</sub> &lt; s.length</code></li>
	<li><code>0 &lt;= direction<sub>i</sub> &lt;= 1</code></li>
	<li><code>s</code>&nbsp;只包含小写英文字母。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：差分数组**

用差分数组 $d$ 记录区间的变化，然后对 $d$ 求前缀和，得到每个下标 $i$ 的变化量 $d[i]$。

然后将原字符串中每个字符加上对应的 $d[i]$，最终得到一个新的字符串。

时间复杂度 $O(n+m)$。其中 $n$ 是原字符串 $s$ 的长度，而 $m$ 是移位操作 $shifts$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def shiftingLetters(self, s: str, shifts: List[List[int]]) -> str:
        n = len(s)
        d = [0] * (n + 1)
        for i, j, v in shifts:
            if v == 0:
                v = -1
            d[i] += v
            d[j + 1] -= v
        for i in range(1, n + 1):
            d[i] += d[i - 1]
        return ''.join(chr(ord('a') + (ord(s[i]) - ord('a') + d[i] + 26) % 26) for i in range(n))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        int[] d = new int[n + 1];
        for (int[] e : shifts) {
            if (e[2] == 0) {
                e[2]--;
            }
            d[e[0]] += e[2];
            d[e[1] + 1] -= e[2];
        }
        for (int i = 1; i <= n; ++i) {
            d[i] += d[i - 1];
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; ++i) {
            int j = (s.charAt(i) - 'a' + d[i] % 26 + 26) % 26;
            ans.append((char) ('a' + j));
        }
        return ans.toString();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string shiftingLetters(string s, vector<vector<int>>& shifts) {
        int n = s.size();
        vector<int> d(n + 1);
        for (auto& e : shifts) {
            if (e[2] == 0) {
                e[2]--;
            }
            d[e[0]] += e[2];
            d[e[1] + 1] -= e[2];
        }
        for (int i = 1; i <= n; ++i) {
            d[i] += d[i - 1];
        }
        string ans;
        for (int i = 0; i < n; ++i) {
            int j = (s[i] - 'a' + d[i] % 26 + 26) % 26;
            ans += ('a' + j);
        }
        return ans;
    }
};
```

### **Go**

```go
func shiftingLetters(s string, shifts [][]int) string {
	n := len(s)
	d := make([]int, n+1)
	for _, e := range shifts {
		if e[2] == 0 {
			e[2]--
		}
		d[e[0]] += e[2]
		d[e[1]+1] -= e[2]
	}
	for i := 1; i <= n; i++ {
		d[i] += d[i-1]
	}
	ans := []byte{}
	for i, c := range s {
		j := (int(c-'a') + d[i]%26 + 26) % 26
		ans = append(ans, byte('a'+j))
	}
	return string(ans)
}
```

### **TypeScript**

```ts

```

### **...**

```


```

<!-- tabs:end -->
