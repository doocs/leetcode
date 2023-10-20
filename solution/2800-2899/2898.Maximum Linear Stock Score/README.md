# [2898. 最大线性股票得分](https://leetcode.cn/problems/maximum-linear-stock-score)

[English Version](/solution/2800-2899/2898.Maximum%20Linear%20Stock%20Score/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个 <strong>1-indexed</strong> 整数数组 <code>prices</code>，其中 <code>prices[i]</code> 是第 <code>i</code> 天某只股票的价格。你的任务是&nbsp;<strong>线性</strong>&nbsp;地选择 <code>prices</code>&nbsp;中的一些元素。</p>

<p>一个选择 <code>indexes</code>，其中 <code>indexes</code> 是一个 <strong>1-indexed</strong> 整数数组，长度为 <code>k</code>，是数组 <code>[1, 2, ..., n]</code> 的子序列，如果以下条件成立，那么它是 <strong>线性</strong> 的：</p>

<ul>
	<li>对于每个 <code>1 &lt; j &lt;= k，prices[indexes[j]] - prices[indexes[j - 1]] == indexes[j] - indexes[j - 1]</code>。</li>
</ul>

<p>数组的 <strong>子序列</strong> 是经由原数组删除一些元素（可能不删除）而产生的新数组，且删除不改变其余元素相对顺序。</p>

<p>选择 <code>indexes</code> 的 <strong>得分</strong> 等于以下数组的总和：<code>[prices[indexes[1]], prices[indexes[2]], ..., prices[indexes[k]]</code>。</p>

<p>返回 <em>线性选择的&nbsp;<strong>最大得分</strong>。</em></p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong> prices = [1,5,3,7,8]
<strong>输出：</strong> 20
<strong>解释：</strong> 我们可以选择索引[2,4,5]。我们可以证明我们的选择是线性的：
对于j = 2，我们有：
indexes[2] - indexes[1] = 4 - 2 = 2。
prices[4] - prices[2] = 7 - 5 = 2。
对于j = 3，我们有：
indexes[3] - indexes[2] = 5 - 4 = 1。
prices[5] - prices[4] = 8 - 7 = 1。
元素的总和是：prices[2] + prices[4] + prices[5] = 20。 
可以证明线性选择的最大和是20。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b> prices = [5,6,7,8,9]
<b>输出：</b> 35
<b>解释：</b> 我们可以选择所有索引[1,2,3,4,5]。因为每个元素与前一个元素的差异恰好为1，所以我们的选择是线性的。
所有元素的总和是35，这是每个选择的最大可能总和。</pre>

<p>&nbsp;</p>

<p><b>提示：</b></p>

<ul>
	<li><code>1 &lt;= prices.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= prices[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表**

我们可以将式子进行变换，得到：

$$
prices[i] - i = prices[j] - j
$$

题目实际上求的是相同的 $prices[i] - i$ 下，所有 $prices[i]$ 的和的最大值和。

因此，我们可以用一个哈希表 $cnt$ 来存储 $prices[i] - i$ 下，所有 $prices[i]$ 的和，最后取哈希表中的最大值即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $prices$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxScore(self, prices: List[int]) -> int:
        cnt = Counter()
        for i, x in enumerate(prices):
            cnt[x - i] += x
        return max(cnt.values())
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public long maxScore(int[] prices) {
        Map<Integer, Long> cnt = new HashMap<>();
        for (int i = 0; i < prices.length; ++i) {
            cnt.merge(prices[i] - i, (long) prices[i], Long::sum);
        }
        long ans = 0;
        for (long v : cnt.values()) {
            ans = Math.max(ans, v);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    long long maxScore(vector<int>& prices) {
        unordered_map<int, long long> cnt;
        for (int i = 0; i < prices.size(); ++i) {
            cnt[prices[i] - i] += prices[i];
        }
        long long ans = 0;
        for (auto& [_, v] : cnt) {
            ans = max(ans, v);
        }
        return ans;
    }
};
```

### **Go**

```go
func maxScore(prices []int) (ans int64) {
	cnt := map[int]int{}
	for i, x := range prices {
		cnt[x-i] += x
	}
	for _, v := range cnt {
		ans = max(ans, int64(v))
	}
	return
}

func max(a, b int64) int64 {
	if a > b {
		return a
	}
	return b
}
```

### **Rust**

```rust
use std::collections::HashMap;

impl Solution {
    pub fn max_score(prices: Vec<i32>) -> i64 {
        let mut cnt: HashMap<i32, i64> = HashMap::new();

        for (i, x) in prices.iter().enumerate() {
            let key = (*x) as i32 - (i as i32);
            let count = cnt.entry(key).or_insert(0);
            *count += *x as i64;
        }

        *cnt.values().max().unwrap_or(&0)
    }
}
```

### **TypeScript**

```ts
function maxScore(prices: number[]): number {
    const cnt: Map<number, number> = new Map();
    for (let i = 0; i < prices.length; ++i) {
        const j = prices[i] - i;
        cnt.set(j, (cnt.get(j) || 0) + prices[i]);
    }
    return Math.max(...cnt.values());
}
```

### **...**

```

```

<!-- tabs:end -->
