---
comments: true
difficulty: 中等
---

<!-- problem:start -->

# [4087. Maximum Subarray Sum of Fixed Length After at Most K Swaps 🔒](https://leetcode.cn/problems/maximum-subarray-sum-of-fixed-length-after-at-most-k-swaps)

[English Version](/solution/4000-4099/4087.Maximum%20Subarray%20Sum%20of%20Fixed%20Length%20After%20at%20Most%20K%20Swaps/README_EN.md)

## 题目描述

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

## 解法

<!-- solution:start -->

### 方法一：枚举窗口并贪心交换

<!-- thinking:start -->

> **思考**
>
> $n$ 不超过 $300$，逐个计算长度为 $\textit{length}$ 的窗口完全来得及。每个窗口再去枚举交换哪些下标，组合数会随 $k$ 指数上升。
>
> 瓶颈在窗口边界上。窗内两个元素互换、窗外两个元素互换，都不改变这个窗口的和；只有把窗内元素和窗外元素对换，窗口和才会变化。因此有用的交换次数不会超过 $\min(k,\textit{length},n-\textit{length})$。
>
> 窗口和只有在换入值大于换出值时才会增加，因此要用窗外较大的值替换窗内较小的值。把窗内升序、窗外降序之后，第 $j$ 次交换的收益单调不增，从最小的窗内元素开始配对，收益一旦非正就可以停止。所有窗口里这样得到的最大和就是答案。

<!-- thinking:end -->

选定起点为 $i$ 的窗口，记窗内升序为 $a_0 \le a_1 \le \cdots$，窗外降序为 $b_0 \ge b_1 \ge \cdots$。交换 $t$ 次以后，窗口和的增量等于换入之和减去换出之和。对固定的 $t$，最优选择是换入窗外最大的 $t$ 个数、换出窗内最小的 $t$ 个数，增量为 $\sum_{j=0}^{t-1}(b_j-a_j)$。若某个换入值不在这 $t$ 个最大元素之中，把它换成尚未使用的更大元素，和不会下降；若某个换出值不在这 $t$ 个最小元素之中，把它换成尚未换出的更小元素，和同样不会下降。

由于 $b_j-a_j$ 单调不增，正收益构成一个前缀。实际交换次数取这个前缀的长度与 $\min(k,\textit{length},n-\textit{length})$ 的较小值。把原窗口和加上对应的收益，就得到该起点的最大和；答案是所有起点中的最大值。

时间复杂度 $O(n^2 \log n)$，空间复杂度 $O(n)$。其中 $n$ 是数组长度。

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
