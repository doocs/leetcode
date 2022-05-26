# [368. 最大整除子集](https://leetcode.cn/problems/largest-divisible-subset)

[English Version](/solution/0300-0399/0368.Largest%20Divisible%20Subset/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

给你一个由 <strong>无重复</strong> 正整数组成的集合 <code>nums</code> ，请你找出并返回其中最大的整除子集 <code>answer</code> ，子集中每一元素对 <code>(answer[i], answer[j])</code> 都应当满足：

<ul>
	<li><code>answer[i] % answer[j] == 0</code> ，或</li>
	<li><code>answer[j] % answer[i] == 0</code></li>
</ul>

<p>如果存在多个有效解子集，返回其中任何一个均可。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3]
<strong>输出：</strong>[1,2]
<strong>解释：</strong>[1,3] 也会被视为正确答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,4,8]
<strong>输出：</strong>[1,2,4,8]
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 1000</code></li>
	<li><code>1 <= nums[i] <= 2 * 10<sup>9</sup></code></li>
	<li><code>nums</code> 中的所有整数 <strong>互不相同</strong></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def largestDivisibleSubset(self, nums: List[int]) -> List[int]:
        nums.sort()
        n = len(nums)
        f, p = [0] * n, [0] * n
        for i in range(n):
            l, pre = 1, i
            for j in range(n):
                if nums[i] % nums[j] == 0 and f[j] + 1 > l:
                    l = f[j] + 1
                    pre = j
            f[i] = l
            p[i] = pre
        max_len, max_index = 0, 0
        for i, v in enumerate(f):
            if max_len < v:
                max_len = v
                max_index = i
        ans = []
        while len(ans) < max_len:
            ans.append(nums[max_index])
            max_index = p[max_index]
        return ans[::-1]
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int[] f = new int[n], p = new int[n];
        for (int i = 0; i < n; i++) {
            int l = 1, pre = i;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && f[j] + 1 > l) {
                    l = f[j] + 1;
                    pre = j;
                }
            }
            f[i] = l;
            p[i] = pre;
        }
        int maxLen = 0, maxIndex = 0;
        for (int i = 0; i < n; i++) {
            if (f[i] > maxLen) {
                maxLen = f[i];
                maxIndex = i;
            }
        }
        List<Integer> ans = new ArrayList<>();
        while (ans.size() < maxLen) {
            ans.add(nums[maxIndex]);
            maxIndex = p[maxIndex];
        }
        Collections.reverse(ans);
        return ans;
    }
}
```

### **...**

```

```

<!-- tabs:end -->
