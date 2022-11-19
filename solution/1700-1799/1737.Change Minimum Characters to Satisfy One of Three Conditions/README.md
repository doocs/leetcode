# [1737. 满足三条件之一需改变的最少字符数](https://leetcode.cn/problems/change-minimum-characters-to-satisfy-one-of-three-conditions)

[English Version](/solution/1700-1799/1737.Change%20Minimum%20Characters%20to%20Satisfy%20One%20of%20Three%20Conditions/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个字符串 <code>a</code> 和 <code>b</code> ，二者均由小写字母组成。一步操作中，你可以将 <code>a</code> 或 <code>b</code> 中的 <strong>任一字符</strong> 改变为 <strong>任一小写字母</strong> 。</p>

<p>操作的最终目标是满足下列三个条件 <strong>之一</strong> ：</p>

<ul>
	<li><code>a</code> 中的 <strong>每个字母</strong> 在字母表中 <strong>严格小于</strong> <code>b</code> 中的 <strong>每个字母</strong> 。</li>
	<li><code>b</code> 中的 <strong>每个字母</strong> 在字母表中 <strong>严格小于</strong> <code>a</code> 中的 <strong>每个字母</strong> 。</li>
	<li><code>a</code> 和 <code>b</code> <strong>都</strong> 由 <strong>同一个</strong> 字母组成。</li>
</ul>

<p>返回达成目标所需的 <strong>最少</strong> 操作数<em>。</em></p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>a = "aba", b = "caa"
<strong>输出：</strong>2
<strong>解释：</strong>满足每个条件的最佳方案分别是：
1) 将 b 变为 "ccc"，2 次操作，满足 a 中的每个字母都小于 b 中的每个字母；
2) 将 a 变为 "bbb" 并将 b 变为 "aaa"，3 次操作，满足 b 中的每个字母都小于 a 中的每个字母；
3) 将 a 变为 "aaa" 并将 b 变为 "aaa"，2 次操作，满足 a 和 b 由同一个字母组成。
最佳的方案只需要 2 次操作（满足条件 1 或者条件 3）。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>a = "dabadd", b = "cda"
<strong>输出：</strong>3
<strong>解释：</strong>满足条件 1 的最佳方案是将 b 变为 "eee" 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= a.length, b.length &lt;= 10<sup>5</sup></code></li>
	<li><code>a</code> 和 <code>b</code> 只由小写字母组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：计数 + 枚举**

我们先统计字符串 $a$ 和 $b$ 中每个字母出现的次数，记为 $cnt_1$ 和 $cnt_2$。

然后考虑条件 $3$，即 $a$ 和 $b$ 中的每个字母都相同。我们只需要枚举最终的字母 $c$，然后统计 $a$ 和 $b$ 中不是 $c$ 的字母的个数，即为需要改变的字符个数。

然后考虑条件 $1$ 和 $2$，即 $a$ 中的每个字母都小于 $b$ 中的每个字母，或者 $b$ 中的每个字母都小于 $a$ 中的每个字母。对于条件 $1$，我们令字符串 $a$ 所有字符都小于字符 $c$，字符串 $b$ 所有字符都不小于 $c$，枚举 $c$ 找出最小的答案即可。条件 $2$ 同理。

最终的答案即为上述三种情况的最小值。

时间复杂度 $O(m + n + C^2)$，其中 $m$ 和 $n$ 分别为字符串 $a$ 和 $b$ 的长度，而 $C$ 为字符集大小。本题中 $C = 26$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minCharacters(self, a: str, b: str) -> int:
        def f(cnt1, cnt2):
            for i in range(1, 26):
                t = sum(cnt1[i:]) + sum(cnt2[:i])
                nonlocal ans
                ans = min(ans, t)

        m, n = len(a), len(b)
        cnt1 = [0] * 26
        cnt2 = [0] * 26
        for c in a:
            cnt1[ord(c) - ord('a')] += 1
        for c in b:
            cnt2[ord(c) - ord('a')] += 1
        ans = m + n
        for c1, c2 in zip(cnt1, cnt2):
            ans = min(ans, m + n - c1 - c2)
        f(cnt1, cnt2)
        f(cnt2, cnt1)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int ans;

    public int minCharacters(String a, String b) {
        int m = a.length(), n = b.length();
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for (int i = 0; i < m; ++i) {
            ++cnt1[a.charAt(i) - 'a'];
        }
        for (int i = 0; i < n; ++i) {
            ++cnt2[b.charAt(i) - 'a'];
        }
        ans = m + n;
        for (int i = 0; i < 26; ++i) {
            ans = Math.min(ans, m + n - cnt1[i] - cnt2[i]);
        }
        f(cnt1, cnt2);
        f(cnt2, cnt1);
        return ans;
    }

    private void f(int[] cnt1, int[] cnt2) {
        for (int i = 1; i < 26; ++i) {
            int t = 0;
            for (int j = i; j < 26; ++j) {
                t += cnt1[j];
            }
            for (int j = 0; j < i; ++j) {
                t += cnt2[j];
            }
            ans = Math.min(ans, t);
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minCharacters(string a, string b) {
        int m = a.size(), n = b.size();
        vector<int> cnt1(26);
        vector<int> cnt2(26);
        for (char& c : a) ++cnt1[c - 'a'];
        for (char& c : b) ++cnt2[c - 'a'];
        int ans = m + n;
        for (int i = 0; i < 26; ++i) ans = min(ans, m + n - cnt1[i] - cnt2[i]);
        auto f = [&](vector<int>& cnt1, vector<int>& cnt2) {
            for (int i = 1; i < 26; ++i) {
                int t = 0;
                for (int j = i; j < 26; ++j) t += cnt1[j];
                for (int j = 0; j < i; ++j) t += cnt2[j];
                ans = min(ans, t);
            }
        };
        f(cnt1, cnt2);
        f(cnt2, cnt1);
        return ans;
    }
};
```

### **Go**

```go
func minCharacters(a string, b string) int {
	cnt1 := [26]int{}
	cnt2 := [26]int{}
	for _, c := range a {
		cnt1[c-'a']++
	}
	for _, c := range b {
		cnt2[c-'a']++
	}
	m, n := len(a), len(b)
	ans := m + n
	for i := 0; i < 26; i++ {
		ans = min(ans, m+n-cnt1[i]-cnt2[i])
	}
	f := func(cnt1, cnt2 [26]int) {
		for i := 1; i < 26; i++ {
			t := 0
			for j := i; j < 26; j++ {
				t += cnt1[j]
			}
			for j := 0; j < i; j++ {
				t += cnt2[j]
			}
			ans = min(ans, t)
		}
	}
	f(cnt1, cnt2)
	f(cnt2, cnt1)
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function minCharacters(a: string, b: string): number {
    const m = a.length,
        n = b.length;
    let count1 = new Array(26).fill(0);
    let count2 = new Array(26).fill(0);
    const base = 'a'.charCodeAt(0);

    for (let char of a) {
        count1[char.charCodeAt(0) - base]++;
    }
    for (let char of b) {
        count2[char.charCodeAt(0) - base]++;
    }

    let pre1 = 0,
        pre2 = 0;
    let ans = m + n;
    for (let i = 0; i < 25; i++) {
        pre1 += count1[i];
        pre2 += count2[i];
        // case1， case2， case3
        ans = Math.min(
            ans,
            m - pre1 + pre2,
            pre1 + n - pre2,
            m + n - count1[i] - count2[i],
        );
    }
    ans = Math.min(ans, m + n - count1[25] - count2[25]);

    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
