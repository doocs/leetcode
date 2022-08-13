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

前缀和 + 哈希表。

题目可以转换为求中间连续子数组的最大长度，使得子数组的和为 `sum(nums) - x`。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

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

<!-- 这里可写当前语言的特殊实现逻辑 -->

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
