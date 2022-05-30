# [2275. 按位与结果大于零的最长组合](https://leetcode.cn/problems/largest-combination-with-bitwise-and-greater-than-zero)

[English Version](/solution/2200-2299/2275.Largest%20Combination%20With%20Bitwise%20AND%20Greater%20Than%20Zero/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

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

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：位运算**

大于 0，实际上就是要求存在某个二进制位（0-31），满足所有数字的这一位均为 1。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def largestCombination(self, candidates: List[int]) -> int:
        ans = 0
        for i in range(32):
            t = 0
            for x in candidates:
                t += (x >> i) & 1
            ans = max(ans, t)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int largestCombination(int[] candidates) {
        int ans = 0;
        for (int i = 0; i < 32; ++i) {
            int t = 0;
            for (int x : candidates) {
                t += (x >> i) & 1;
            }
            ans = Math.max(ans, t);
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function largestCombination(candidates: number[]): number {
    const n = 24;
    let ans = 0;
    for (let i = 0; i < n; i++) {
        let count = 0;
        for (let num of candidates) {
            if ((num >> i) & 1) count++;
        }
        ans = Math.max(ans, count);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
