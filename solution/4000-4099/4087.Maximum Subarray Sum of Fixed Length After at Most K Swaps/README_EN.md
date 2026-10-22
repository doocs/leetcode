---
comments: true
difficulty: Medium
---

<!-- problem:start -->

# [4087. Maximum Subarray Sum of Fixed Length After at Most K Swaps 🔒](https://leetcode.com/problems/maximum-subarray-sum-of-fixed-length-after-at-most-k-swaps)

[中文文档](/solution/4000-4099/4087.Maximum%20Subarray%20Sum%20of%20Fixed%20Length%20After%20at%20Most%20K%20Swaps/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> and two integers <code>length</code> and <code>k</code>.</p>

<p>You may perform <strong>at most</strong> <code>k</code> swap operations on <code>nums</code>.</p>

<p>In one swap operation, you may choose two <strong>distinct</strong> indices <code>i</code> and <code>j</code> and swap <code>nums[i]</code> and <code>nums[j]</code>.</p>

<p>Return the <strong>maximum possible sum</strong> of a <span data-keyword="subarray-nonempty">subarray</span> of length <strong>exactly</strong> <code>length</code> after performing the swaps.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,-1,0,2], length = 2, k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Swap the elements at indices 1 and 3, resulting in <code>[1, 2, 0, -1]</code>.</li>
	<li>The subarray <code>[1, 2]</code> has length 2 and sum 3.</li>
	<li>No subarray of length 2 can have a larger sum, since 1 and 2 are the two largest elements.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,3,2,4], length = 3, k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">11</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Swap the elements at indices 2 and 3, resulting in <code>[4, 3, 4, 2]</code>.</li>
	<li>The subarray <code>[4, 3, 4]</code> has length 3 and sum 11.</li>
	<li>This is the sum of the three largest elements, so no larger answer is possible. Only one swap is needed.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [-1,-2], length = 2, k = 0</span></p>

<p><strong>Output:</strong> <span class="example-io">-3</span></p>

<p><strong>Explanation:</strong></p>

<p>No swaps are allowed. The only subarray of length 2 is <code>[-1, -2]</code>, whose sum is -3.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 300</code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= length &lt;= nums.length</code></li>
	<li><code>0 &lt;= k &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumerate Windows and Swap Greedily

<!-- thinking:start -->

> **Thinking**
>
> $n$ is at most $300$, so every window of length $\textit{length}$ can be evaluated directly. Choosing which indices to swap inside a window grows exponentially with $k$.
>
> The bottleneck is the window boundary. Swapping two elements that are both inside the window, or both outside it, does not change that window's sum. Only an exchange across the boundary does, so the number of useful swaps is at most $\min(k,\textit{length},n-\textit{length})$.
>
> The sum grows only when the incoming value is larger than the outgoing one, so a larger outside value should replace a smaller inside value. After the inside values are sorted in ascending order and the outside values in descending order, the gain of the $j$-th swap is non-increasing. Pair from the smallest inside value and stop at the first non-positive gain. The best sum among all windows is the answer.

<!-- thinking:end -->

Fix the window starting at index $i$. Write the inside values in non-decreasing order $a_0 \le a_1 \le \cdots$ and the outside values in non-increasing order $b_0 \ge b_1 \ge \cdots$. After $t$ swaps, the change in the window sum equals the sum of the incoming values minus the sum of the outgoing values. For a fixed $t$, the optimal choice brings in the $t$ largest outside values and sends out the $t$ smallest inside values, for a change of $\sum_{j=0}^{t-1}(b_j-a_j)$. If an incoming value is outside that set, replacing it with a larger unused outside value cannot decrease the sum. If an outgoing value is not one of those $t$ smallest inside values, replacing it with a smaller inside value that has not yet been sent out cannot decrease the sum either.

Because $b_j-a_j$ is non-increasing, the positive terms form a prefix. The number of swaps actually used is the smaller of that prefix length and $\min(k,\textit{length},n-\textit{length})$. Adding the corresponding gains to the original window sum gives the best sum for that starting index, and the answer is the maximum over all starting indices.

The time complexity is $O(n^2 \log n)$ and the space complexity is $O(n)$, where $n$ is the length of $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxSum(self, nums: list[int], length: int, k: int) -> int:
        n = len(nums)
        max_swaps = min(k, length, n - length)
        ans = -inf
        for i in range(n - length + 1):
            internal = sorted(nums[i : i + length])
            external = sorted(nums[:i] + nums[i + length :], reverse=True)
            total = sum(internal)
            for j in range(max_swaps):
                gain = external[j] - internal[j]
                if gain <= 0:
                    break
                total += gain
            ans = max(ans, total)
        return ans
```

#### Java

```java
class Solution {
    public long maxSum(int[] nums, int length, int k) {
        int n = nums.length;
        int maxSwaps = Math.min(k, Math.min(length, n - length));
        long ans = Long.MIN_VALUE;
        for (int i = 0; i <= n - length; ++i) {
            int[] internal = Arrays.copyOfRange(nums, i, i + length);
            int[] external = new int[n - length];
            System.arraycopy(nums, 0, external, 0, i);
            System.arraycopy(nums, i + length, external, i, n - length - i);
            Arrays.sort(internal);
            Arrays.sort(external);
            long total = 0;
            for (int v : internal) {
                total += v;
            }
            for (int j = 0; j < maxSwaps; ++j) {
                long gain = (long) external[external.length - 1 - j] - internal[j];
                if (gain <= 0) {
                    break;
                }
                total += gain;
            }
            ans = Math.max(ans, total);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long maxSum(vector<int>& nums, int length, int k) {
        int n = nums.size();
        int maxSwaps = min(k, min(length, n - length));
        long long ans = LLONG_MIN;
        for (int i = 0; i <= n - length; ++i) {
            vector<int> internal(nums.begin() + i, nums.begin() + i + length);
            vector<int> external(nums.begin(), nums.begin() + i);
            external.insert(external.end(), nums.begin() + i + length, nums.end());
            sort(internal.begin(), internal.end());
            sort(external.rbegin(), external.rend());
            long long total = accumulate(internal.begin(), internal.end(), 0LL);
            for (int j = 0; j < maxSwaps; ++j) {
                long long gain = (long long) external[j] - internal[j];
                if (gain <= 0) {
                    break;
                }
                total += gain;
            }
            ans = max(ans, total);
        }
        return ans;
    }
};
```

#### Go

```go
func maxSum(nums []int, length int, k int) int64 {
	n := len(nums)
	maxSwaps := min(k, min(length, n-length))
	ans := int64(math.MinInt64)
	for i := 0; i <= n-length; i++ {
		internal := make([]int, length)
		copy(internal, nums[i:i+length])
		external := make([]int, 0, n-length)
		external = append(external, nums[:i]...)
		external = append(external, nums[i+length:]...)
		sort.Ints(internal)
		sort.Slice(external, func(a, b int) bool { return external[a] > external[b] })
		var total int64
		for _, v := range internal {
			total += int64(v)
		}
		for j := 0; j < maxSwaps; j++ {
			gain := int64(external[j]) - int64(internal[j])
			if gain <= 0 {
				break
			}
			total += gain
		}
		ans = max(ans, total)
	}
	return ans
}
```

#### TypeScript

```ts
function maxSum(nums: number[], length: number, k: number): number {
    const n = nums.length;
    const maxSwaps = Math.min(k, length, n - length);
    let ans = -Infinity;
    for (let i = 0; i <= n - length; ++i) {
        const internal = nums.slice(i, i + length).sort((a, b) => a - b);
        const external = [...nums.slice(0, i), ...nums.slice(i + length)].sort((a, b) => b - a);
        let total = internal.reduce((a, b) => a + b, 0);
        for (let j = 0; j < maxSwaps; ++j) {
            const gain = external[j] - internal[j];
            if (gain <= 0) {
                break;
            }
            total += gain;
        }
        ans = Math.max(ans, total);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
