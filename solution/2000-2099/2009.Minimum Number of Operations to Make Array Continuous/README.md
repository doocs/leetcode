# [2009. 使数组连续的最少操作数](https://leetcode.cn/problems/minimum-number-of-operations-to-make-array-continuous)

[English Version](/solution/2000-2099/2009.Minimum%20Number%20of%20Operations%20to%20Make%20Array%20Continuous/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;。每一次操作中，你可以将&nbsp;<code>nums</code>&nbsp;中&nbsp;<strong>任意</strong>&nbsp;一个元素替换成 <strong>任意&nbsp;</strong>整数。</p>

<p>如果&nbsp;<code>nums</code>&nbsp;满足以下条件，那么它是 <strong>连续的</strong>&nbsp;：</p>

<ul>
	<li><code>nums</code>&nbsp;中所有元素都是 <b>互不相同</b>&nbsp;的。</li>
	<li><code>nums</code>&nbsp;中 <strong>最大</strong>&nbsp;元素与&nbsp;<strong>最小</strong>&nbsp;元素的差等于&nbsp;<code>nums.length - 1</code>&nbsp;。</li>
</ul>

<p>比方说，<code>nums = [4, 2, 5, 3]</code>&nbsp;是 <strong>连续的</strong>&nbsp;，但是&nbsp;<code>nums = [1, 2, 3, 5, 6]</code> <strong>不是连续的</strong>&nbsp;。</p>

<p>请你返回使 <code>nums</code>&nbsp;<strong>连续</strong>&nbsp;的 <strong>最少</strong>&nbsp;操作次数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [4,2,5,3]
<b>输出：</b>0
<b>解释：</b>nums 已经是连续的了。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [1,2,3,5,6]
<b>输出：</b>1
<b>解释：</b>一个可能的解是将最后一个元素变为 4 。
结果数组为 [1,2,3,5,4] ，是连续数组。
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>nums = [1,10,100,1000]
<b>输出：</b>3
<b>解释：</b>一个可能的解是：
- 将第二个元素变为 2 。
- 将第三个元素变为 3 。
- 将第四个元素变为 4 。
结果数组为 [1,2,3,4] ，是连续数组。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minOperations(self, nums: List[int]) -> int:
        n = len(nums)
        nums = sorted(set(nums))

        ans = n
        for i, start in enumerate(nums):
            end = start + n - 1
            j = bisect_right(nums, end)
            remainLen = j - i
            ans = min(ans, n - remainLen)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minOperations(int[] nums) {
        int N = nums.length;
        if (N == 1) return 0;
        Arrays.sort(nums);
        int M = 1;
        for (int i = 1; i < N; i++) {
            if (nums[i] != nums[i - 1])
                nums[M++] = nums[i];
        }

        int j = 0;
        int ans = N;
        for (int i = 0; i < M; i++) {
            while (j < M && nums[j] <= N + nums[i] - 1) j++;
            ans = Math.min(ans, N - j + i);
        }

        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int End = unique(nums.begin(), nums.end()) - nums.begin();
        int n = nums.size();

        int len = 0;
        for (int i = 0; i < End; ++i) {
            int temp = upper_bound(nums.begin(), nums.begin() + End, n + nums[i] - 1) - nums.begin() - i;
            len = max(len, temp);
        }
        return n - len;
    }
};
```

### **...**

```

```

<!-- tabs:end -->
