# [2592. 最大化数组的伟大值](https://leetcode.cn/problems/maximize-greatness-of-an-array)

[English Version](/solution/2500-2599/2592.Maximize%20Greatness%20of%20an%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 0 开始的整数数组&nbsp;<code>nums</code>&nbsp;。你需要将&nbsp;<code>nums</code>&nbsp;重新排列成一个新的数组&nbsp;<code>perm</code>&nbsp;。</p>

<p>定义 <code>nums</code>&nbsp;的 <strong>伟大值</strong>&nbsp;为满足&nbsp;<code>0 &lt;= i &lt; nums.length</code>&nbsp;且&nbsp;<code>perm[i] &gt; nums[i]</code>&nbsp;的下标数目。</p>

<p>请你返回重新排列 <code>nums</code>&nbsp;后的 <strong>最大</strong>&nbsp;伟大值。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,3,5,2,1,3,1]
<b>输出：</b>4
<b>解释：</b>一个最优安排方案为 perm = [2,5,1,3,3,1,1] 。
在下标为 0, 1, 3 和 4 处，都有 perm[i] &gt; nums[i] 。因此我们返回 4 。</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [1,2,3,4]
<b>输出：</b>3
<b>解释：</b>最优排列为 [2,3,4,1] 。
在下标为 0, 1 和 2 处，都有 perm[i] &gt; nums[i] 。因此我们返回 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

我们可以先将数组 $nums$ 进行排序。

接下来定义一个指针 $i$，初始时指向数组 $nums$ 的第一个元素。然后遍历数组 $nums$，对于遍历到的每个元素 $x$，如果 $x$ 大于 $nums[i]$，则将指针 $i$ 向右移动一位。

最后返回指针 $i$ 的值即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximizeGreatness(self, nums: List[int]) -> int:
        nums.sort()
        i = 0
        for x in nums:
            i += x > nums[i]
        return i
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maximizeGreatness(int[] nums) {
        Arrays.sort(nums);
        int i = 0;
        for (int x : nums) {
            if (x > nums[i]) {
                ++i;
            }
        }
        return i;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximizeGreatness(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int i = 0;
        for (int x : nums) {
            i += x > nums[i];
        }
        return i;
    }
};
```

### **Go**

```go
func maximizeGreatness(nums []int) int {
	sort.Ints(nums)
	i := 0
	for _, x := range nums {
		if x > nums[i] {
			i++
		}
	}
	return i
}
```

### **TypeScript**

```ts
function maximizeGreatness(nums: number[]): number {
    nums.sort((a, b) => a - b);
    let i = 0;
    for (const x of nums) {
        if (x > nums[i]) {
            i += 1;
        }
    }
    return i;
}
```

### **...**

```

```

<!-- tabs:end -->
