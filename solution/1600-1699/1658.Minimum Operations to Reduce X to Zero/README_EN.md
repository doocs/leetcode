# [1658. Minimum Operations to Reduce X to Zero](https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero)

[中文文档](/solution/1600-1699/1658.Minimum%20Operations%20to%20Reduce%20X%20to%20Zero/README.md)

## Description

<p>You are given an integer array <code>nums</code> and an integer <code>x</code>. In one operation, you can either remove the leftmost or the rightmost element from the array <code>nums</code> and subtract its value from <code>x</code>. Note that this <strong>modifies</strong> the array for future operations.</p>

<p>Return <em>the <strong>minimum number</strong> of operations to reduce </em><code>x</code> <em>to <strong>exactly</strong></em> <code>0</code> <em>if it is possible</em><em>, otherwise, return </em><code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,4,2,3], x = 5
<strong>Output:</strong> 2
<strong>Explanation:</strong> The optimal solution is to remove the last two elements to reduce x to zero.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,6,7,8,9], x = 4
<strong>Output:</strong> -1
</pre>

<p><strong>Example 3:</strong></p>

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

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minOperations(self, nums: List[int], x: int) -> int:
        x = sum(nums) - x
        n = len(nums)
        s = 0
        seen = {0: -1}
        ans = inf
        for i, v in enumerate(nums):
            s += v
            if s not in seen:
                seen[s] = i
            if s - x in seen:
                j = seen[s - x]
                ans = min(ans, n - (i - j))
        return -1 if ans == inf else ans
```

### **Java**

```java
class Solution {
    public int minOperations(int[] nums, int x) {
        x = -x;
        for (int v : nums) {
            x += v;
        }
        int s = 0;
        int n = nums.length;
        Map<Integer, Integer> seen = new HashMap<>();
        seen.put(0, -1);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; ++i) {
            s += nums[i];
            seen.putIfAbsent(s, i);
            if (seen.containsKey(s - x)) {
                int j = seen.get(s - x);
                ans = Math.min(ans, n - (i - j));
            }
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
```

### **TypeScript**

```ts
function minOperations(nums: number[], x: number): number {
    const total = nums.reduce((a, c) => a + c, 0);
    if (total < x) return -1;
    // 前缀和 + 哈希表, 求何为total - x的最长子序列
    const n = nums.length;
    const target = total - x;
    let hashMap = new Map();
    hashMap.set(0, -1);
    let pre = 0;
    let ans = -1;
    for (let right = 0; right < n; right++) {
        pre += nums[right];
        if (!hashMap.has(pre)) {
            hashMap.set(pre, right);
        }
        if (hashMap.has(pre - target)) {
            let left = hashMap.get(pre - target);
            ans = Math.max(right - left, ans);
        }
    }
    return ans == -1 ? -1 : n - ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums, int x) {
        x = -x;
        for (int& v : nums) x += v;
        int s = 0, n = nums.size();
        unordered_map<int, int> seen;
        seen[0] = -1;
        int ans = INT_MAX;
        for (int i = 0; i < n; ++i) {
            s += nums[i];
            if (!seen.count(s)) seen[s] = i;
            if (seen.count(s - x)) {
                int j = seen[s - x];
                ans = min(ans, n - (i - j));
            }
        }
        return ans == INT_MAX ? -1 : ans;
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
	s, n := 0, len(nums)
	seen := map[int]int{0: -1}
	ans := math.MaxInt32
	for i, v := range nums {
		s += v
		if _, ok := seen[s]; !ok {
			seen[s] = i
		}
		if j, ok := seen[s-x]; ok {
			ans = min(ans, n-(i-j))
		}
	}
	if ans == math.MaxInt32 {
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

### **...**

```

```

<!-- tabs:end -->
