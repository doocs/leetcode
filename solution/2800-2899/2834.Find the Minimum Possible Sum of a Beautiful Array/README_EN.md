# [2834. Find the Minimum Possible Sum of a Beautiful Array](https://leetcode.com/problems/find-the-minimum-possible-sum-of-a-beautiful-array)

[中文文档](/solution/2800-2899/2834.Find%20the%20Minimum%20Possible%20Sum%20of%20a%20Beautiful%20Array/README.md)

## Description

<p>You are given positive integers <code>n</code> and <code>target</code>.</p>

<p>An array <code>nums</code> is <strong>beautiful</strong> if it meets the following conditions:</p>

<ul>
	<li><code>nums.length == n</code>.</li>
	<li><code>nums</code> consists of pairwise <strong>distinct</strong> <strong>positive</strong> integers.</li>
	<li>There doesn&#39;t exist two <strong>distinct</strong> indices, <code>i</code> and <code>j</code>, in the range <code>[0, n - 1]</code>, such that <code>nums[i] + nums[j] == target</code>.</li>
</ul>

<p>Return <em>the <strong>minimum</strong> possible sum that a beautiful array could have modulo </em><code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> n = 2, target = 3
<strong>Output:</strong> 4
<strong>Explanation:</strong> We can see that nums = [1,3] is beautiful.
- The array nums has length n = 2.
- The array nums consists of pairwise distinct positive integers.
- There doesn&#39;t exist two distinct indices, i and j, with nums[i] + nums[j] == 3.
It can be proven that 4 is the minimum possible sum that a beautiful array could have.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 3, target = 3
<strong>Output:</strong> 8
<strong>Explanation:</strong> We can see that nums = [1,3,4] is beautiful.
- The array nums has length n = 3.
- The array nums consists of pairwise distinct positive integers.
- There doesn&#39;t exist two distinct indices, i and j, with nums[i] + nums[j] == 3.
It can be proven that 8 is the minimum possible sum that a beautiful array could have.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 1, target = 1
<strong>Output:</strong> 1
<strong>Explanation:</strong> We can see, that nums = [1] is beautiful.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= target &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Greedy + Hash Table

We start from the positive integer $i=1$, and judge whether $i$ can be added to the array in turn. If it can be added, we add $i$ to the array, accumulate it to the answer, and then mark $target-i$ as visited, indicating that $target-i$ cannot be added to the array. The loop continues until the length of the array is $n$.

The time complexity is $O(n + target)$, and the space complexity is $O(n + target)$. Here, $n$ is the length of the array.

<!-- tabs:start -->

```python
class Solution:
    def minimumPossibleSum(self, n: int, target: int) -> int:
        vis = set()
        ans = 0
        i = 1
        for _ in range(n):
            while i in vis:
                i += 1
            ans += i
            vis.add(target - i)
            i += 1
        return ans
```

```java
class Solution {
    public long minimumPossibleSum(int n, int target) {
        boolean[] vis = new boolean[n + target];
        long ans = 0;
        for (int i = 1; n > 0; --n, ++i) {
            while (vis[i]) {
                ++i;
            }
            ans += i;
            if (target >= i) {
                vis[target - i] = true;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long minimumPossibleSum(int n, int target) {
        bool vis[n + target];
        memset(vis, false, sizeof(vis));
        long long ans = 0;
        for (int i = 1; n; ++i, --n) {
            while (vis[i]) {
                ++i;
            }
            ans += i;
            if (target >= i) {
                vis[target - i] = true;
            }
        }
        return ans;
    }
};
```

```go
func minimumPossibleSum(n int, target int) (ans int64) {
	vis := make([]bool, n+target)
	for i := 1; n > 0; i, n = i+1, n-1 {
		for vis[i] {
			i++
		}
		ans += int64(i)
		if target >= i {
			vis[target-i] = true
		}
	}
	return
}
```

```ts
function minimumPossibleSum(n: number, target: number): number {
    const vis: boolean[] = Array(n + target).fill(false);
    let ans = 0;
    for (let i = 1; n; ++i, --n) {
        while (vis[i]) {
            ++i;
        }
        ans += i;
        if (target >= i) {
            vis[target - i] = true;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
