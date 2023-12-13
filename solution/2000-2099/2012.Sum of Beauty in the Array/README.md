# [2012. 数组美丽值求和](https://leetcode.cn/problems/sum-of-beauty-in-the-array)

[English Version](/solution/2000-2099/2012.Sum%20of%20Beauty%20in%20the%20Array/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> 。对于每个下标 <code>i</code>（<code>1 &lt;= i &lt;= nums.length - 2</code>），<code>nums[i]</code> 的 <strong>美丽值</strong> 等于：</p>

<ul>
	<li><code>2</code>，对于所有 <code>0 &lt;= j &lt; i</code> 且 <code>i &lt; k &lt;= nums.length - 1</code> ，满足 <code>nums[j] &lt; nums[i] &lt; nums[k]</code></li>
	<li><code>1</code>，如果满足 <code>nums[i - 1] &lt; nums[i] &lt; nums[i + 1]</code> ，且不满足前面的条件</li>
	<li><code>0</code>，如果上述条件全部不满足</li>
</ul>

<p>返回符合 <code>1 &lt;= i &lt;= nums.length - 2</code> 的所有<em> </em><code>nums[i]</code><em> </em>的 <strong>美丽值的总和</strong> 。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre><strong>输入：</strong>nums = [1,2,3]
<strong>输出：</strong>2
<strong>解释：</strong>对于每个符合范围 1 &lt;= i &lt;= 1 的下标 i :
- nums[1] 的美丽值等于 2
</pre>

<p><strong>示例 2：</strong></p>

<pre><strong>输入：</strong>nums = [2,4,6,4]
<strong>输出：</strong>1
<strong>解释：</strong>对于每个符合范围 1 &lt;= i &lt;= 2 的下标 i :
- nums[1] 的美丽值等于 1
- nums[2] 的美丽值等于 0
</pre>

<p><strong>示例 3：</strong></p>

<pre><strong>输入：</strong>nums = [3,2,1]
<strong>输出：</strong>0
<strong>解释：</strong>对于每个符合范围 1 &lt;= i &lt;= 1 的下标 i :
- nums[1] 的美丽值等于 0
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：预处理右侧最小值 + 遍历维护左侧最大值**

我们可以预处理出右侧最小值数组 $right$，其中 $right[i]$ 表示 $nums[i..n-1]$ 中的最小值。

然后我们从左到右遍历数组 $nums$，同时维护左侧最大值 $l$。对于每个位置 $i$，我们判断 $l < nums[i] < right[i + 1]$ 是否成立，如果成立则将 $2$ 累加至答案，否则判断 $nums[i - 1] < nums[i] < nums[i + 1]$ 是否成立，如果成立则将 $1$ 累加至答案。

遍历结束后即可得到答案。

时间复杂度 $O(n)$，空间复杂度 $O(n)$。其中 $n$ 为数组 $nums$ 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def sumOfBeauties(self, nums: List[int]) -> int:
        n = len(nums)
        right = [nums[-1]] * n
        for i in range(n - 2, -1, -1):
            right[i] = min(right[i + 1], nums[i])
        ans = 0
        l = nums[0]
        for i in range(1, n - 1):
            r = right[i + 1]
            if l < nums[i] < r:
                ans += 2
            elif nums[i - 1] < nums[i] < nums[i + 1]:
                ans += 1
            l = max(l, nums[i])
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int sumOfBeauties(int[] nums) {
        int n = nums.length;
        int[] right = new int[n];
        right[n - 1] = nums[n - 1];
        for (int i = n - 2; i > 0; --i) {
            right[i] = Math.min(right[i + 1], nums[i]);
        }
        int ans = 0;
        int l = nums[0];
        for (int i = 1; i < n - 1; ++i) {
            int r = right[i + 1];
            if (l < nums[i] && nums[i] < r) {
                ans += 2;
            } else if (nums[i - 1] < nums[i] && nums[i] < nums[i + 1]) {
                ans += 1;
            }
            l = Math.max(l, nums[i]);
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int sumOfBeauties(vector<int>& nums) {
        int n = nums.size();
        vector<int> right(n, nums[n - 1]);
        for (int i = n - 2; i; --i) {
            right[i] = min(right[i + 1], nums[i]);
        }
        int ans = 0;
        for (int i = 1, l = nums[0]; i < n - 1; ++i) {
            int r = right[i + 1];
            if (l < nums[i] && nums[i] < r) {
                ans += 2;
            } else if (nums[i - 1] < nums[i] && nums[i] < nums[i + 1]) {
                ans += 1;
            }
            l = max(l, nums[i]);
        }
        return ans;
    }
};
```

### **Go**

```go
func sumOfBeauties(nums []int) (ans int) {
	n := len(nums)
	right := make([]int, n)
	right[n-1] = nums[n-1]
	for i := n - 2; i > 0; i-- {
		right[i] = min(right[i+1], nums[i])
	}
	for i, l := 1, nums[0]; i < n-1; i++ {
		r := right[i+1]
		if l < nums[i] && nums[i] < r {
			ans += 2
		} else if nums[i-1] < nums[i] && nums[i] < nums[i+1] {
			ans++
		}
		l = max(l, nums[i])
	}
	return
}
```

### **TypeScript**

```ts
function sumOfBeauties(nums: number[]): number {
    const n = nums.length;
    const right: number[] = Array(n).fill(nums[n - 1]);
    for (let i = n - 2; i; --i) {
        right[i] = Math.min(right[i + 1], nums[i]);
    }
    let ans = 0;
    for (let i = 1, l = nums[0]; i < n - 1; ++i) {
        const r = right[i + 1];
        if (l < nums[i] && nums[i] < r) {
            ans += 2;
        } else if (nums[i - 1] < nums[i] && nums[i] < nums[i + 1]) {
            ans += 1;
        }
        l = Math.max(l, nums[i]);
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
