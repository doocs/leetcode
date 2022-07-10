# [561. 数组拆分](https://leetcode.cn/problems/array-partition)

[English Version](/solution/0500-0599/0561.Array%20Partition/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定长度为&nbsp;<code>2n</code><strong>&nbsp;</strong>的整数数组 <code>nums</code> ，你的任务是将这些数分成&nbsp;<code>n</code><strong> </strong>对, 例如 <code>(a<sub>1</sub>, b<sub>1</sub>), (a<sub>2</sub>, b<sub>2</sub>), ..., (a<sub>n</sub>, b<sub>n</sub>)</code> ，使得从 <code>1</code> 到&nbsp;<code>n</code> 的 <code>min(a<sub>i</sub>, b<sub>i</sub>)</code> 总和最大。</p>

<p>返回该 <strong>最大总和</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [1,4,3,2]
<strong>输出：</strong>4
<strong>解释：</strong>所有可能的分法（忽略元素顺序）为：
1. (1, 4), (2, 3) -&gt; min(1, 4) + min(2, 3) = 1 + 2 = 3
2. (1, 3), (2, 4) -&gt; min(1, 3) + min(2, 4) = 1 + 2 = 3
3. (1, 2), (3, 4) -&gt; min(1, 2) + min(3, 4) = 1 + 3 = 4
所以最大总和为 4</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [6,2,6,5,1,2]
<strong>输出：</strong>9
<strong>解释：</strong>最优的分法为 (2, 1), (2, 5), (6, 6). min(2, 1) + min(2, 5) + min(6, 6) = 1 + 2 + 6 = 9
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>nums.length == 2 * n</code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：排序**

先排序，然后求相邻的两个元素的最小值，得到的总和即为结果。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def arrayPairSum(self, nums: List[int]) -> int:
        return sum(sorted(nums)[::2])
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int ans = 0;
        for (int i = 0; i < nums.length; i += 2) {
            ans += nums[i];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int arrayPairSum(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int ans = 0;
        for (int i = 0; i < nums.size(); i += 2) ans += nums[i];
        return ans;
    }
};
```

### **Go**

```go
func arrayPairSum(nums []int) int {
	sort.Ints(nums)
	ans := 0
	for i := 0; i < len(nums); i += 2 {
		ans += nums[i]
	}
	return ans
}
```

### **JavaScript**

```js
/**
 * @param {number[]} nums
 * @return {number}
 */
var arrayPairSum = function (nums) {
    nums.sort((a, b) => a - b);
    let ans = 0;
    for (let i = 0; i < nums.length; i += 2) {
        ans += nums[i];
    }
    return ans;
};
```

### **Rust**

```rust
impl Solution {
    pub fn array_pair_sum(mut nums: Vec<i32>) -> i32 {
        nums.sort();
        let n = nums.len();
        let mut i = 0;
        let mut res = 0;
        while i < n {
            res += nums[i];
            i += 2;
        }
        res
    }
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
