---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1400-1499/1493.Longest%20Subarray%20of%201%27s%20After%20Deleting%20One%20Element/README.md
rating: 1423
source: 第 29 场双周赛 Q3
tags:
    - 数组
    - 动态规划
    - 滑动窗口
---

<!-- problem:start -->

# [1493. 删掉一个元素以后全为 1 的最长子数组](https://leetcode.cn/problems/longest-subarray-of-1s-after-deleting-one-element)

[English Version](/solution/1400-1499/1493.Longest%20Subarray%20of%201%27s%20After%20Deleting%20One%20Element/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个二进制数组&nbsp;<code>nums</code>&nbsp;，你需要从中删掉一个元素。</p>

<p>请你在删掉元素的结果数组中，返回最长的且只包含 1 的非空子数组的长度。</p>

<p>如果不存在这样的子数组，请返回 0 。</p>

<p>&nbsp;</p>

<p><strong>提示 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,0,1]
<strong>输出：</strong>3
<strong>解释：</strong>删掉位置 2 的数后，[1,1,1] 包含 3 个 1 。</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,1,1,1,0,1,1,0,1]
<strong>输出：</strong>5
<strong>解释：</strong>删掉位置 4 的数字后，[0,1,1,1,1,1,0,1] 的最长全 1 子数组为 [1,1,1,1,1] 。</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,1]
<strong>输出：</strong>2
<strong>解释：</strong>你必须要删除一个元素。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code>&nbsp;要么是&nbsp;<code>0</code>&nbsp;要么是&nbsp;<code>1</code> 。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：枚举

我们可以枚举每个删除的位置 $i$，然后计算左侧和右侧的连续 1 的个数，最后取最大值。

具体地，我们使用两个长度为 $n+1$ 的数组 $left$ 和 $right$，其中 $left[i]$ 表示以 $nums[i-1]$ 结尾的连续 $1$ 的个数，而 $right[i]$ 表示以 $nums[i]$ 开头的连续 $1$ 的个数。

最终答案即为 $\max_{0 \leq i < n} \{left[i] + right[i+1]\}$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestSubarray(self, nums: List[int]) -> int:
        n = len(nums)
        left = [0] * (n + 1)
        right = [0] * (n + 1)
        for i, x in enumerate(nums, 1):
            if x:
                left[i] = left[i - 1] + 1
        for i in range(n - 1, -1, -1):
            if nums[i]:
                right[i] = right[i + 1] + 1
        return max(left[i] + right[i + 1] for i in range(n))
```

#### Java

```java
class Solution {
    public int longestSubarray(int[] nums) {
        int n = nums.length;
        int[] left = new int[n + 1];
        int[] right = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            if (nums[i - 1] == 1) {
                left[i] = left[i - 1] + 1;
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            if (nums[i] == 1) {
                right[i] = right[i + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, left[i] + right[i + 1]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestSubarray(vector<int>& nums) {
        int n = nums.size();
        vector<int> left(n + 1);
        vector<int> right(n + 1);
        for (int i = 1; i <= n; ++i) {
            if (nums[i - 1]) {
                left[i] = left[i - 1] + 1;
            }
        }
        for (int i = n - 1; ~i; --i) {
            if (nums[i]) {
                right[i] = right[i + 1] + 1;
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = max(ans, left[i] + right[i + 1]);
        }
        return ans;
    }
};
```

#### Go

```go
func longestSubarray(nums []int) (ans int) {
	n := len(nums)
	left := make([]int, n+1)
	right := make([]int, n+1)
	for i := 1; i <= n; i++ {
		if nums[i-1] == 1 {
			left[i] = left[i-1] + 1
		}
	}
	for i := n - 1; i >= 0; i-- {
		if nums[i] == 1 {
			right[i] = right[i+1] + 1
		}
	}
	for i := 0; i < n; i++ {
		ans = max(ans, left[i]+right[i+1])
	}
	return
}
```

#### TypeScript

```ts
function longestSubarray(nums: number[]): number {
    const n = nums.length;
    const left: number[] = Array(n + 1).fill(0);
    const right: number[] = Array(n + 1).fill(0);
    for (let i = 1; i <= n; ++i) {
        if (nums[i - 1]) {
            left[i] = left[i - 1] + 1;
        }
    }
    for (let i = n - 1; ~i; --i) {
        if (nums[i]) {
            right[i] = right[i + 1] + 1;
        }
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        ans = Math.max(ans, left[i] + right[i + 1]);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：双指针

题目实际上是让我们找出一个最长的子数组，该子数组中最多只包含一个 $0$，删掉该子数组中的其中一个元素后，剩余的长度即为答案。

因此，我们可以用两个指针 $j$ 和 $i$ 分别指向子数组的左右边界，初始时 $j = 0$, $i = 0$。另外，我们用一个变量 $cnt$ 记录子数组中 $0$ 的个数。

接下来，我们移动右指针 $i$，如果 $nums[i] = 0$，则 $cnt$ 加 $1$。当 $cnt > 1$ 时，我们需要移动左指针 $j$，直到 $cnt \leq 1$。然后，我们更新答案，即 $ans = \max(ans, i - j)$。继续移动右指针 $i$，直到 $i$ 到达数组的末尾。

时间复杂度 $O(n)$，其中 $n$ 为数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestSubarray(self, nums: List[int]) -> int:
        ans = 0
        cnt = j = 0
        for i, x in enumerate(nums):
            cnt += x ^ 1
            while cnt > 1:
                cnt -= nums[j] ^ 1
                j += 1
            ans = max(ans, i - j)
        return ans
```

#### Java

```java
class Solution {
    public int longestSubarray(int[] nums) {
        int ans = 0, n = nums.length;
        for (int i = 0, j = 0, cnt = 0; i < n; ++i) {
            cnt += nums[i] ^ 1;
            while (cnt > 1) {
                cnt -= nums[j++] ^ 1;
            }
            ans = Math.max(ans, i - j);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestSubarray(vector<int>& nums) {
        int ans = 0, n = nums.size();
        for (int i = 0, j = 0, cnt = 0; i < n; ++i) {
            cnt += nums[i] ^ 1;
            while (cnt > 1) {
                cnt -= nums[j++] ^ 1;
            }
            ans = max(ans, i - j);
        }
        return ans;
    }
};
```

#### Go

```go
func longestSubarray(nums []int) (ans int) {
	cnt, j := 0, 0
	for i, x := range nums {
		cnt += x ^ 1
		for ; cnt > 1; j++ {
			cnt -= nums[j] ^ 1
		}
		ans = max(ans, i-j)
	}
	return
}
```

#### TypeScript

```ts
function longestSubarray(nums: number[]): number {
    let [ans, cnt, j] = [0, 0, 0];
    for (let i = 0; i < nums.length; ++i) {
        cnt += nums[i] ^ 1;
        while (cnt > 1) {
            cnt -= nums[j++] ^ 1;
        }
        ans = Math.max(ans, i - j);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法三：双指针（优化）

方法二中，我们每次会循环移动左指针，直到 $cnt \leq 1$。由于题目求的是最长子数组，意味着我们不需要缩小子数组的长度，因此，如果 $\textit{cnt} \gt 1$，我们只移动左指针一次，右指针也继续向右移动。这样可以保证子数组的长度不会减小。

最后，我们返回的答案即为 $n - l - 1$，其中 $l$ 为左指针的位置。

时间复杂度 $O(n)$，其中 $n$ 为数组 $nums$ 的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def longestSubarray(self, nums: List[int]) -> int:
        cnt = l = 0
        for x in nums:
            cnt += x ^ 1
            if cnt > 1:
                cnt -= nums[l] ^ 1
                l += 1
        return len(nums) - l - 1
```

#### Java

```java
class Solution {
    public int longestSubarray(int[] nums) {
        int ans = 0, cnt = 0, l = 0;
        for (int x : nums) {
            cnt += x ^ 1;
            if (cnt > 1) {
                cnt -= nums[l++] ^ 1;
            }
        }
        return nums.length - l - 1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int longestSubarray(vector<int>& nums) {
        int ans = 0, cnt = 0, l = 0;
        for (int x : nums) {
            cnt += x ^ 1;
            if (cnt > 1) {
                cnt -= nums[l++] ^ 1;
            }
        }
        return nums.size() - l - 1;
    }
};
```

#### Go

```go
func longestSubarray(nums []int) (ans int) {
	cnt, l := 0, 0
	for _, x := range nums {
		cnt += x ^ 1
		if cnt > 1 {
			cnt -= nums[l] ^ 1
			l++
		}
	}
	return len(nums) - l - 1
}
```

#### TypeScript

```ts
function longestSubarray(nums: number[]): number {
    let [cnt, l] = [0, 0];
    for (const x of nums) {
        cnt += x ^ 1;
        if (cnt > 1) {
            cnt -= nums[l++] ^ 1;
        }
    }
    return nums.length - l - 1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
