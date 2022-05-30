# [462. 最少移动次数使数组元素相等 II](https://leetcode.cn/problems/minimum-moves-to-equal-array-elements-ii)

[English Version](/solution/0400-0499/0462.Minimum%20Moves%20to%20Equal%20Array%20Elements%20II/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个长度为 <code>n</code> 的整数数组 <code>nums</code> ，返回使所有数组元素相等需要的最少移动数。</p>

<p>在一步操作中，你可以使数组中的一个元素加 <code>1</code> 或者减 <code>1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,2,3]
<strong>输出：</strong>2
<strong>解释：</strong>
只需要两步操作（每步操作指南使一个元素加 1 或减 1）：
[<strong><em>1</em></strong>,2,3]  =&gt;  [2,2,<strong><em>3</em></strong>]  =&gt;  [2,2,2]
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,10,2,9]
<strong>输出：</strong>16
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序 + 中位数**

**方法二：排序 + 前缀和**

以上两种方法的时间复杂度均为 O(nlogn)。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minMoves2(self, nums: List[int]) -> int:
        nums.sort()
        k = nums[len(nums) >> 1]
        return sum(abs(v - k) for v in nums)
```

```python
class Solution:
    def minMoves2(self, nums: List[int]) -> int:
        def move(i):
            v = nums[i]
            a = v * i - s[i]
            b = s[-1] - s[i + 1] - v * (n - i - 1)
            return a + b

        nums.sort()
        s = [0] + list(accumulate(nums))
        n = len(nums)
        return min(move(i) for i in range(n))
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int k = nums[nums.length >> 1];
        int ans = 0;
        for (int v : nums) {
            ans += Math.abs(v - k);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minMoves2(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int k = nums[nums.size() >> 1];
        int ans = 0;
        for (int& v : nums) ans += abs(v - k);
        return ans;
    }
};
```

### **Go**

```go
func minMoves2(nums []int) int {
	sort.Ints(nums)
	k := nums[len(nums)>>1]
	ans := 0
	for _, v := range nums {
		ans += abs(v - k)
	}
	return ans
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
function minMoves2(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const mid = nums[nums.length >> 1];
    return nums.reduce((r, v) => r + Math.abs(v - mid), 0);
}
```

### **Rust**

```rust
impl Solution {
    pub fn min_moves2(mut nums: Vec<i32>) -> i32 {
        nums.sort();
        let mid = nums[nums.len() / 2];
        let mut res = 0;
        for num in nums.iter() {
            res += (num - mid).abs();
        }
        res
    }
}
```

### **...**

```

```

<!-- tabs:end -->
