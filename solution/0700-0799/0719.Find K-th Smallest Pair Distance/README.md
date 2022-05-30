# [719. 找出第 K 小的数对距离](https://leetcode.cn/problems/find-k-th-smallest-pair-distance)

[English Version](/solution/0700-0799/0719.Find%20K-th%20Smallest%20Pair%20Distance/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>数对 <code>(a,b)</code> 由整数 <code>a</code> 和 <code>b</code> 组成，其数对距离定义为 <code>a</code> 和 <code>b</code> 的绝对差值。</p>

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>k</code> ，数对由 <code>nums[i]</code> 和 <code>nums[j]</code> 组成且满足 <code>0 &lt;= i &lt; j &lt; nums.length</code> 。返回 <strong>所有数对距离中</strong> 第 <code>k</code> 小的数对距离。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,3,1], k = 1
<strong>输出：</strong>0
<strong>解释：</strong>数对和对应的距离如下：
(1,3) -&gt; 2
(1,1) -&gt; 0
(3,1) -&gt; 2
距离第 1 小的数对是 (1,1) ，距离为 0 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,1], k = 2
<strong>输出：</strong>0
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,6,1], k = 3
<strong>输出：</strong>5
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k &lt;= n * (n - 1) / 2</code></li>
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
function smallestDistancePair(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let left = 0,
        right = nums[n - 1] - nums[0];
    while (left < right) {
        let mid = (left + right) >> 1;
        let count = 0,
            i = 0;
        for (let j = 0; j < n; j++) {
            // 索引[i, j]距离nums[j]的距离<=mid
            while (nums[j] - nums[i] > mid) {
                i++;
            }
            count += j - i;
        }
        if (count >= k) {
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
