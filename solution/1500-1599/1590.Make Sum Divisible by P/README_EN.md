# [1590. Make Sum Divisible by P](https://leetcode.com/problems/make-sum-divisible-by-p)

[中文文档](/solution/1500-1599/1590.Make%20Sum%20Divisible%20by%20P/README.md)

## Description

<p>Given an array of positive integers <code>nums</code>, remove the <strong>smallest</strong> subarray (possibly <strong>empty</strong>) such that the <strong>sum</strong> of the remaining elements is divisible by <code>p</code>. It is <strong>not</strong> allowed to remove the whole array.</p>

<p>Return <em>the length of the smallest subarray that you need to remove, or </em><code>-1</code><em> if it&#39;s impossible</em>.</p>

<p>A <strong>subarray</strong> is defined as a contiguous block of elements in the array.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1,4,2], p = 6
<strong>Output:</strong> 1
<strong>Explanation:</strong> The sum of the elements in nums is 10, which is not divisible by 6. We can remove the subarray [4], and the sum of the remaining elements is 6, which is divisible by 6.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [6,3,5,2], p = 9
<strong>Output:</strong> 2
<strong>Explanation:</strong> We cannot remove a single element to get a sum divisible by 9. The best way is to remove the subarray [5,2], leaving us with [6,3] with sum 9.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3], p = 3
<strong>Output:</strong> 0
<strong>Explanation:</strong> Here the sum is 6. which is already divisible by 3. Thus we do not need to remove anything.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= p &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python

```

### **Java**

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
