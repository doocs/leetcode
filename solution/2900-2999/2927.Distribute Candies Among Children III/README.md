# [2927. Distribute Candies Among Children III](https://leetcode.cn/problems/distribute-candies-among-children-iii)

[English Version](/solution/2900-2999/2927.Distribute%20Candies%20Among%20Children%20III/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>You are given two positive integers <code>n</code> and <code>limit</code>.</p>

<p>Return <em>the <strong>total number</strong> of ways to distribute </em><code>n</code> <em>candies among </em><code>3</code><em> children such that no child gets more than </em><code>limit</code><em> candies.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5, limit = 2
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are 3 ways to distribute 5 candies such that no child gets more than 2 candies: (1, 2, 2), (2, 1, 2) and (2, 2, 1).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, limit = 3
<strong>Output:</strong> 10
<strong>Explanation:</strong> There are 10 ways to distribute 3 candies such that no child gets more than 3 candies: (0, 0, 3), (0, 1, 2), (0, 2, 1), (0, 3, 0), (1, 0, 2), (1, 1, 1), (1, 2, 0), (2, 0, 1), (2, 1, 0) and (3, 0, 0).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>8</sup></code></li>
	<li><code>1 &lt;= limit &lt;= 10<sup>8</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：组合数学 + 容斥原理**

根据题目描述，我们需要将 $n$ 个糖果分给 $3$ 个小孩，每个小孩分到的糖果数在 $[0, limit]$ 之间。

这实际上等价于把 $n$ 个球放入 $3$ 个盒子中。由于盒子可以为空，我们可以再增加 $3$ 个虚拟球，然后再利用隔板法，即一共有 $n + 3$ 个球，我们在其中 $n + 3 - 1$ 个位置插入 $2$ 个隔板，从而将实际的 $n$ 个球分成 $3$ 组，并且允许盒子为空，因此初始方案数为 $C_{n + 2}^2$。

我们需要在这些方案中，排除掉存在盒子分到的小球数超过 $limit$ 的方案。考虑其中有一个盒子分到的小球数超过 $limit$，那么剩下的球（包括虚拟球）最多有 $n + 3 - (limit + 1) = n - limit + 2$ 个，位置数为 $n - limit + 1$，因此方案数为 $C_{n - limit + 1}^2$。由于存在 $3$ 个盒子，因此这样的方案数为 $3 \times C_{n - limit + 1}^2$。这样子算，我们会多排除掉同时存在两个盒子分到的小球数超过 $limit$ 的方案，因此我们需要再加上这样的方案数，即 $3 \times C_{n - 2 \times limit}^2$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def distributeCandies(self, n: int, limit: int) -> int:
        if n > 3 * limit:
            return 0
        ans = comb(n + 2, 2)
        if n > limit:
            ans -= 3 * comb(n - limit + 1, 2)
        if n - 2 >= 2 * limit:
            ans += 3 * comb(n - 2 * limit, 2)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long distributeCandies(int n, int limit) {
        if (n > 3 * limit) {
            return 0;
        }
        long ans = comb2(n + 2);
        if (n > limit) {
            ans -= 3 * comb2(n - limit + 1);
        }
        if (n - 2 >= 2 * limit) {
            ans += 3 * comb2(n - 2 * limit);
        }
        return ans;
    }

    private long comb2(int n) {
        return 1L * n * (n - 1) / 2;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long distributeCandies(int n, int limit) {
        auto comb2 = [](int n) {
            return 1LL * n * (n - 1) / 2;
        };
        if (n > 3 * limit) {
            return 0;
        }
        long long ans = comb2(n + 2);
        if (n > limit) {
            ans -= 3 * comb2(n - limit + 1);
        }
        if (n - 2 >= 2 * limit) {
            ans += 3 * comb2(n - 2 * limit);
        }
        return ans;
    }
};
```

### **Go**

```go
func distributeCandies(n int, limit int) int64 {
	comb2 := func(n int) int {
		return n * (n - 1) / 2
	}
	if n > 3*limit {
		return 0
	}
	ans := comb2(n+2)
	if n > limit {
		ans -= 3 * comb2(n-limit+1)
	}
	if n-2 >= 2*limit {
		ans += 3 * comb2(n-2*limit)
	}
	return int64(ans)
}
```

### **TypeScript**

```ts
function distributeCandies(n: number, limit: number): number {
    const comb2 = (n: number) => (n * (n - 1)) / 2;
    if (n > 3 * limit) {
        return 0;
    }
    let ans = comb2(n + 2);
    if (n > limit) {
        ans -= 3 * comb2(n - limit + 1);
    }
    if (n - 2 >= 2 * limit) {
        ans += 3 * comb2(n - 2 * limit);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
