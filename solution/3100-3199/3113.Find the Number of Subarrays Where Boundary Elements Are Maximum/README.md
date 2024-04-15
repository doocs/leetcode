# [3113. 边界元素是最大值的子数组数目](https://leetcode.cn/problems/find-the-number-of-subarrays-where-boundary-elements-are-maximum)

[English Version](/solution/3100-3199/3113.Find%20the%20Number%20of%20Subarrays%20Where%20Boundary%20Elements%20Are%20Maximum/README_EN.md)

<!-- tags: -->

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个 <strong>正</strong>&nbsp;整数数组&nbsp;<code>nums</code>&nbsp;。</p>

<p>请你求出&nbsp;<code>nums</code>&nbsp;中有多少个子数组，满足子数组中&nbsp;<strong>第一个</strong>&nbsp;和 <strong>最后一个</strong>&nbsp;元素都是这个子数组中的 <strong>最大</strong>&nbsp;值。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1,4,3,3,2]</span></p>

<p><span class="example-io"><b>输出：</b>6</span></p>

<p><strong>解释：</strong></p>

<p>总共有 6 个子数组满足第一个元素和最后一个元素都是子数组中的最大值：</p>

<ul>
	<li>子数组&nbsp;<code>[<u><em><strong>1</strong></em></u>,4,3,3,2]</code>&nbsp;，最大元素为 1 ，第一个和最后一个元素都是 1 。</li>
	<li>子数组&nbsp;<code>[1,<u><em><strong>4</strong></em></u>,3,3,2]</code>&nbsp;，最大元素为 4 ，第一个和最后一个元素都是 4 。</li>
	<li>子数组&nbsp;<code>[1,4,<u><em><strong>3</strong></em></u>,3,2]</code>&nbsp;，最大元素为 3 ，第一个和最后一个元素都是 3 。</li>
	<li>子数组&nbsp;<code>[1,4,3,<u><em><strong>3</strong></em></u>,2]</code>&nbsp;，最大元素为 3 ，第一个和最后一个元素都是 3 。</li>
	<li>子数组&nbsp;<code>[1,4,3,3,<u><em><strong>2</strong></em></u>]</code>&nbsp;，最大元素为 2 ，第一个和最后一个元素都是 2 。</li>
	<li>子数组&nbsp;<code>[1,4,<u><em><strong>3,3</strong></em></u>,2]</code>&nbsp;，最大元素为 3 ，第一个和最后一个元素都是 3 。</li>
</ul>

<p>所以我们返回 6 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [3,3,3]</span></p>

<p><span class="example-io"><b>输出：</b>6</span></p>

<p><strong>解释：</strong></p>

<p>总共有 6 个子数组满足第一个元素和最后一个元素都是子数组中的最大值：</p>

<ul>
	<li>子数组 <code>[<u><em><strong>3</strong></em></u>,3,3]</code>&nbsp;，最大元素为 3&nbsp;，第一个和最后一个元素都是 3&nbsp;。</li>
	<li>子数组 <code>[3,<u><em><strong>3</strong></em></u>,3]</code>&nbsp;，最大元素为 3&nbsp;，第一个和最后一个元素都是 3&nbsp;。</li>
	<li>子数组 <code>[3,3,<u><em><strong>3</strong></em></u>]</code>&nbsp;，最大元素为 3&nbsp;，第一个和最后一个元素都是 3&nbsp;。</li>
	<li>子数组 <code>[<u><em><strong>3,3</strong></em></u>,3]</code>&nbsp;，最大元素为 3&nbsp;，第一个和最后一个元素都是 3&nbsp;。</li>
	<li>子数组 <code>[3,<u><em><strong>3,3</strong></em></u>]</code>&nbsp;，最大元素为 3&nbsp;，第一个和最后一个元素都是 3&nbsp;。</li>
	<li>子数组 <code>[<u><em><strong>3,3,3</strong></em></u>]</code>&nbsp;，最大元素为 3&nbsp;，第一个和最后一个元素都是 3&nbsp;。</li>
</ul>

<p>所以我们返回 6 。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [1]</span></p>

<p><span class="example-io"><b>输出：</b>1</span></p>

<p><strong>解释：</strong></p>

<p><code>nums</code>&nbsp;中只有一个子数组&nbsp;<code>[<em><strong>1</strong></em>]</code>&nbsp;，最大元素为 1 ，第一个和最后一个元素都是 1 。</p>

<p>所以我们返回 1 。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一：单调栈

我们考虑以数组 $nums$ 中的每个元素 $x$ 作为子数组的边界元素且最大值的情况。

每个长度为 $1$ 的子数组都满足条件，而对于长度大于 $1$ 的子数组，子数组中的所有元素都不能大于边界元素 $x$，我们可以用单调栈来实现。

我们维护一个从栈底到栈顶单调递减的栈，单调栈的每个元素是一个二元组 $[x, cnt]$，表示元素 $x$ 且以 $x$ 为边界元素且最大值的子数组的个数为 $cnt$。

我们从左到右遍历数组 $nums$，对于每个元素 $x$，我们不断地将栈顶元素弹出，直到栈为空或者栈顶元素的第一个元素大于等于 $x$。如果栈为空，或者栈顶元素的第一个元素大于 $x$，说明当前遇到第一个边界元素为 $x$ 且最大值的子数组，该子数组的长度为 $1$，所以我们将 $[x, 1]$ 入栈。如果栈顶元素的第一个元素等于 $x$，说明当前遇到的边界元素为 $x$ 且最大值的子数组，我们将栈顶元素的第二个元素加 $1$。然后，我们将栈顶元素的第二个元素加到答案中。

遍历结束后，返回答案即可。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def numberOfSubarrays(self, nums: List[int]) -> int:
        stk = []
        ans = 0
        for x in nums:
            while stk and stk[-1][0] < x:
                stk.pop()
            if not stk or stk[-1][0] > x:
                stk.append([x, 1])
            else:
                stk[-1][1] += 1
            ans += stk[-1][1]
        return ans
```

```java
class Solution {
    public long numberOfSubarrays(int[] nums) {
        Deque<int[]> stk = new ArrayDeque<>();
        long ans = 0;
        for (int x : nums) {
            while (!stk.isEmpty() && stk.peek()[0] < x) {
                stk.pop();
            }
            if (stk.isEmpty() || stk.peek()[0] > x) {
                stk.push(new int[] {x, 1});
            } else {
                stk.peek()[1]++;
            }
            ans += stk.peek()[1];
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long numberOfSubarrays(vector<int>& nums) {
        vector<pair<int, int>> stk;
        long long ans = 0;
        for (int x : nums) {
            while (!stk.empty() && stk.back().first < x) {
                stk.pop_back();
            }
            if (stk.empty() || stk.back().first > x) {
                stk.push_back(make_pair(x, 1));
            } else {
                stk.back().second++;
            }
            ans += stk.back().second;
        }
        return ans;
    }
};
```

```go
func numberOfSubarrays(nums []int) (ans int64) {
	stk := [][2]int{}
	for _, x := range nums {
		for len(stk) > 0 && stk[len(stk)-1][0] < x {
			stk = stk[:len(stk)-1]
		}
		if len(stk) == 0 || stk[len(stk)-1][0] > x {
			stk = append(stk, [2]int{x, 1})
		} else {
			stk[len(stk)-1][1]++
		}
		ans += int64(stk[len(stk)-1][1])
	}
	return
}
```

```ts
function numberOfSubarrays(nums: number[]): number {
    const stk: number[][] = [];
    let ans = 0;
    for (const x of nums) {
        while (stk.length > 0 && stk.at(-1)![0] < x) {
            stk.pop();
        }
        if (stk.length === 0 || stk.at(-1)![0] > x) {
            stk.push([x, 1]);
        } else {
            stk.at(-1)![1]++;
        }
        ans += stk.at(-1)![1];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
