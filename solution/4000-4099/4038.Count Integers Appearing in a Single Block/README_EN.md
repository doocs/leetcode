---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4038.Count%20Integers%20Appearing%20in%20a%20Single%20Block/README_EN.md
rating: 1165
source: Weekly Contest 517 Q1
---

<!-- problem:start -->

# [4038. Count Integers Appearing in a Single Block](https://leetcode.com/problems/count-integers-appearing-in-a-single-block)

[中文文档](/solution/4000-4099/4038.Count%20Integers%20Appearing%20in%20a%20Single%20Block/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>An integer <code>x</code> is <strong>special</strong> if all occurrences of <code>x</code> in <code>nums</code> appear in a single <strong>contiguous</strong> block.</p>

<p>Return the number of <strong>distinct</strong> special integers in <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>1 appears at indices 0 and 3, forming two separate blocks, so it is not special.</li>
	<li>2 appears in a single contiguous block at indices <code>[1, 2]</code>, so it is special.</li>
</ul>

<p>Therefore, there is one special integer.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,3,1,2,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>3 appears in a single contiguous block at indices <code>[0, 1]</code>, so it is special.</li>
	<li>1 appears at indices 2 and 5, forming two separate blocks, so it is not special.</li>
	<li>2 appears in a single contiguous block at indices <code>[3, 4]</code>, so it is special.</li>
</ul>

<p>Therefore, there are two special integers.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Count the Blocks of Each Integer

Call each maximal run of consecutive equal elements a **block**. An integer $x$ is special if and only if it forms exactly one block.

So we traverse the array, and whenever $i = 0$ or $\textit{nums}[i] \neq \textit{nums}[i - 1]$, position $i$ starts a new block, and we increment $\textit{cnt}[\textit{nums}[i]]$. After the traversal, the answer is the number of integers whose count in $\textit{cnt}$ is exactly $1$.

The time complexity is $O(n + M)$, and the space complexity is $O(M)$. Here, $n$ is the length of the array $\textit{nums}$, and $M = 100$ is the maximum value in the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countSpecialIntegers(self, nums: List[int]) -> int:
        cnt = Counter(x for i, x in enumerate(nums) if i == 0 or x != nums[i - 1])
        return sum(v == 1 for v in cnt.values())
```

#### Java

```java
class Solution {
    public int countSpecialIntegers(int[] nums) {
        int[] cnt = new int[101];
        for (int i = 0; i < nums.length; ++i) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                ++cnt[nums[i]];
            }
        }
        int ans = 0;
        for (int c : cnt) {
            if (c == 1) {
                ++ans;
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
    int countSpecialIntegers(vector<int>& nums) {
        int cnt[101]{};
        for (int i = 0; i < nums.size(); ++i) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                ++cnt[nums[i]];
            }
        }
        return count(begin(cnt), end(cnt), 1);
    }
};
```

#### Go

```go
func countSpecialIntegers(nums []int) int {
	cnt := [101]int{}
	for i, x := range nums {
		if i == 0 || x != nums[i-1] {
			cnt[x]++
		}
	}
	ans := 0
	for _, c := range cnt {
		if c == 1 {
			ans++
		}
	}
	return ans
}
```

#### TypeScript

```ts
function countSpecialIntegers(nums: number[]): number {
    const cnt: number[] = Array(101).fill(0);
    for (let i = 0; i < nums.length; ++i) {
        if (i === 0 || nums[i] !== nums[i - 1]) {
            ++cnt[nums[i]];
        }
    }
    return cnt.filter(c => c === 1).length;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
