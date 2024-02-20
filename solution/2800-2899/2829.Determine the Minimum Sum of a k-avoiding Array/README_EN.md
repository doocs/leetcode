# [2829. Determine the Minimum Sum of a k-avoiding Array](https://leetcode.com/problems/determine-the-minimum-sum-of-a-k-avoiding-array)

[中文文档](/solution/2800-2899/2829.Determine%20the%20Minimum%20Sum%20of%20a%20k-avoiding%20Array/README.md)

<!-- tags:Greedy,Math -->

## Description

<p>You are given two integers,&nbsp;<code>n</code> and <code>k</code>.</p>

<p>An array of <strong>distinct</strong> positive integers is called a <b>k-avoiding</b> array if there does not exist any pair of distinct elements that sum to <code>k</code>.</p>

<p>Return <em>the <strong>minimum</strong> possible sum of a k-avoiding array of length </em><code>n</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 5, k = 4
<strong>Output:</strong> 18
<strong>Explanation:</strong> Consider the k-avoiding array [1,2,4,5,6], which has a sum of 18.
It can be proven that there is no k-avoiding array with a sum less than 18.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, k = 6
<strong>Output:</strong> 3
<strong>Explanation:</strong> We can construct the array [1,2], which has a sum of 3.
It can be proven that there is no k-avoiding array with a sum less than 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n, k &lt;= 50</code></li>
</ul>

## Solutions

### Solution 1: Greedy + Simulation

We start from the positive integer $i=1$, and judge whether $i$ can be added to the array in turn. If it can be added, we add $i$ to the array, accumulate it to the answer, and then mark $k-i$ as visited, indicating that $k-i$ cannot be added to the array. The loop continues until the length of the array is $n$.

The time complexity is $O(n^2)$, and the space complexity is $O(n^2)$. Here, $n$ is the length of the array.

<!-- tabs:start -->

```python
class Solution:
    def minimumSum(self, n: int, k: int) -> int:
        s, i = 0, 1
        vis = set()
        for _ in range(n):
            while i in vis:
                i += 1
            vis.add(i)
            vis.add(k - i)
            s += i
        return s
```

```java
class Solution {
    public int minimumSum(int n, int k) {
        int s = 0, i = 1;
        boolean[] vis = new boolean[k + n * n + 1];
        while (n-- > 0) {
            while (vis[i]) {
                ++i;
            }
            vis[i] = true;
            if (k >= i) {
                vis[k - i] = true;
            }
            s += i;
        }
        return s;
    }
}
```

```cpp
class Solution {
public:
    int minimumSum(int n, int k) {
        int s = 0, i = 1;
        bool vis[k + n * n + 1];
        memset(vis, false, sizeof(vis));
        while (n--) {
            while (vis[i]) {
                ++i;
            }
            vis[i] = true;
            if (k >= i) {
                vis[k - i] = true;
            }
            s += i;
        }
        return s;
    }
};
```

```go
func minimumSum(n int, k int) int {
	s, i := 0, 1
	vis := make([]bool, k+n*n+1)
	for ; n > 0; n-- {
		for vis[i] {
			i++
		}
		vis[i] = true
		if k >= i {
			vis[k-i] = true
		}
		s += i
	}
	return s
}
```

```ts
function minimumSum(n: number, k: number): number {
    let s = 0;
    let i = 1;
    const vis: boolean[] = Array(n * n + k + 1);
    while (n--) {
        while (vis[i]) {
            ++i;
        }
        vis[i] = true;
        if (k >= i) {
            vis[k - i] = true;
        }
        s += i;
    }
    return s;
}
```

<!-- tabs:end -->

<!-- end -->
