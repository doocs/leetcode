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

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def sumOfBeauties(self, nums: List[int]) -> int:
        n = len(nums)
        lmx = [0] * n
        for i in range(1, n):
            lmx[i] = max(lmx[i - 1], nums[i - 1])
        rmi = [100001] * n
        for i in range(n - 2, -1, -1):
            rmi[i] = min(rmi[i + 1], nums[i + 1])
        ans = 0
        for i in range(1, n - 1):
            if lmx[i] < nums[i] < rmi[i]:
                ans += 2
            elif nums[i - 1] < nums[i] < nums[i + 1]:
                ans += 1
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int sumOfBeauties(int[] nums) {
        int n = nums.length;
        int[] lmx = new int[n];
        int[] rmi = new int[n];
        rmi[n - 1] = 100001;
        for (int i = 1; i < n; ++i) {
            lmx[i] = Math.max(lmx[i - 1], nums[i - 1]);
        }
        for (int i = n - 2; i >= 0; --i) {
            rmi[i] = Math.min(rmi[i + 1], nums[i + 1]);
        }
        int ans = 0;
        for (int i = 1; i < n - 1; ++i) {
            if (lmx[i] < nums[i] && nums[i] < rmi[i]) {
                ans += 2;
            } else if (nums[i - 1] < nums[i] && nums[i] < nums[i + 1]) {
                ans += 1;
            }
        }
        return ans;
    }
}
```

### \*\*TypeScript

```ts
function sumOfBeauties(nums: number[]): number {
    let n = nums.length;
    let prefix = new Array(n),
        postfix = new Array(n);
    prefix[0] = nums[0];
    postfix[n - 1] = nums[n - 1];
    for (let i = 1, j = n - 2; i < n; ++i, --j) {
        prefix[i] = Math.max(nums[i], prefix[i - 1]);
        postfix[j] = Math.min(nums[j], postfix[j + 1]);
    }
    let ans = 0;
    for (let i = 1; i < n - 1; ++i) {
        if (prefix[i - 1] < nums[i] && nums[i] < postfix[i + 1]) {
            ans += 2;
        } else if (nums[i - 1] < nums[i] && nums[i] < nums[i + 1]) {
            ans += 1;
        }
    }
    return ans;
}
```

### **C++**

```cpp
class Solution {
public:
    int sumOfBeauties(vector<int>& nums) {
        int n = nums.size();
        vector<int> lmx(n);
        vector<int> rmi(n, 100001);
        for (int i = 1; i < n; ++i) lmx[i] = max(lmx[i - 1], nums[i - 1]);
        for (int i = n - 2; i >= 0; --i) rmi[i] = min(rmi[i + 1], nums[i + 1]);
        int ans = 0;
        for (int i = 1; i < n - 1; ++i) {
            if (lmx[i] < nums[i] && nums[i] < rmi[i])
                ans += 2;
            else if (nums[i - 1] < nums[i] && nums[i] < nums[i + 1])
                ans += 1;
        }
        return ans;
    }
};
```

### **Go**

```go
func sumOfBeauties(nums []int) int {
	n := len(nums)
	lmx := make([]int, n)
	rmi := make([]int, n)
	rmi[n-1] = 100001
	for i := 1; i < n; i++ {
		lmx[i] = max(lmx[i-1], nums[i-1])
	}
	for i := n - 2; i >= 0; i-- {
		rmi[i] = min(rmi[i+1], nums[i+1])
	}
	ans := 0
	for i := 1; i < n-1; i++ {
		if lmx[i] < nums[i] && nums[i] < rmi[i] {
			ans += 2
		} else if nums[i-1] < nums[i] && nums[i] < nums[i+1] {
			ans += 1
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
