---
comments: true
difficulty: 困难
rating: 2854
source: 第 432 场周赛 Q4
tags:
    - 栈
    - 线段树
    - 队列
    - 数组
    - 滑动窗口
    - 单调队列
    - 单调栈
---

<!-- problem:start -->

# [3420. 统计 K 次操作以内得到非递减子数组的数目](https://leetcode.cn/problems/count-non-decreasing-subarrays-after-k-operations)

[English Version](/solution/3400-3499/3420.Count%20Non-Decreasing%20Subarrays%20After%20K%20Operations/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个长度为 <code>n</code>&nbsp;的数组&nbsp;<code>nums</code>&nbsp;和一个整数&nbsp;<code>k</code>&nbsp;。</p>

<p>对于&nbsp;<code>nums</code>&nbsp;中的每一个子数组，你可以对它进行 <strong>至多</strong>&nbsp;<code>k</code>&nbsp;次操作。每次操作中，你可以将子数组中的任意一个元素增加 1 。</p>

<p><b>注意</b>&nbsp;，每个子数组都是独立的，也就是说你对一个子数组的修改不会保留到另一个子数组中。</p>

<p>请你返回最多 <code>k</code>&nbsp;次操作以内，有多少个子数组可以变成 <strong>非递减</strong>&nbsp;的。</p>

<p>如果一个数组中的每一个元素都大于等于前一个元素（如果前一个元素存在），那么我们称这个数组是 <strong>非递减</strong>&nbsp;的。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [6,3,1,2,4,4], k = 7</span></p>

<p><span class="example-io"><b>输出：</b>17</span></p>

<p><b>解释：</b></p>

<p><code>nums</code>&nbsp;的所有&nbsp;21 个子数组中，只有子数组&nbsp;<code>[6, 3, 1]</code>&nbsp;，<code>[6, 3, 1, 2]</code>&nbsp;，<code>[6, 3, 1, 2, 4]</code>&nbsp;和&nbsp;<code>[6, 3, 1, 2, 4, 4]</code>&nbsp;无法在 k = 7 次操作以内变为非递减的。所以非递减子数组的数目为&nbsp;<code>21 - 4 = 17</code>&nbsp;。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><span class="example-io"><b>输入：</b>nums = [6,3,1,3,6], k = 4</span></p>

<p><strong>输出：</strong><span class="example-io">12</span></p>

<p><strong>解释：</strong></p>

<p>子数组&nbsp;<code>[3, 1, 3, 6]</code>&nbsp;和&nbsp;<code>nums</code>&nbsp;中所有小于等于三个元素的子数组中，除了&nbsp;<code>[6, 3, 1]</code>&nbsp;以外，都可以在&nbsp;<code>k</code>&nbsp;次操作以内变为非递减子数组。总共有 5 个包含单个元素的子数组，4 个包含两个元素的子数组，除 <code>[6, 3, 1]</code>&nbsp;以外有 2 个包含三个元素的子数组，所以总共有&nbsp;<code>1 + 5 + 4 + 2 = 12</code>&nbsp;个子数组可以变为非递减的。</p>

<p>&nbsp;</p>
</div>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一

<!-- thinking:start -->

> **思考**
>
> 一次操作把某个元素加一。要把子数组变成非递减，等价于把每个位置抬到它左侧的前缀最大值，代价为各处抬升量之和。 $n\le 10^5$，不能对每个子数组重算。
>
> 子数组越长，所需代价单调不减。因此对每个右端，存在最远的左端使得代价 $\le k$。
>
> 用单调栈维护「作为前缀最大」的一段段贡献，滑动左端弹出过期段并退回代价。对每个右端累加合法左端个数，即答案。

<!-- thinking:end -->

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countNonDecreasingSubarrays(self, nums: list[int], k: int) -> int:
        n = len(nums)
        q = deque()
        ans = 0
        cost = 0
        r = n - 1
        for l in range(n - 1, -1, -1):
            x = nums[l]
            while q and nums[q[-1]] <= x:
                s = q.pop()
                end = q[-1] if q else r + 1
                cost += (end - s) * (x - nums[s])
            q.append(l)
            while cost > k:
                cost -= nums[q[0]] - nums[r]
                if q[0] == r:
                    q.popleft()
                r -= 1
            ans += r - l + 1
        return ans
```

#### Java

```java
class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> q = new ArrayDeque<>();
        long ans = 0;
        long cost = 0;
        int r = n - 1;
        for (int l = n - 1; l >= 0; --l) {
            int x = nums[l];
            while (!q.isEmpty() && nums[q.peekLast()] <= x) {
                int s = q.pollLast();
                int end = q.isEmpty() ? r + 1 : q.peekLast();
                cost += (long) (end - s) * (x - nums[s]);
            }
            q.offerLast(l);
            while (cost > k) {
                cost -= nums[q.peekFirst()] - nums[r];
                if (q.peekFirst() == r) {
                    q.pollFirst();
                }
                --r;
            }
            ans += r - l + 1;
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long countNonDecreasingSubarrays(vector<int>& nums, int k) {
        int n = nums.size();
        deque<int> q;
        long long ans = 0, cost = 0;
        int r = n - 1;
        for (int l = n - 1; l >= 0; --l) {
            int x = nums[l];
            while (!q.empty() && nums[q.back()] <= x) {
                int s = q.back();
                q.pop_back();
                int end = q.empty() ? r + 1 : q.back();
                cost += 1LL * (end - s) * (x - nums[s]);
            }
            q.push_back(l);
            while (cost > k) {
                cost -= nums[q.front()] - nums[r];
                if (q.front() == r) {
                    q.pop_front();
                }
                --r;
            }
            ans += r - l + 1;
        }
        return ans;
    }
};
```

#### Go

```go
func countNonDecreasingSubarrays(nums []int, k int) int64 {
	n := len(nums)
	q := []int{}
	var ans, cost int64
	r := n - 1
	for l := n - 1; l >= 0; l-- {
		x := nums[l]
		for len(q) > 0 && nums[q[len(q)-1]] <= x {
			s := q[len(q)-1]
			q = q[:len(q)-1]
			end := r + 1
			if len(q) > 0 {
				end = q[len(q)-1]
			}
			cost += int64(end-s) * int64(x-nums[s])
		}
		q = append(q, l)
		for cost > int64(k) {
			cost -= int64(nums[q[0]] - nums[r])
			if q[0] == r {
				q = q[1:]
			}
			r--
		}
		ans += int64(r - l + 1)
	}
	return ans
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
