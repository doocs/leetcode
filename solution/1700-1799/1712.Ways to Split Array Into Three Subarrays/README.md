# [1712. 将数组分成三个子数组的方案数](https://leetcode.cn/problems/ways-to-split-array-into-three-subarrays)

[English Version](/solution/1700-1799/1712.Ways%20to%20Split%20Array%20Into%20Three%20Subarrays/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>我们称一个分割整数数组的方案是 <strong>好的</strong> ，当它满足：</p>

<ul>
	<li>数组被分成三个 <strong>非空</strong> 连续子数组，从左至右分别命名为 <code>left</code> ， <code>mid</code> ， <code>right</code> 。</li>
	<li><code>left</code> 中元素和小于等于 <code>mid</code> 中元素和，<code>mid</code> 中元素和小于等于 <code>right</code> 中元素和。</li>
</ul>

<p>给你一个 <strong>非负</strong> 整数数组 <code>nums</code> ，请你返回 <strong>好的</strong> 分割 <code>nums</code> 方案数目。由于答案可能会很大，请你将结果对 <code>10<sup>9 </sup>+ 7</code> 取余后返回。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,1,1]
<b>输出：</b>1
<b>解释：</b>唯一一种好的分割方案是将 nums 分成 [1] [1] [1] 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,2,2,5,0]
<b>输出：</b>3
<b>解释：</b>nums 总共有 3 种好的分割方案：
[1] [2] [2,2,5,0]
[1] [2,2] [2,5,0]
[1,2] [2,2] [5,0]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [3,2,1]
<b>输出：</b>0
<b>解释：</b>没有好的分割方案。</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>0 <= nums[i] <= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def waysToSplit(self, nums: List[int]) -> int:
        mod = 1e9 + 7
        n = len(nums)
        pre = [0] * (n + 1)
        for i in range(1, n + 1):
            pre[i] = pre[i - 1] + nums[i - 1]
        ans = 0
        for i in range(1, n - 1):
            if pre[i] * 3 > pre[n]:
                break
            left, right = i + 1, n - 1
            while left < right:
                mid = (left + right + 1) >> 1
                if pre[mid] - pre[i] <= pre[n] - pre[mid]:
                    left = mid
                else:
                    right = mid - 1
            mid_right = left
            left, right = i + 1, n - 1
            while left < right:
                mid = (left + right) >> 1
                if pre[mid] - pre[i] >= pre[i]:
                    right = mid
                else:
                    left = mid + 1
            ans += (mid_right - left + 1) % mod
        return int(ans % mod)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int waysToSplit(int[] nums) {
        double mod = 1e9 + 7;
        int n = nums.length;
        long[] pre = new long[n + 1];
        for (int i = 1; i < n + 1; i++) {
            pre[i] = pre[i - 1] + nums[i - 1];
        }
        double ans = 0;
        for (int i = 1; i < n - 1; i++) {
            if (pre[i] * 3 > pre[n]) {
                break;
            }
            int left = i + 1, right = n - 1;
            while (left < right) {
                int mid = (left + right + 1) >> 1;
                if (pre[mid] - pre[i] <= pre[n] - pre[mid]) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
            int midRight = left;
            left = i + 1; right = n - 1;
            while (left < right) {
                int mid = (left + right) >> 1;
                if (pre[mid] - pre[i] >= pre[i]) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            ans += (midRight - left + 1) % mod;
        }
        return (int) (ans % mod);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
