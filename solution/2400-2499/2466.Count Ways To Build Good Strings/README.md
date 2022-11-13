# [2466. 统计构造好字符串的方案数](https://leetcode.cn/problems/count-ways-to-build-good-strings)

[English Version](/solution/2400-2499/2466.Count%20Ways%20To%20Build%20Good%20Strings/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你整数&nbsp;<code>zero</code>&nbsp;，<code>one</code>&nbsp;，<code>low</code>&nbsp;和&nbsp;<code>high</code>&nbsp;，我们从空字符串开始构造一个字符串，每一步执行下面操作中的一种：</p>

<ul>
	<li>将&nbsp;<code>'0'</code>&nbsp;在字符串末尾添加&nbsp;<code>zero</code>&nbsp; 次。</li>
	<li>将&nbsp;<code>'1'</code>&nbsp;在字符串末尾添加&nbsp;<code>one</code>&nbsp;次。</li>
</ul>

<p>以上操作可以执行任意次。</p>

<p>如果通过以上过程得到一个 <strong>长度</strong>&nbsp;在&nbsp;<code>low</code> 和&nbsp;<code>high</code>&nbsp;之间（包含上下边界）的字符串，那么这个字符串我们称为&nbsp;<strong>好</strong>&nbsp;字符串。</p>

<p>请你返回满足以上要求的 <strong>不同</strong>&nbsp;好字符串数目。由于答案可能很大，请将结果对&nbsp;<code>10<sup>9</sup> + 7</code>&nbsp;<strong>取余</strong>&nbsp;后返回。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>low = 3, high = 3, zero = 1, one = 1
<b>输出：</b>8
<b>解释：</b>
一个可能的好字符串是 "011" 。
可以这样构造得到："" -&gt; "0" -&gt; "01" -&gt; "011" 。
从 "000" 到 "111" 之间所有的二进制字符串都是好字符串。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>low = 2, high = 3, zero = 1, one = 2
<b>输出：</b>5
<b>解释：</b>好字符串为 "00" ，"11" ，"000" ，"110" 和 "011" 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= low&nbsp;&lt;= high&nbsp;&lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= zero, one &lt;= low</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

我们设计一个函数 $dfs(i)$ 表示从第 $i$ 位开始构造的好字符串的个数，答案即为 $dfs(0)$。

函数 $dfs(i)$ 的计算过程如下：

-   如果 $i \gt high$，返回 $0$；
-   如果 $ low \leq i \leq high$，答案累加 $1$，然后 $i$ 之后既可以添加 `zero` 个 $0$，也可以添加 `one` 个 $1$，因此答案累加上 $dfs(i + zero) + dfs(i + one)$。

过程中，我们需要对答案取模，并且可以使用记忆化搜索减少重复计算。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n = high$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countGoodStrings(self, low: int, high: int, zero: int, one: int) -> int:
        @cache
        def dfs(i):
            if i > high:
                return 0
            ans = 0
            if low <= i <= high:
                ans += 1
            ans += dfs(i + zero) + dfs(i + one)
            return ans % mod

        mod = 10**9 + 7
        return dfs(0)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;
    private int[] f;
    private int lo;
    private int hi;
    private int zero;
    private int one;

    public int countGoodStrings(int low, int high, int zero, int one) {
        f = new int[high + 1];
        Arrays.fill(f, -1);
        lo = low;
        hi = high;
        this.zero = zero;
        this.one = one;
        return dfs(0);
    }

    private int dfs(int i) {
        if (i > hi) {
            return 0;
        }
        if (f[i] != -1) {
            return f[i];
        }
        long ans = 0;
        if (i >= lo && i <= hi) {
            ++ans;
        }
        ans += dfs(i + zero) + dfs(i + one);
        ans %= MOD;
        f[i] = (int) ans;
        return f[i];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    const int mod = 1e9 + 7;

    int countGoodStrings(int low, int high, int zero, int one) {
        vector<int> f(high + 1, -1);
        function<int(int)> dfs = [&](int i) -> int {
            if (i > high) return 0;
            if (f[i] != -1) return f[i];
            long ans = i >= low && i <= high;
            ans += dfs(i + zero) + dfs(i + one);
            ans %= mod;
            f[i] = ans;
            return ans;
        };
        return dfs(0);
    }
};
```

### **Go**

```go
func countGoodStrings(low int, high int, zero int, one int) int {
	f := make([]int, high+1)
	for i := range f {
		f[i] = -1
	}
	const mod int = 1e9 + 7
	var dfs func(i int) int
	dfs = func(i int) int {
		if i > high {
			return 0
		}
		if f[i] != -1 {
			return f[i]
		}
		ans := 0
		if i >= low && i <= high {
			ans++
		}
		ans += dfs(i+zero) + dfs(i+one)
		ans %= mod
		f[i] = ans
		return ans
	}
	return dfs(0)
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
