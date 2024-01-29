# [3022. 给定操作次数内使剩余元素的或值最小](https://leetcode.cn/problems/minimize-or-of-remaining-elements-using-operations)

[English Version](/solution/3000-3099/3022.Minimize%20OR%20of%20Remaining%20Elements%20Using%20Operations/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。</p>

<p>一次操作中，你可以选择 <code>nums</code>&nbsp;中满足&nbsp;<code>0 &lt;= i &lt; nums.length - 1</code>&nbsp;的一个下标 <code>i</code>&nbsp;，并将&nbsp;<code>nums[i]</code> 和&nbsp;<code>nums[i + 1]</code>&nbsp;替换为数字&nbsp;<code>nums[i] &amp; nums[i + 1]</code>&nbsp;，其中&nbsp;<code>&amp;</code>&nbsp;表示按位&nbsp;<code>AND</code>&nbsp;操作。</p>

<p>请你返回 <strong>至多</strong>&nbsp;<code>k</code>&nbsp;次操作以内，使 <code>nums</code>&nbsp;中所有剩余元素按位 <code>OR</code>&nbsp;结果的 <strong>最小值</strong>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [3,5,3,2,7], k = 2
<b>输出：</b>3
<b>解释：</b>执行以下操作：
1. 将 nums[0] 和 nums[1] 替换为 (nums[0] &amp; nums[1]) ，得到 nums 为 [1,3,2,7] 。
2. 将 nums[2] 和 nums[3] 替换为 (nums[2] &amp; nums[3]) ，得到 nums 为 [1,3,2] 。
最终数组的按位或值为 3 。
3 是 k 次操作以内，可以得到的剩余元素的最小按位或值。</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [7,3,15,14,2,8], k = 4
<b>输出：</b>2
<b>解释：</b>执行以下操作：
1. 将 nums[0] 和 nums[1] 替换为 (nums[0] &amp; nums[1]) ，得到 nums 为 [3,15,14,2,8] 。
2. 将 nums[0] 和 nums[1] 替换为 (nums[0] &amp; nums[1]) ，得到 nums 为 [3,14,2,8] 。
3. 将 nums[0] 和 nums[1] 替换为 (nums[0] &amp; nums[1]) ，得到 nums 为 [2,2,8] 。
4. 将 nums[1] 和 nums[2] 替换为 (nums[1] &amp; nums[2]) ，得到 nums 为 [2,0] 。
最终数组的按位或值为 2 。
2 是 k 次操作以内，可以得到的剩余元素的最小按位或值。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [10,7,10,3,9,14,9,4], k = 1
<b>输出：</b>15
<b>解释：</b>不执行任何操作，nums 的按位或值为 15 。
15 是 k 次操作以内，可以得到的剩余元素的最小按位或值。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt; 2<sup>30</sup></code></li>
	<li><code>0 &lt;= k &lt; nums.length</code></li>
</ul>

## 解法

### 方法一

<!-- tabs:start -->

```python
class Solution:
    def minOrAfterOperations(self, nums: List[int], k: int) -> int:
        ans = 0
        rans = 0
        for i in range(29, -1, -1):
            test = ans + (1 << i)
            cnt = 0
            val = 0
            for num in nums:
                if val == 0:
                    val = test & num
                else:
                    val &= test & num
                if val:
                    cnt += 1
            if cnt > k:
                rans += (1 << i)
            else:
                ans += (1 << i)
        return rans
```

```java
class Solution {
    public int minOrAfterOperations(int[] nums, int k) {
        int ans = 0, rans = 0;
        for (int i = 29; i >= 0; i--) {
            int test = ans + (1 << i);
            int cnt = 0;
            int val = 0;
            for (int num : nums) {
                if (val == 0) {
                    val = test & num;
                } else {
                    val &= test & num;
                }
                if (val != 0) {
                    cnt++;
                }
            }
            if (cnt > k) {
                rans += (1 << i);
            } else {
                ans += (1 << i);
            }
        }
        return rans;
    }
}
```

```cpp
class Solution {
public:
    int minOrAfterOperations(vector<int>& nums, int k) {
        int ans = 0, rans = 0;
        for (int i = 29; i >= 0; i--) {
            int test = ans + (1 << i);
            int cnt = 0;
            int val = 0;
            for (auto it : nums) {
                if (val == 0) {
                    val = test & it;
                } else {
                    val &= test & it;
                }
                if (val) {
                    cnt++;
                }
            }
            if (cnt > k) {
                rans += (1 << i);
            } else{
                ans += (1 << i);
            }
        }
        return rans;
    }
};
```

```go
func minOrAfterOperations(nums []int, k int) int {
    ans := 0
    rans := 0
    for i := 29; i >= 0; i-- {
        test := ans + (1 << i)
        cnt := 0
        val := 0
        for _, num := range nums {
            if val == 0 {
                val = test & num
            } else {
                val &= test & num
            }
            if val != 0 {
                cnt++
            }
        }
        if cnt > k {
            rans += (1 << i)
        } else {
            ans += (1 << i)
        }
    }
    return rans
}
```

<!-- tabs:end -->

<!-- end -->
