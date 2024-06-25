---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0900-0999/0945.Minimum%20Increment%20to%20Make%20Array%20Unique/README.md
tags:
    - 贪心
    - 数组
    - 计数
    - 排序
---

<!-- problem:start -->

# [945. 使数组唯一的最小增量](https://leetcode.cn/problems/minimum-increment-to-make-array-unique)

[English Version](/solution/0900-0999/0945.Minimum%20Increment%20to%20Make%20Array%20Unique/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 。每次 move 操作将会选择任意一个满足 <code>0 &lt;= i &lt; nums.length</code> 的下标 <code>i</code>，并将&nbsp;<code>nums[i]</code> 递增&nbsp;<code>1</code>。</p>

<p>返回使 <code>nums</code> 中的每个值都变成唯一的所需要的最少操作次数。</p>

<div class="original__bRMd">
<div>
<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,2]
<strong>输出：</strong>1
<strong>解释：</strong>经过一次 <em>move</em> 操作，数组将变为 [1, 2, 3]。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,2,1,2,1,7]
<strong>输出：</strong>6
<strong>解释：</strong>经过 6 次 <em>move</em> 操作，数组将变为 [3, 4, 1, 2, 5, 7]。
可以看出 5 次或 5 次以下的 <em>move</em> 操作是不能让数组的每个值唯一的。</pre>
</div>
</div>

<p>&nbsp;</p>
<strong>提示：</strong>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：排序 + 贪心

我们首先对数组进行排序，然后从前往后遍历数组，对于每个元素 `nums[i]`，如果它小于等于前一个元素 `nums[i - 1]`，那么我们将它增加到 `nums[i - 1] + 1`，那么操作的次数就是 `nums[i - 1] - nums[i] + 1`，累加到结果中。

遍历完成后，返回结果即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 `nums` 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minIncrementForUnique(self, nums: List[int]) -> int:
        nums.sort()
        ans = 0
        for i in range(1, len(nums)):
            if nums[i] <= nums[i - 1]:
                d = nums[i - 1] - nums[i] + 1
                nums[i] += d
                ans += d
        return ans
```

#### Java

```java
class Solution {
    public int minIncrementForUnique(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] <= nums[i - 1]) {
                int d = nums[i - 1] - nums[i] + 1;
                nums[i] += d;
                ans += d;
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
    int minIncrementForUnique(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int ans = 0;
        for (int i = 1; i < nums.size(); ++i) {
            if (nums[i] <= nums[i - 1]) {
                int d = nums[i - 1] - nums[i] + 1;
                nums[i] += d;
                ans += d;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minIncrementForUnique(nums []int) (ans int) {
	sort.Ints(nums)
	for i := 1; i < len(nums); i++ {
		if nums[i] <= nums[i-1] {
			d := nums[i-1] - nums[i] + 1
			nums[i] += d
			ans += d
		}
	}
	return
}
```

#### TypeScript

```ts
function minIncrementForUnique(nums: number[]): number {
    nums.sort((a, b) => a - b);
    let ans = 0;
    for (let i = 1; i < nums.length; ++i) {
        if (nums[i] <= nums[i - 1]) {
            ans += nums[i - 1] - nums[i] + 1;
            nums[i] = nums[i - 1] + 1;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：计数 + 贪心

根据题目描述，结果数组的最大值 $m = \max(\text{nums}) + \text{len}(\text{nums})$，我们可以使用一个计数数组 `cnt` 来记录每个元素出现的次数。

然后从 $0$ 到 $m - 1$ 遍历，对于每个元素 $i$，如果它出现的次数 $\text{cnt}[i]$ 大于 $1$，那么我们将 $\text{cnt}[i] - 1$ 个元素增加到 $i + 1$，并将操作次数累加到结果中。

遍历完成后，返回结果即可。

时间复杂度 $O(m)$，空间复杂度 $O(m)$。其中 $m$ 是数组 `nums` 的长度加上数组 `nums` 的最大值。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minIncrementForUnique(self, nums: List[int]) -> int:
        m = max(nums) + len(nums)
        cnt = Counter(nums)
        ans = 0
        for i in range(m - 1):
            if (diff := cnt[i] - 1) > 0:
                cnt[i + 1] += diff
                ans += diff
        return ans
```

#### Java

```java
class Solution {
    public int minIncrementForUnique(int[] nums) {
        int m = Arrays.stream(nums).max().getAsInt() + nums.length;
        int[] cnt = new int[m];
        for (int x : nums) {
            ++cnt[x];
        }
        int ans = 0;
        for (int i = 0; i < m - 1; ++i) {
            int diff = cnt[i] - 1;
            if (diff > 0) {
                cnt[i + 1] += diff;
                ans += diff;
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
    int minIncrementForUnique(vector<int>& nums) {
        int m = *max_element(nums.begin(), nums.end()) + nums.size();
        int cnt[m];
        memset(cnt, 0, sizeof(cnt));
        for (int x : nums) {
            ++cnt[x];
        }
        int ans = 0;
        for (int i = 0; i < m - 1; ++i) {
            int diff = cnt[i] - 1;
            if (diff > 0) {
                cnt[i + 1] += diff;
                ans += diff;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func minIncrementForUnique(nums []int) (ans int) {
	m := slices.Max(nums) + len(nums)
	cnt := make([]int, m)
	for _, x := range nums {
		cnt[x]++
	}
	for i := 0; i < m-1; i++ {
		if diff := cnt[i] - 1; diff > 0 {
			cnt[i+1] += diff
			ans += diff
		}
	}
	return ans
}
```

#### TypeScript

```ts
function minIncrementForUnique(nums: number[]): number {
    const m = Math.max(...nums) + nums.length;
    const cnt: number[] = Array(m).fill(0);
    for (const x of nums) {
        cnt[x]++;
    }
    let ans = 0;
    for (let i = 0; i < m - 1; ++i) {
        const diff = cnt[i] - 1;
        if (diff > 0) {
            cnt[i + 1] += diff;
            ans += diff;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
