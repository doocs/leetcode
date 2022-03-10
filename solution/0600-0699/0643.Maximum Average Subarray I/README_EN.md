# [643. Maximum Average Subarray I](https://leetcode.com/problems/maximum-average-subarray-i)

[中文文档](/solution/0600-0699/0643.Maximum%20Average%20Subarray%20I/README.md)

## Description

<p>You are given an integer array <code>nums</code> consisting of <code>n</code> elements, and an integer <code>k</code>.</p>

<p>Find a contiguous subarray whose <strong>length is equal to</strong> <code>k</code> that has the maximum average value and return <em>this value</em>. Any answer with a calculation error less than <code>10<sup>-5</sup></code> will be accepted.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,12,-5,-6,50,3], k = 4
<strong>Output:</strong> 12.75000
<strong>Explanation:</strong> Maximum average is (12 - 5 - 6 + 50) / 4 = 51 / 4 = 12.75
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5], k = 1
<strong>Output:</strong> 5.00000
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= k &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

Slide window.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findMaxAverage(self, nums: List[int], k: int) -> float:
        s = sum(nums[:k])
        ans = s
        for i in range(k, len(nums)):
            s += (nums[i] - nums[i - k])
            ans = max(ans, s)
        return ans / k
```

### **Java**

```java
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int s = 0;
        for (int i = 0; i < k; ++i) {
            s += nums[i];
        }
        int ans = s;
        for (int i = k; i < nums.length; ++i) {
            s += (nums[i] - nums[i - k]);
            ans = Math.max(ans, s);
        }
        return ans * 1.0 / k;
    }
}
```

### **TypeScript**

```ts
function findMaxAverage(nums: number[], k: number): number {
    let n = nums.length;
    let ans = 0;
    let sum = 0;
    // 前k
    for (let i = 0; i < k; i++) {
        sum += nums[i];
    }
    ans = sum;
    for (let i = k; i < n; i++) {
        sum += nums[i] - nums[i - k];
        ans = Math.max(ans, sum);
    }
    return ans / k;
}
```

### **...**

```

```

<!-- tabs:end -->
