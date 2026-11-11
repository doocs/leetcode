---
comments: true
difficulty: Medium
---

<!-- problem:start -->

# [4107. Count Valid Suffix Sorts I](https://leetcode.com/problems/count-valid-suffix-sorts-i)

[中文文档](/solution/4100-4199/4107.Count%20Valid%20Suffix%20Sorts%20I/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays <code>nums</code> and <code>target</code>, both of length <code>n</code>.</p>

<p>For each index <code>i</code>, consider sorting the <strong>suffix</strong> <code>nums[i..n - 1]</code> of the original array in <strong>non-increasing order</strong>.</p>

<p>Return the number of indices <code>i</code> for which the resulting array is equal to <code>target</code>.</p>

<p>A <strong>suffix</strong> of an array is a <span data-keyword="subarray-nonempty">subarray</span> that begins at any index and extends to the end of the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,2,3], target = [3,2,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li><code>i = 0</code>: The resulting array is <code>[3, 2, 1]</code>, which equals <code>target</code>.</li>
	<li><code>i = 1</code>: The resulting array is <code>[1, 3, 2]</code>.</li>
	<li><code>i = 2</code>: The resulting array is <code>[1, 2, 3]</code>.</li>
</ul>

<p>Only <code>i = 0</code> is valid, so the answer is 1.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,5,6,4,6], target = [1,5,6,6,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>Choosing either <code>i = 2</code> or <code>i = 3</code> results in <code>[1, 5, 6, 6, 4]</code>. No other index produces <code>target</code>. Therefore, the answer is 2.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [7,6,8], target = [8,6,7]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>Sorting the suffix starting at indices 0, 1, and 2 produces <code>[8, 7, 6]</code>, <code>[7, 8, 6]</code>, and <code>[7, 6, 8]</code>, respectively. None equals <code>target</code>, so the answer is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length == target.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i], target[i] &lt;= 10<sup>​​​​​​​5</sup></code>​​​​​​​​​​​​​​</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

<!-- thinking:start -->

> **Thinking**
>
> $n$ reaches $10^5$. Sorting a suffix for every start and comparing it with $\textit{target}$ is $O(n^2\log n)$, which is too slow.
>
> Sorting only rearranges the suffix. The prefix $\textit{nums}[0..i)$ stays in place, and the multiset of the whole array never changes. Once the start passes the first position where the two arrays differ, that position remains in the unsorted prefix.
>
> A suffix sorted in non-increasing order cannot contain an ascent. The rightmost place where $\textit{target}[k-1]<\textit{target}[k]$ forces every valid start to be at least $k$.
>
> When the prefix matches element by element, the suffix multisets match as well. If that piece of $\textit{target}$ is already non-increasing, it is the unique non-increasing arrangement of its multiset. A hash table checks the two counts, and the answer is the number of indices from that rightmost ascent through the first mismatch.

<!-- thinking:end -->

Sorting a suffix into non-increasing order does not change the multiset of the whole array, and it leaves the prefix $\textit{nums}[0..i)$ untouched. We compare the counts of $\textit{nums}$ and $\textit{target}$ with a hash table $\textit{diff}$: increment each value in $\textit{nums}$, then decrement each value in $\textit{target}$. A negative count means the multisets differ, so return $0$.

Let $\textit{maxI}$ be the first index where $\textit{nums}[i]\neq\textit{target}[i]$. If the two arrays are identical, $\textit{maxI}=n-1$. A start greater than $\textit{maxI}$ leaves that mismatch inside the unsorted prefix, so the result cannot equal $\textit{target}$.

Let $\textit{minI}$ be the rightmost strict ascent in $\textit{target}$. Scan from right to left for the largest $i>0$ such that $\textit{target}[i-1]<\textit{target}[i]$. If no such $i$ exists, $\textit{minI}=0$. A start smaller than $\textit{minI}$ leaves an ascent inside $\textit{target}[i..n)$, which cannot be the result of a non-increasing sort.

When the two arrays have the same multiset and $\textit{nums}[0..i)$ equals $\textit{target}[0..i)$ element by element, the suffix multisets are equal too. $\textit{target}[i..n)$ is then non-increasing, so it is exactly $\textit{nums}[i..n)$ sorted in non-increasing order. Every $i\in[\textit{minI},\textit{maxI}]$ is valid, and the answer is $\max(\textit{maxI}-\textit{minI}+1,0)$.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the arrays.

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
