# [2016. 增量元素之间的最大差值](https://leetcode.cn/problems/maximum-difference-between-increasing-elements)

[English Version](/solution/2000-2099/2016.Maximum%20Difference%20Between%20Increasing%20Elements/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> ，该数组的大小为 <code>n</code> ，请你计算 <code>nums[j] - nums[i]</code> 能求得的 <strong>最大差值 </strong>，其中 <code>0 &lt;= i &lt; j &lt; n</code> 且 <code>nums[i] &lt; nums[j]</code> 。</p>

<p>返回 <strong>最大差值</strong> 。如果不存在满足要求的 <code>i</code> 和 <code>j</code> ，返回 <code>-1</code> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [7,<em><strong>1</strong></em>,<em><strong>5</strong></em>,4]
<strong>输出：</strong>4
<strong>解释：</strong>
最大差值出现在 i = 1 且 j = 2 时，nums[j] - nums[i] = 5 - 1 = 4 。
注意，尽管 i = 1 且 j = 0 时 ，nums[j] - nums[i] = 7 - 1 = 6 &gt; 4 ，但 i &gt; j 不满足题面要求，所以 6 不是有效的答案。
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [9,4,3,2]
<strong>输出：</strong>-1
<strong>解释：</strong>
不存在同时满足 i &lt; j 和 nums[i] &lt; nums[j] 这两个条件的 i, j 组合。
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [<em><strong>1</strong></em>,5,2,<em><strong>10</strong></em>]
<strong>输出：</strong>9
<strong>解释：</strong>
最大差值出现在 i = 0 且 j = 3 时，nums[j] - nums[i] = 10 - 1 = 9 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>2 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

此题并不能单纯遍历数组，寻找其中的最大最小值，然后计算差，因为需要保证最大最小值的前后顺序。

**步骤：**

1. 只维护最小值与最大差值（返回值）。
2. 遍历数组，当遍历元素比最小值大时，与最小值比较，更新最大差值。
3. 否则更新最小值。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def maximumDifference(self, nums: List[int]) -> int:
        mi = nums[0]
        ans, n = -1, len(nums)
        for i in range(1, n):
            if nums[i] > mi:
                ans = max(ans, nums[i] - mi)
            else:
                mi = nums[i]
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int maximumDifference(int[] nums) {
        int mi = nums[0];
        int ans = -1;
        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] > mi) {
                ans = Math.max(ans, nums[i] - mi);
            } else {
                mi = nums[i];
            }
        }
        return ans;
    }
}
```

### **TypeScript**

```ts
function maximumDifference(nums: number[]): number {
    const n = nums.length;
    let min = nums[0];
    let res = -1;
    for (let i = 1; i < n; i++) {
        res = Math.max(res, nums[i] - min);
        min = Math.min(min, nums[i]);
    }
    return res === 0 ? -1 : res;
}
```

### **Rust**

```rust
impl Solution {
    pub fn maximum_difference(nums: Vec<i32>) -> i32 {
        let mut min = nums[0];
        let mut res = -1;
        for i in 1..nums.len() {
            res = res.max(nums[i] - min);
            min = min.min(nums[i]);
        }
        match res {
            0 => -1,
            _ => res,
        }
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumDifference(vector<int>& nums) {
        int mi = nums[0];
        int ans = -1;
        for (int i = 1, n = nums.size(); i < n; ++i) {
            if (nums[i] > mi)
                ans = max(ans, nums[i] - mi);
            else
                mi = nums[i];
        }
        return ans;
    }
};
```

### **Go**

```go
func maximumDifference(nums []int) int {
	mi, ans := nums[0], -1
	for i, n := 1, len(nums); i < n; i++ {
		if nums[i] > mi {
			ans = max(ans, nums[i]-mi)
		} else {
			mi = nums[i]
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
