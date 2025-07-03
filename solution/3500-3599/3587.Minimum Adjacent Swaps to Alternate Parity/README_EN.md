---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3500-3599/3587.Minimum%20Adjacent%20Swaps%20to%20Alternate%20Parity/README_EN.md
tags:
    - Greedy
    - Array
---

<!-- problem:start -->

# [3587. Minimum Adjacent Swaps to Alternate Parity](https://leetcode.com/problems/minimum-adjacent-swaps-to-alternate-parity)

[中文文档](/solution/3500-3599/3587.Minimum%20Adjacent%20Swaps%20to%20Alternate%20Parity/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>nums</code> of <strong>distinct</strong> integers.</p>

<p>In one operation, you can swap any two <strong>adjacent</strong> elements in the array.</p>

<p>An arrangement of the array is considered <strong>valid</strong> if the parity of adjacent elements <strong>alternates</strong>, meaning every pair of neighboring elements consists of one even and one odd number.</p>

<p>Return the <strong>minimum</strong> number of adjacent swaps required to transform <code>nums</code> into any valid arrangement.</p>

<p>If it is impossible to rearrange <code>nums</code> such that no two adjacent elements have the same parity, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,4,6,5,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>Swapping 5 and 6, the array becomes <code>[2,4,5,6,7]</code></p>

<p>Swapping 5 and 4, the array becomes <code>[2,5,4,6,7]</code></p>

<p>Swapping 6 and 7, the array becomes <code>[2,5,4,7,6]</code>. The array is now a valid arrangement. Thus, the answer is 3.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,4,5,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>By swapping 4 and 5, the array becomes <code>[2,5,4,7]</code>, which is a valid arrangement. Thus, the answer is 1.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The array is already a valid arrangement. Thus, no operations are needed.</p>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,5,6,8]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<p>No valid arrangement is possible. Thus, the answer is -1.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li>All elements in <code>nums</code> are <strong>distinct</strong>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Case Analysis + Greedy

For a valid arrangement, the number of odd and even numbers can only differ by 1 or be equal. Therefore, if the difference between the number of odd and even numbers is greater than 1, it is impossible to form a valid arrangement, and we should return -1 directly.

We use an array $\text{pos}$ to store the indices of odd and even numbers, where $\text{pos}[0]$ stores the indices of even numbers and $\text{pos}[1]$ stores the indices of odd numbers.

If the number of odd and even numbers is equal, there are two valid arrangements: odd numbers before even numbers, or even numbers before odd numbers. We can calculate the number of swaps required for both arrangements and take the minimum.

If the number of odd numbers is greater than the number of even numbers, there is only one valid arrangement, which is odd numbers before even numbers. In this case, we only need to calculate the number of swaps for this arrangement.

Therefore, we define a function $\text{calc}(k)$, where $k$ indicates the parity of the first element (0 for even, 1 for odd). This function calculates the number of swaps needed to transform the current arrangement into a valid arrangement starting with $k$. We just need to iterate over the indices in $\text{pos}[k]$ and sum the differences between each index and its position in the valid arrangement.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array $\text{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def minSwaps(self, nums: List[int]) -> int:
        def calc(k: int) -> int:
            return sum(abs(i - j) for i, j in zip(range(0, len(nums), 2), pos[k]))

        pos = [[], []]
        for i, x in enumerate(nums):
            pos[x & 1].append(i)
        if abs(len(pos[0]) - len(pos[1])) > 1:
            return -1
        if len(pos[0]) > len(pos[1]):
            return calc(0)
        if len(pos[0]) < len(pos[1]):
            return calc(1)
        return min(calc(0), calc(1))
```

#### Java

```java
class Solution {
    private List<Integer>[] pos = new List[2];
    private int[] nums;

    public int minSwaps(int[] nums) {
        this.nums = nums;
        Arrays.setAll(pos, k -> new ArrayList<>());
        for (int i = 0; i < nums.length; ++i) {
            pos[nums[i] & 1].add(i);
        }
        if (Math.abs(pos[0].size() - pos[1].size()) > 1) {
            return -1;
        }
        if (pos[0].size() > pos[1].size()) {
            return calc(0);
        }
        if (pos[0].size() < pos[1].size()) {
            return calc(1);
        }
        return Math.min(calc(0), calc(1));
    }

    private int calc(int k) {
        int res = 0;
        for (int i = 0; i < nums.length; i += 2) {
            res += Math.abs(pos[k].get(i / 2) - i);
        }
        return res;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int minSwaps(vector<int>& nums) {
        vector<int> pos[2];
        for (int i = 0; i < nums.size(); ++i) {
            pos[nums[i] & 1].push_back(i);
        }
        if (abs(int(pos[0].size() - pos[1].size())) > 1) {
            return -1;
        }
        auto calc = [&](int k) {
            int res = 0;
            for (int i = 0; i < nums.size(); i += 2) {
                res += abs(pos[k][i / 2] - i);
            }
            return res;
        };
        if (pos[0].size() > pos[1].size()) {
            return calc(0);
        }
        if (pos[0].size() < pos[1].size()) {
            return calc(1);
        }
        return min(calc(0), calc(1));
    }
};
```

#### Go

```go
func minSwaps(nums []int) int {
	pos := [2][]int{}
	for i, x := range nums {
		pos[x&1] = append(pos[x&1], i)
	}
	if abs(len(pos[0])-len(pos[1])) > 1 {
		return -1
	}
	calc := func(k int) int {
		res := 0
		for i := 0; i < len(nums); i += 2 {
			res += abs(pos[k][i/2] - i)
		}
		return res
	}
	if len(pos[0]) > len(pos[1]) {
		return calc(0)
	}
	if len(pos[0]) < len(pos[1]) {
		return calc(1)
	}
	return min(calc(0), calc(1))
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

#### TypeScript

```ts
function minSwaps(nums: number[]): number {
    const pos: number[][] = [[], []];
    for (let i = 0; i < nums.length; ++i) {
        pos[nums[i] & 1].push(i);
    }
    if (Math.abs(pos[0].length - pos[1].length) > 1) {
        return -1;
    }
    const calc = (k: number): number => {
        let res = 0;
        for (let i = 0; i < nums.length; i += 2) {
            res += Math.abs(pos[k][i >> 1] - i);
        }
        return res;
    };
    if (pos[0].length > pos[1].length) {
        return calc(0);
    }
    if (pos[0].length < pos[1].length) {
        return calc(1);
    }
    return Math.min(calc(0), calc(1));
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
