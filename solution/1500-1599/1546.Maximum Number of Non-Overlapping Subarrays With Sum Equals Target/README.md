# [1546. 和为目标值且不重叠的非空子数组的最大数目](https://leetcode.cn/problems/maximum-number-of-non-overlapping-subarrays-with-sum-equals-target)

[English Version](/solution/1500-1599/1546.Maximum%20Number%20of%20Non-Overlapping%20Subarrays%20With%20Sum%20Equals%20Target/README_EN.md)

<!-- tags:贪心,数组,哈希表,前缀和 -->

<!-- difficulty:中等 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>target</code>&nbsp;。</p>

<p>请你返回&nbsp;<strong>非空不重叠</strong>&nbsp;子数组的最大数目，且每个子数组中数字和都为 <code>target</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,1,1,1,1], target = 2
<strong>输出：</strong>2
<strong>解释：</strong>总共有 2 个不重叠子数组（加粗数字表示） [<strong>1,1</strong>,1,<strong>1,1</strong>] ，它们的和为目标值 2 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [-1,3,5,1,4,2,-9], target = 6
<strong>输出：</strong>2
<strong>解释：</strong>总共有 3 个子数组和为 6 。
([5,1], [4,2], [3,5,1,4,2,-9]) 但只有前 2 个是不重叠的。</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [-2,6,6,3,5,4,1,2,8], target = 10
<strong>输出：</strong>3
</pre>

<p><strong>示例 4：</strong></p>

<pre><strong>输入：</strong>nums = [0,0,0], target = 0
<strong>输出：</strong>3
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;=&nbsp;10^5</code></li>
	<li><code>-10^4 &lt;= nums[i] &lt;=&nbsp;10^4</code></li>
	<li><code>0 &lt;= target &lt;= 10^6</code></li>
</ul>

## 解法

### 方法一：贪心 + 前缀和 + 哈希表

我们遍历数组 $nums$，利用前缀和 + 哈希表的方法，寻找和为 $target$ 的子数组，若找到，则答案加一，然后我们将前缀和置为 $0$，继续遍历数组 $nums$，直到遍历完整个数组。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def maxNonOverlapping(self, nums: List[int], target: int) -> int:
        ans = 0
        i, n = 0, len(nums)
        while i < n:
            s = 0
            vis = {0}
            while i < n:
                s += nums[i]
                if s - target in vis:
                    ans += 1
                    break
                i += 1
                vis.add(s)
            i += 1
        return ans
```

```java
class Solution {
    public int maxNonOverlapping(int[] nums, int target) {
        int ans = 0, n = nums.length;
        for (int i = 0; i < n; ++i) {
            Set<Integer> vis = new HashSet<>();
            int s = 0;
            vis.add(0);
            while (i < n) {
                s += nums[i];
                if (vis.contains(s - target)) {
                    ++ans;
                    break;
                }
                ++i;
                vis.add(s);
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxNonOverlapping(vector<int>& nums, int target) {
        int ans = 0, n = nums.size();
        for (int i = 0; i < n; ++i) {
            unordered_set<int> vis{{0}};
            int s = 0;
            while (i < n) {
                s += nums[i];
                if (vis.count(s - target)) {
                    ++ans;
                    break;
                }
                ++i;
                vis.insert(s);
            }
        }
        return ans;
    }
};
```

```go
func maxNonOverlapping(nums []int, target int) (ans int) {
	n := len(nums)
	for i := 0; i < n; i++ {
		s := 0
		vis := map[int]bool{0: true}
		for ; i < n; i++ {
			s += nums[i]
			if vis[s-target] {
				ans++
				break
			}
			vis[s] = true
		}
	}
	return
}
```

```ts
function maxNonOverlapping(nums: number[], target: number): number {
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        let s = 0;
        const vis: Set<number> = new Set();
        vis.add(0);
        for (; i < n; ++i) {
            s += nums[i];
            if (vis.has(s - target)) {
                ++ans;
                break;
            }
            vis.add(s);
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
