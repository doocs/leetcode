# [1856. 子数组最小乘积的最大值](https://leetcode-cn.com/problems/maximum-subarray-min-product)

[English Version](/solution/1800-1899/1856.Maximum%20Subarray%20Min-Product/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>一个数组的 <strong>最小乘积</strong> 定义为这个数组中 <strong>最小值</strong> <strong>乘以 </strong>数组的 <strong>和</strong> 。</p>

<ul>
	<li>比方说，数组 <code>[3,2,5]</code> （最小值是 <code>2</code>）的最小乘积为 <code>2 * (3+2+5) = 2 * 10 = 20</code> 。</li>
</ul>

<p>给你一个正整数数组 <code>nums</code> ，请你返回 <code>nums</code> 任意 <strong>非空子数组</strong> 的<strong>最小乘积</strong> 的 <strong>最大值</strong> 。由于答案可能很大，请你返回答案对  <code>10<sup>9</sup> + 7</code> <strong>取余 </strong>的结果。</p>

<p>请注意，最小乘积的最大值考虑的是取余操作 <strong>之前</strong> 的结果。题目保证最小乘积的最大值在 <strong>不取余</strong> 的情况下可以用 <strong>64 位有符号整数</strong> 保存。</p>

<p><strong>子数组</strong> 定义为一个数组的 <strong>连续</strong> 部分。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,<strong>2,3,2</strong>]
<b>输出：</b>14
<b>解释：</b>最小乘积的最大值由子数组 [2,3,2] （最小值是 2）得到。
2 * (2+3+2) = 2 * 7 = 14 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [2,<strong>3,3</strong>,1,2]
<b>输出：</b>18
<b>解释：</b>最小乘积的最大值由子数组 [3,3] （最小值是 3）得到。
3 * (3+3) = 3 * 6 = 18 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [3,1,<strong>5,6,4</strong>,2]
<b>输出：</b>60
<b>解释：</b>最小乘积的最大值由子数组 [5,6,4] （最小值是 4）得到。
4 * (5+6+4) = 4 * 15 = 60 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums[i] <= 10<sup>7</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

“前缀和 + 单调栈”实现。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxSumMinProduct(self, nums: List[int]) -> int:
        n = len(nums)
        # 前缀和
        pre_sum = [0] * (n + 1)
        for i in range(1, n + 1):
            pre_sum[i] = pre_sum[i - 1] + nums[i - 1]

        # 单调栈求下一个较小值
        stack = []
        next_lesser = [n] * n
        for i in range(n):
            while stack and nums[stack[-1]] > nums[i]:
                next_lesser[stack.pop()] = i
            stack.append(i)

        # 单调栈求前一个较小值
        stack = []
        prev_lesser = [-1] * n
        for i in range(n - 1, -1, -1):
            while stack and nums[stack[-1]] > nums[i]:
                prev_lesser[stack.pop()] = i
            stack.append(i)

        res = 0
        for i in range(n):
            start, end = prev_lesser[i], next_lesser[i]
            t = nums[i] * (pre_sum[end] - pre_sum[start + 1])
            res = max(res, t)
        return res % (10 ** 9 + 7)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxSumMinProduct(int[] nums) {
        int n = nums.length;

        // 前缀和
        long[] preSum = new long[n + 1];
        for (int i = 1; i < n + 1; ++i) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        // 单调栈求下一个较小值
        Deque<Integer> stack = new ArrayDeque<>();
        int[] nextLesser = new int[n];
        Arrays.fill(nextLesser, n);
        for (int i = 0; i < n; ++i) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                nextLesser[stack.pop()] = i;
            }
            stack.push(i);
        }

        // 单调栈求前一个较小值
        stack = new ArrayDeque<>();
        int[] prevLesser = new int[n];
        Arrays.fill(prevLesser, -1);
        for (int i = n - 1; i >= 0; --i) {
            while (!stack.isEmpty() && nums[stack.peek()] > nums[i]) {
                prevLesser[stack.pop()] = i;
            }
            stack.push(i);
        }
        long res = 0;
        for (int i = 0; i < n; ++i) {
            int start = prevLesser[i], end = nextLesser[i];
            long t = nums[i] * (preSum[end] - preSum[start + 1]);
            res = Math.max(res, t);
        }
        return (int) (res % 1000000007);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
