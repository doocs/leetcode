# [697. 数组的度](https://leetcode.cn/problems/degree-of-an-array)

[English Version](/solution/0600-0699/0697.Degree%20of%20an%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个非空且只包含非负数的整数数组&nbsp;<code>nums</code>，数组的 <strong>度</strong> 的定义是指数组里任一元素出现频数的最大值。</p>

<p>你的任务是在 <code>nums</code> 中找到与&nbsp;<code>nums</code>&nbsp;拥有相同大小的度的最短连续子数组，返回其长度。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,2,3,1]
<strong>输出：</strong>2
<strong>解释：</strong>
输入数组的度是 2 ，因为元素 1 和 2 的出现频数最大，均为 2 。
连续子数组里面拥有相同度的有如下所示：
[1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
最短连续子数组 [2, 2] 的长度为 2 ，所以返回 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,2,3,1,4,2]
<strong>输出：</strong>6
<strong>解释：</strong>
数组的度是 3 ，因为元素 2 重复出现 3 次。
所以 [2,2,3,1,4,2] 是最短子数组，因此返回 6 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>nums.length</code>&nbsp;在 <code>1</code> 到 <code>50,000</code> 范围内。</li>
	<li><code>nums[i]</code>&nbsp;是一个在 <code>0</code> 到 <code>49,999</code> 范围内的整数。</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

遍历数组，用哈希表记录数组每个元素出现的次数，以及首次、末次出现的位置。然后遍历哈希表，获取元素出现次数最多（可能有多个）且首末位置差最小的数。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findShortestSubArray(self, nums: List[int]) -> int:
        mapper = {}
        for i, v in enumerate(nums):
            if v in mapper:
                arr = mapper[v]
                arr[0] += 1
                arr[2] = i
            else:
                arr = [1, i, i]
                mapper[v] = arr
        max_degree = min_len = 0
        for arr in mapper.values():
            if max_degree < arr[0]:
                max_degree = arr[0]
                min_len = arr[2] - arr[1] + 1
            elif max_degree == arr[0]:
                min_len = min(min_len, arr[2] - arr[1] + 1)
        return min_len
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, int[]> mapper = new HashMap<>();
        for (int i = 0, n = nums.length; i < n; ++i) {
            if (mapper.containsKey(nums[i])) {
                int[] arr = mapper.get(nums[i]);
                ++arr[0];
                arr[2] = i;
            } else {
                int[] arr = new int[]{1, i, i};
                mapper.put(nums[i], arr);
            }
        }

        int maxDegree = 0, minLen = 0;
        for (Map.Entry<Integer, int[]> entry : mapper.entrySet()) {
            int[] arr = entry.getValue();
            if (maxDegree < arr[0]) {
                maxDegree = arr[0];
                minLen = arr[2] - arr[1] + 1;
            } else if (maxDegree == arr[0]) {
                minLen = Math.min(minLen, arr[2] - arr[1] + 1);
            }
        }
        return minLen;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
