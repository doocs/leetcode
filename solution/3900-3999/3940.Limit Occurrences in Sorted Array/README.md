---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3940.Limit%20Occurrences%20in%20Sorted%20Array/README.md
---

<!-- problem:start -->

# [3940. 限制有序数组中的元素出现次数](https://leetcode.cn/problems/limit-occurrences-in-sorted-array)

[English Version](/solution/3900-3999/3940.Limit%20Occurrences%20in%20Sorted%20Array/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个&nbsp;<strong>按升序排序&nbsp;</strong>的整数数组 <code>nums</code> 和一个整数 <code>k</code>。</p>

<p>返回一个数组，使得每个<strong>&nbsp;不同</strong>&nbsp;元素最多出现 <code>k</code> 次，同时保持 <code>nums</code> 中元素的相对顺序不变。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,1,1,2,2,3], k = 2</span></p>

<p><strong>输出：</strong> <span class="example-io">[1,1,2,2,3]</span></p>

<p><strong>解释：</strong></p>

<p>每个元素最多可以出现 2 次。</p>

<ul>
	<li>元素 1 出现了 3 次，因此只保留其中 2 次。</li>
	<li>元素 2 出现了 2 次，因此全部保留。</li>
	<li>元素 3 出现了 1 次，因此保留。</li>
</ul>

<p>因此，结果数组为 <code>[1, 1, 2, 2, 3]</code>。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,3], k = 1</span></p>

<p><strong>输出：</strong> <span class="example-io">[1,2,3]</span></p>

<p><strong>解释：</strong></p>

<p>所有元素都互不相同，且已经最多只出现一次，因此数组保持不变。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>nums</code> 按非递减顺序排序。</li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong></p>

<ul>
	<li>你能使用原地算法，并仅使用 <code>O(1)</code> 额外空间解决该问题吗？</li>
	<li>注意：用于返回结果或调整结果大小所占用的空间不计入上述空间复杂度，因为有些语言不支持原地调整数组大小。</li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

我们定义两个指针 $l$ 和 $r$，分别表示当前处理的元素和下一个要处理的元素。我们还定义一个计数器 $cnt$ 来记录当前元素出现的次数。初始时 $l$ 和 $cnt$ 都为 1。

然后，我们从 $r = 1$ 开始遍历数组：

1. 如果当前元素 $nums[r]$ 与前一个元素 $nums[r - 1]$ 不同，则说明我们遇到了一个新的元素，我们将计数器 $cnt$ 重置为 1。
2. 如果当前元素 $nums[r]$ 与前一个元素 $nums[r - 1]$ 相同，则说明我们遇到了一个重复的元素，我们将计数器 $cnt$ 加 1。

如果计数器 $cnt$ 小于或等于 $k$，则说明当前元素出现的次数没有超过限制，我们将其保留在数组中，即将 $nums[l]$ 设置为 $nums[r]$，并将指针 $l$ 向右移动一位。

最后，我们返回数组的前 $l$ 个元素，即 $nums[:l]$。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$，我们只使用了常数级别的额外空间。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def limitOccurrences(self, nums: list[int], k: int) -> list[int]:
        n = len(nums)
        cnt = l = 1
        for r in range(1, n):
            if nums[r] != nums[r - 1]:
                cnt = 1
            else:
                cnt += 1
            if cnt <= k:
                nums[l] = nums[r]
                l += 1
        return nums[:l]
```

#### Java

```java
class Solution {
    public int[] limitOccurrences(int[] nums, int k) {
        int n = nums.length;
        int cnt = 1, l = 1;

        for (int r = 1; r < n; r++) {
            if (nums[r] != nums[r - 1]) {
                cnt = 1;
            } else {
                cnt++;
            }

            if (cnt <= k) {
                nums[l] = nums[r];
                l++;
            }
        }

        return Arrays.copyOf(nums, l);
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> limitOccurrences(vector<int>& nums, int k) {
        int n = nums.size();
        int cnt = 1, l = 1;

        for (int r = 1; r < n; r++) {
            if (nums[r] != nums[r - 1]) {
                cnt = 1;
            } else {
                cnt++;
            }

            if (cnt <= k) {
                nums[l] = nums[r];
                l++;
            }
        }

        nums.resize(l);
        return nums;
    }
};
```

#### Go

```go
func limitOccurrences(nums []int, k int) []int {
	n := len(nums)
	cnt, l := 1, 1

	for r := 1; r < n; r++ {
		if nums[r] != nums[r-1] {
			cnt = 1
		} else {
			cnt++
		}

		if cnt <= k {
			nums[l] = nums[r]
			l++
		}
	}

	return nums[:l]
}
```

#### TypeScript

```ts
function limitOccurrences(nums: number[], k: number): number[] {
    const n = nums.length;
    let cnt = 1;
    let l = 1;

    for (let r = 1; r < n; r++) {
        if (nums[r] !== nums[r - 1]) {
            cnt = 1;
        } else {
            cnt++;
        }

        if (cnt <= k) {
            nums[l] = nums[r];
            l++;
        }
    }

    return nums.slice(0, l);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
