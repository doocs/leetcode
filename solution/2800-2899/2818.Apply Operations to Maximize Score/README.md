# [2818. 操作使得分最大](https://leetcode.cn/problems/apply-operations-to-maximize-score)

[English Version](/solution/2800-2899/2818.Apply%20Operations%20to%20Maximize%20Score/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code>&nbsp;的正整数数组&nbsp;<code>nums</code>&nbsp;和一个整数 <code>k</code>&nbsp;。</p>

<p>一开始，你的分数为 <code>1</code>&nbsp;。你可以进行以下操作至多 <code>k</code>&nbsp;次，目标是使你的分数最大：</p>

<ul>
	<li>选择一个之前没有选过的 <strong>非空</strong>&nbsp;子数组&nbsp;<code>nums[l, ..., r]</code>&nbsp;。</li>
	<li>从&nbsp;<code>nums[l, ..., r]</code>&nbsp;里面选择一个 <strong>质数分数</strong>&nbsp;最高的元素 <code>x</code>&nbsp;。如果多个元素质数分数相同且最高，选择下标最小的一个。</li>
	<li>将你的分数乘以&nbsp;<code>x</code>&nbsp;。</li>
</ul>

<p><code>nums[l, ..., r]</code>&nbsp;表示&nbsp;<code>nums</code>&nbsp;中起始下标为&nbsp;<code>l</code>&nbsp;，结束下标为 <code>r</code>&nbsp;的子数组，两个端点都包含。</p>

<p>一个整数的 <strong>质数分数</strong>&nbsp;等于&nbsp;<code>x</code>&nbsp;不同质因子的数目。比方说，&nbsp;<code>300</code>&nbsp;的质数分数为&nbsp;<code>3</code>&nbsp;，因为&nbsp;<code>300 = 2 * 2 * 3 * 5 * 5</code>&nbsp;。</p>

<p>请你返回进行至多 <code>k</code>&nbsp;次操作后，可以得到的 <strong>最大分数</strong>&nbsp;。</p>

<p>由于答案可能很大，请你将结果对&nbsp;<code>10<sup>9 </sup>+ 7</code>&nbsp;取余后返回。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [8,3,9,3,8], k = 2
<b>输出：</b>81
<b>解释：</b>进行以下操作可以得到分数 81 ：
- 选择子数组 nums[2, ..., 2] 。nums[2] 是子数组中唯一的元素。所以我们将分数乘以 nums[2] ，分数变为 1 * 9 = 9 。
- 选择子数组 nums[2, ..., 3] 。nums[2] 和 nums[3] 质数分数都为 1 ，但是 nums[2] 下标更小。所以我们将分数乘以 nums[2] ，分数变为 9 * 9 = 81 。
81 是可以得到的最高得分。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [19,12,14,6,10,18], k = 3
<b>输出：</b>4788
<b>解释：</b>进行以下操作可以得到分数 4788 ：
- 选择子数组 nums[0, ..., 0] 。nums[0] 是子数组中唯一的元素。所以我们将分数乘以 nums[0] ，分数变为 1 * 19 = 19 。
- 选择子数组 nums[5, ..., 5] 。nums[5] 是子数组中唯一的元素。所以我们将分数乘以 nums[5] ，分数变为 19 * 18 = 342 。
- 选择子数组 nums[2, ..., 3] 。nums[2] 和 nums[3] 质数分数都为 2，但是 nums[2] 下标更小。所以我们将分数乘以 nums[2] ，分数变为  342 * 14 = 4788 。
4788 是可以得到的最高的分。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length == n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= min(n * (n + 1) / 2, 10<sup>9</sup>)</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：单调栈 + 排序贪心**

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
def primeFactors(n):
    i = 2
    ans = set()
    while i * i <= n:
        while n % i == 0:
            ans.add(i)
            n //= i
        i += 1
    if n > 1:
        ans.add(n)
    return len(ans)


class Solution:
    def maximumScore(self, nums: List[int], k: int) -> int:
        mod = 10 ** 9 + 7
        arr = [(i, primeFactors(x), x) for i, x in enumerate(nums)]
        n = len(nums)

        left = [-1] * n
        right = [n] * n
        stk = []
        for i, f, x in arr:
            while stk and stk[-1][0] < f:
                stk.pop()
            if stk:
                left[i] = stk[-1][1]
            stk.append((f, i))

        stk = []
        for i, f, x in arr[::-1]:
            while stk and stk[-1][0] <= f:
                stk.pop()
            if stk:
                right[i] = stk[-1][1]
            stk.append((f, i))

        arr.sort(key=lambda x: -x[2])
        ans = 1
        for i, f, x in arr:
            l, r = left[i], right[i]
            cnt = (i - l) * (r - i)
            if cnt <= k:
                ans = ans * pow(x, cnt, mod) % mod
                k -= cnt
            else:
                ans = ans * pow(x, k, mod) % mod
                break
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **C++**

```cpp

```

### **Go**

```go

```

### **...**

```

```

<!-- tabs:end -->
