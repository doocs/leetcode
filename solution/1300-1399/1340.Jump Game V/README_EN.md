---
comments: true
difficulty: Hard
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1340.Jump%20Game%20V/README_EN.md
rating: 1866
source: Weekly Contest 174 Q4
tags:
    - Array
    - Dynamic Programming
    - Sorting
---

<!-- problem:start -->

# [1340. Jump Game V](https://leetcode.com/problems/jump-game-v)

[中文文档](/solution/1300-1399/1340.Jump%20Game%20V/README.md)

## Description

<!-- description:start -->

<p>Given an array of&nbsp;integers <code>arr</code> and an integer <code>d</code>. In one step you can jump from index <code>i</code> to index:</p>

<ul>
	<li><code>i + x</code> where:&nbsp;<code>i + x &lt; arr.length</code> and <code> 0 &lt;&nbsp;x &lt;= d</code>.</li>
	<li><code>i - x</code> where:&nbsp;<code>i - x &gt;= 0</code> and <code> 0 &lt;&nbsp;x &lt;= d</code>.</li>
</ul>

<p>In addition, you can only jump from index <code>i</code> to index <code>j</code>&nbsp;if <code>arr[i] &gt; arr[j]</code> and <code>arr[i] &gt; arr[k]</code> for all indices <code>k</code> between <code>i</code> and <code>j</code> (More formally <code>min(i,&nbsp;j) &lt; k &lt; max(i, j)</code>).</p>

<p>You can choose any index of the array and start jumping. Return <em>the maximum number of indices</em>&nbsp;you can visit.</p>

<p>Notice that you can not jump outside of the array at any time.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/1300-1399/1340.Jump%20Game%20V/images/meta-chart.jpeg" style="width: 633px; height: 419px;" />
<pre>
<strong>Input:</strong> arr = [6,4,14,6,8,13,9,7,10,6,12], d = 2
<strong>Output:</strong> 4
<strong>Explanation:</strong> You can start at index 10. You can jump 10 --&gt; 8 --&gt; 6 --&gt; 7 as shown.
Note that if you start at index 6 you can only jump to index 7. You cannot jump to index 5 because 13 &gt; 9. You cannot jump to index 4 because index 5 is between index 4 and 6 and 13 &gt; 9.
Similarly You cannot jump from index 3 to index 2 or index 1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [3,3,3,3,3], d = 3
<strong>Output:</strong> 1
<strong>Explanation:</strong> You can start at any index. You always cannot jump to any index.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [7,6,5,4,3,2,1], d = 1
<strong>Output:</strong> 7
<strong>Explanation:</strong> Start at index 0. You can visit all the indicies. 
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= d &lt;= arr.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Memoized Search

We design a function $\text{dfs}(i)$ to represent the maximum number of indices that can be visited starting from index $i$. We enumerate all valid jump targets $j$ for $i$, where $i - d \leq j \leq i + d$ and $\text{arr}[i] > \text{arr}[j]$. For each valid $j$, we recursively compute $\text{dfs}(j)$ and take the maximum among them. The final answer is the maximum value of $\text{dfs}(i)$ over all indices $i$.

We can use memoized search to optimize this process, that is, use an array $f$ to record the value of $\text{dfs}$ for each index and avoid repeated computation.

The time complexity is $O(n \times d)$, and the space complexity is $O(n)$, where $n$ is the length of the array $\text{arr}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxJumps(self, arr: List[int], d: int) -> int:
        @cache
        def dfs(i):
            ans = 1
            for j in range(i - 1, -1, -1):
                if i - j > d or arr[j] >= arr[i]:
                    break
                ans = max(ans, 1 + dfs(j))
            for j in range(i + 1, n):
                if j - i > d or arr[j] >= arr[i]:
                    break
                ans = max(ans, 1 + dfs(j))
            return ans

        n = len(arr)
        return max(dfs(i) for i in range(n))
```

#### Java

```java
class Solution {
    private int n;
    private int d;
    private int[] arr;
    private Integer[] f;

    public int maxJumps(int[] arr, int d) {
        n = arr.length;
        this.d = d;
        this.arr = arr;
        f = new Integer[n];
        int ans = 1;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, dfs(i));
        }
        return ans;
    }

    private int dfs(int i) {
        if (f[i] != null) {
            return f[i];
        }
        int ans = 1;
        for (int j = i - 1; j >= 0; --j) {
            if (i - j > d || arr[j] >= arr[i]) {
                break;
            }
            ans = Math.max(ans, 1 + dfs(j));
        }
        for (int j = i + 1; j < n; ++j) {
            if (j - i > d || arr[j] >= arr[i]) {
                break;
            }
            ans = Math.max(ans, 1 + dfs(j));
        }
        return f[i] = ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxJumps(vector<int>& arr, int d) {
        int n = arr.size();
        int f[n];
        memset(f, 0, sizeof(f));
        auto dfs = [&](this auto&& dfs, int i) -> int {
            if (f[i]) {
                return f[i];
            }
            int ans = 1;
            for (int j = i - 1; j >= 0; --j) {
                if (i - j > d || arr[j] >= arr[i]) {
                    break;
                }
                ans = max(ans, 1 + dfs(j));
            }
            for (int j = i + 1; j < n; ++j) {
                if (j - i > d || arr[j] >= arr[i]) {
                    break;
                }
                ans = max(ans, 1 + dfs(j));
            }
            return f[i] = ans;
        };
        int ans = 1;
        for (int i = 0; i < n; ++i) {
            ans = max(ans, dfs(i));
        }
        return ans;
    }
};
```

#### Go

```go
func maxJumps(arr []int, d int) (ans int) {
	n := len(arr)
	f := make([]int, n)
	var dfs func(int) int
	dfs = func(i int) int {
		if f[i] != 0 {
			return f[i]
		}
		ans := 1
		for j := i - 1; j >= 0; j-- {
			if i-j > d || arr[j] >= arr[i] {
				break
			}
			ans = max(ans, 1+dfs(j))
		}
		for j := i + 1; j < n; j++ {
			if j-i > d || arr[j] >= arr[i] {
				break
			}
			ans = max(ans, 1+dfs(j))
		}
		f[i] = ans
		return ans
	}
	for i := 0; i < n; i++ {
		ans = max(ans, dfs(i))
	}
	return
}
```

#### TypeScript

```ts
function maxJumps(arr: number[], d: number): number {
    const n = arr.length;
    const f: number[] = new Array(n).fill(0);
    const dfs = (i: number): number => {
        if (f[i] !== 0) {
            return f[i];
        }
        let ans = 1;
        for (let j = i - 1; j >= 0; j--) {
            if (i - j > d || arr[j] >= arr[i]) {
                break;
            }
            ans = Math.max(ans, 1 + dfs(j));
        }
        for (let j = i + 1; j < n; j++) {
            if (j - i > d || arr[j] >= arr[i]) {
                break;
            }
            ans = Math.max(ans, 1 + dfs(j));
        }
        f[i] = ans;
        return ans;
    };
    let ans = 0;
    for (let i = 0; i < n; i++) {
        ans = Math.max(ans, dfs(i));
    }
    return ans;
}
```

#### Rust

```rust
impl Solution {
    pub fn max_jumps(arr: Vec<i32>, d: i32) -> i32 {
        fn dfs(i: usize, n: usize, d: usize, arr: &[i32], f: &mut Vec<i32>) -> i32 {
            if f[i] != 0 {
                return f[i];
            }
            let mut ans = 1;
            let mut j = (i as isize) - 1;
            while j >= 0 {
                if i - (j as usize) > d || arr[j as usize] >= arr[i] {
                    break;
                }
                ans = ans.max(1 + dfs(j as usize, n, d, arr, f));
                j -= 1;
            }
            j = (i as isize) + 1;
            while (j as usize) < n {
                if j as usize - i > d || arr[j as usize] >= arr[i] {
                    break;
                }
                ans = ans.max(1 + dfs(j as usize, n, d, arr, f));
                j += 1;
            }
            f[i] = ans;
            ans
        }
        let n = arr.len();
        let d = d as usize;
        let mut f = vec![0; n];
        let mut ans = 0;
        for i in 0..n {
            ans = ans.max(dfs(i, n, d, &arr, &mut f));
        }
        ans
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Sorting + Dynamic Programming

We can pair each element $x$ in the array $\text{arr}$ with its index $i$ to form a tuple $(x, i)$, and sort these tuples in ascending order by $x$.

Next, define $f[i]$ to be the maximum number of indices that can be visited starting from index $i$. Initially, $f[i] = 1$, meaning each index can be visited alone as a single jump.

We enumerate $i$ in the order of the tuples $(x, i)$, and enumerate all valid jump targets $j$ for $i$, namely $i - d \leq j \leq i + d$, with $\text{arr}[i] > \text{arr}[j]$. For each valid $j$, we can update $f[i]$, namely $f[i] = \max(f[i], 1 + f[j])$.

The final answer is $\max_{0 \leq i < n} f[i]$.

The time complexity is $O(n \log n + n \times d)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\text{arr}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def maxJumps(self, arr: List[int], d: int) -> int:
        n = len(arr)
        f = [1] * n
        for x, i in sorted(zip(arr, range(n))):
            for j in range(i - 1, -1, -1):
                if i - j > d or arr[j] >= x:
                    break
                f[i] = max(f[i], 1 + f[j])
            for j in range(i + 1, n):
                if j - i > d or arr[j] >= x:
                    break
                f[i] = max(f[i], 1 + f[j])
        return max(f)
```

#### Java

```java
class Solution {
    public int maxJumps(int[] arr, int d) {
        int n = arr.length;
        Integer[] idx = new Integer[n];
        Arrays.setAll(idx, i -> i);
        Arrays.sort(idx, (i, j) -> arr[i] - arr[j]);
        int[] f = new int[n];
        Arrays.fill(f, 1);
        int ans = 0;
        for (int i : idx) {
            for (int j = i - 1; j >= 0; --j) {
                if (i - j > d || arr[j] >= arr[i]) {
                    break;
                }
                f[i] = Math.max(f[i], 1 + f[j]);
            }
            for (int j = i + 1; j < n; ++j) {
                if (j - i > d || arr[j] >= arr[i]) {
                    break;
                }
                f[i] = Math.max(f[i], 1 + f[j]);
            }
            ans = Math.max(ans, f[i]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int maxJumps(vector<int>& arr, int d) {
        int n = arr.size();
        vector<int> idx(n);
        iota(idx.begin(), idx.end(), 0);
        sort(idx.begin(), idx.end(), [&](int i, int j) { return arr[i] < arr[j]; });
        vector<int> f(n, 1);
        for (int i : idx) {
            for (int j = i - 1; j >= 0; --j) {
                if (i - j > d || arr[j] >= arr[i]) {
                    break;
                }
                f[i] = max(f[i], 1 + f[j]);
            }
            for (int j = i + 1; j < n; ++j) {
                if (j - i > d || arr[j] >= arr[i]) {
                    break;
                }
                f[i] = max(f[i], 1 + f[j]);
            }
        }
        return ranges::max(f);
    }
};
```

#### Go

```go
func maxJumps(arr []int, d int) int {
	n := len(arr)
	idx := make([]int, n)
	f := make([]int, n)
	for i := range f {
		idx[i] = i
		f[i] = 1
	}
	sort.Slice(idx, func(i, j int) bool { return arr[idx[i]] < arr[idx[j]] })
	for _, i := range idx {
		for j := i - 1; j >= 0; j-- {
			if i-j > d || arr[j] >= arr[i] {
				break
			}
			f[i] = max(f[i], 1+f[j])
		}
		for j := i + 1; j < n; j++ {
			if j-i > d || arr[j] >= arr[i] {
				break
			}
			f[i] = max(f[i], 1+f[j])
		}
	}
	return slices.Max(f)
}
```

#### TypeScript

```ts
function maxJumps(arr: number[], d: number): number {
    const n = arr.length;
    const f: number[] = new Array(n).fill(1);
    const idx: number[] = Array.from({ length: n }, (_, i) => i);
    idx.sort((a, b) => arr[a] - arr[b]);
    for (const i of idx) {
        for (let j = i - 1; j >= 0; j--) {
            if (i - j > d || arr[j] >= arr[i]) {
                break;
            }
            f[i] = Math.max(f[i], 1 + f[j]);
        }
        for (let j = i + 1; j < n; j++) {
            if (j - i > d || arr[j] >= arr[i]) {
                break;
            }
            f[i] = Math.max(f[i], 1 + f[j]);
        }
    }
    return Math.max(...f);
}
```

#### Rust

```rust
impl Solution {
    pub fn max_jumps(arr: Vec<i32>, d: i32) -> i32 {
        let n = arr.len();
        let d = d as usize;

        let mut idx: Vec<usize> = (0..n).collect();
        idx.sort_by_key(|&i| arr[i]);

        let mut f = vec![1; n];

        for &i in &idx {
            let mut j = i as i32 - 1;
            while j >= 0 {
                let k = j as usize;

                if i - k > d || arr[k] >= arr[i] {
                    break;
                }

                f[i] = f[i].max(1 + f[k]);
                j -= 1;
            }

            let mut j = i + 1;
            while j < n {
                if j - i > d || arr[j] >= arr[i] {
                    break;
                }

                f[i] = f[i].max(1 + f[j]);
                j += 1;
            }
        }

        *f.iter().max().unwrap()
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
