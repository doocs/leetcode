---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4035.Maximum%20Valid%20Split%20Positions%20I/README_EN.md
rating: 1663
source: Biweekly Contest 190 Q2
---

<!-- problem:start -->

# [4035. Maximum Valid Split Positions I](https://leetcode.com/problems/maximum-valid-split-positions-i)

[中文文档](/solution/4000-4099/4035.Maximum%20Valid%20Split%20Positions%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>You may remove <strong>at most one</strong> element from <code>nums</code>. Let <code>arr</code> be the array of remaining elements in their original order, and let <code>m</code> be its length.</p>

<p>A <strong>split position</strong> <code>i</code> of <code>arr</code> is <strong>valid</strong> if:</p>

<ul>
	<li><code>0 &lt;= i &lt; m - 1</code>, and</li>
	<li><code>gcd(arr[0..i]) == gcd(arr[i + 1..m - 1])</code>.</li>
</ul>

<p>An array of length 1 has no valid split positions.</p>

<p>The <strong>score</strong> of <code>arr</code> is the number of valid split positions in it.</p>

<p>Return the <strong>maximum possible score</strong> of <code>arr</code>.</p>

<p>Here, <code>gcd(a)</code> denotes the <strong>greatest common divisor</strong> of all elements in the array <code>a</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [10,30,15,10]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal solution is to remove <code>nums[2] = 15</code>. Then <code>arr = [10, 30, 10]</code>.</p>

<p>The split positions are:</p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse; text-align:center;">
	<tbody>
		<tr>
			<th>Split Position <code>i</code></th>
			<th><code>gcd(arr[0..i])</code></th>
			<th><code>gcd(arr[i + 1..m - 1])</code></th>
		</tr>
		<tr>
			<td>0</td>
			<td>10</td>
			<td>10</td>
		</tr>
		<tr>
			<td>1</td>
			<td>10</td>
			<td>10</td>
		</tr>
	</tbody>
</table>

<p>All split positions are valid. Thus, the answer is 2.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,10,14]</span></p>

<p><strong>Output:</strong> <span class="example-io">1</span></p>

<p><strong>Explanation:</strong></p>

<p>One optimal solution is to not remove any element. Then <code>arr = [2, 10, 14]</code>.</p>

<p>The split positions are:</p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse; text-align:center;">
	<tbody>
		<tr>
			<th>Split Position <code>i</code></th>
			<th><code>gcd(arr[0..i])</code></th>
			<th><code>gcd(arr[i + 1..m - 1])</code></th>
		</tr>
		<tr>
			<td>0</td>
			<td>2</td>
			<td>2</td>
		</tr>
		<tr>
			<td>1</td>
			<td>2</td>
			<td>14</td>
		</tr>
	</tbody>
</table>

<p>Only the split position at index 0 is valid. Thus, the answer is 1.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [2,4]</span></p>

<p><strong>Output:</strong> <span class="example-io">0</span></p>

<p><strong>Explanation:</strong></p>

<p>The only remaining array that has a split position is <code>arr = [2, 4]</code>.</p>

<p>The split positions are:</p>

<table border="1" bordercolor="#ccc" cellpadding="5" cellspacing="0" style="border-collapse:collapse; text-align:center;">
	<tbody>
		<tr>
			<th>Split Position <code>i</code></th>
			<th><code>gcd(arr[0..i])</code></th>
			<th><code>gcd(arr[i + 1..m - 1])</code></th>
		</tr>
		<tr>
			<td>0</td>
			<td>2</td>
			<td>4</td>
		</tr>
	</tbody>
</table>

<p>There are no valid split positions. Thus, the answer is 0.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code>​​​​​​​</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Enumerate the Removed Index + Prefix and Suffix GCD

Since the array length satisfies $n \leq 1000$, we can enumerate the index of the removed element (including the case where nothing is removed) to obtain the array $\textit{arr}$, compute the score of $\textit{arr}$, and take the maximum over all cases.

For an array $\textit{arr}$ of length $m$, we precompute the prefix GCD array $\textit{pre}$ and the suffix GCD array $\textit{suf}$, where $\textit{pre}[i] = \gcd(\textit{arr}[0..i])$ and $\textit{suf}[i] = \gcd(\textit{arr}[i..m - 1])$. A split position $i$ is valid if and only if $\textit{pre}[i] = \textit{suf}[i + 1]$, so the score of $\textit{arr}$ is the number of indices satisfying this condition.

The time complexity is $O(n^2 \times \log M)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$, and $M$ is the maximum value in the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxValidSplits(self, nums: List[int]) -> int:
        def calc(arr: List[int]) -> int:
            m = len(arr)
            pre = list(accumulate(arr, gcd))
            suf = list(accumulate(arr[::-1], gcd))[::-1]
            return sum(pre[i] == suf[i + 1] for i in range(m - 1))

        ans = calc(nums)
        for i in range(len(nums)):
            ans = max(ans, calc(nums[:i] + nums[i + 1 :]))
        return ans
```

#### Java

```java
class Solution {
    public int maxValidSplits(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int del = -1; del < n; ++del) {
            int m = del == -1 ? n : n - 1;
            int[] arr = new int[m];
            for (int i = 0, j = 0; i < n; ++i) {
                if (i != del) {
                    arr[j++] = nums[i];
                }
            }
            ans = Math.max(ans, calc(arr));
        }
        return ans;
    }

    private int calc(int[] arr) {
        int m = arr.length;
        int[] pre = new int[m];
        int[] suf = new int[m];
        pre[0] = arr[0];
        for (int i = 1; i < m; ++i) {
            pre[i] = gcd(pre[i - 1], arr[i]);
        }
        suf[m - 1] = arr[m - 1];
        for (int i = m - 2; i >= 0; --i) {
            suf[i] = gcd(suf[i + 1], arr[i]);
        }
        int ans = 0;
        for (int i = 0; i < m - 1; ++i) {
            if (pre[i] == suf[i + 1]) {
                ++ans;
            }
        }
        return ans;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxValidSplits(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;
        for (int del = -1; del < n; ++del) {
            vector<int> arr;
            arr.reserve(n);
            for (int i = 0; i < n; ++i) {
                if (i != del) {
                    arr.push_back(nums[i]);
                }
            }
            ans = max(ans, calc(arr));
        }
        return ans;
    }

private:
    int calc(const vector<int>& arr) {
        int m = arr.size();
        vector<int> pre(m), suf(m);
        pre[0] = arr[0];
        for (int i = 1; i < m; ++i) {
            pre[i] = gcd(pre[i - 1], arr[i]);
        }
        suf[m - 1] = arr[m - 1];
        for (int i = m - 2; i >= 0; --i) {
            suf[i] = gcd(suf[i + 1], arr[i]);
        }
        int ans = 0;
        for (int i = 0; i < m - 1; ++i) {
            if (pre[i] == suf[i + 1]) {
                ++ans;
            }
        }
        return ans;
    }
};
```

#### Go

```go
func maxValidSplits(nums []int) int {
	n := len(nums)
	calc := func(arr []int) int {
		m := len(arr)
		pre := make([]int, m)
		suf := make([]int, m)
		pre[0] = arr[0]
		for i := 1; i < m; i++ {
			pre[i] = gcd(pre[i-1], arr[i])
		}
		suf[m-1] = arr[m-1]
		for i := m - 2; i >= 0; i-- {
			suf[i] = gcd(suf[i+1], arr[i])
		}
		ans := 0
		for i := 0; i < m-1; i++ {
			if pre[i] == suf[i+1] {
				ans++
			}
		}
		return ans
	}
	ans := 0
	for del := -1; del < n; del++ {
		arr := make([]int, 0, n)
		for i, x := range nums {
			if i != del {
				arr = append(arr, x)
			}
		}
		ans = max(ans, calc(arr))
	}
	return ans
}

func gcd(a, b int) int {
	if b == 0 {
		return a
	}
	return gcd(b, a%b)
}
```

#### TypeScript

```ts
function maxValidSplits(nums: number[]): number {
    const n = nums.length;
    const gcd = (a: number, b: number): number => (b === 0 ? a : gcd(b, a % b));
    const calc = (arr: number[]): number => {
        const m = arr.length;
        const pre: number[] = Array(m).fill(0);
        const suf: number[] = Array(m).fill(0);
        pre[0] = arr[0];
        for (let i = 1; i < m; ++i) {
            pre[i] = gcd(pre[i - 1], arr[i]);
        }
        suf[m - 1] = arr[m - 1];
        for (let i = m - 2; i >= 0; --i) {
            suf[i] = gcd(suf[i + 1], arr[i]);
        }
        let ans = 0;
        for (let i = 0; i < m - 1; ++i) {
            if (pre[i] === suf[i + 1]) {
                ++ans;
            }
        }
        return ans;
    };
    let ans = 0;
    for (let del = -1; del < n; ++del) {
        const arr: number[] = [];
        for (let i = 0; i < n; ++i) {
            if (i !== del) {
                arr.push(nums[i]);
            }
        }
        ans = Math.max(ans, calc(arr));
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
