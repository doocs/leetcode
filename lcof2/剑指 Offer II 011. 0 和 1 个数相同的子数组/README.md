# [剑指 Offer II 011. 0 和 1 个数相同的子数组](https://leetcode-cn.com/problems/A1NYOS)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个二进制数组 <code>nums</code> , 找到含有相同数量的 <code>0</code> 和 <code>1</code> 的最长连续子数组，并返回该子数组的长度。</p>

<p>&nbsp;</p>

<p><strong>示例 1:</strong></p>

<pre>
<strong>输入:</strong> nums = [0,1]
<strong>输出:</strong> 2
<strong>说明:</strong> [0, 1] 是具有相同数量 0 和 1 的最长连续子数组。</pre>

<p><strong>示例 2:</strong></p>

<pre>
<strong>输入:</strong> nums = [0,1,0]
<strong>输出:</strong> 2
<strong>说明:</strong> [0, 1] (或 [1, 0]) 是具有相同数量 0 和 1 的最长连续子数组。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> 不是 <code>0</code> 就是 <code>1</code></li>
</ul>

<p>&nbsp;</p>

<p><meta charset="UTF-8" />注意：本题与主站 525&nbsp;题相同：&nbsp;<a href="https://leetcode-cn.com/problems/contiguous-array/">https://leetcode-cn.com/problems/contiguous-array/</a></p>


## 解法

<!-- 这里可写通用的实现逻辑 -->

前缀和加哈希表，把 0 当作 -1 处理，题目变成求和为 0 的子数组

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findMaxLength(self, nums: List[int]) -> int:
        m = {0: -1}
        ans, sum = 0, 0
        for i, num in enumerate(nums):
            sum += 1 if num == 1 else -1
            if sum in m:
                ans = max(ans, i - m[sum])
            else:
                m[sum] = i
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> m = new HashMap<>();
        m.put(0, -1);
        int ans = 0, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i] == 1 ? 1 : -1;
            if (m.containsKey(sum)) {
                ans = Math.max(ans, i - m.get(sum));
            } else {
                m.put(sum, i);
            }
        }
        return ans;
    }
}
```

### **Go**

```go
func findMaxLength(nums []int) int {
	m := map[int]int{0: -1}
	ans, sum := 0, 0
	for i, num := range nums {
		if num == 0 {
			sum -= 1
		} else {
			sum += 1
		}
		if j, ok := m[sum]; ok {
			ans = max(ans, i-j)
		} else {
			m[sum] = i
		}
	}
	return ans
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **...**

```

```

<!-- tabs:end -->
