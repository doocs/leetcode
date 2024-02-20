# [2600. K 件物品的最大和](https://leetcode.cn/problems/k-items-with-the-maximum-sum)

[English Version](/solution/2600-2699/2600.K%20Items%20With%20the%20Maximum%20Sum/README_EN.md)

<!-- tags:贪心,数学 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>袋子中装有一些物品，每个物品上都标记着数字 <code>1</code> 、<code>0</code> 或 <code>-1</code> 。</p>

<p>给你四个非负整数 <code>numOnes</code> 、<code>numZeros</code> 、<code>numNegOnes</code> 和 <code>k</code> 。</p>

<p>袋子最初包含：</p>

<ul>
	<li><code>numOnes</code> 件标记为 <code>1</code> 的物品。</li>
	<li><code>numZeros</code> 件标记为 <code>0</code> 的物品。</li>
	<li><code>numNegOnes</code> 件标记为 <code>-1</code> 的物品。</li>
</ul>

<p>现计划从这些物品中恰好选出 <code>k</code> 件物品。返回所有可行方案中，物品上所标记数字之和的最大值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>numOnes = 3, numZeros = 2, numNegOnes = 0, k = 2
<strong>输出：</strong>2
<strong>解释：</strong>袋子中的物品分别标记为 {1, 1, 1, 0, 0} 。取 2 件标记为 1 的物品，得到的数字之和为 2 。
可以证明 2 是所有可行方案中的最大值。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>numOnes = 3, numZeros = 2, numNegOnes = 0, k = 4
<strong>输出：</strong>3
<strong>解释：</strong>袋子中的物品分别标记为 {1, 1, 1, 0, 0} 。取 3 件标记为 1 的物品，1 件标记为 0 的物品，得到的数字之和为 3 。
可以证明 3 是所有可行方案中的最大值。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= numOnes, numZeros, numNegOnes &lt;= 50</code></li>
	<li><code>0 &lt;= k &lt;= numOnes + numZeros + numNegOnes</code></li>
</ul>

## 解法

### 方法一：贪心

根据题目描述，我们应该尽可能多地取标记为 $1$ 的物品，然后取标记为 $0$ 的物品，最后取标记为 $-1$ 的物品。

因此：

-   如果袋子中的物品标记为 $1$ 的数量大于等于 $k$，那么取 $k$ 件物品，数字之和为 $k$；
-   如果袋子中的物品标记为 $1$ 的数量小于 $k$，那么取 $numOnes$ 件物品，数字之和为 $numOnes$；如果标记为 $0$ 的物品数量大于等于 $k - numOnes$，那么再取 $k - numOnes$ 件物品，数字之和还是 $numOnes$；
-   否则，我们再从标记为 $-1$ 的物品中取 $k - numOnes - numZeros$ 件物品，数字之和为 $numOnes - (k - numOnes - numZeros)$。

时间复杂度 $O(1)$，空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def kItemsWithMaximumSum(
        self, numOnes: int, numZeros: int, numNegOnes: int, k: int
    ) -> int:
        if numOnes >= k:
            return k
        if numZeros >= k - numOnes:
            return numOnes
        return numOnes - (k - numOnes - numZeros)
```

```java
class Solution {
    public int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        if (numOnes >= k) {
            return k;
        }
        if (numZeros >= k - numOnes) {
            return numOnes;
        }
        return numOnes - (k - numOnes - numZeros);
    }
}
```

```cpp
class Solution {
public:
    int kItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        if (numOnes >= k) {
            return k;
        }
        if (numZeros >= k - numOnes) {
            return numOnes;
        }
        return numOnes - (k - numOnes - numZeros);
    }
};
```

```go
func kItemsWithMaximumSum(numOnes int, numZeros int, numNegOnes int, k int) int {
	if numOnes >= k {
		return k
	}
	if numZeros >= k-numOnes {
		return numOnes
	}
	return numOnes - (k - numOnes - numZeros)
}
```

```ts
function kItemsWithMaximumSum(
    numOnes: number,
    numZeros: number,
    numNegOnes: number,
    k: number,
): number {
    if (numOnes >= k) {
        return k;
    }
    if (numZeros >= k - numOnes) {
        return numOnes;
    }
    return numOnes - (k - numOnes - numZeros);
}
```

```rust
impl Solution {
    pub fn k_items_with_maximum_sum(
        num_ones: i32,
        num_zeros: i32,
        num_neg_ones: i32,
        k: i32
    ) -> i32 {
        if num_ones > k {
            return k;
        }

        if num_ones + num_zeros > k {
            return num_ones;
        }

        num_ones - (k - num_ones - num_zeros)
    }
}
```

```cs
public class Solution {
    public int KItemsWithMaximumSum(int numOnes, int numZeros, int numNegOnes, int k) {
        if (numOnes >= k) {
            return k;
        }
        if (numZeros >= k - numOnes) {
            return numOnes;
        }
        return numOnes - (k - numOnes - numZeros);
    }
}
```

<!-- tabs:end -->

<!-- end -->
