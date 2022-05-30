# [2275. Largest Combination With Bitwise AND Greater Than Zero](https://leetcode.com/problems/largest-combination-with-bitwise-and-greater-than-zero)

[中文文档](/solution/2200-2299/2275.Largest%20Combination%20With%20Bitwise%20AND%20Greater%20Than%20Zero/README.md)

## Description

<p>The <strong>bitwise AND</strong> of an array <code>nums</code> is the bitwise AND of all integers in <code>nums</code>.</p>

<ul>
	<li>For example, for <code>nums = [1, 5, 3]</code>, the bitwise AND is equal to <code>1 &amp; 5 &amp; 3 = 1</code>.</li>
	<li>Also, for <code>nums = [7]</code>, the bitwise AND is <code>7</code>.</li>
</ul>

<p>You are given an array of positive integers <code>candidates</code>. Evaluate the <strong>bitwise AND</strong> of every <strong>combination</strong> of numbers of <code>candidates</code>. Each number in <code>candidates</code> may only be used <strong>once</strong> in each combination.</p>

<p>Return <em>the size of the <strong>largest</strong> combination of </em><code>candidates</code><em> with a bitwise AND <strong>greater</strong> than </em><code>0</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> candidates = [16,17,71,62,12,24,14]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The combination [16,17,62,24] has a bitwise AND of 16 &amp; 17 &amp; 62 &amp; 24 = 16 &gt; 0.
The size of the combination is 4.
It can be shown that no combination with a size greater than 4 has a bitwise AND greater than 0.
Note that more than one combination may have the largest size.
For example, the combination [62,12,24,14] has a bitwise AND of 62 &amp; 12 &amp; 24 &amp; 14 = 8 &gt; 0.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> candidates = [8,8]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The largest combination [8,8] has a bitwise AND of 8 &amp; 8 = 8 &gt; 0.
The size of the combination is 2, so we return 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= candidates.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= candidates[i] &lt;= 10<sup>7</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
