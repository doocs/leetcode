# [2587. 重排数组以得到最大前缀分数](https://leetcode.cn/problems/rearrange-array-to-maximize-prefix-score)

[English Version](/solution/2500-2599/2587.Rearrange%20Array%20to%20Maximize%20Prefix%20Score/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> 。你可以将 <code>nums</code> 中的元素按 <strong>任意顺序</strong> 重排（包括给定顺序）。</p>

<p>令 <code>prefix</code> 为一个数组，它包含了 <code>nums</code> 重新排列后的前缀和。换句话说，<code>prefix[i]</code> 是 <code>nums</code> 重新排列后下标从 <code>0</code> 到 <code>i</code> 的元素之和。<code>nums</code> 的 <strong>分数</strong> 是 <code>prefix</code> 数组中正整数的个数。</p>

<p>返回可以得到的最大分数。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [2,-1,0,1,-3,3,-3]
<strong>输出：</strong>6
<strong>解释：</strong>数组重排为 nums = [2,3,1,-1,-3,0,-3] 。
prefix = [2,5,6,5,2,2,-1] ，分数为 6 。
可以证明 6 是能够得到的最大分数。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [-2,-3,0]
<strong>输出：</strong>0
<strong>解释：</strong>不管怎么重排数组得到的分数都是 0 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>6</sup> &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心 + 排序**

要使得前缀和数组中正整数的个数最多，就要使得前缀和数组中的元素尽可能大，即尽可能多的正整数相加。因此，我们可以将数组 `nums` 降序排序，然后遍历数组，维护前缀和 $s$，如果 $s \leq 0$，则说明当前位置以及之后的位置都不可能再有正整数，因此直接返回当前位置即可。

否则，遍历结束后，返回数组长度。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maxScore(self, nums: List[int]) -> int:
        nums.sort(reverse=True)
        s = 0
        for i, x in enumerate(nums):
            s += x
            if s <= 0:
                return i
        return len(nums)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maxScore(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        long s = 0;
        for (int i = 0; i < n; ++i) {
            s += nums[n - i - 1];
            if (s <= 0) {
                return i;
            }
        }
        return n;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maxScore(vector<int>& nums) {
        sort(nums.rbegin(), nums.rend());
        long long s = 0;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            s += nums[i];
            if (s <= 0) {
                return i;
            }
        }
        return n;
    }
};
```

### **Go**

```go
func maxScore(nums []int) int {
	sort.Ints(nums)
	n := len(nums)
	s := 0
	for i := range nums {
		s += nums[n-i-1]
		if s <= 0 {
			return i
		}
	}
	return n
}
```

### **TypeScript**

```ts
function maxScore(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let s = 0;
    for (let i = 0; i < n; ++i) {
        s += nums[n - i - 1];
        if (s <= 0) {
            return i;
        }
    }
    return n;
}
```

### **...**

```

```

<!-- tabs:end -->
