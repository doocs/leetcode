# [2576. 求出最多标记下标](https://leetcode.cn/problems/find-the-maximum-number-of-marked-indices)

[English Version](/solution/2500-2599/2576.Find%20the%20Maximum%20Number%20of%20Marked%20Indices/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>一开始，所有下标都没有被标记。你可以执行以下操作任意次：</p>

<ul>
	<li>选择两个 <strong>互不相同且未标记</strong>&nbsp;的下标&nbsp;<code>i</code> 和&nbsp;<code>j</code>&nbsp;，满足&nbsp;<code>2 * nums[i] &lt;= nums[j]</code>&nbsp;，标记下标&nbsp;<code>i</code> 和&nbsp;<code>j</code>&nbsp;。</li>
</ul>

<p>请你执行上述操作任意次，返回<em>&nbsp;</em><code>nums</code>&nbsp;中最多可以标记的下标数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [3,5,2,4]
<b>输出：</b>2
<strong>解释：</strong>第一次操作中，选择 i = 2 和 j = 1 ，操作可以执行的原因是 2 * nums[2] &lt;= nums[1] ，标记下标 2 和 1 。
没有其他更多可执行的操作，所以答案为 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [9,2,5,4]
<b>输出：</b>4
<strong>解释：</strong>第一次操作中，选择 i = 3 和 j = 0 ，操作可以执行的原因是 2 * nums[3] &lt;= nums[0] ，标记下标 3 和 0 。
第二次操作中，选择 i = 1 和 j = 2 ，操作可以执行的原因是 2 * nums[1] &lt;= nums[2] ，标记下标 1 和 2 。
没有其他更多可执行的操作，所以答案为 4 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [7,6,8]
<b>输出：</b>0
<strong>解释：</strong>没有任何可以执行的操作，所以答案为 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 双指针**

为了将下标尽可能多地标记，我们可以将数组 `nums` 排序，然后从左到右遍历数组，对于每个下标 $i$，我们在数组的右半部分找到第一个满足 $2 \times nums[i] \leq nums[j]$ 的下标 $j$，然后标记下标 $i$ 和 $j$。继续遍历下一个下标 $i$。当我们遍历完数组的右半部分时，说明标记已经完成，此时标记的下标数目即为答案。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxNumOfMarkedIndices(self, nums: List[int]) -> int:
        nums.sort()
        n = len(nums)
        i, j = 0, (n + 1) // 2
        ans = 0
        while j < n:
            while j < n and nums[i] * 2 > nums[j]:
                j += 1
            if j < n:
                ans += 2
            i, j = i + 1, j + 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxNumOfMarkedIndices(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int ans = 0;
        for (int i = 0, j = (n + 1) / 2; j < n; ++i, ++j) {
            while (j < n && nums[i] * 2 > nums[j]) {
                ++j;
            }
            if (j < n) {
                ans += 2;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxNumOfMarkedIndices(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        int ans = 0;
        for (int i = 0, j = (n + 1) / 2; j < n; ++i, ++j) {
            while (j < n && nums[i] * 2 > nums[j]) {
                ++j;
            }
            if (j < n) {
                ans += 2;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func maxNumOfMarkedIndices(nums []int) (ans int) {
	sort.Ints(nums)
	n := len(nums)
	for i, j := 0, (n+1)/2; j < n; i, j = i+1, j+1 {
		for j < n && nums[i]*2 > nums[j] {
			j++
		}
		if j < n {
			ans += 2
		}
	}
	return
}
```

### **TypeScript**

```ts
function maxNumOfMarkedIndices(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let ans = 0;
    for (let i = 0, j = Math.floor((n + 1) / 2); j < n; ++i, ++j) {
        while (j < n && nums[i] * 2 > nums[j]) {
            ++j;
        }
        if (j < n) {
            ans += 2;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
