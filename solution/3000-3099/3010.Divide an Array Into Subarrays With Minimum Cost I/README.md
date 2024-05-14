# [3010. 将数组分成最小总代价的子数组 I](https://leetcode.cn/problems/divide-an-array-into-subarrays-with-minimum-cost-i)

[English Version](/solution/3000-3099/3010.Divide%20an%20Array%20Into%20Subarrays%20With%20Minimum%20Cost%20I/README_EN.md)

<!-- tags:数组,枚举,排序 -->

<!-- difficulty:简单 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code>&nbsp;的整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>一个数组的 <strong>代价</strong>&nbsp;是它的 <strong>第一个</strong>&nbsp;元素。比方说，<code>[1,2,3]</code>&nbsp;的代价是&nbsp;<code>1</code>&nbsp;，<code>[3,4,1]</code>&nbsp;的代价是&nbsp;<code>3</code>&nbsp;。</p>

<p>你需要将&nbsp;<code>nums</code>&nbsp;分成&nbsp;<code>3</code>&nbsp;个&nbsp;<strong>连续且没有交集</strong>&nbsp;的子数组。</p>

<p>请你返回这些<span data-keyword="subarray">子数组</span>的 <strong>最小</strong>&nbsp;代价&nbsp;<b>总和</b>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3,12]
<b>输出：</b>6
<b>解释：</b>最佳分割成 3 个子数组的方案是：[1] ，[2] 和 [3,12] ，总代价为 1 + 2 + 3 = 6 。
其他得到 3 个子数组的方案是：
- [1] ，[2,3] 和 [12] ，总代价是 1 + 2 + 12 = 15 。
- [1,2] ，[3] 和 [12] ，总代价是 1 + 3 + 12 = 16 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [5,4,3]
<b>输出：</b>12
<b>解释：</b>最佳分割成 3 个子数组的方案是：[5] ，[4] 和 [3] ，总代价为 5 + 4 + 3 = 12 。
12 是所有分割方案里的最小总代价。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [10,3,1,1]
<b>输出：</b>12
<b>解释：</b>最佳分割成 3 个子数组的方案是：[10,3] ，[1] 和 [1] ，总代价为 10 + 1 + 1 = 12 。
12 是所有分割方案里的最小总代价。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= n &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 50</code></li>
</ul>

## 解法

### 方法一：遍历找最小值和次小值

我们记数组 $nums$ 的第一个元素为 $a$，其余元素中最小的元素为 $b$，次小的元素为 $c$，那么答案就是 $a+b+c$。

时间复杂度 $O(n)$，其中 $n$ 是数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

```python
class Solution:
    def minimumCost(self, nums: List[int]) -> int:
        a, b, c = nums[0], inf, inf
        for x in nums[1:]:
            if x < b:
                c, b = b, x
            elif x < c:
                c = x
        return a + b + c
```

```java
class Solution {
    public int minimumCost(int[] nums) {
        int a = nums[0], b = 100, c = 100;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] < b) {
                c = b;
                b = nums[i];
            } else if (nums[i] < c) {
                c = nums[i];
            }
        }
        return a + b + c;
    }
}
```

```cpp
class Solution {
public:
    int minimumCost(vector<int>& nums) {
        int a = nums[0], b = 100, c = 100;
        for (int i = 1; i < nums.size(); ++i) {
            if (nums[i] < b) {
                c = b;
                b = nums[i];
            } else if (nums[i] < c) {
                c = nums[i];
            }
        }
        return a + b + c;
    }
};
```

```go
func minimumCost(nums []int) int {
	a, b, c := nums[0], 100, 100
	for _, x := range nums[1:] {
		if x < b {
			b, c = x, b
		} else if x < c {
			c = x
		}
	}
	return a + b + c
}
```

```ts
function minimumCost(nums: number[]): number {
    let [a, b, c] = [nums[0], 100, 100];
    for (const x of nums.slice(1)) {
        if (x < b) {
            [b, c] = [x, b];
        } else if (x < c) {
            c = x;
        }
    }
    return a + b + c;
}
```

<!-- tabs:end -->

<!-- end -->
