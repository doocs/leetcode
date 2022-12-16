# [1785. 构成特定和需要添加的最少元素](https://leetcode.cn/problems/minimum-elements-to-add-to-form-a-given-sum)

[English Version](/solution/1700-1799/1785.Minimum%20Elements%20to%20Add%20to%20Form%20a%20Given%20Sum/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，和两个整数 <code>limit</code> 与 <code>goal</code> 。数组 <code>nums</code> 有一条重要属性：<code>abs(nums[i]) <= limit</code> 。</p>

<p>返回使数组元素总和等于 <code>goal</code> 所需要向数组中添加的 <strong>最少元素数量</strong> ，添加元素 <strong>不应改变</strong> 数组中 <code>abs(nums[i]) <= limit</code> 这一属性。</p>

<p>注意，如果 <code>x >= 0</code> ，那么 <code>abs(x)</code> 等于 <code>x</code> ；否则，等于 <code>-x</code> 。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,-1,1], limit = 3, goal = -4
<strong>输出：</strong>2
<strong>解释：</strong>可以将 -2 和 -3 添加到数组中，数组的元素总和变为 1 - 1 + 1 - 2 - 3 = -4 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,-10,9,1], limit = 100, goal = 0
<strong>输出：</strong>1
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= nums.length <= 10<sup>5</sup></code></li>
	<li><code>1 <= limit <= 10<sup>6</sup></code></li>
	<li><code>-limit <= nums[i] <= limit</code></li>
	<li><code>-10<sup>9</sup> <= goal <= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：贪心**

我们先计算数组元素总和 $s$，然后计算 $s$ 与 $goal$ 的差值 $d$。

那么需要添加的元素数量为 $d$ 的绝对值除以 $limit$ 向上取整，即 $\lceil \frac{|d|}{limit} \rceil$。

注意，本题中数组元素的数据范围为 $[-10^6, 10^6]$，元素个数最大为 $10^5$，总和 $s$ 以及差值 $d$ 可能会超过 $32$ 位整数的表示范围，因此需要使用 $64$ 位整数。

时间复杂度 $O(n)$，空间复杂度 $O(1)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minElements(self, nums: List[int], limit: int, goal: int) -> int:
        d = abs(sum(nums) - goal)
        return (d + limit - 1) // limit
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minElements(int[] nums, int limit, int goal) {
        // long s = Arrays.stream(nums).asLongStream().sum();
        long s = 0;
        for (int v : nums) {
            s += v;
        }
        long d = Math.abs(s - goal);
        return (int) ((d + limit - 1) / limit);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minElements(vector<int>& nums, int limit, int goal) {
        long long s = accumulate(nums.begin(), nums.end(), 0ll);
        long long d = abs(s - goal);
        return (d + limit - 1) / limit;
    }
};
```

### **Go**

```go
func minElements(nums []int, limit int, goal int) int {
	s := 0
	for _, v := range nums {
		s += v
	}
	d := abs(s - goal)
	return (d + limit - 1) / limit
}

func abs(x int) int {
	if x < 0 {
		return -x
	}
	return x
}
```

### **TypeScript**

```ts
function minElements(nums: number[], limit: number, goal: number): number {
    const sum = nums.reduce((r, v) => r + v, 0);
    const diff = Math.abs(goal - sum);
    return Math.floor((diff + limit - 1) / limit);
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_elements(nums: Vec<i32>, limit: i32, goal: i32) -> i32 {
        let limit = limit as i64;
        let goal = goal as i64;
        let mut sum = 0;
        for &num in nums.iter() {
            sum += num as i64;
        }
        let diff = (goal - sum).abs();
        ((diff + limit - 1) / limit) as i32
    }
}
```

### **C**

```c
int minElements(int *nums, int numsSize, int limit, int goal) {
    long long sum = 0;
    for (int i = 0; i < numsSize; i++) {
        sum += nums[i];
    }
    long long diff = labs(goal - sum);
    return (diff + limit - 1) / limit;
}
```

### **...**

```

```

<!-- tabs:end -->
