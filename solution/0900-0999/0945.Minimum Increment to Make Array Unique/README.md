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

<p>生成的测试用例保证答案在 32 位整数范围内。</p>

<div class="original__bRMd">
<div>
<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,2]
<strong>输出：</strong>1
<strong>解释：</strong>经过一次 <em>move</em> 操作，数组将变为 [1, 2, 3]。
</pre>

<p><strong class="example">示例 2：</strong></p>

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

我们首先对数组 $\textit{nums}$ 进行排序，用一个变量 $\textit{y}$ 记录当前的最大值，初始时 $\textit{y} = -1$。

然后遍历数组 $\textit{nums}$，对于每个元素 $x$，我们将 $y$ 更新为 $\max(y + 1, x)$，并将操作次数 $y - x$ 累加到结果中。

遍历完成后，返回结果即可。

时间复杂度 $O(n \times \log n)$，空间复杂度 $O(\log n)$。其中 $n$ 是数组 $\textit{nums}$ 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minIncrementForUnique(self, nums: List[int]) -> int:
        nums.sort()
        ans, y = 0, -1
        for x in nums:
            y = max(y + 1, x)
            ans += y - x
        return ans
```

#### Java

```java
class Solution {
    public int minIncrementForUnique(int[] nums) {
        Arrays.sort(nums);
        int ans = 0, y = -1;
        for (int x : nums) {
            y = Math.max(y + 1, x);
            ans += y - x;
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
        int ans = 0, y = -1;
        for (int x : nums) {
            y = max(y + 1, x);
            ans += y - x;
        }
        return ans;
    }
};
```

#### Go

```go
func minIncrementForUnique(nums []int) (ans int) {
	sort.Ints(nums)
	y := -1
	for _, x := range nums {
		y = max(y+1, x)
		ans += y - x
	}
	return
}
```

#### TypeScript

```ts
function minIncrementForUnique(nums: number[]): number {
    nums.sort((a, b) => a - b);
    let [ans, y] = [0, -1];
    for (const x of nums) {
        y = Math.max(y + 1, x);
        ans += y - x;
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### 方法二：计数 + 贪心

根据题目描述，结果数组的最大值 $m = \max(\textit{nums}) + \textit{len}(\textit{nums})$，我们可以使用一个计数数组 $\textit{cnt}$ 来记录每个元素出现的次数。

然后从 $0$ 到 $m - 1$ 遍历，对于每个元素 $i$，如果它出现的次数 $\textit{cnt}[i]$ 大于 $1$，那么我们将 $\textit{cnt}[i] - 1$ 个元素增加到 $i + 1$，并将操作次数累加到结果中。

遍历完成后，返回结果即可。

时间复杂度 $O(m)$，空间复杂度 $O(m)$。其中 $m$ 是数组 $\textit{nums}$ 的长度加上数组的最大值。

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
