# [2996. 大于等于顺序前缀和的最小缺失整数](https://leetcode.cn/problems/smallest-missing-integer-greater-than-sequential-prefix-sum)

[English Version](/solution/2900-2999/2996.Smallest%20Missing%20Integer%20Greater%20Than%20Sequential%20Prefix%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>如果一个前缀&nbsp;<code>nums[0..i]</code>&nbsp;满足对于&nbsp;<code>1 &lt;= j &lt;= i</code>&nbsp;的所有元素都有&nbsp;<code>nums[j] = nums[j - 1] + 1</code>&nbsp;，那么我们称这个前缀是一个 <strong>顺序前缀</strong> 。特殊情况是，只包含&nbsp;<code>nums[0]</code>&nbsp;的前缀也是一个 <strong>顺序前缀</strong> 。</p>

<p>请你返回 <code>nums</code>&nbsp;中没有出现过的 <strong>最小</strong>&nbsp;整数&nbsp;<code>x</code>&nbsp;，满足&nbsp;<code>x</code>&nbsp;大于等于&nbsp;<strong>最长</strong> 顺序前缀的和。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3,2,5]
<b>输出：</b>6
<b>解释：</b>nums 的最长顺序前缀是 [1,2,3] ，和为 6 ，6 不在数组中，所以 6 是大于等于最长顺序前缀和的最小整数。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,4,5,1,12,14,13]
<b>输出：</b>15
<b>解释：</b>nums 的最长顺序前缀是 [3,4,5] ，和为 12 ，12、13 和 14 都在数组中，但 15 不在，所以 15 是大于等于最长顺序前缀和的最小整数。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 50</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def missingInteger(self, nums: List[int]) -> int:
        s, n = nums[0], len(nums)
        j = 1
        while j < len(nums) and nums[j] == nums[j - 1] + 1:
            s += nums[j]
            j += 1
        vis = set(nums)
        for x in count(s):
            if x not in vis:
                return x
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int missingInteger(int[] nums) {
        int s = nums[0], j = 1;
        while (j < nums.length && nums[j] == nums[j - 1] + 1) {
            s += nums[j++];
        }
        boolean[] vis = new boolean[51];
        for (int x : nums) {
            vis[x] = true;
        }
        for (int x = s;; ++x) {
            if (x >= vis.length || !vis[x]) {
                return x;
            }
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int missingInteger(vector<int>& nums) {
        int s = nums[0], j = 1;
        while (j < nums.size() && nums[j] == nums[j - 1] + 1) {
            s += nums[j++];
        }
        bool vis[51]{};
        for (int x : nums) {
            vis[x] = true;
        }
        for (int x = s;; ++x) {
            if (x >= 51 || !vis[x]) {
                return x;
            }
        }
    }
};
```

### **Go**

```go
func missingInteger(nums []int) int {
	s, j := nums[0], 1
	for j < len(nums) && nums[j] == nums[j-1]+1 {
		s, j = s+nums[j], j+1
	}
	vis := [51]bool{}
	for _, x := range nums {
		vis[x] = true
	}
	for x := s; ; x++ {
		if x >= len(vis) || !vis[x] {
			return x
		}
	}
}
```

### **TypeScript**

```ts
function missingInteger(nums: number[]): number {
    let [s, j] = [nums[0], 1];
    const n = nums.length;
    while (j < n && nums[j] === nums[j - 1] + 1) {
        s += nums[j++];
    }
    const vis: boolean[] = Array(51).fill(false);
    for (const x of nums) {
        vis[x] = true;
    }
    for (let x = s; ; ++x) {
        if (x >= vis.length || !vis[x]) {
            return x;
        }
    }
}
```

### **...**

```

```

<!-- tabs:end -->
