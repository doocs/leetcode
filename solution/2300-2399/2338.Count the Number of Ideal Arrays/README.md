# [2338. 统计理想数组的数目](https://leetcode.cn/problems/count-the-number-of-ideal-arrays)

[English Version](/solution/2300-2399/2338.Count%20the%20Number%20of%20Ideal%20Arrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你两个整数 <code>n</code> 和 <code>maxValue</code> ，用于描述一个 <strong>理想数组</strong> 。</p>

<p>对于下标从 <strong>0</strong> 开始、长度为 <code>n</code> 的整数数组 <code>arr</code> ，如果满足以下条件，则认为该数组是一个 <strong>理想数组</strong> ：</p>

<ul>
	<li>每个 <code>arr[i]</code> 都是从 <code>1</code> 到 <code>maxValue</code> 范围内的一个值，其中 <code>0 &lt;= i &lt; n</code> 。</li>
	<li>每个 <code>arr[i]</code> 都可以被 <code>arr[i - 1]</code> 整除，其中 <code>0 &lt; i &lt; n</code> 。</li>
</ul>

<p>返回长度为 <code>n</code> 的 <strong>不同</strong> 理想数组的数目。由于答案可能很大，返回对 <code>10<sup>9</sup> + 7</code> 取余的结果。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>n = 2, maxValue = 5
<strong>输出：</strong>10
<strong>解释：</strong>存在以下理想数组：
- 以 1 开头的数组（5 个）：[1,1]、[1,2]、[1,3]、[1,4]、[1,5]
- 以 2 开头的数组（2 个）：[2,2]、[2,4]
- 以 3 开头的数组（1 个）：[3,3]
- 以 4 开头的数组（1 个）：[4,4]
- 以 5 开头的数组（1 个）：[5,5]
共计 5 + 2 + 1 + 1 + 1 = 10 个不同理想数组。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>n = 5, maxValue = 3
<strong>输出：</strong>11
<strong>解释：</strong>存在以下理想数组：
- 以 1 开头的数组（9 个）：
   - 不含其他不同值（1 个）：[1,1,1,1,1] 
   - 含一个不同值 2（4 个）：[1,1,1,1,2], [1,1,1,2,2], [1,1,2,2,2], [1,2,2,2,2]
   - 含一个不同值 3（4 个）：[1,1,1,1,3], [1,1,1,3,3], [1,1,3,3,3], [1,3,3,3,3]
- 以 2 开头的数组（1 个）：[2,2,2,2,2]
- 以 3 开头的数组（1 个）：[3,3,3,3,3]
共计 9 + 1 + 1 = 11 个不同理想数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= maxValue &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：记忆化搜索 + 组合计数**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def idealArrays(self, n: int, maxValue: int) -> int:
        @cache
        def dfs(i, cnt):
            res = c[-1][cnt - 1]
            if cnt < n:
                k = 2
                while k * i <= maxValue:
                    res = (res + dfs(k * i, cnt + 1)) % mod
                    k += 1
            return res

        c = [[0] * 16 for _ in range(n)]
        mod = 10**9 + 7
        for i in range(n):
            for j in range(min(16, i + 1)):
                c[i][j] = 1 if j == 0 else (
                    c[i - 1][j] + c[i - 1][j - 1]) % mod
        ans = 0
        for i in range(1, maxValue + 1):
            ans = (ans + dfs(i, 1)) % mod
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    private int[][] f;
    private int[][] c;
    private int n;
    private int m;
    private static final int MOD = (int) 1e9 + 7;

    public int idealArrays(int n, int maxValue) {
        this.n = n;
        this.m = maxValue;
        this.f = new int[maxValue + 1][16];
        for (int[] row : f) {
            Arrays.fill(row, -1);
        }
        c = new int[n][16];
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j <= i && j < 16; ++j) {
                c[i][j] = j == 0 ? 1 : (c[i - 1][j] + c[i - 1][j - 1]) % MOD;
            }
        }
        int ans = 0;
        for (int i = 1; i <= m; ++i) {
            ans = (ans + dfs(i, 1)) % MOD;
        }
        return ans;
    }

    private int dfs(int i, int cnt) {
        if (f[i][cnt] != -1) {
            return f[i][cnt];
        }
        int res = c[n - 1][cnt - 1];
        if (cnt < n) {
            for (int k = 2; k * i <= m; ++k) {
                res = (res + dfs(k * i, cnt + 1)) % MOD;
            }
        }
        f[i][cnt] = res;
        return res;

    }
}
```

### **C++**

```cpp
class Solution {
public:
    int m, n;
    const int mod = 1e9 + 7;
    vector<vector<int>> f;
    vector<vector<int>> c;

    int idealArrays(int n, int maxValue) {
        this->m = maxValue;
        this->n = n;
        f.assign(maxValue + 1, vector<int>(16, -1));
        c.assign(n, vector<int>(16, 0));
        for (int i = 0; i < n; ++i)
            for (int j = 0; j <= i && j < 16; ++j)
                c[i][j] = !j ? 1 : (c[i - 1][j] + c[i - 1][j - 1]) % mod;
        int ans = 0;
        for (int i = 1; i <= m; ++i) ans = (ans + dfs(i, 1)) % mod;
        return ans;
    }

    int dfs(int i, int cnt) {
        if (f[i][cnt] != -1) return f[i][cnt];
        int res = c[n - 1][cnt - 1];
        if (cnt < n)
            for (int k = 2; k * i <= m; ++k)
                res = (res + dfs(k * i, cnt + 1)) % mod;
        f[i][cnt] = res;
        return res;
    }
};
```

### **Go**

```go
func idealArrays(n int, maxValue int) int {
	mod := int(1e9) + 7
	m := maxValue
	c := make([][]int, n)
	f := make([][]int, m+1)
	for i := range c {
		c[i] = make([]int, 16)
	}
	for i := range f {
		f[i] = make([]int, 16)
		for j := range f[i] {
			f[i][j] = -1
		}
	}
	var dfs func(int, int) int
	dfs = func(i, cnt int) int {
		if f[i][cnt] != -1 {
			return f[i][cnt]
		}
		res := c[n-1][cnt-1]
		if cnt < n {
			for k := 2; k*i <= m; k++ {
				res = (res + dfs(k*i, cnt+1)) % mod
			}
		}
		f[i][cnt] = res
		return res
	}
	for i := 0; i < n; i++ {
		for j := 0; j <= i && j < 16; j++ {
			if j == 0 {
				c[i][j] = 1
			} else {
				c[i][j] = (c[i-1][j] + c[i-1][j-1]) % mod
			}
		}
	}
	ans := 0
	for i := 1; i <= m; i++ {
		ans = (ans + dfs(i, 1)) % mod
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
