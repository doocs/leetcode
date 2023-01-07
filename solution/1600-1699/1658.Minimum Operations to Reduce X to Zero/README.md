# [1658. 将 x 减到 0 的最小操作数](https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero)

[English Version](/solution/1600-1699/1658.Minimum%20Operations%20to%20Reduce%20X%20to%20Zero/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> 和一个整数 <code>x</code> 。每一次操作时，你应当移除数组 <code>nums</code> 最左边或最右边的元素，然后从 <code>x</code> 中减去该元素的值。请注意，需要 <strong>修改</strong> 数组以供接下来的操作使用。</p>

<p>如果可以将 <code>x</code> <strong>恰好</strong> 减到 <code>0</code> ，返回<strong> 最小操作数 </strong>；否则，返回 <code>-1</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,1,4,2,3], x = 5
<strong>输出：</strong>2
<strong>解释：</strong>最佳解决方案是移除后两个元素，将 x 减到 0 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,6,7,8,9], x = 4
<strong>输出：</strong>-1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,2,20,1,1,3], x = 10
<strong>输出：</strong>5
<strong>解释：</strong>最佳解决方案是移除后三个元素和前两个元素（总共 5 次操作），将 x 减到 0 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= nums[i] <= 10<sup>4</sup></code></li>
	<li><code>1 <= x <= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：哈希表 + 前缀和**

我们可以将问题转换为求中间连续子数组的最大长度，使得子数组的和为 $x = sum(nums) - x$。

定义一个哈希表 `vis`，其中 `vis[s]` 表示前缀和为 $s$ 的最小下标。

遍历数组 `nums`，对于每个元素 $nums[i]$，我们先将 $nums[i]$ 加到前缀和 $s$ 上，如果哈希表中不存在 $s$，则将其加入哈希表，其值为当前下标 $i$。然后我们判断 $s - x$ 是否在哈希表中，如果存在，则说明存在一个下标 $j$，使得 $nums[j + 1,..i]$ 的和为 $x$，此时我们更新答案的最小值，即 $ans = min(ans, n - (i - j))$。

遍历结束，如果找不到满足条件的子数组，返回 $-1$，否则返回 $ans$。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 `nums` 的长度。

**方法二：双指针**

与方法一类似，我们要找到一个子数组，使得子数组的和为 $x = sum(nums) - x$。

定义两个指针 $j$ 和 $i$，初始时 $i = j = 0$，然后我们向右移动指针 $i$，将 $nums[i]$ 加到前缀和 $s$ 上。如果 $s \gt x$，那么我们循环向右移动指针 $j$，并且将 $nums[j]$ 从前缀和 $s$ 上减去，直到 $s \le x$。如果 $s = x$，我们可以更新答案的最小值，即 $ans = min(ans, n - (i - j + 1))$。继续向右移动指针 $i$，重复上述过程。

最后，如果找不到满足条件的子数组，返回 $-1$，否则返回 $ans$。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

### **C++**

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

### **Go**

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

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
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

func min(a, b int) int {
	if a < b {
		return a
	}
	return b
}
```

### **TypeScript**

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

### **Rust**

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
                ans = ans.min((n - 1 - (j - i)) as i32)
            }
        }
        if ans == i32::MAX {
            return -1;
        }
        ans
    }
}
```

### **C**

```c
#define min(a, b) (((a) < (b)) ? (a) : (b))

int minOperations(int *nums, int numsSize, int x) {
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

### **...**

```

```

<!-- tabs:end -->
