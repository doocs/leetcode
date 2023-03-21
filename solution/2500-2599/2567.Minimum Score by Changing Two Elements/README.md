# [2567. 修改两个元素的最小分数](https://leetcode.cn/problems/minimum-score-by-changing-two-elements)

[English Version](/solution/2500-2599/2567.Minimum%20Score%20by%20Changing%20Two%20Elements/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<ul>
	<li><code>nums</code> 的 <strong>最小</strong>&nbsp;得分是满足 <code>0 &lt;= i &lt; j &lt; nums.length</code>&nbsp;的&nbsp;<code>|nums[i]&nbsp;- nums[j]|</code>&nbsp;的最小值。</li>
	<li><code>nums</code>的 <strong>最大 </strong>得分是满足 <code>0 &lt;= i &lt; j &lt; nums.length</code>&nbsp;的&nbsp;<code>|nums[i]&nbsp;- nums[j]|</code>&nbsp;的最大值。</li>
	<li><code>nums</code>&nbsp;的分数是 <strong>最大</strong>&nbsp;得分与 <strong>最小</strong>&nbsp;得分的和。</li>
</ul>

<p>我们的目标是最小化&nbsp;<code>nums</code>&nbsp;的分数。你 <strong>最多</strong> 可以修改&nbsp;<code>nums</code>&nbsp;中&nbsp;<strong>2</strong>&nbsp;个元素的值。</p>

<p>请你返回修改&nbsp;<code>nums</code>&nbsp;中&nbsp;<strong>至多两个</strong>&nbsp;元素的值后，可以得到的 <strong>最小分数</strong>&nbsp;。</p>

<p><code>|x|</code>&nbsp;表示 <code>x</code>&nbsp;的绝对值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,4,3]
<b>输出：</b>0
<b>解释：</b>将 nums[1] 和 nums[2] 的值改为 1 ，nums 变为 [1,1,1] 。<code>|nums[i] - nums[j]|</code> 的值永远为 0 ，所以我们返回 0 + 0 = 0 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,4,7,8,5]
<b>输出：</b>3
<b>解释：
</b>将 nums[0] 和 nums[1] 的值变为 6 ，nums 变为 [6,6,7,8,5] 。
最小得分是 i = 0 且 j = 1 时得到的 |<code>nums[i] - nums[j]</code>| = |6 - 6| = 0 。
最大得分是 i = 3 且 j = 4 时得到的 |<code>nums[i] - nums[j]</code>| = |8 - 5| = 3 。
最大得分与最小得分之和为 3 。这是最优答案。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 贪心**

根据题意我们知道，最小得分实际上是排序数组相邻两个元素的最小差值，最大得分是排序数组首尾元素的差值。数组 `nums` 的分数是最小得分与最大得分的和。

因此，我们可以先对数组进行排序。由于题目允许我们修改数组中最多两个元素的值，我们可以通过修改一个数，让其跟数组中的另一个数相同，使得最小得分为 $0$，那么数组 `nums` 的分数实际上就是最大得分。我们可以选择进行如下修改之一：

1. 修改最小的两个数为 $nums[2]$，那么最大得分为 $nums[n - 1] - nums[2]$；
1. 修改最小的一个数为 $nums[1]$，最大的一个数为 $nums[n - 2]$，那么最大得分为 $nums[n - 2] - nums[1]$；
1. 修改最大的两个数为 $nums[n - 3]$，那么最大得分为 $nums[n - 3] - nums[0]$。

最后，我们返回上述三种修改的得分的最小值即可。

时间复杂度 $O(n \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 `nums` 的长度。

相似题目：

-   [1509. 三次操作后最大值与最小值的最小差](/solution/1500-1599/1509.Minimum%20Difference%20Between%20Largest%20and%20Smallest%20Value%20in%20Three%20Moves/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minimizeSum(self, nums: List[int]) -> int:
        nums.sort()
        return min(nums[-1] - nums[2], nums[-2] - nums[1], nums[-3] - nums[0])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minimizeSum(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int a = nums[n - 1] - nums[2];
        int b = nums[n - 2] - nums[1];
        int c = nums[n - 3] - nums[0];
        return Math.min(a, Math.min(b, c));
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minimizeSum(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        return min({nums[n - 1] - nums[2], nums[n - 2] - nums[1], nums[n - 3] - nums[0]});
    }
};
```

### **Go**

```go
func minimizeSum(nums []int) int {
	sort.Ints(nums)
	n := len(nums)
	return min(nums[n-1]-nums[2], min(nums[n-2]-nums[1], nums[n-3]-nums[0]))
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts
function minimizeSum(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    return Math.min(
        nums[n - 3] - nums[0],
        nums[n - 2] - nums[1],
        nums[n - 1] - nums[2],
    );
}
```

### **Rust**

```rust
impl Solution {
    pub fn minimize_sum(mut nums: Vec<i32>) -> i32 {
        nums.sort();
        let n = nums.len();
        (nums[n - 1] - nums[2])
            .min(nums[n - 2] - nums[1])
            .min(nums[n - 3] - nums[0])
    }
}
```

### **C**

```c
#define min(a, b) (((a) < (b)) ? (a) : (b))

int cmp(const void *a, const void *b) {
    return *(int *) a - *(int *) b;
}

int minimizeSum(int *nums, int numsSize) {
    qsort(nums, numsSize, sizeof(int), cmp);
    return min(nums[numsSize - 1] - nums[2], min(nums[numsSize - 2] - nums[1], nums[numsSize - 3] - nums[0]));
}
```

### **...**

```

```

<!-- tabs:end -->
