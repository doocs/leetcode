# [1658. Minimum Operations to Reduce X to Zero](https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero)

[中文文档](/solution/1600-1699/1658.Minimum%20Operations%20to%20Reduce%20X%20to%20Zero/README.md)

## Description

<p>You are given an integer array <code>nums</code> and an integer <code>x</code>. In one operation, you can either remove the leftmost or the rightmost element from the array <code>nums</code> and subtract its value from <code>x</code>. Note that this <strong>modifies</strong> the array for future operations.</p>

<p>Return <em>the <strong>minimum number</strong> of operations to reduce </em><code>x</code> <em>to <strong>exactly</strong></em> <code>0</code> <em>if it is possible</em><em>, otherwise, return </em><code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,4,2,3], x = 5
<strong>Output:</strong> 2
<strong>Explanation:</strong> The optimal solution is to remove the last two elements to reduce x to zero.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,6,7,8,9], x = 4
<strong>Output:</strong> -1
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,20,1,1,3], x = 10
<strong>Output:</strong> 5
<strong>Explanation:</strong> The optimal solution is to remove the last three elements and the first two elements (5 operations in total) to reduce x to zero.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= x &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

### Solution 1: Hash Table + Prefix Sum

We can transform the problem into finding the maximum length of a continuous subarray in the middle, such that the sum of the subarray is $x = sum(nums) - x$.

Define a hash table `vis`, where `vis[s]` represents the smallest index with a prefix sum of $s$.

Traverse the array `nums`. For each element $nums[i]$, we first add $nums[i]$ to the prefix sum $s$. If $s$ does not exist in the hash table, we add it to the hash table, and its value is the current index $i$. Then we check whether $s - x$ exists in the hash table. If it does, it means that there exists an index $j$ such that the sum of $nums[j + 1,..i]$ is $x$. At this time, we update the minimum value of the answer, that is, $ans = min(ans, n - (i - j))$.

At the end of the traversal, if no subarray meets the condition, return $-1$, otherwise return $ans$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array `nums`.

<!-- tabs:start -->

```python
class Solution:
    def minOperations(self, nums: List[int], x: int) -> int:
        x = sum(nums) - x
        vis = {0: -1}
        ans = inf
        s, n = 0, len(nums)
        for i, v in enumerate(nums):
            s += v
            if s not in vis:
                vis[s] = i
            if s - x in vis:
                j = vis[s - x]
                ans = min(ans, n - (i - j))
        return -1 if ans == inf else ans
```

```java
class Solution {
    public int minOperations(int[] nums, int x) {
        x = -x;
        for (int v : nums) {
            x += v;
        }
        Map<Integer, Integer> vis = new HashMap<>();
        vis.put(0, -1);
        int n = nums.length;
        int ans = 1 << 30;
        for (int i = 0, s = 0; i < n; ++i) {
            s += nums[i];
            vis.putIfAbsent(s, i);
            if (vis.containsKey(s - x)) {
                int j = vis.get(s - x);
                ans = Math.min(ans, n - (i - j));
            }
        }
        return ans == 1 << 30 ? -1 : ans;
    }
}
```

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums, int x) {
        x = accumulate(nums.begin(), nums.end(), 0) - x;
        unordered_map<int, int> vis{{0, -1}};
        int n = nums.size();
        int ans = 1 << 30;
        for (int i = 0, s = 0; i < n; ++i) {
            s += nums[i];
            if (!vis.count(s)) {
                vis[s] = i;
            }
            if (vis.count(s - x)) {
                int j = vis[s - x];
                ans = min(ans, n - (i - j));
            }
        }
        return ans == 1 << 30 ? -1 : ans;
    }
};
```

```go
func minOperations(nums []int, x int) int {
	x = -x
	for _, v := range nums {
		x += v
	}
	vis := map[int]int{0: -1}
	ans := 1 << 30
	s, n := 0, len(nums)
	for i, v := range nums {
		s += v
		if _, ok := vis[s]; !ok {
			vis[s] = i
		}
		if j, ok := vis[s-x]; ok {
			ans = min(ans, n-(i-j))
		}
	}
	if ans == 1<<30 {
		return -1
	}
	return ans
}
```

```ts
function minOperations(nums: number[], x: number): number {
    x = nums.reduce((a, b) => a + b, 0) - x;
    const vis = new Map();
    vis.set(0, -1);
    const n = nums.length;
    let ans = 1 << 30;
    for (let i = 0, s = 0; i < n; ++i) {
        s += nums[i];
        if (!vis.has(s)) {
            vis.set(s, i);
        }
        if (vis.has(s - x)) {
            const j = vis.get(s - x);
            ans = Math.min(ans, n - (i - j));
        }
    }
    return ans == 1 << 30 ? -1 : ans;
}
```

```rust
impl Solution {
    pub fn min_operations(nums: Vec<i32>, x: i32) -> i32 {
        let n = nums.len();
        let target = nums.iter().sum::<i32>() - x;
        if target < 0 {
            return -1;
        }
        let mut ans = i32::MAX;
        let mut sum = 0;
        let mut i = 0;
        for j in 0..n {
            sum += nums[j];
            while sum > target {
                sum -= nums[i];
                i += 1;
            }
            if sum == target {
                ans = ans.min((n - 1 - (j - i)) as i32);
            }
        }
        if ans == i32::MAX {
            return -1;
        }
        ans
    }
}
```

```c
#define min(a, b) (((a) < (b)) ? (a) : (b))

int minOperations(int* nums, int numsSize, int x) {
    int target = -x;
    for (int i = 0; i < numsSize; i++) {
        target += nums[i];
    }
    if (target < 0) {
        return -1;
    }
    int ans = INT_MAX;
    int sum = 0;
    int i = 0;
    for (int j = 0; j < numsSize; j++) {
        sum += nums[j];
        while (sum > target) {
            sum -= nums[i++];
        }
        if (sum == target) {
            ans = min(ans, numsSize - 1 - (j - i));
        }
    }
    if (ans == INT_MAX) {
        return -1;
    }
    return ans;
}
```

<!-- tabs:end -->

### Solution 2: Two Pointers

Similar to Solution 1, we need to find a subarray such that the sum of the subarray is $x = sum(nums) - x$.

Define two pointers $j$ and $i$, initially $i = j = 0$. Then we move the pointer $i$ to the right, add $nums[i]$ to the prefix sum $s$. If $s > x$, then we move the pointer $j$ to the right in a loop, and subtract $nums[j]$ from the prefix sum $s$, until $s \le x$. If $s = x$, we can update the minimum value of the answer, that is, $ans = min(ans, n - (i - j + 1))$. Continue to move the pointer $i$ to the right, and repeat the above process.

Finally, if no subarray meets the condition, return $-1$, otherwise return $ans$.

The time complexity is $O(n)$, and the space complexity is $O(1)$. Here, $n$ is the length of the array `nums`.

<!-- tabs:start -->

```python
class Solution:
    def minOperations(self, nums: List[int], x: int) -> int:
        x = sum(nums) - x
        ans = inf
        n = len(nums)
        s = j = 0
        for i, v in enumerate(nums):
            s += v
            while j <= i and s > x:
                s -= nums[j]
                j += 1
            if s == x:
                ans = min(ans, n - (i - j + 1))
        return -1 if ans == inf else ans
```

```java
class Solution {
    public int minOperations(int[] nums, int x) {
        x = -x;
        for (int v : nums) {
            x += v;
        }
        int n = nums.length;
        int ans = 1 << 30;
        for (int i = 0, j = 0, s = 0; i < n; ++i) {
            s += nums[i];
            while (j <= i && s > x) {
                s -= nums[j++];
            }
            if (s == x) {
                ans = Math.min(ans, n - (i - j + 1));
            }
        }
        return ans == 1 << 30 ? -1 : ans;
    }
}
```

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums, int x) {
        x = accumulate(nums.begin(), nums.end(), 0) - x;
        int n = nums.size();
        int ans = 1 << 30;
        for (int i = 0, j = 0, s = 0; i < n; ++i) {
            s += nums[i];
            while (j <= i && s > x) {
                s -= nums[j++];
            }
            if (s == x) {
                ans = min(ans, n - (i - j + 1));
            }
        }
        return ans == 1 << 30 ? -1 : ans;
    }
};
```

```go
func minOperations(nums []int, x int) int {
	x = -x
	for _, v := range nums {
		x += v
	}
	ans := 1 << 30
	s, n := 0, len(nums)
	j := 0
	for i, v := range nums {
		s += v
		for j <= i && s > x {
			s -= nums[j]
			j++
		}
		if s == x {
			ans = min(ans, n-(i-j+1))
		}
	}
	if ans == 1<<30 {
		return -1
	}
	return ans
}
```

```ts
function minOperations(nums: number[], x: number): number {
    x = nums.reduce((a, b) => a + b, 0) - x;
    const n = nums.length;
    let ans = 1 << 30;
    for (let i = 0, j = 0, s = 0; i < n; ++i) {
        s += nums[i];
        while (j <= i && s > x) {
            s -= nums[j++];
        }
        if (s == x) {
            ans = Math.min(ans, n - (i - j + 1));
        }
    }
    return ans == 1 << 30 ? -1 : ans;
}
```

<!-- tabs:end -->

<!-- end -->
