---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2200-2299/2275.Largest%20Combination%20With%20Bitwise%20AND%20Greater%20Than%20Zero/README.md
rating: 1642
source: 第 293 场周赛 Q3
tags:
    - 位运算
    - 数组
    - 哈希表
    - 计数
---

<!-- problem:start -->

# [2275. 按位与结果大于零的最长组合](https://leetcode.cn/problems/largest-combination-with-bitwise-and-greater-than-zero)

[English Version](/solution/2200-2299/2275.Largest%20Combination%20With%20Bitwise%20AND%20Greater%20Than%20Zero/README_EN.md)

## 题目描述

<!-- description:start -->

<p>对数组&nbsp;<code>nums</code> 执行 <strong>按位与</strong> 相当于对数组&nbsp;<code>nums</code> 中的所有整数执行 <strong>按位与</strong> 。</p>

<ul>
	<li>例如，对 <code>nums = [1, 5, 3]</code> 来说，按位与等于 <code>1 &amp; 5 &amp; 3 = 1</code> 。</li>
	<li>同样，对 <code>nums = [7]</code> 而言，按位与等于 <code>7</code> 。</li>
</ul>

<p>给你一个正整数数组 <code>candidates</code> 。计算 <code>candidates</code> 中的数字每种组合下 <strong>按位与</strong> 的结果。 <code>candidates</code> 中的每个数字在每种组合中只能使用 <strong>一次</strong> 。</p>

<p>返回按位与结果大于 <code>0</code> 的 <strong>最长</strong> 组合的长度<em>。</em></p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>candidates = [16,17,71,62,12,24,14]
<strong>输出：</strong>4
<strong>解释：</strong>组合 [16,17,62,24] 的按位与结果是 16 &amp; 17 &amp; 62 &amp; 24 = 16 &gt; 0 。
组合长度是 4 。
可以证明不存在按位与结果大于 0 且长度大于 4 的组合。
注意，符合长度最大的组合可能不止一种。
例如，组合 [62,12,24,14] 的按位与结果是 62 &amp; 12 &amp; 24 &amp; 14 = 8 &gt; 0 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>candidates = [8,8]
<strong>输出：</strong>2
<strong>解释：</strong>最长组合是 [8,8] ，按位与结果 8 &amp; 8 = 8 &gt; 0 。
组合长度是 2 ，所以返回 2 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= candidates.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= candidates[i] &lt;= 10<sup>7</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：位运算

题目需要找到按位与结果大于 $0$ 的数字组合的最大长度，那么说明一定存在某个二进制位，所有数字在这个二进制位上都是 $1$。因此，我们可以枚举每个二进制位，统计所有数字在这个二进制位上的 $1$ 的个数，最后取最大值即可。

时间复杂度 $O(n \times \log M)$，其中 $n$ 和 $M$ 分别是数组 $\textit{candidates}$ 的长度和数组中的最大值。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def largestCombination(self, candidates: List[int]) -> int:
        ans = 0
        for i in range(max(candidates).bit_length()):
            ans = max(ans, sum(x >> i & 1 for x in candidates))
        return ans
```

#### Java

```java
class Solution {
    public int largestCombination(int[] candidates) {
        int mx = Arrays.stream(candidates).max().getAsInt();
        int m = Integer.SIZE - Integer.numberOfLeadingZeros(mx);
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            int cnt = 0;
            for (int x : candidates) {
                cnt += x >> i & 1;
            }
            ans = Math.max(ans, cnt);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int largestCombination(vector<int>& candidates) {
        int mx = *max_element(candidates.begin(), candidates.end());
        int m = 32 - __builtin_clz(mx);
        int ans = 0;
        for (int i = 0; i < m; ++i) {
            int cnt = 0;
            for (int x : candidates) {
                cnt += x >> i & 1;
            }
            ans = max(ans, cnt);
        }
        return ans;
    }
};
```

#### Go

```go
func largestCombination(candidates []int) (ans int) {
	mx := slices.Max(candidates)
	m := bits.Len(uint(mx))
	for i := 0; i < m; i++ {
		cnt := 0
		for _, x := range candidates {
			cnt += (x >> i) & 1
		}
		ans = max(ans, cnt)
	}
	return
}
```

#### TypeScript

```ts
function largestCombination(candidates: number[]): number {
    const mx = Math.max(...candidates);
    const m = mx.toString(2).length;
    let ans = 0;
    for (let i = 0; i < m; ++i) {
        let cnt = 0;
        for (const x of candidates) {
            cnt += (x >> i) & 1;
        }
        ans = Math.max(ans, cnt);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
