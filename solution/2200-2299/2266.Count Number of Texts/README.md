# [2266. 统计打字方案数](https://leetcode.cn/problems/count-number-of-texts)

[English Version](/solution/2200-2299/2266.Count%20Number%20of%20Texts/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>Alice 在给 Bob 用手机打字。数字到字母的 <strong>对应</strong>&nbsp;如下图所示。</p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2266.Count%20Number%20of%20Texts/images/1200px-telephone-keypad2svg.png" style="width: 200px; height: 162px;"></p>

<p>为了 <strong>打出</strong>&nbsp;一个字母，Alice 需要 <strong>按</strong>&nbsp;对应字母 <code>i</code>&nbsp;次，<code>i</code>&nbsp;是该字母在这个按键上所处的位置。</p>

<ul>
	<li>比方说，为了按出字母&nbsp;<code>'s'</code>&nbsp;，Alice 需要按&nbsp;<code>'7'</code>&nbsp;四次。类似的， Alice 需要按&nbsp;<code>'5'</code>&nbsp;两次得到字母&nbsp;&nbsp;<code>'k'</code>&nbsp;。</li>
	<li>注意，数字&nbsp;<code>'0'</code> 和&nbsp;<code>'1'</code>&nbsp;不映射到任何字母，所以&nbsp;Alice <strong>不</strong>&nbsp;使用它们。</li>
</ul>

<p>但是，由于传输的错误，Bob 没有收到 Alice 打字的字母信息，反而收到了 <strong>按键的字符串信息</strong>&nbsp;。</p>

<ul>
	<li>比方说，Alice 发出的信息为&nbsp;<code>"bob"</code>&nbsp;，Bob 将收到字符串&nbsp;<code>"2266622"</code>&nbsp;。</li>
</ul>

<p>给你一个字符串&nbsp;<code>pressedKeys</code>&nbsp;，表示 Bob 收到的字符串，请你返回 Alice <strong>总共可能发出多少种文字信息</strong>&nbsp;。</p>

<p>由于答案可能很大，将它对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong> 后返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>pressedKeys = "22233"
<b>输出：</b>8
<strong>解释：</strong>
Alice 可能发出的文字信息包括：
"aaadd", "abdd", "badd", "cdd", "aaae", "abe", "bae" 和 "ce" 。
由于总共有 8 种可能的信息，所以我们返回 8 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>pressedKeys = "222222222222222222222222222222222222"
<b>输出：</b>82876089
<strong>解释：</strong>
总共有 2082876103 种 Alice 可能发出的文字信息。
由于我们需要将答案对 10<sup>9</sup> + 7 取余，所以我们返回 2082876103 % (10<sup>9</sup> + 7) = 82876089 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= pressedKeys.length &lt;= 10<sup>5</sup></code></li>
	<li><code>pressedKeys</code> 只包含数字&nbsp;<code>'2'</code>&nbsp;到&nbsp;<code>'9'</code>&nbsp;。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
mod = 10**9 + 7
f = [1, 1, 2, 4]
g = [1, 1, 2, 4]
for _ in range(100000):
    f.append((f[-1] + f[-2] + f[-3]) % mod)
    g.append((g[-1] + g[-2] + g[-3] + g[-4]) % mod)


class Solution:
    def countTexts(self, pressedKeys: str) -> int:
        ans = 1
        for ch, s in groupby(pressedKeys):
            m = len(list(s))
            ans = ans * (g[m] if ch in "79" else f[m]) % mod
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int N = 100010;
    private static final int MOD = (int) 1e9 + 7;
    private static long[] f = new long[N];
    private static long[] g = new long[N];
    static {
        f[0] = 1;
        f[1] = 1;
        f[2] = 2;
        f[3] = 4;
        g[0] = 1;
        g[1] = 1;
        g[2] = 2;
        g[3] = 4;
        for (int i = 4; i < N; ++i) {
            f[i] = (f[i - 1] + f[i - 2] + f[i - 3]) % MOD;
            g[i] = (g[i - 1] + g[i - 2] + g[i - 3] + g[i - 4]) % MOD;
        }
    }

    public int countTexts(String pressedKeys) {
        long ans = 1;
        for (int i = 0, n = pressedKeys.length(); i < n; ++i) {
            int j = i;
            char c = pressedKeys.charAt(i);
            for (; j + 1 < n && pressedKeys.charAt(j + 1) == c; ++j)
                ;
            int cnt = j - i + 1;
            ans = c == '7' || c == '9' ? ans * g[cnt] : ans * f[cnt];
            ans %= MOD;
            i = j;
        }
        return (int) ans;
    }
}
```

### **Go**

```go
const mod int = 1e9 + 7
const n int = 1e5 + 10

var f = [n]int{1, 1, 2, 4}
var g = f

func init() {
	for i := 4; i < n; i++ {
		f[i] = (f[i-1] + f[i-2] + f[i-3]) % mod
		g[i] = (g[i-1] + g[i-2] + g[i-3] + g[i-4]) % mod
	}
}

func countTexts(pressedKeys string) int {
	ans := 1
	for i, j, n := 0, 0, len(pressedKeys); i < n; i++ {
		c := pressedKeys[i]
		j = i
		for j+1 < n && pressedKeys[j+1] == c {
			j++
		}
		cnt := j - i + 1
		if c == '7' || c == '9' {
			ans = ans * g[cnt] % mod
		} else {
			ans = ans * f[cnt] % mod
		}
		i = j
	}
	return ans
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
