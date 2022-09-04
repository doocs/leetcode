# [2400. 恰好移动 k 步到达某一位置的方法数目](https://leetcode.cn/problems/number-of-ways-to-reach-a-position-after-exactly-k-steps)

[English Version](/solution/2400-2499/2400.Number%20of%20Ways%20to%20Reach%20a%20Position%20After%20Exactly%20k%20Steps/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个 <strong>正</strong> 整数 <code>startPos</code> 和 <code>endPos</code> 。最初，你站在 <strong>无限</strong> 数轴上位置 <code>startPos</code> 处。在一步移动中，你可以向左或者向右移动一个位置。</p>

<p>给你一个正整数 <code>k</code> ，返回从 <code>startPos</code> 出发、<strong>恰好</strong> 移动 <code>k</code> 步并到达 <code>endPos</code> 的 <strong>不同</strong> 方法数目。由于答案可能会很大，返回对 <code>10<sup>9</sup> + 7</code> <strong>取余</strong> 的结果。</p>

<p>如果所执行移动的顺序不完全相同，则认为两种方法不同。</p>

<p><strong>注意：</strong>数轴包含负整数<strong>。</strong></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>startPos = 1, endPos = 2, k = 3
<strong>输出：</strong>3
<strong>解释：</strong>存在 3 种从 1 到 2 且恰好移动 3 步的方法：
- 1 -&gt; 2 -&gt; 3 -&gt; 2.
- 1 -&gt; 2 -&gt; 1 -&gt; 2.
- 1 -&gt; 0 -&gt; 1 -&gt; 2.
可以证明不存在其他方法，所以返回 3 。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>startPos = 2, endPos = 5, k = 10
<strong>输出：</strong>0
<strong>解释：</strong>不存在从 2 到 5 且恰好移动 10 步的方法。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= startPos, endPos, k &lt;= 1000</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索**

时间复杂度 $O(k^2)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def numberOfWays(self, startPos: int, endPos: int, k: int) -> int:
        @cache
        def dfs(d, k):
            if k < 0 or abs(d) > k:
                return 0
            if k == 0:
                return d == 0
            res = dfs(d - 1, k - 1) + dfs(d + 1, k - 1)
            return res % (10**9 + 7)

        return dfs(abs(startPos - endPos), k)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private static final int MOD = (int) 1e9 + 7;
    private int[][] f = new int[3010][3010];
    private int j;

    public int numberOfWays(int startPos, int endPos, int k) {
        startPos += 1000;
        endPos += 1000;
        for (var e : f) {
            Arrays.fill(e, -1);
        }
        j = endPos;
        return dfs(startPos, k);
    }

    private int dfs(int i, int k) {
        if (Math.abs(i - j) > k) {
            return 0;
        }
        if (f[i][k] != -1) {
            return f[i][k];
        }
        if (k == 0) {
            return i == j ? 1 : 0;
        }
        int res = dfs(i + 1, k - 1) + dfs(i - 1, k - 1);
        res %= MOD;
        f[i][k] = res;
        return res;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    unordered_map<int, int> f;
    int mod = 1e9 + 7;
    int j;

    int numberOfWays(int startPos, int endPos, int k) {
        j = endPos;
        return dfs(startPos, k);
    }

    int dfs(int i, int k) {
        if (f.count(i * 10000 + k)) return f[i * 10000 + k];
        if (abs(i - j) > k) return 0;
        if (k == 0) return i == j;
        int res = dfs(i - 1, k - 1) + dfs(i + 1, k - 1);
        res %= mod;
        f[i * 10000 + k] = res;
        return res;
    }
};
```

### **Go**

```go
func numberOfWays(startPos int, endPos int, k int) int {
	f := map[int]int{}
	var dfs func(i, k int) int
	dfs = func(i, k int) int {
		if abs(i-endPos) > k {
			return 0
		}
		if k == 0 {
			if i == endPos {
				return 1
			}
			return 0
		}
		if v, ok := f[i*10000+k]; ok {
			return v
		}
		res := dfs(i+1, k-1) + dfs(i-1, k-1)
		res %= 1e9 + 7
		f[i*10000+k] = res
		return res
	}
	return dfs(startPos, k)
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **TypeScript**

```ts

```

### **...**

```


```

<!-- tabs:end -->
