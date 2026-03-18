---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3874.Valid%20Subarrays%20With%20Exactly%20One%20Peak/README_EN.md
---

<!-- problem:start -->

# [3874. Valid Subarrays With Exactly One Peak 🔒](https://leetcode.com/problems/valid-subarrays-with-exactly-one-peak)

[中文文档](/solution/3800-3899/3874.Valid%20Subarrays%20With%20Exactly%20One%20Peak/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code> of length <code>n</code> and an integer <code>k</code>.</p>

<p>An index <code>i</code> is a <strong>peak</strong> if:</p>

<ul>
	<li><code>0 &lt; i &lt; n - 1</code></li>
	<li><code>nums[i] &gt; nums[i - 1]</code> and <code>nums[i] &gt; nums[i + 1]</code></li>
</ul>

<p>A subarray <code>[l, r]</code> is <strong>valid</strong> if:</p>

<ul>
	<li>It contains <strong>exactly one</strong> peak at index <code>i</code> from <code>nums</code></li>
	<li><code>i - l &lt;= k</code> and <code>r - i &lt;= k</code></li>
</ul>

<p>Return an integer denoting the number of <strong>valid subarrays</strong> in <code>nums</code>.</p>
A <strong>subarray</strong> is a contiguous <b>non-empty</b> sequence of elements within an array.
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,3,2], k = 1</span></p>

<p><strong>Output:</strong> <span class="example-io">4</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Index <code>i = 1</code> is a peak because <code>nums[1] = 3</code> is greater than <code>nums[0] = 1</code> and <code>nums[2] = 2</code>.</li>
	<li>Any valid subarray must include index 1, and the distance from the peak to both ends of the subarray must not exceed <code>k = 1</code>.</li>
	<li>The valid subarrays are <code>[3]</code>, <code>[1, 3]</code>, <code>[3, 2]</code>, and <code>[1, 3, 2]</code>, so the answer is 4.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [7,8,9], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>There is no index <code>i</code> such that <code>nums[i]</code> is greater than both <code>nums[i - 1]</code> and <code>nums[i + 1]</code>.</li>
	<li>Therefore, the array contains no peak. Thus, the number of valid subarrays is 0.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [4,3,5,1], k = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">6</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>Index <code>i = 2</code> is a peak because <code>nums[2] = 5</code> is greater than <code>nums[1] = 3</code> and <code>nums[3] = 1</code>.</li>
	<li>Any valid subarray must contain this peak, and the distance from the peak to both ends of the subarray must not exceed <code>k = 2</code>.</li>
	<li>The valid subarrays are <code>[5]</code>, <code>[3, 5]</code>, <code>[5, 1]</code>, <code>[3, 5, 1]</code>, <code>[4, 3, 5]</code>, and <code>[4, 3, 5, 1]</code>, so the answer is 6.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= k &lt;= n</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Simulation

We first traverse the array to find all peak positions and store them in a list $\textit{peaks}$.

For each peak position, we calculate the left and right boundaries centered at the peak with a distance not exceeding $k$. Note that if there are multiple peaks, we need to ensure the calculated subarray does not contain other peaks. Then, based on the left and right boundaries, we calculate the number of valid subarrays centered at each peak and accumulate it into the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def validSubarrays(self, nums: list[int], k: int) -> int:
        n = len(nums)
        peaks = []
        for i in range(1, n - 1):
            if nums[i] > nums[i - 1] and nums[i] > nums[i + 1]:
                peaks.append(i)

        ans = 0
        for j, p in enumerate(peaks):
            left_min = max(p - k, 0)
            if j > 0:
                left_min = max(left_min, peaks[j - 1] + 1)

            right_max = min(p + k, n - 1)
            if j < len(peaks) - 1:
                right_max = min(right_max, peaks[j + 1] - 1)

            ans += (p - left_min + 1) * (right_max - p + 1)
        return ans
```

#### Java

```java
class Solution {
    public long validSubarrays(int[] nums, int k) {
        int n = nums.length;
        List<Integer> peaks = new ArrayList<>();

        for (int i = 1; i < n - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                peaks.add(i);
            }
        }

        long ans = 0;
        for (int j = 0; j < peaks.size(); j++) {
            int p = peaks.get(j);

            int leftMin = Math.max(p - k, 0);
            if (j > 0) {
                leftMin = Math.max(leftMin, peaks.get(j - 1) + 1);
            }

            int rightMax = Math.min(p + k, n - 1);
            if (j < peaks.size() - 1) {
                rightMax = Math.min(rightMax, peaks.get(j + 1) - 1);
            }

            ans += (long) (p - leftMin + 1) * (rightMax - p + 1);
        }

        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    long long validSubarrays(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> peaks;

        for (int i = 1; i < n - 1; ++i) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                peaks.push_back(i);
            }
        }

        long long ans = 0;
        for (int j = 0; j < peaks.size(); ++j) {
            int p = peaks[j];

            int leftMin = max(p - k, 0);
            if (j > 0) {
                leftMin = max(leftMin, peaks[j - 1] + 1);
            }

            int rightMax = min(p + k, n - 1);
            if (j < peaks.size() - 1) {
                rightMax = min(rightMax, peaks[j + 1] - 1);
            }

            ans += 1LL * (p - leftMin + 1) * (rightMax - p + 1);
        }

        return ans;
    }
};
```

#### Go

```go
func validSubarrays(nums []int, k int) int64 {
	n := len(nums)
	peaks := []int{}

	for i := 1; i < n-1; i++ {
		if nums[i] > nums[i-1] && nums[i] > nums[i+1] {
			peaks = append(peaks, i)
		}
	}

	var ans int64
	for j, p := range peaks {
		leftMin := max(p-k, 0)
		if j > 0 {
			leftMin = max(leftMin, peaks[j-1]+1)
		}

		rightMax := min(p+k, n-1)
		if j < len(peaks)-1 {
			rightMax = min(rightMax, peaks[j+1]-1)
		}

		ans += int64(p-leftMin+1) * int64(rightMax-p+1)
	}

	return ans
}
```

#### TypeScript

```ts
function validSubarrays(nums: number[], k: number): number {
    const n = nums.length;
    const peaks: number[] = [];

    for (let i = 1; i < n - 1; i++) {
        if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
            peaks.push(i);
        }
    }

    let ans = 0;
    for (let j = 0; j < peaks.length; j++) {
        const p = peaks[j];

        let leftMin = Math.max(p - k, 0);
        if (j > 0) {
            leftMin = Math.max(leftMin, peaks[j - 1] + 1);
        }

        let rightMax = Math.min(p + k, n - 1);
        if (j < peaks.length - 1) {
            rightMax = Math.min(rightMax, peaks[j + 1] - 1);
        }

        ans += (p - leftMin + 1) * (rightMax - p + 1);
    }

    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
