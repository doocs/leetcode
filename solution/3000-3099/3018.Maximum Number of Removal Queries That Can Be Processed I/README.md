# [3018. Maximum Number of Removal Queries That Can Be Processed I](https://leetcode.cn/problems/maximum-number-of-removal-queries-that-can-be-processed-i)

[English Version](/solution/3000-3099/3018.Maximum%20Number%20of%20Removal%20Queries%20That%20Can%20Be%20Processed%20I/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>You are given a <strong>0-indexed</strong> array <code>nums</code> and a <strong>0-indexed</strong> array <code>queries</code>.</p>

<p>You can do the following operation at the beginning <strong>at most once</strong>:</p>

<ul>
	<li>Replace <code>nums</code> with a <span data-keyword="subsequence-array">subsequence</span> of <code>nums</code>.</li>
</ul>

<p>We start processing queries in the given order; for each query, we do the following:</p>

<ul>
	<li>If the first <strong>and</strong> the last element of <code>nums</code> is <strong>less than</strong> <code>queries[i]</code>, the processing of queries <strong>ends</strong>.</li>
	<li>Otherwise, we choose either the first <strong>or</strong> the last element of <code>nums</code> if it is <strong>greater than or equal to</strong> <code>queries[i]</code>, and we <strong>remove</strong> the chosen element from <code>nums</code>.</li>
</ul>

<p>Return <em>the <strong>maximum</strong> number of queries that can be processed by doing the operation optimally.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5], queries = [1,2,3,4,6]
<strong>Output:</strong> 4
<strong>Explanation:</strong> We don&#39;t do any operation and process the queries as follows:
1- We choose and remove nums[0] since 1 &lt;= 1, then nums becomes [2,3,4,5].
2- We choose and remove nums[0] since 2 &lt;= 2, then nums becomes [3,4,5].
3- We choose and remove nums[0] since 3 &lt;= 3, then nums becomes [4,5].
4- We choose and remove nums[0] since 4 &lt;= 4, then nums becomes [5].
5- We can not choose any elements from nums since they are not greater than or equal to 5.
Hence, the answer is 4.
It can be shown that we can&#39;t process more than 4 queries.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,2], queries = [2,2,3]
<strong>Output:</strong> 3
<strong>Explanation:</strong> We don&#39;t do any operation and process the queries as follows:
1- We choose and remove nums[0] since 2 &lt;= 2, then nums becomes [3,2].
2- We choose and remove nums[1] since 2 &lt;= 2, then nums becomes [3].
3- We choose and remove nums[0] since 3 &lt;= 3, then nums becomes [].
Hence, the answer is 3.
It can be shown that we can&#39;t process more than 3 queries.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,4,3], queries = [4,3,2]
<strong>Output:</strong> 2
<strong>Explanation:</strong> First we replace nums with the subsequence of nums [4,3].
Then we can process the queries as follows:
1- We choose and remove nums[0] since 4 &lt;= 4, then nums becomes [3].
2- We choose and remove nums[0] since 3 &lt;= 3, then nums becomes [].
3- We can not process any more queries since nums is empty.
Hence, the answer is 2.
It can be shown that we can&#39;t process more than 2 queries.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= queries.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i], queries[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

### 方法一：动态规划

我们定义 $f[i][j]$ 表示区间 $[i, j]$ 的数还没有被删除时，我们能够处理的查询的最大数量。

考虑 $f[i][j]$：

-   如果 $i \gt 0$，此时 $f[i][j]$ 的值可以由 $f[i - 1][j]$ 转移而来。如果 $nums[i - 1] \ge queries[f[i - 1][j]]$，那么我们可以选择删除 $nums[i - 1]$，因此我们有 $f[i][j] = f[i - 1][j] + (nums[i - 1] \ge queries[f[i - 1][j]])$。
-   如果 $j + 1 \lt n$，此时 $f[i][j]$ 的值可以由 $f[i][j + 1]$ 转移而来。如果 $nums[j + 1] \ge queries[f[i][j + 1]]$，那么我们可以选择删除 $nums[j + 1]$，因此我们有 $f[i][j] = f[i][j + 1] + (nums[j + 1] \ge queries[f[i][j + 1]])$。
-   如果 $f[i][j] = m$，那么我们就可以直接返回 $m$。

最后的答案即为 $\max\limits_{0 \le i \lt n} f[i][i] + (nums[i] \ge queries[f[i][i]])$。

时间复杂度 $O(n^2)$，空间复杂度 $O(n^2)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

```python
class Solution:
    def maximumProcessableQueries(self, nums: List[int], queries: List[int]) -> int:
        n = len(nums)
        f = [[0] * n for _ in range(n)]
        m = len(queries)
        for i in range(n):
            for j in range(n - 1, i - 1, -1):
                if i:
                    f[i][j] = max(
                        f[i][j], f[i - 1][j] + (nums[i - 1] >= queries[f[i - 1][j]])
                    )
                if j + 1 < n:
                    f[i][j] = max(
                        f[i][j], f[i][j + 1] + (nums[j + 1] >= queries[f[i][j + 1]])
                    )
                if f[i][j] == m:
                    return m
        return max(f[i][i] + (nums[i] >= queries[f[i][i]]) for i in range(n))
```

```java
class Solution {
    public int maximumProcessableQueries(int[] nums, int[] queries) {
        int n = nums.length;
        int[][] f = new int[n][n];
        int m = queries.length;
        for (int i = 0; i < n; ++i) {
            for (int j = n - 1; j >= i; --j) {
                if (i > 0) {
                    f[i][j] = Math.max(f[i][j], f[i - 1][j] + (nums[i - 1] >= queries[f[i - 1][j]] ? 1 : 0));
                }
                if (j + 1 < n) {
                    f[i][j] = Math.max(f[i][j], f[i][j + 1] + (nums[j + 1] >= queries[f[i][j + 1]] ? 1 : 0));
                }
                if (f[i][j] == m) {
                    return m;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = Math.max(ans, f[i][i] + (nums[i] >= queries[f[i][i]] ? 1 : 0));
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maximumProcessableQueries(vector<int>& nums, vector<int>& queries) {
        int n = nums.size();
        int f[n][n];
        memset(f, 0, sizeof(f));
        int m = queries.size();
        for (int i = 0; i < n; ++i) {
            for (int j = n - 1; j >= i; --j) {
                if (i > 0) {
                    f[i][j] = max(f[i][j], f[i - 1][j] + (nums[i - 1] >= queries[f[i - 1][j]] ? 1 : 0));
                }
                if (j + 1 < n) {
                    f[i][j] = max(f[i][j], f[i][j + 1] + (nums[j + 1] >= queries[f[i][j + 1]] ? 1 : 0));
                }
                if (f[i][j] == m) {
                    return m;
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            ans = max(ans, f[i][i] + (nums[i] >= queries[f[i][i]] ? 1 : 0));
        }
        return ans;
    }
};
```

```go
func maximumProcessableQueries(nums []int, queries []int) (ans int) {
	n := len(nums)
	f := make([][]int, n)
	for i := range f {
		f[i] = make([]int, n)
	}
	m := len(queries)
	for i := 0; i < n; i++ {
		for j := n - 1; j >= i; j-- {
			if i > 0 {
				t := 0
				if nums[i-1] >= queries[f[i-1][j]] {
					t = 1
				}
				f[i][j] = max(f[i][j], f[i-1][j]+t)
			}
			if j+1 < n {
				t := 0
				if nums[j+1] >= queries[f[i][j+1]] {
					t = 1
				}
				f[i][j] = max(f[i][j], f[i][j+1]+t)
			}
			if f[i][j] == m {
				return m
			}
		}
	}
	for i := 0; i < n; i++ {
		t := 0
		if nums[i] >= queries[f[i][i]] {
			t = 1
		}
		ans = max(ans, f[i][i]+t)
	}
	return
}
```

```ts
function maximumProcessableQueries(nums: number[], queries: number[]): number {
    const n = nums.length;
    const f: number[][] = Array.from({ length: n }, () => Array.from({ length: n }, () => 0));
    const m = queries.length;
    for (let i = 0; i < n; ++i) {
        for (let j = n - 1; j >= i; --j) {
            if (i > 0) {
                f[i][j] = Math.max(
                    f[i][j],
                    f[i - 1][j] + (nums[i - 1] >= queries[f[i - 1][j]] ? 1 : 0),
                );
            }
            if (j + 1 < n) {
                f[i][j] = Math.max(
                    f[i][j],
                    f[i][j + 1] + (nums[j + 1] >= queries[f[i][j + 1]] ? 1 : 0),
                );
            }
            if (f[i][j] == m) {
                return m;
            }
        }
    }
    let ans = 0;
    for (let i = 0; i < n; ++i) {
        ans = Math.max(ans, f[i][i] + (nums[i] >= queries[f[i][i]] ? 1 : 0));
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
