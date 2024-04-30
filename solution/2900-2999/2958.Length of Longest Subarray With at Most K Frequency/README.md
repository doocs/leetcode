# [2958. 最多 K 个重复元素的最长子数组](https://leetcode.cn/problems/length-of-longest-subarray-with-at-most-k-frequency)

[English Version](/solution/2900-2999/2958.Length%20of%20Longest%20Subarray%20With%20at%20Most%20K%20Frequency/README_EN.md)

<!-- tags:数组,哈希表,滑动窗口 -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。</p>

<p>一个元素 <code>x</code>&nbsp;在数组中的 <strong>频率</strong>&nbsp;指的是它在数组中的出现次数。</p>

<p>如果一个数组中所有元素的频率都 <strong>小于等于&nbsp;</strong><code>k</code>&nbsp;，那么我们称这个数组是 <strong>好</strong>&nbsp;数组。</p>

<p>请你返回 <code>nums</code>&nbsp;中 <strong>最长好</strong>&nbsp;子数组的长度。</p>

<p><strong>子数组</strong> 指的是一个数组中一段连续非空的元素序列。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [1,2,3,1,2,3,1,2], k = 2
<b>输出：</b>6
<strong>解释：</strong>最长好子数组是 [1,2,3,1,2,3] ，值 1 ，2 和 3 在子数组中的频率都没有超过 k = 2 。[2,3,1,2,3,1] 和 [3,1,2,3,1,2] 也是好子数组。
最长好子数组的长度为 6 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,1,2,1,2,1,2], k = 1
<b>输出：</b>2
<b>解释：</b>最长好子数组是 [1,2] ，值 1 和 2 在子数组中的频率都没有超过 k = 1 。[2,1] 也是好子数组。
最长好子数组的长度为 2 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<b>输入：</b>nums = [5,5,5,5,5,5,5], k = 4
<b>输出：</b>4
<b>解释：</b>最长好子数组是 [5,5,5,5] ，值 5 在子数组中的频率没有超过 k = 4 。
最长好子数组的长度为 4 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

## 解法

### 方法一：双指针

我们可以用两个指针 $j$ 和 $i$ 分别表示子数组的左右端点，初始时两个指针都指向数组的第一个元素。

接下来，我们遍历数组 $nums$ 中的每个元素 $x$，对于每个元素 $x$，我们将 $x$ 的出现次数加一，然后判断当前子数组是否满足要求。如果当前子数组不满足要求，我们就将指针 $j$ 右移一位，并将 $nums[j]$ 的出现次数减一，直到当前子数组满足要求为止。然后我们更新答案 $ans = \max(ans, i - j + 1)$。继续遍历，直到 $i$ 到达数组的末尾。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def maxSubarrayLength(self, nums: List[int], k: int) -> int:
        cnt = defaultdict(int)
        ans = j = 0
        for i, x in enumerate(nums):
            cnt[x] += 1
            while cnt[x] > k:
                cnt[nums[j]] -= 1
                j += 1
            ans = max(ans, i - j + 1)
        return ans
```

```java
class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        int ans = 0;
        for (int i = 0, j = 0; i < nums.length; ++i) {
            cnt.merge(nums[i], 1, Integer::sum);
            while (cnt.get(nums[i]) > k) {
                cnt.merge(nums[j++], -1, Integer::sum);
            }
            ans = Math.max(ans, i - j + 1);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxSubarrayLength(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        int ans = 0;
        for (int i = 0, j = 0; i < nums.size(); ++i) {
            ++cnt[nums[i]];
            while (cnt[nums[i]] > k) {
                --cnt[nums[j++]];
            }
            ans = max(ans, i - j + 1);
        }
        return ans;
    }
};
```

```go
func maxSubarrayLength(nums []int, k int) (ans int) {
	cnt := map[int]int{}
	for i, j, n := 0, 0, len(nums); i < n; i++ {
		cnt[nums[i]]++
		for ; cnt[nums[i]] > k; j++ {
			cnt[nums[j]]--
		}
		ans = max(ans, i-j+1)
	}
	return
}
```

```ts
function maxSubarrayLength(nums: number[], k: number): number {
    const cnt: Map<number, number> = new Map();
    let ans = 0;
    for (let i = 0, j = 0; i < nums.length; ++i) {
        cnt.set(nums[i], (cnt.get(nums[i]) ?? 0) + 1);
        for (; cnt.get(nums[i])! > k; ++j) {
            cnt.set(nums[j], cnt.get(nums[j])! - 1);
        }
        ans = Math.max(ans, i - j + 1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
