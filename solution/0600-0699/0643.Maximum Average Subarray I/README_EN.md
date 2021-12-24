# [643. Maximum Average Subarray I](https://leetcode.com/problems/maximum-average-subarray-i)

[中文文档](/solution/0600-0699/0643.Maximum%20Average%20Subarray%20I/README.md)

## Description

<p>Given an array consisting of <code>n</code> integers, find the contiguous subarray of given length <code>k</code> that has the maximum average value. And you need to output the maximum average value.</p>

<p><b>Example 1:</b></p>

<pre>

<b>Input:</b> [1,12,-5,-6,50,3], k = 4

<b>Output:</b> 12.75

<b>Explanation:</b> Maximum average is (12-5-6+50)/4 = 51/4 = 12.75

</pre>

<p>&nbsp;</p>

<p><b>Note:</b></p>

<ol>
	<li>1 &lt;= <code>k</code> &lt;= <code>n</code> &lt;= 30,000.</li>
	<li>Elements of the given array will be in the range [-10,000, 10,000].</li>
</ol>

<p>&nbsp;</p>

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
