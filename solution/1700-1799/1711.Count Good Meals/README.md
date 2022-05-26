# [1711. 大餐计数](https://leetcode.cn/problems/count-good-meals)

[English Version](/solution/1700-1799/1711.Count%20Good%20Meals/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p><strong>大餐</strong> 是指 <strong>恰好包含两道不同餐品</strong> 的一餐，其美味程度之和等于 2 的幂。</p>

<p>你可以搭配 <strong>任意</strong> 两道餐品做一顿大餐。</p>

<p>给你一个整数数组 <code>deliciousness</code> ，其中 <code>deliciousness[i]</code> 是第 <code>i<sup>​​​​​​</sup>​​​​</code>​​​​ 道餐品的美味程度，返回你可以用数组中的餐品做出的不同 <strong>大餐</strong> 的数量。结果需要对 <code>10<sup>9</sup> + 7</code> 取余。</p>

<p>注意，只要餐品下标不同，就可以认为是不同的餐品，即便它们的美味程度相同。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>deliciousness = [1,3,5,7,9]
<strong>输出：</strong>4
<strong>解释：</strong>大餐的美味程度组合为 (1,3) 、(1,7) 、(3,5) 和 (7,9) 。
它们各自的美味程度之和分别为 4 、8 、8 和 16 ，都是 2 的幂。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>deliciousness = [1,1,1,3,3,3,7]
<strong>输出：</strong>15
<strong>解释：</strong>大餐的美味程度组合为 3 种 (1,1) ，9 种 (1,3) ，和 3 种 (1,7) 。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= deliciousness.length <= 10<sup>5</sup></code></li>
	<li><code>0 <= deliciousness[i] <= 2<sup>20</sup></code></li>
</ul>

## 解法

用最暴力的方法枚举每对元素肯定会超时，可以用哈希表优化对**之前元素出现次数**的查询。

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countPairs(self, deliciousness: List[int]) -> int:
        mod = 1000000007
        limit = max(deliciousness) * 2
        pairs = 0
        freq = defaultdict(int)
        for d in deliciousness:
            target = 1
            while target <= limit:
                pairs = (pairs + freq[target - d]) % mod
                target = target << 1
            freq[d] += 1
        return pairs
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {

    private static final int MOD = 1000000007;

    public int countPairs(int[] deliciousness) {
        int limit = Arrays.stream(deliciousness).max().getAsInt() * 2;
        int pairs = 0;
        Map<Integer, Integer> freq = new HashMap<>();
        for (int d : deliciousness) {
            for (int sum = 1; sum <= limit; sum <<= 1) {
                int count = freq.getOrDefault(sum - d, 0);
                pairs = (pairs + count) % MOD;
            }
            freq.merge(d, 1, Integer::sum);
        }
        return pairs;
    }
}
```

### **Go**

```go
const mod int = 1e9 + 7

func countPairs(deliciousness []int) int {
	limit := 0
	for _, d := range deliciousness {
		limit = max(limit, d)
	}
	limit *= 2
	pairs := 0
	freq := make(map[int]int)
	for _, d := range deliciousness {
		for sum := 1; sum <= limit; sum <<= 1 {
			pairs = (pairs + freq[sum-d]) % mod
		}
		freq[d]++
	}
	return pairs
}

func max(x, y int) int {
	if x > y {
		return x
	}
	return y
}
```

### **...**

```

```

<!-- tabs:end -->
