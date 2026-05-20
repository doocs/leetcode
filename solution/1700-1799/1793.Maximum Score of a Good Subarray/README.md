---
comments: true
difficulty: 困难
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1700-1799/1793.Maximum%20Score%20of%20a%20Good%20Subarray/README.md
rating: 1945
source: 第 232 场周赛 Q4
tags:
    - 栈
    - 数组
    - 双指针
    - 二分查找
    - 单调栈
---

<!-- problem:start -->

# [1793. 好子数组的最大分数](https://leetcode.cn/problems/maximum-score-of-a-good-subarray)

[English Version](/solution/1700-1799/1793.Maximum%20Score%20of%20a%20Good%20Subarray/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> <strong>（下标从 0 开始）</strong>和一个整数 <code>k</code> 。</p>

<p>一个子数组 <code>(i, j)</code> 的 <strong>分数</strong> 定义为 <code>min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1)</code> 。一个 <strong>好</strong> 子数组的两个端点下标需要满足 <code>i &lt;= k &lt;= j</code> 。</p>

<p>请你返回 <strong>好</strong> 子数组的最大可能 <strong>分数</strong> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,4,3,7,4,5], k = 3
<b>输出：</b>15
<b>解释：</b>最优子数组的左右端点下标是 (1, 5) ，分数为 min(4,3,7,4,5) * (5-1+1) = 3 * 5 = 15 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [5,5,4,5,4,1,1,1], k = 0
<b>输出：</b>20
<b>解释：</b>最优子数组的左右端点下标是 (0, 4) ，分数为 min(5,5,4,5,4) * (4-0+1) = 4 * 5 = 20 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= k &lt; nums.length</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：单调栈

我们可以枚举 $nums$ 中的每个元素 $nums[i]$ 作为子数组的最小值，利用单调栈找出其左边第一个小于 $nums[i]$ 的位置 $left[i]$ 和右边第一个小于等于 $nums[i]$ 的位置 $right[i]$，则以 $nums[i]$ 为最小值的子数组的分数为 $nums[i] \times (right[i] - left[i] - 1)$。

需要注意的是，只有当左右边界 $left[i]$ 和 $right[i]$ 满足 $left[i]+1 \leq k \leq right[i]-1$ 时，答案才有可能更新。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumScore(self, nums: List[int], k: int) -> int:
        n = len(nums)
        left = [-1] * n
        right = [n] * n
        stk = []
        for i, v in enumerate(nums):
            while stk and nums[stk[-1]] >= v:
                stk.pop()
            if stk:
                left[i] = stk[-1]
            stk.append(i)
        stk = []
        for i in range(n - 1, -1, -1):
            v = nums[i]
            while stk and nums[stk[-1]] > v:
                stk.pop()
            if stk:
                right[i] = stk[-1]
            stk.append(i)
        ans = 0
        for i, v in enumerate(nums):
            if left[i] + 1 <= k <= right[i] - 1:
                ans = max(ans, v * (right[i] - left[i] - 1))
        return ans
```

#### Java

```java
class Solution {
    public int maximumScore(int[] nums, int k) {
        int n = nums.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, n);
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n; ++i) {
            int v = nums[i];
            while (!stk.isEmpty() && nums[stk.peek()] >= v) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                left[i] = stk.peek();
            }
            stk.push(i);
        }
        stk.clear();
        for (int i = n - 1; i >= 0; --i) {
            int v = nums[i];
            while (!stk.isEmpty() && nums[stk.peek()] > v) {
                stk.pop();
            }
            if (!stk.isEmpty()) {
                right[i] = stk.peek();
            }
            stk.push(i);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (left[i] + 1 <= k && k <= right[i] - 1) {
                ans = Math.max(ans, nums[i] * (right[i] - left[i] - 1));
            }
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maximumScore(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> left(n, -1);
        vector<int> right(n, n);
        stack<int> stk;
        for (int i = 0; i < n; ++i) {
            int v = nums[i];
            while (!stk.empty() && nums[stk.top()] >= v) {
                stk.pop();
            }
            if (!stk.empty()) {
                left[i] = stk.top();
            }
            stk.push(i);
        }
        stk = stack<int>();
        for (int i = n - 1; i >= 0; --i) {
            int v = nums[i];
            while (!stk.empty() && nums[stk.top()] > v) {
                stk.pop();
            }
            if (!stk.empty()) {
                right[i] = stk.top();
            }
            stk.push(i);
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            if (left[i] + 1 <= k && k <= right[i] - 1) {
                ans = max(ans, nums[i] * (right[i] - left[i] - 1));
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maximumScore(nums []int, k int) (ans int) {
	n := len(nums)
	left := make([]int, n)
	right := make([]int, n)
	for i := range left {
		left[i] = -1
		right[i] = n
	}
	stk := []int{}
	for i, v := range nums {
		for len(stk) > 0 && nums[stk[len(stk)-1]] >= v {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			left[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	stk = []int{}
	for i := n - 1; i >= 0; i-- {
		v := nums[i]
		for len(stk) > 0 && nums[stk[len(stk)-1]] > v {
			stk = stk[:len(stk)-1]
		}
		if len(stk) > 0 {
			right[i] = stk[len(stk)-1]
		}
		stk = append(stk, i)
	}
	for i, v := range nums {
		if left[i]+1 <= k && k <= right[i]-1 {
			ans = max(ans, v*(right[i]-left[i]-1))
		}
	}
	return
}
```

#### TypeScript

```ts
function maximumScore(nums: number[], k: number): number {
    const n = nums.length;
    const left: number[] = Array(n).fill(-1);
    const right: number[] = Array(n).fill(n);
    const stk: number[] = [];
    for (let i = 0; i < n; ++i) {
        while (stk.length && nums[stk.at(-1)] >= nums[i]) {
            stk.pop();
        }
        if (stk.length) {
            left[i] = stk.at(-1);
        }
        stk.push(i);
    }
    stk.length = 0;
    for (let i = n - 1; ~i; --i) {
        while (stk.length && nums[stk.at(-1)] > nums[i]) {
            stk.pop();
        }
        if (stk.length) {
            right[i] = stk.at(-1);
        }
        stk.push(i);
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        if (left[i] + 1 <= k && k <= right[i] - 1) {
            ans = Math.max(ans, nums[i] * (right[i] - left[i] - 1));
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：双指针
我们可从核心索引 `k` 出发，利用双指针向左右两侧交替扩展，
动态维护当前窗口内的最小值，从而求解最大得分。

**算法步骤：**

1. 初始化左指针 `i = k`，右指针 `j = k`，当前窗口内最小值 `min_num = nums[k]`，同时将初始得分 `max_score` 设为 `nums[k]`。

2. 当 `i > 0` 或 `j < len(nums) - 1` 时，执行双指针扩展：
   - **方向**：若左边界无法扩展（`i == 0`），只能向右移动 `j`；若右边界无法扩展（`j == len(nums) - 1`），只能向左移动 `i`。
   - 若两侧均可扩展，为使窗口内最小值下降得尽可能慢，我们比较 `nums[i - 1]` 和 `nums[j + 1]`，优先将指针向边界值较大的一侧扩展（即若 `nums[i - 1] >= nums[j + 1]`，则 `i--`；否则 `j++`）。

3. **动态更新状态**：在每次指针移动后，新纳入窗口的元素值更新 `min_num = min(min_num, nums[i] 或 nums[j])`。

4. **计算得分**：当前子数组长度 `j + 1 - i`，其得分为 `score = min_num * (j + 1 - i)`，更新 `max_score = max(max_score, score)`。

5. 循环结束后，`max_score` 即为答案。

**复杂度：**
- **时间复杂度**：$O(n)$，其中 $n$ 为数组 `nums` 的长度。每个元素最多被扫描一次，属于严格的线性时间复杂度。
- **空间复杂度**：$O(1)$，仅需常数级别的额外空间来维护双指针、局部最小值和全局最大得分。

---

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maximumScore(self, nums: list[int], k: int) -> int:
        max_score = nums[k]  # Base case.
        min_num = nums[k]

        left_idx, right_idx = k, k

        while 0 < left_idx or right_idx < len(nums) - 1:
            if left_idx == 0:  # Can only go right.
                right_idx += 1
                min_num = min(min_num, nums[right_idx])

            elif right_idx == len(nums) - 1:  # Can only go left.
                left_idx -= 1
                min_num = min(min_num, nums[left_idx])

            else:  # Can go bidirectional.
                if nums[left_idx - 1] >= nums[right_idx + 1]:
                    left_idx -= 1
                    min_num = min(min_num, nums[left_idx])

                else:
                    right_idx += 1
                    min_num = min(min_num, nums[right_idx])

            score = min_num * (right_idx + 1 - left_idx)
            max_score = max(max_score, score)

        return max_score
```

#### C++

```cpp
class Solution {
public:
    int maximumScore(vector<int>& nums, int k) {
        int maxScore = nums[k], minNum = nums[k]; // Base case.

        int leftIdx = k, rightIdx = k;

        while (0 < leftIdx || rightIdx < nums.size() - 1) {
            if (leftIdx == 0) { // Can only go right.
                rightIdx++;
                minNum = min(minNum, nums[rightIdx]);
            }

            else if (rightIdx == nums.size() - 1) { // Can only go left.
                leftIdx--;
                minNum = min(minNum, nums[leftIdx]);
            }

            else { // Can go bidirectional.
                if (nums[leftIdx - 1] >= nums[rightIdx + 1]) {
                    leftIdx--;
                    minNum = min(minNum, nums[leftIdx]);
                }

                else {
                    rightIdx++;
                    minNum = min(minNum, nums[rightIdx]);
                }
            }

            int score = minNum * (rightIdx + 1 - leftIdx);
            maxScore = max(maxScore, score);
        }

        return maxScore;
    }
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
