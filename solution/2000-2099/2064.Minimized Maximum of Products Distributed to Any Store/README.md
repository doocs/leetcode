# [2064. 分配给商店的最多商品的最小值](https://leetcode.cn/problems/minimized-maximum-of-products-distributed-to-any-store)

[English Version](/solution/2000-2099/2064.Minimized%20Maximum%20of%20Products%20Distributed%20to%20Any%20Store/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数&nbsp;<code>n</code>&nbsp;，表示有&nbsp;<code>n</code>&nbsp;间零售商店。总共有&nbsp;<code>m</code>&nbsp;种产品，每种产品的数目用一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>quantities</code>&nbsp;表示，其中&nbsp;<code>quantities[i]</code>&nbsp;表示第&nbsp;<code>i</code>&nbsp;种商品的数目。</p>

<p>你需要将 <strong>所有商品</strong>&nbsp;分配到零售商店，并遵守这些规则：</p>

<ul>
	<li>一间商店 <strong>至多</strong>&nbsp;只能有 <strong>一种商品</strong> ，但一间商店拥有的商品数目可以为&nbsp;<strong>任意</strong>&nbsp;件。</li>
	<li>分配后，每间商店都会被分配一定数目的商品（可能为 <code>0</code>&nbsp;件）。用&nbsp;<code>x</code>&nbsp;表示所有商店中分配商品数目的最大值，你希望 <code>x</code>&nbsp;越小越好。也就是说，你想 <strong>最小化</strong>&nbsp;分配给任意商店商品数目的 <strong>最大值</strong>&nbsp;。</li>
</ul>

<p>请你返回最小的可能的&nbsp;<code>x</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>n = 6, quantities = [11,6]
<b>输出：</b>3
<strong>解释： </strong>一种最优方案为：
- 11 件种类为 0 的商品被分配到前 4 间商店，分配数目分别为：2，3，3，3 。
- 6 件种类为 1 的商品被分配到另外 2 间商店，分配数目分别为：3，3 。
分配给所有商店的最大商品数目为 max(2, 3, 3, 3, 3, 3) = 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>n = 7, quantities = [15,10,10]
<b>输出：</b>5
<b>解释：</b>一种最优方案为：
- 15 件种类为 0 的商品被分配到前 3 间商店，分配数目为：5，5，5 。
- 10 件种类为 1 的商品被分配到接下来 2 间商店，数目为：5，5 。
- 10 件种类为 2 的商品被分配到最后 2 间商店，数目为：5，5 。
分配给所有商店的最大商品数目为 max(5, 5, 5, 5, 5, 5, 5) = 5 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>n = 1, quantities = [100000]
<b>输出：</b>100000
<b>解释：</b>唯一一种最优方案为：
- 所有 100000 件商品 0 都分配到唯一的商店中。
分配给所有商店的最大商品数目为 max(100000) = 100000 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>m == quantities.length</code></li>
	<li><code>1 &lt;= m &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= quantities[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：二分查找**

我们注意到，如果分配给任意商店商品数目的最大值为 $x$，且满足题目要求，那么 $x+1$ 也一定满足题目要求，这存在着单调性。因此我们可以通过二分查找，找到一个最小的 $x$，使得 $x$ 满足题目要求。

我们定义二分查找的左边界 $left=1$，右边界 $right=10^5$。对于二分查找的每一步，我们取中间值 $mid$，判断是否存在一个分配方案，使得分配给任意商店商品数目的最大值为 $mid$，如果存在，那么我们将右边界 $right$ 移动到 $mid$，否则将左边界 $left$ 移动到 $mid+1$。

二分查找结束后，答案即为 $left$。

时间复杂度 $O(m \times \log M)$，空间复杂度 $O(1)$。其中 $m$ 为商品种类数，而 $M$ 为商品数目的最大值，本题中 $M \leq 10^5$。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimizedMaximum(self, n: int, quantities: List[int]) -> int:
        def check(x):
            return sum((v + x - 1) // x for v in quantities) <= n

        return 1 + bisect_left(range(1, 10**6), True, key=check)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimizedMaximum(int n, int[] quantities) {
        int left = 1, right = (int) 1e5;
        while (left < right) {
            int mid = (left + right) >> 1;
            int cnt = 0;
            for (int v : quantities) {
                cnt += (v + mid - 1) / mid;
            }
            if (cnt <= n) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimizedMaximum(int n, vector<int>& quantities) {
        int left = 1, right = 1e5;
        while (left < right) {
            int mid = (left + right) >> 1;
            int cnt = 0;
            for (int& v : quantities) {
                cnt += (v + mid - 1) / mid;
            }
            if (cnt <= n) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
};
```

### **Go**

```go
func minimizedMaximum(n int, quantities []int) int {
	return 1 + sort.Search(1e5, func(x int) bool {
		x++
		cnt := 0
		for _, v := range quantities {
			cnt += (v + x - 1) / x
		}
		return cnt <= n
	})
}
```

### **TypeScript**

```ts
function minimizedMaximum(n: number, quantities: number[]): number {
    let left = 1;
    let right = 1e5;
    while (left < right) {
        const mid = (left + right) >> 1;
        let cnt = 0;
        for (const v of quantities) {
            cnt += Math.ceil(v / mid);
        }
        if (cnt <= n) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

### **...**

```

```

<!-- tabs:end -->
