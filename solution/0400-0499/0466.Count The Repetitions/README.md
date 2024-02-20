# [466. 统计重复个数](https://leetcode.cn/problems/count-the-repetitions)

[English Version](/solution/0400-0499/0466.Count%20The%20Repetitions/README_EN.md)

<!-- tags:字符串,动态规划 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>定义 <code>str = [s, n]</code> 表示 <code>str</code> 由 <code>n</code> 个字符串 <code>s</code> 连接构成。</p>

<ul>
	<li>例如，<code>str == ["abc", 3] =="abcabcabc"</code> 。</li>
</ul>

<p>如果可以从 <code>s2</code><sub> </sub>中删除某些字符使其变为 <code>s1</code>，则称字符串 <code>s1</code><sub> </sub>可以从字符串 <code>s2</code> 获得。</p>

<ul>
	<li>例如，根据定义，<code>s1 = "abc"</code> 可以从 <code>s2 = "ab<em><strong>dbe</strong></em>c"</code> 获得，仅需要删除加粗且用斜体标识的字符。</li>
</ul>

<p>现在给你两个字符串 <code>s1</code> 和 <code>s2</code> 和两个整数 <code>n1</code> 和 <code>n2</code> 。由此构造得到两个字符串，其中 <code>str1 = [s1, n1]</code>、<code>str2 = [s2, n2]</code> 。</p>

<p>请你找出一个最大整数 <code>m</code> ，以满足 <code>str = [str2, m]</code> 可以从 <code>str1</code> 获得。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>s1 = "acb", n1 = 4, s2 = "ab", n2 = 2
<strong>输出：</strong>2
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>s1 = "acb", n1 = 1, s2 = "acb", n2 = 1
<strong>输出：</strong>1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= s1.length, s2.length <= 100</code></li>
	<li><code>s1</code> 和 <code>s2</code> 由小写英文字母组成</li>
	<li><code>1 <= n1, n2 <= 10<sup>6</sup></code></li>
</ul>

## 解法

### 方法一：预处理 + 递推

我们预处理出以字符串 $s2$ 的每个位置 $i$ 开始匹配一个完整的 $s1$ 后，下一个位置 $j$ 以及经过了多少个 $s2$，即 $d[i] = (cnt, j)$，其中 $cnt$ 表示匹配了多少个 $s2$，而 $j$ 表示字符串 $s2$ 的下一个位置。

接下来，我们初始化 $j=0$，然后循环 $n1$ 次，每一次将 $d[j][0]$ 加到答案中，然后更新 $j=d[j][1]$。

最后得到的答案就是 $n1$ 个 $s1$ 所能匹配的 $s2$ 的个数，除以 $n2$ 即可得到答案。

时间复杂度 $O(m \times n + n1)$，空间复杂度 $O(n)$。其中 $m$ 和 $n$ 分别是 $s1$ 和 $s2$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def getMaxRepetitions(self, s1: str, n1: int, s2: str, n2: int) -> int:
        n = len(s2)
        d = {}
        for i in range(n):
            cnt = 0
            j = i
            for c in s1:
                if c == s2[j]:
                    j += 1
                if j == n:
                    cnt += 1
                    j = 0
            d[i] = (cnt, j)

        ans = 0
        j = 0
        for _ in range(n1):
            cnt, j = d[j]
            ans += cnt
        return ans // n2
```

```java
class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        int m = s1.length(), n = s2.length();
        int[][] d = new int[n][0];
        for (int i = 0; i < n; ++i) {
            int j = i;
            int cnt = 0;
            for (int k = 0; k < m; ++k) {
                if (s1.charAt(k) == s2.charAt(j)) {
                    if (++j == n) {
                        j = 0;
                        ++cnt;
                    }
                }
            }
            d[i] = new int[] {cnt, j};
        }
        int ans = 0;
        for (int j = 0; n1 > 0; --n1) {
            ans += d[j][0];
            j = d[j][1];
        }
        return ans / n2;
    }
}
```

```cpp
class Solution {
public:
    int getMaxRepetitions(string s1, int n1, string s2, int n2) {
        int m = s1.size(), n = s2.size();
        vector<pair<int, int>> d;
        for (int i = 0; i < n; ++i) {
            int j = i;
            int cnt = 0;
            for (int k = 0; k < m; ++k) {
                if (s1[k] == s2[j]) {
                    if (++j == n) {
                        ++cnt;
                        j = 0;
                    }
                }
            }
            d.emplace_back(cnt, j);
        }
        int ans = 0;
        for (int j = 0; n1; --n1) {
            ans += d[j].first;
            j = d[j].second;
        }
        return ans / n2;
    }
};
```

```go
func getMaxRepetitions(s1 string, n1 int, s2 string, n2 int) (ans int) {
	n := len(s2)
	d := make([][2]int, n)
	for i := 0; i < n; i++ {
		j := i
		cnt := 0
		for k := range s1 {
			if s1[k] == s2[j] {
				j++
				if j == n {
					cnt++
					j = 0
				}
			}
		}
		d[i] = [2]int{cnt, j}
	}
	for j := 0; n1 > 0; n1-- {
		ans += d[j][0]
		j = d[j][1]
	}
	ans /= n2
	return
}
```

```ts
function getMaxRepetitions(s1: string, n1: number, s2: string, n2: number): number {
    const n = s2.length;
    const d: number[][] = new Array(n).fill(0).map(() => new Array(2).fill(0));
    for (let i = 0; i < n; ++i) {
        let j = i;
        let cnt = 0;
        for (const c of s1) {
            if (c === s2[j]) {
                if (++j === n) {
                    j = 0;
                    ++cnt;
                }
            }
        }
        d[i] = [cnt, j];
    }
    let ans = 0;
    for (let j = 0; n1 > 0; --n1) {
        ans += d[j][0];
        j = d[j][1];
    }
    return Math.floor(ans / n2);
}
```

<!-- tabs:end -->

<!-- end -->
