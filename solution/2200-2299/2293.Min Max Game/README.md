# [2293. 极大极小游戏](https://leetcode.cn/problems/min-max-game)

[English Version](/solution/2200-2299/2293.Min%20Max%20Game/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> ，其长度是 <code>2</code> 的幂。</p>

<p>对 <code>nums</code> 执行下述算法：</p>

<ol>
	<li>设 <code>n</code> 等于 <code>nums</code> 的长度，如果 <code>n == 1</code> ，<strong>终止</strong> 算法过程。否则，<strong>创建</strong> 一个新的整数数组&nbsp;<code>newNums</code> ，新数组长度为 <code>n / 2</code> ，下标从 <strong>0</strong> 开始。</li>
	<li>对于满足&nbsp;<code>0 &lt;= i &lt; n / 2</code> 的每个 <strong>偶数</strong> 下标 <code>i</code> ，将 <code>newNums[i]</code> <strong>赋值</strong> 为 <code>min(nums[2 * i], nums[2 * i + 1])</code> 。</li>
	<li>对于满足&nbsp;<code>0 &lt;= i &lt; n / 2</code> 的每个 <strong>奇数</strong> 下标 <code>i</code> ，将 <code>newNums[i]</code> <strong>赋值</strong> 为 <code>max(nums[2 * i], nums[2 * i + 1])</code> 。</li>
	<li>用 <code>newNums</code> 替换 <code>nums</code> 。</li>
	<li>从步骤 1 开始 <strong>重复</strong> 整个过程。</li>
</ol>

<p>执行算法后，返回 <code>nums</code> 中剩下的那个数字。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<p><img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2200-2299/2293.Min%20Max%20Game/images/example1drawio-1.png" style="width: 500px; height: 240px;" /></p>

<pre>
<strong>输入：</strong>nums = [1,3,5,2,4,8,2,2]
<strong>输出：</strong>1
<strong>解释：</strong>重复执行算法会得到下述数组。
第一轮：nums = [1,5,4,2]
第二轮：nums = [1,4]
第三轮：nums = [1]
1 是最后剩下的那个数字，返回 1 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [3]
<strong>输出：</strong>3
<strong>解释：</strong>3 就是最后剩下的数字，返回 3 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1024</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>nums.length</code> 是 <code>2</code> 的幂</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def minMaxGame(self, nums: List[int]) -> int:
        n = len(nums)
        if n == 1:
            return nums[0]
        t = []
        for i in range(n >> 1):
            v = (
                max(nums[i << 1], nums[i << 1 | 1])
                if i & 1
                else min(nums[i << 1], nums[i << 1 | 1])
            )
            t.append(v)
        return self.minMaxGame(t)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public int minMaxGame(int[] nums) {
        int n = nums.length;
        if (n == 1) {
            return nums[0];
        }
        int[] t = new int[n >> 1];
        for (int i = 0; i < t.length; ++i) {
            int a = nums[i << 1], b = nums[i << 1 | 1];
            t[i] = (i & 1) == 1 ? Math.max(a, b) : Math.min(a, b);
        }
        return minMaxGame(t);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minMaxGame(vector<int>& nums) {
        int n = nums.size();
        if (n == 1) return nums[0];
        vector<int> t(n >> 1);
        for (int i = 0; i < t.size(); ++i) {
            int a = nums[i << 1], b = nums[i << 1 | 1];
            t[i] = (i & 1) == 1 ? max(a, b) : min(a, b);
        }
        return minMaxGame(t);
    }
};
```

### **Go**

```go
func minMaxGame(nums []int) int {
	n := len(nums)
	if n == 1 {
		return nums[0]
	}
	var t []int
	for i := 0; i < n>>1; i++ {
		a, b := nums[i<<1], nums[i<<1|1]
		if (i & 1) == 1 {
			t = append(t, max(a, b))
		} else {
			t = append(t, min(a, b))
		}
	}
	return minMaxGame(t)
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

### **TypeScript**

```ts
function minMaxGame(nums: number[]): number {
    while (nums.length > 1) {
        let n = nums.length;
        let tmp = [];
        for (let i = 0; i < n; i += 2) {
            if (i % 4 == 2) {
                tmp.push(Math.max(nums[i], nums[i + 1]));
            } else {
                tmp.push(Math.min(nums[i], nums[i + 1]));
            }
        }
        nums = tmp;
    }
    return nums[0];
}
```

### **...**

```

```

<!-- tabs:end -->
