# [643. 子数组最大平均数 I](https://leetcode-cn.com/problems/maximum-average-subarray-i)

[English Version](/solution/0600-0699/0643.Maximum%20Average%20Subarray%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定 <code>n</code> 个整数，找出平均数最大且长度为 <code>k</code> 的连续子数组，并输出该最大平均数。</p>

<p> </p>

<p><strong>示例：</strong></p>

<pre>
<strong>输入：</strong>[1,12,-5,-6,50,3], k = 4
<strong>输出：</strong>12.75
<strong>解释：</strong>最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li>1 <= <code>k</code> <= <code>n</code> <= 30,000。</li>
	<li>所给数据范围 [-10,000，10,000]。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

滑动窗口。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
