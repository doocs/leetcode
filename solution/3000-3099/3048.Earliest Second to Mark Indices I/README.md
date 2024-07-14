---
comments: true
difficulty: 中等
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3000-3099/3048.Earliest%20Second%20to%20Mark%20Indices%20I/README.md
rating: 2262
source: 第 386 场周赛 Q3
tags:
    - 数组
    - 二分查找
---

<!-- problem:start -->

# [3048. 标记所有下标的最早秒数 I](https://leetcode.cn/problems/earliest-second-to-mark-indices-i)

[English Version](/solution/3000-3099/3048.Earliest%20Second%20to%20Mark%20Indices%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个下标从 <strong>1</strong>&nbsp;开始的整数数组&nbsp;<code>nums</code> 和&nbsp;<code>changeIndices</code>&nbsp;，数组的长度分别为&nbsp;<code>n</code> 和&nbsp;<code>m</code>&nbsp;。</p>

<p>一开始，<code>nums</code>&nbsp;中所有下标都是未标记的，你的任务是标记 <code>nums</code>&nbsp;中 <strong>所有</strong>&nbsp;下标。</p>

<p>从第 <code>1</code>&nbsp;秒到第 <code>m</code>&nbsp;秒（<b>包括&nbsp;</b>第&nbsp;<code>m</code>&nbsp;秒），对于每一秒 <code>s</code>&nbsp;，你可以执行以下操作 <strong>之一</strong>&nbsp;：</p>

<ul>
	<li>选择范围&nbsp;<code>[1, n]</code>&nbsp;中的一个下标 <code>i</code>&nbsp;，并且将&nbsp;<code>nums[i]</code> <strong>减少</strong>&nbsp;<code>1</code>&nbsp;。</li>
	<li>如果&nbsp;<code>nums[changeIndices[s]]</code>&nbsp;<strong>等于</strong>&nbsp;<code>0</code>&nbsp;，<strong>标记</strong>&nbsp;下标&nbsp;<code>changeIndices[s]</code>&nbsp;。</li>
	<li>什么也不做。</li>
</ul>

<p>请你返回范围 <code>[1, m]</code>&nbsp;中的一个整数，表示最优操作下，标记&nbsp;<code>nums</code>&nbsp;中 <strong>所有</strong>&nbsp;下标的 <strong>最早秒数</strong>&nbsp;，如果无法标记所有下标，返回 <code>-1</code>&nbsp;。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<b>输入：</b>nums = [2,2,0], changeIndices = [2,2,2,2,3,2,2,1]
<b>输出：</b>8
<b>解释：</b>这个例子中，我们总共有 8 秒。按照以下操作标记所有下标：
第 1 秒：选择下标 1 ，将 nums[1] 减少 1 。nums 变为 [1,2,0] 。
第 2 秒：选择下标 1 ，将 nums[1] 减少 1 。nums 变为 [0,2,0] 。
第 3 秒：选择下标 2 ，将 nums[2] 减少 1 。nums 变为 [0,1,0] 。
第 4 秒：选择下标 2 ，将 nums[2] 减少 1 。nums 变为 [0,0,0] 。
第 5 秒，标​​​​​记 changeIndices[5] ，也就是标记下标 3 ，因为 nums[3] 等于 0 。
第 6 秒，标​​​​​记 changeIndices[6] ，也就是标记下标 2 ，因为 nums[2] 等于 0 。
第 7 秒，什么也不做。
第 8 秒，标记 changeIndices[8] ，也就是标记下标 1 ，因为 nums[1] 等于 0 。
现在所有下标已被标记。
最早可以在第 8 秒标记所有下标。
所以答案是 8 。
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<b>输入：</b>nums = [1,3], changeIndices = [1,1,1,2,1,1,1]
<b>输出：</b>6
<b>解释：</b>这个例子中，我们总共有 7 秒。按照以下操作标记所有下标：
第 1 秒：选择下标 2 ，将 nums[2] 减少 1 。nums 变为 [1,2] 。
第 2 秒：选择下标 2 ，将 nums[2] 减少 1 。nums 变为 [1,1] 。
第 3 秒：选择下标 2 ，将 nums[2] 减少 1 。nums 变为 [1,0] 。
第 4 秒：标​​​​​记 changeIndices[4] ，也就是标记下标 2 ，因为 nums[2] 等于 0 。
第 5 秒：选择下标 1 ，将 nums[1] 减少 1 。nums 变为 [0,0] 。
第 6 秒：标​​​​​记 changeIndices[6] ，也就是标记下标 1 ，因为 nums[1] 等于 0 。
现在所有下标已被标记。
最早可以在第 6 秒标记所有下标。
所以答案是 6 。
</pre>

<p><strong class="example">示例 3：</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1], changeIndices = [2,2,2]
<strong>Output:</strong> -1
<strong>Explanation:</strong> 这个例子中，无法标记所有下标，因为下标 1 不在 changeIndices 中。
所以答案是 -1 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 2000</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= m == changeIndices.length &lt;= 2000</code></li>
	<li><code>1 &lt;= changeIndices[i] &lt;= n</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：二分查找

我们注意到，如果我们能够在 $t$ 秒内标记所有下标，那么我们也能在 $t' \geq t$ 秒内标记所有下标。因此，我们可以使用二分查找的方法找到最早的秒数。

我们定义二分查找的左右边界分别为 $l = 1$ 和 $r = m + 1$，其中 $m$ 是数组 `changeIndices` 的长度。对于每一个 $t = \frac{l + r}{2}$，我们检查是否能在 $t$ 秒内标记所有下标。如果能，我们将右边界移动到 $t$，否则我们将左边界移动到 $t + 1$。最终，我们判定左边界是否大于 $m$，如果是则返回 $-1$，否则返回左边界。

题目的关键在于如何判断是否能在 $t$ 秒内标记所有下标。我们可以使用一个数组 $last$ 记录每一个下标最晚需要被标记的时间，用一个变量 $decrement$ 记录当前可以减少的次数，用一个变量 $marked$ 记录已经被标记的下标的数量。

我们遍历数组 `changeIndices` 的前 $t$ 个元素，对于每一个元素 $i$，如果 $last[i] = s$，那么我们需要检查 $decrement$ 是否大于等于 $nums[i - 1]$，如果是，我们将 $decrement$ 减去 $nums[i - 1]$，并且将 $marked$ 加一；否则，我们返回 `False`。如果 $last[i] \neq s$，那么我们可以暂时不标记下标，因此将 $decrement$ 加一。最后，我们检查 $marked$ 是否等于 $n$，如果是，我们返回 `True`，否则返回 `False`。

时间复杂度 $O(m \times \log m)$，空间复杂度 $O(n)$。其中 $n$ 和 $m$ 分别是数组 `nums` 和 `changeIndices` 的长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def earliestSecondToMarkIndices(
        self, nums: List[int], changeIndices: List[int]
    ) -> int:
        def check(t: int) -> bool:
            decrement = 0
            marked = 0
            last = {i: s for s, i in enumerate(changeIndices[:t])}
            for s, i in enumerate(changeIndices[:t]):
                if last[i] == s:
                    if decrement < nums[i - 1]:
                        return False
                    decrement -= nums[i - 1]
                    marked += 1
                else:
                    decrement += 1
            return marked == len(nums)

        m = len(changeIndices)
        l = bisect_left(range(1, m + 2), True, key=check) + 1
        return -1 if l > m else l
```

#### Java

```java
class Solution {
    private int[] nums;
    private int[] changeIndices;

    public int earliestSecondToMarkIndices(int[] nums, int[] changeIndices) {
        this.nums = nums;
        this.changeIndices = changeIndices;
        int m = changeIndices.length;
        int l = 1, r = m + 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l > m ? -1 : l;
    }

    private boolean check(int t) {
        int[] last = new int[nums.length + 1];
        for (int s = 0; s < t; ++s) {
            last[changeIndices[s]] = s;
        }
        int decrement = 0;
        int marked = 0;
        for (int s = 0; s < t; ++s) {
            int i = changeIndices[s];
            if (last[i] == s) {
                if (decrement < nums[i - 1]) {
                    return false;
                }
                decrement -= nums[i - 1];
                ++marked;
            } else {
                ++decrement;
            }
        }
        return marked == nums.length;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int earliestSecondToMarkIndices(vector<int>& nums, vector<int>& changeIndices) {
        int n = nums.size();
        int last[n + 1];
        auto check = [&](int t) {
            memset(last, 0, sizeof(last));
            for (int s = 0; s < t; ++s) {
                last[changeIndices[s]] = s;
            }
            int decrement = 0, marked = 0;
            for (int s = 0; s < t; ++s) {
                int i = changeIndices[s];
                if (last[i] == s) {
                    if (decrement < nums[i - 1]) {
                        return false;
                    }
                    decrement -= nums[i - 1];
                    ++marked;
                } else {
                    ++decrement;
                }
            }
            return marked == n;
        };

        int m = changeIndices.size();
        int l = 1, r = m + 1;
        while (l < r) {
            int mid = (l + r) >> 1;
            if (check(mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l > m ? -1 : l;
    }
};
```

#### Go

```go
func earliestSecondToMarkIndices(nums []int, changeIndices []int) int {
	n, m := len(nums), len(changeIndices)
	l := sort.Search(m+1, func(t int) bool {
		last := make([]int, n+1)
		for s, i := range changeIndices[:t] {
			last[i] = s
		}
		decrement, marked := 0, 0
		for s, i := range changeIndices[:t] {
			if last[i] == s {
				if decrement < nums[i-1] {
					return false
				}
				decrement -= nums[i-1]
				marked++
			} else {
				decrement++
			}
		}
		return marked == n
	})
	if l > m {
		return -1
	}
	return l
}
```

#### TypeScript

```ts
function earliestSecondToMarkIndices(nums: number[], changeIndices: number[]): number {
    const [n, m] = [nums.length, changeIndices.length];
    let [l, r] = [1, m + 1];
    const check = (t: number): boolean => {
        const last: number[] = Array(n + 1).fill(0);
        for (let s = 0; s < t; ++s) {
            last[changeIndices[s]] = s;
        }
        let [decrement, marked] = [0, 0];
        for (let s = 0; s < t; ++s) {
            const i = changeIndices[s];
            if (last[i] === s) {
                if (decrement < nums[i - 1]) {
                    return false;
                }
                decrement -= nums[i - 1];
                ++marked;
            } else {
                ++decrement;
            }
        }
        return marked === n;
    };
    while (l < r) {
        const mid = (l + r) >> 1;
        if (check(mid)) {
            r = mid;
        } else {
            l = mid + 1;
        }
    }
    return l > m ? -1 : l;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
