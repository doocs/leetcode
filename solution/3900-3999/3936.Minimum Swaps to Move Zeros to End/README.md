---
comments: true
difficulty: 简单
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3900-3999/3936.Minimum%20Swaps%20to%20Move%20Zeros%20to%20End/README.md
rating: 1346
source: 第 183 场双周赛 Q1
---

<!-- problem:start -->

# [3936. 将 0 移到末尾的最少交换次数](https://leetcode.cn/problems/minimum-swaps-to-move-zeros-to-end)

[English Version](/solution/3900-3999/3936.Minimum%20Swaps%20to%20Move%20Zeros%20to%20End/README_EN.md)

## 题目描述

<!-- description:start -->

<p>给你一个整数数组 <code>nums</code> 。</p>

<p>在一步操作中，你可以选择任意两个 <strong>不同</strong> 的下标 <code>i</code> 和 <code>j</code> 并交换 <code>nums[i]</code> 和 <code>nums[j]</code> 。</p>

<p>返回将所有 0 移动到数组末尾所需的 <strong>最少</strong> 操作次数。</p>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [0,1,0,3,12]</span></p>

<p><strong>输出：</strong> <span class="example-io">2</span></p>

<p><strong>解释：</strong></p>

<p>我们执行以下交换操作：</p>

<ul>
	<li>交换 <code>nums[0]</code> 和 <code>nums[3]</code> ，得到 <code>nums = [3, 1, 0, 0, 12]</code> 。</li>
	<li>交换 <code>nums[2]</code> 和 <code>nums[4]</code> ，得到 <code>nums = [3, 1, 12, 0, 0]</code> 。</li>
</ul>

<p>因此，答案是 2 。</p>
</div>

<p><strong class="example">示例 2：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [0,1,0,2]</span></p>

<p><strong>输出：</strong> <span class="example-io">1</span></p>

<p><strong>解释：</strong></p>

<p>我们执行以下交换操作：</p>

<ul>
	<li>交换 <code>nums[0]</code> 和 <code>nums[3]</code> ，得到 <code>nums = [2, 1, 0, 0]</code> 。</li>
</ul>

<p>因此，答案是 1 。</p>
</div>

<p><strong class="example">示例 3：</strong></p>

<div class="example-block">
<p><strong>输入：</strong> <span class="example-io">nums = [1,2,0]</span></p>

<p><strong>输出：</strong> <span class="example-io">0</span></p>

<p><strong>解释：</strong></p>

<p>数组已经满足条件。因此，不需要任何交换操作。</p>
</div>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## 解法

<!-- solution:start -->

### 方法一：双指针

我们用两个指针 $i$ 和 $j$ 分别指向数组的开头和结尾。每次我们将 $i$ 向右移动直到找到一个 0，将 $j$ 向左移动直到找到一个非 0 的数。如果 $i < j$，我们就交换这两个数，并将答案加 1。重复这个过程直到 $i \geq j$。

时间复杂度 $O(n)$，其中 $n$ 是数组的长度。空间复杂度 $O(1)$。

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minimumSwaps(self, nums: list[int]) -> int:
        ans = 0
        n = len(nums)
        i, j = 0, n - 1
        while i < j:
            while i < n and nums[i] != 0:
                i += 1
            while j and nums[j] == 0:
                j -= 1
            if i >= j:
                break
            ans += 1
            i += 1
            j -= 1
        return ans
```

#### Java

```java
class Solution {
    public int minimumSwaps(int[] nums) {
        int ans = 0;
        int n = nums.length;
        for (int i = 0, j = n - 1; i < j; ++i, --j) {
            while (i < n && nums[i] != 0) {
                ++i;
            }

            while (j > 0 && nums[j] == 0) {
                --j;
            }

            if (i >= j) {
                break;
            }

            ++ans;
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minimumSwaps(vector<int>& nums) {
        int ans = 0;
        int n = nums.size();
        for (int i = 0, j = n - 1; i < j; ++i, --j) {
            while (i < n && nums[i] != 0) {
                ++i;
            }

            while (j > 0 && nums[j] == 0) {
                --j;
            }

            if (i >= j) {
                break;
            }

            ++ans;
        }

        return ans;
    }
};
```

#### Go

```go
func minimumSwaps(nums []int) int {
	ans := 0
	n := len(nums)

	for i, j := 0, n-1; i < j; i, j = i+1, j-1 {
		for i < n && nums[i] != 0 {
			i++
		}

		for j > 0 && nums[j] == 0 {
			j--
		}

		if i >= j {
			break
		}

		ans++
	}

	return ans
}
```

#### TypeScript

```ts
function minimumSwaps(nums: number[]): number {
    let ans = 0;
    const n = nums.length;

    let i = 0;
    let j = n - 1;

    while (i < j) {
        while (i < n && nums[i] !== 0) {
            ++i;
        }

        while (j > 0 && nums[j] === 0) {
            --j;
        }

        if (i >= j) {
            break;
        }

        ++ans;
        ++i;
        --j;
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
