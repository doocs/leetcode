# [1509. 三次操作后最大值与最小值的最小差](https://leetcode.cn/problems/minimum-difference-between-largest-and-smallest-value-in-three-moves)

[English Version](/solution/1500-1599/1509.Minimum%20Difference%20Between%20Largest%20and%20Smallest%20Value%20in%20Three%20Moves/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个数组&nbsp;<code>nums</code>&nbsp;，每次操作你可以选择&nbsp;<code>nums</code>&nbsp;中的任意一个元素并将它改成任意值。</p>

<p>请你返回三次操作后， <code>nums</code>&nbsp;中最大值与最小值的差的最小值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [5,3,2,4]
<strong>输出：</strong>0
<strong>解释：</strong>将数组 [5,3,2,4] 变成 [<strong>2</strong>,<strong>2</strong>,2,<strong>2</strong>].
最大值与最小值的差为 2-2 = 0 。</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [1,5,0,10,14]
<strong>输出：</strong>1
<strong>解释：</strong>将数组 [1,5,0,10,14] 变成 [1,<strong>1</strong>,0,<strong>1</strong>,<strong>1</strong>] 。
最大值与最小值的差为 1-0 = 1 。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [6,6,0,1,1,4,6]
<strong>输出：</strong>2
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>nums = [1,5,6,14,15]
<strong>输出：</strong>1
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10^5</code></li>
	<li><code>-10^9 &lt;= nums[i] &lt;= 10^9</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 贪心**

我们可以先判断数组长度是否小于 $5$，如果小于 $5$，那么直接返回 $0$。

否则，我们将数组排序，然后贪心地选择数组左边最小的 $l=[0,..3]$ 个数和右边最小的 $r = 3 - l$ 个数，取其中最小的差值 $nums[n - 1 - r] - nums[l]$ 即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 `nums` 的长度。

相似题目：

-   [2567. 修改两个元素的最小分数](/solution/2500-2599/2567.Minimum%20Score%20by%20Changing%20Two%20Elements/README.md)

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minDifference(self, nums: List[int]) -> int:
        n = len(nums)
        if n < 5:
            return 0
        nums.sort()
        ans = inf
        for l in range(4):
            r = 3 - l
            ans = min(ans, nums[n - 1 - r] - nums[l])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minDifference(int[] nums) {
        int n = nums.length;
        if (n < 5) {
            return 0;
        }
        Arrays.sort(nums);
        long ans = 1L << 60;
        for (int l = 0; l <= 3; ++l) {
            int r = 3 - l;
            ans = Math.min(ans, (long) nums[n - 1 - r] - nums[l]);
        }
        return (int) ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minDifference(vector<int>& nums) {
        int n = nums.size();
        if (n < 5) {
            return 0;
        }
        sort(nums.begin(), nums.end());
        long long ans = 1L << 60;
        for (int l = 0; l <= 3; ++l) {
            int r = 3 - l;
            ans = min(ans, 1LL * nums[n - 1 - r] - nums[l]);
        }
        return ans;
    }
};
```

### **Go**

```go
func minDifference(nums []int) int {
	n := len(nums)
	if n < 5 {
		return 0
	}
	sort.Ints(nums)
	ans := 1 << 60
	for l := 0; l <= 3; l++ {
		r := 3 - l
		ans = min(ans, nums[n-1-r]-nums[l])
	}
	return ans
}

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
