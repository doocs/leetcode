# [1827. 最少操作使数组递增](https://leetcode.cn/problems/minimum-operations-to-make-the-array-increasing)

[English Version](/solution/1800-1899/1827.Minimum%20Operations%20to%20Make%20the%20Array%20Increasing/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> （<strong>下标从 0 开始</strong>）。每一次操作中，你可以选择数组中一个元素，并将它增加 <code>1</code> 。</p>

<ul>
	<li>比方说，如果 <code>nums = [1,2,3]</code> ，你可以选择增加 <code>nums[1]</code> 得到 <code>nums = [1,<b>3</b>,3]</code> 。</li>
</ul>

<p>请你返回使 <code>nums</code> <strong>严格递增</strong> 的 <strong>最少</strong> 操作次数。</p>

<p>我们称数组 <code>nums</code> 是 <strong>严格递增的</strong> ，当它满足对于所有的 <code>0 &lt;= i &lt; nums.length - 1</code> 都有 <code>nums[i] &lt; nums[i+1]</code> 。一个长度为 <code>1</code> 的数组是严格递增的一种特殊情况。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre><b>输入：</b>nums = [1,1,1]
<b>输出：</b>3
<b>解释：</b>你可以进行如下操作：
1) 增加 nums[2] ，数组变为 [1,1,<strong>2</strong>] 。
2) 增加 nums[1] ，数组变为 [1,<strong>2</strong>,2] 。
3) 增加 nums[2] ，数组变为 [1,2,<strong>3</strong>] 。
</pre>

<p><strong>示例 2：</strong></p>

<pre><b>输入：</b>nums = [1,5,2,4,1]
<b>输出：</b>14
</pre>

<p><strong>示例 3：</strong></p>

<pre><b>输入：</b>nums = [8]
<b>输出：</b>0
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 5000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：一次遍历**

我们用变量 $mx$ 记录当前严格递增数组的最大值，初始时 $mx = 0$。

从左到右遍历数组 `nums`，对于当前遍历到的元素 $v$，如果 $v \lt mx + 1$，那么我们需要将其增加到 $mx + 1$，这样才能保证数组严格递增。因此，我们此次需要进行的操作次数为 $max(0, mx + 1 - v)$，累加到答案中，然后更新 $mx=max(mx + 1, v)$。继续遍历下一个元素，直到遍历完整个数组。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minOperations(self, nums: List[int]) -> int:
        ans = mx = 0
        for v in nums:
            ans += max(0, mx + 1 - v)
            mx = max(mx + 1, v)
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minOperations(int[] nums) {
        int ans = 0, mx = 0;
        for (int v : nums) {
            ans += Math.max(0, mx + 1 - v);
            mx = Math.max(mx + 1, v);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums) {
        int ans = 0, mx = 0;
        for (int& v : nums) {
            ans += max(0, mx + 1 - v);
            mx = max(mx + 1, v);
        }
        return ans;
    }
};
```

### **Go**

```go
func minOperations(nums []int) (ans int) {
	mx := 0
	for _, v := range nums {
		ans += max(0, mx+1-v)
		mx = max(mx+1, v)
	}
	return
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **C#**

```cs
public class Solution {
    public int MinOperations(int[] nums) {
        int ans = 0, mx = 0;
        foreach (int v in nums) {
            ans += Math.Max(0, mx + 1 - v);
            mx = Math.Max(mx + 1, v);
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function minOperations(nums: number[]): number {
    let ans = 0;
    let max = 0;
    for (const v of nums) {
        ans += Math.max(0, max + 1 - v);
        max = Math.max(max + 1, v);
    }
    return ans;
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_operations(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        let mut max = 0;
        for &v in nums.iter() {
            ans += 0.max(max + 1 - v);
            max = v.max(max + 1);
        }
        ans
    }
}
```

### **C**

```c
#define max(a, b) (((a) > (b)) ? (a) : (b))

int minOperations(int *nums, int numsSize) {
    int ans = 0;
    int mx = 0;
    for (int i = 0; i < numsSize; i++) {
        ans += max(0, mx + 1 - nums[i]);
        mx = max(mx + 1, nums[i]);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
