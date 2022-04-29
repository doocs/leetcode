# [1590. 使数组和能被 P 整除](https://leetcode.cn/problems/make-sum-divisible-by-p)

[English Version](/solution/1500-1599/1590.Make%20Sum%20Divisible%20by%20P/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个正整数数组&nbsp;<code>nums</code>，请你移除 <strong>最短</strong>&nbsp;子数组（可以为 <strong>空</strong>），使得剩余元素的 <strong>和</strong>&nbsp;能被 <code>p</code>&nbsp;整除。 <strong>不允许</strong>&nbsp;将整个数组都移除。</p>

<p>请你返回你需要移除的最短子数组的长度，如果无法满足题目要求，返回 <code>-1</code>&nbsp;。</p>

<p><strong>子数组</strong>&nbsp;定义为原数组中连续的一组元素。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [3,1,4,2], p = 6
<strong>输出：</strong>1
<strong>解释：</strong>nums 中元素和为 10，不能被 p 整除。我们可以移除子数组 [4] ，剩余元素的和为 6 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [6,3,5,2], p = 9
<strong>输出：</strong>2
<strong>解释：</strong>我们无法移除任何一个元素使得和被 9 整除，最优方案是移除子数组 [5,2] ，剩余元素为 [6,3]，和为 9 。
</pre>

<p><strong>示例&nbsp;3：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,3], p = 3
<strong>输出：</strong>0
<strong>解释：</strong>和恰好为 6 ，已经能被 3 整除了。所以我们不需要移除任何元素。
</pre>

<p><strong>示例&nbsp; 4：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,3], p = 7
<strong>输出：</strong>-1
<strong>解释：</strong>没有任何方案使得移除子数组后剩余元素的和被 7 整除。
</pre>

<p><strong>示例 5：</strong></p>

<pre><strong>输入：</strong>nums = [1000000000,1000000000,1000000000], p = 3
<strong>输出：</strong>0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= p &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python

```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java

```

### **TypeScript**

```ts
function minSubarray(nums: number[], p: number): number {
    const n = nums.length;
    let mod = 0;
    for (let i = 0; i < n; i++) {
        mod = (nums[i] + mod) % p;
    }
    if (!mod) return 0;

    let hashMap = new Map<number, number>();
    hashMap.set(0, -1);
    let ans = n;
    let subMod = 0;
    for (let i = 0; i < n; i++) {
        let cur = nums[i];
        subMod = (subMod + cur) % p;
        let target = (subMod - mod + p) % p;
        if (hashMap.has(target)) {
            let j = hashMap.get(target);
            ans = Math.min(i - j, ans);
            if (ans == 1 && ans != n) {
                return ans;
            }
        }
        hashMap.set(subMod, i);
    }
    return ans == n ? -1 : ans;
}
```

### **...**

```

```

<!-- tabs:end -->
