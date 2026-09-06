---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/4000-4099/4037.Maximum%20Valid%20Split%20Positions%20II/README_EN.md
rating: 2372
source: Biweekly Contest 190 Q4
---

<!-- problem:start -->

# [4037. Maximum Valid Split Positions II](https://leetcode.com/problems/maximum-valid-split-positions-ii)

[中文文档](/solution/4000-4099/4037.Maximum%20Valid%20Split%20Positions%20II/README.md)

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
	<li><code>2 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code>​​​​​​​</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix and Suffix GCD + Enumerate Candidate Removed Indices

Following the idea of the previous problem, for an array $\textit{arr}$ of length $m$ we precompute the prefix GCD array $\textit{pre}$ and the suffix GCD array $\textit{suf}$. A split position $i$ is valid if and only if $\textit{pre}[i] = \textit{suf}[i + 1]$, so the score of $\textit{arr}$ is the number of indices satisfying this condition. However, $n$ can be as large as $10^5$ here, so enumerating every removed index and spending $O(n)$ on each of them is too slow.

Observe that every entry of the prefix GCD sequence divides the previous one, so it is at least halved whenever it changes, meaning the whole sequence changes only $O(\log M)$ times. If the prefix GCD does not change at index $i$, i.e. $\textit{pre}[i] = \textit{pre}[i - 1]$, which is equivalent to $\textit{pre}[i - 1]$ dividing $\textit{nums}[i]$, then removing $\textit{nums}[i]$ leaves every prefix GCD unchanged. Likewise, if the suffix GCD does not change at index $i$ either, removing it leaves every suffix GCD unchanged as well. In that case the only effect of the removal is to merge the split positions $i - 1$ and $i$ into a single one, and those two positions are either both valid or both invalid, so the score can only decrease.

Therefore only the indices where the prefix GCD changes or the suffix GCD changes are worth enumerating, and there are at most $O(\log M)$ of them. We call $\textit{mark}$ once forward and once backward to collect the candidate indices, then compute the score with $\textit{calc}$ after removing each candidate, and take the maximum together with the score of the untouched array.

The time complexity is $O(n \times \log^2 M)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$, and $M$ is the maximum value in the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxValidSplits(self, nums: List[int]) -> int:
        n = len(nums)

        def calc(arr):
            m = len(arr)
            pre = [0] * m
            suf = [0] * m

            pre[0] = arr[0]
            for i in range(1, m):
                pre[i] = gcd(pre[i - 1], arr[i])

            suf[-1] = arr[-1]
            for i in range(m - 2, -1, -1):
                suf[i] = gcd(suf[i + 1], arr[i])

            ans = 0
            for i in range(m - 1):
                if pre[i] == suf[i + 1]:
                    ans += 1

            return ans

        def mark(arr):
            pos = [False] * n
            pos[0] = True
            g = arr[0]

            for i in range(1, n):
                ng = gcd(g, arr[i])
                pos[i] = ng != g
                g = ng

            return pos

        pos1 = mark(nums)
        pos2 = mark(nums[::-1])

        ans = calc(nums)

        for i in range(n):
            if pos1[i] or pos2[n - 1 - i]:
                arr = nums[:i] + nums[i + 1 :]
                ans = max(ans, calc(arr))

        return ans
```

#### Java

```java
class Solution {
    public int maxValidSplits(int[] nums) {
        int n = nums.length;

        boolean[] pos1 = mark(nums);

        int[] rev = nums.clone();
        for (int i = 0; i < n / 2; ++i) {
            int t = rev[i];
            rev[i] = rev[n - 1 - i];
            rev[n - 1 - i] = t;
        }

        boolean[] pos2 = mark(rev);

        int ans = calc(nums);

        for (int i = 0; i < n; ++i) {
            if (pos1[i] || pos2[n - 1 - i]) {
                int[] arr = new int[n - 1];
                for (int j = 0, k = 0; j < n; ++j) {
                    if (j != i) {
                        arr[k++] = nums[j];
                    }
                }
                ans = Math.max(ans, calc(arr));
            }
        }

        return ans;
    }

    private boolean[] mark(int[] nums) {
        int n = nums.length;
        boolean[] pos = new boolean[n];

        pos[0] = true;
        int g = nums[0];

        for (int i = 1; i < n; ++i) {
            int ng = gcd(g, nums[i]);
            pos[i] = ng != g;
            g = ng;
        }

        return pos;
    }

    private int calc(int[] arr) {
        int n = arr.length;
        int[] pre = new int[n];
        int[] suf = new int[n];

        pre[0] = arr[0];
        for (int i = 1; i < n; ++i) {
            pre[i] = gcd(pre[i - 1], arr[i]);
        }

        suf[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            suf[i] = gcd(suf[i + 1], arr[i]);
        }

        int ans = 0;
        for (int i = 0; i + 1 < n; ++i) {
            if (pre[i] == suf[i + 1]) {
                ++ans;
            }
        }

        return ans;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxValidSplits(vector<int>& nums) {
        int n = nums.size();

        vector<bool> pos1 = mark(nums);

        vector<int> rev = nums;
        reverse(rev.begin(), rev.end());
        vector<bool> pos2 = mark(rev);

        int ans = calc(nums);

        for (int i = 0; i < n; ++i) {
            if (pos1[i] || pos2[n - 1 - i]) {
                vector<int> arr;
                arr.reserve(n - 1);

                for (int j = 0; j < n; ++j) {
                    if (i != j) {
                        arr.push_back(nums[j]);
                    }
                }

                ans = max(ans, calc(arr));
            }
        }

        return ans;
    }

private:
    vector<bool> mark(const vector<int>& nums) {
        int n = nums.size();
        vector<bool> pos(n);

        pos[0] = true;
        int g = nums[0];

        for (int i = 1; i < n; ++i) {
            int ng = gcd(g, nums[i]);
            pos[i] = ng != g;
            g = ng;
        }

        return pos;
    }

    int calc(const vector<int>& arr) {
        int n = arr.size();
        vector<int> pre(n), suf(n);

        pre[0] = arr[0];
        for (int i = 1; i < n; ++i) {
            pre[i] = gcd(pre[i - 1], arr[i]);
        }

        suf[n - 1] = arr[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            suf[i] = gcd(suf[i + 1], arr[i]);
        }

        int ans = 0;
        for (int i = 0; i + 1 < n; ++i) {
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

	pos1 := mark(nums)

	rev := make([]int, n)
	for i := 0; i < n; i++ {
		rev[i] = nums[n-1-i]
	}
	pos2 := mark(rev)

	ans := calc(nums)

	for i := 0; i < n; i++ {
		if pos1[i] || pos2[n-1-i] {
			arr := make([]int, 0, n-1)
			for j := 0; j < n; j++ {
				if i != j {
					arr = append(arr, nums[j])
				}
			}
			ans = max(ans, calc(arr))
		}
	}

	return ans
}

func mark(nums []int) []bool {
	n := len(nums)
	pos := make([]bool, n)

	pos[0] = true
	g := nums[0]

	for i := 1; i < n; i++ {
		ng := gcd(g, nums[i])
		pos[i] = ng != g
		g = ng
	}

	return pos
}

func calc(arr []int) int {
	n := len(arr)
	pre := make([]int, n)
	suf := make([]int, n)

	pre[0] = arr[0]
	for i := 1; i < n; i++ {
		pre[i] = gcd(pre[i-1], arr[i])
	}

	suf[n-1] = arr[n-1]
	for i := n - 2; i >= 0; i-- {
		suf[i] = gcd(suf[i+1], arr[i])
	}

	ans := 0
	for i := 0; i+1 < n; i++ {
		if pre[i] == suf[i+1] {
			ans++
		}
	}

	return ans
}

func gcd(a, b int) int {
	for b != 0 {
		a, b = b, a%b
	}
	return a
}
```

#### TypeScript

```ts
function maxValidSplits(nums: number[]): number {
    const n = nums.length;

    const pos1 = mark(nums);

    const rev = [...nums].reverse();
    const pos2 = mark(rev);

    let ans = calc(nums);

    for (let i = 0; i < n; ++i) {
        if (pos1[i] || pos2[n - 1 - i]) {
            const arr = nums.slice(0, i).concat(nums.slice(i + 1));
            ans = Math.max(ans, calc(arr));
        }
    }

    return ans;
}

function mark(nums: number[]): boolean[] {
    const n = nums.length;
    const pos = Array(n).fill(false);

    pos[0] = true;
    let g = nums[0];

    for (let i = 1; i < n; ++i) {
        const ng = gcd(g, nums[i]);
        pos[i] = ng !== g;
        g = ng;
    }

    return pos;
}

function calc(arr: number[]): number {
    const n = arr.length;
    const pre = Array(n);
    const suf = Array(n);

    pre[0] = arr[0];
    for (let i = 1; i < n; ++i) {
        pre[i] = gcd(pre[i - 1], arr[i]);
    }

    suf[n - 1] = arr[n - 1];
    for (let i = n - 2; i >= 0; --i) {
        suf[i] = gcd(suf[i + 1], arr[i]);
    }

    let ans = 0;
    for (let i = 0; i + 1 < n; ++i) {
        if (pre[i] === suf[i + 1]) {
            ++ans;
        }
    }

    return ans;
}

function gcd(a: number, b: number): number {
    while (b !== 0) {
        [a, b] = [b, a % b];
    }
    return a;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
