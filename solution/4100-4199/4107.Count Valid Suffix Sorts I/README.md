---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [4107. 统计有效后缀排序的数量 I](https://leetcode.cn/problems/count-valid-suffix-sorts-i)

[English Version](/solution/4100-4199/4107.Count%20Valid%20Suffix%20Sorts%20I/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你两个长度均为 <code>n</code> 的整数数组 <code>nums</code> 和 <code>target</code>。</p>

<p>对于每个下标 <code>i</code>，考虑将原数组的<strong>&nbsp;后缀</strong> <code>nums[i..n - 1]</code> 按<strong>&nbsp;非递增顺序</strong>&nbsp;排序。</p>

<p>返回使排序后的数组等于 <code>target</code> 的下标 <code>i</code> 的数量。</p>

<p><strong>子数组&nbsp;</strong>是数组中一个连续的<strong>&nbsp;非空&nbsp;</strong>元素序列。</p>

<p>数组的&nbsp;<strong>后缀&nbsp;</strong>是从任意下标开始并延伸至数组末尾的子数组。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3], target = [3,2,1]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<ul>
	<li><code>i = 0</code>：排序后的数组为 <code>[3, 2, 1]</code>，等于 <code>target</code>。</li>
	<li><code>i = 1</code>：排序后的数组为 <code>[1, 3, 2]</code>。</li>
	<li><code>i = 2</code>：排序后的数组为 <code>[1, 2, 3]</code>。</li>
</ul>

<p>只有 <code>i = 0</code> 符合要求，因此答案为 1。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,5,6,4,6], target = [1,5,6,6,4]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>选择 <code>i = 2</code> 或 <code>i = 3</code>，排序后都会得到 <code>[1, 5, 6, 6, 4]</code>。其他下标均无法得到 <code>target</code>。因此，答案为 2。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [7,6,8], target = [8,6,7]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>分别对从下标 0、1 和 2 开始的后缀排序，会依次得到 <code>[8, 7, 6]</code>、<code>[7, 8, 6]</code> 和 <code>[7, 6, 8]</code>。它们均不等于 <code>target</code>，因此答案为 0。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length == target.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], target[i] &lt;= 10<sup>​​​​​​​5</sup></code>​​​​​​​​​​​​​​</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：哈希表

<!-- thinking:start -->

> **思考**
>
> $n$ 达到 $10^5$，对每个起点把后缀排序后再与 $\textit{target}$ 比较，复杂度是 $O(n^2\log n)$，无法通过。
>
> 排序只重排后缀，前缀 $\textit{nums}[0..i)$ 原样留下，整个数组的多重集也不变。起点一旦越过第一处不同的位置，那个位置就留在未排序的前缀里。
>
> 非递增排序的后缀内部不能出现上升。 $\textit{target}$ 里最靠右的一处 $\textit{target}[k-1]<\textit{target}[k]$，把合法起点限制在 $k$ 及其右侧。
>
> 前缀逐位相等时，后缀的多重集随之相等；这段 $\textit{target}$ 若已经非递增，它就是该多重集唯一的非递增排列。于是用哈希表核对两个数组的计数，再统计右端上升位置到第一处不同位置之间的下标个数。

<!-- thinking:end -->

对后缀做非递增排序不会改变整个数组的多重集，也不会改动前缀 $\textit{nums}[0..i)$。我们用哈希表 $\textit{diff}$ 比较 $\textit{nums}$ 与 $\textit{target}$ 的元素计数： $\textit{nums}$ 中每个值加一， $\textit{target}$ 中每个值减一。减到负数说明两者多重集不同，直接返回 $0$。

令 $\textit{maxI}$ 为从左到右第一处 $\textit{nums}[i]\neq\textit{target}[i]$ 的下标；两数组完全相同时 $\textit{maxI}=n-1$。起点大于 $\textit{maxI}$ 时，这处不同落在未排序的前缀中，排序结果不可能等于 $\textit{target}$。

令 $\textit{minI}$ 为 $\textit{target}$ 最靠右的严格上升位置。从右向左寻找最大的 $i>0$，使 $\textit{target}[i-1]<\textit{target}[i]$；若不存在这样的 $i$，则 $\textit{minI}=0$。起点小于 $\textit{minI}$ 时， $\textit{target}[i..n)$ 内部仍有上升，不可能是非递增排序的结果。

两个数组的多重集相同，且 $\textit{nums}[0..i)$ 与 $\textit{target}[0..i)$ 逐位相等时，后缀的多重集也相同。此时 $\textit{target}[i..n)$ 非递增，就等于将 $\textit{nums}[i..n)$ 按非递增顺序排序。因此每个 $i\in[\textit{minI},\textit{maxI}]$ 都合法，答案为 $\max(\textit{maxI}-\textit{minI}+1,0)$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 是数组长度。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSuffix(self, nums: list[int], target: list[int]) -> int:
        diff = defaultdict(int)
        for x in nums:
            diff[x] += 1
        for x in target:
            diff[x] -= 1
            if diff[x] < 0:
                return 0

        n = len(nums)
        max_i = n - 1
        for i, (x, y) in enumerate(zip(nums, target)):
            if x != y:
                max_i = i
                break

        min_i = 0
        for i in range(n - 1, 0, -1):
            if target[i - 1] < target[i]:
                min_i = i
                break

        return max(max_i - min_i + 1, 0)
```

#### Java

```java
class Solution {
    public int countSuffix(int[] nums, int[] target) {
        Map<Integer, Integer> diff = new HashMap<>();
        for (int x : nums) {
            diff.merge(x, 1, Integer::sum);
        }
        for (int x : target) {
            diff.merge(x, -1, Integer::sum);
            if (diff.get(x) < 0) {
                return 0;
            }
        }

        int n = nums.length;
        int maxI = n - 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] != target[i]) {
                maxI = i;
                break;
            }
        }

        int minI = 0;
        for (int i = n - 1; i > 0; i--) {
            if (target[i - 1] < target[i]) {
                minI = i;
                break;
            }
        }

        return Math.max(maxI - minI + 1, 0);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countSuffix(vector<int>& nums, vector<int>& target) {
        unordered_map<int, int> diff;
        for (int x : nums) {
            diff[x]++;
        }
        for (int x : target) {
            if (--diff[x] < 0) {
                return 0;
            }
        }

        int n = nums.size();
        int max_i = n - 1;
        for (int i = 0; i < n; i++) {
            if (nums[i] != target[i]) {
                max_i = i;
                break;
            }
        }

        int min_i = 0;
        for (int i = n - 1; i > 0; i--) {
            if (target[i - 1] < target[i]) {
                min_i = i;
                break;
            }
        }

        return max(max_i - min_i + 1, 0);
    }
};
```

#### Go

```go
func countSuffix(nums []int, target []int) int {
	diff := map[int]int{}
	for _, x := range nums {
		diff[x]++
	}
	for _, x := range target {
		diff[x]--
		if diff[x] < 0 {
			return 0
		}
	}

	n := len(nums)
	maxI := n - 1
	for i, x := range nums {
		if x != target[i] {
			maxI = i
			break
		}
	}

	minI := 0
	for i := n - 1; i > 0; i-- {
		if target[i-1] < target[i] {
			minI = i
			break
		}
	}

	return max(maxI-minI+1, 0)
}
```

#### TypeScript

```ts
function countSuffix(nums: number[], target: number[]): number {
    const diff = new Map<number, number>();
    for (const x of nums) {
        diff.set(x, (diff.get(x) ?? 0) + 1);
    }
    for (const x of target) {
        const cnt = (diff.get(x) ?? 0) - 1;
        diff.set(x, cnt);
        if (cnt < 0) {
            return 0;
        }
    }

    const n = nums.length;
    let maxI = n - 1;
    for (let i = 0; i < n; i++) {
        if (nums[i] !== target[i]) {
            maxI = i;
            break;
        }
    }

    let minI = 0;
    for (let i = n - 1; i > 0; i--) {
        if (target[i - 1] < target[i]) {
            minI = i;
            break;
        }
    }

    return Math.max(maxI - minI + 1, 0);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
