# [2149. 按符号重排数组](https://leetcode.cn/problems/rearrange-array-elements-by-sign)

[English Version](/solution/2100-2199/2149.Rearrange%20Array%20Elements%20by%20Sign/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个下标从 <strong>0</strong> 开始的整数数组 <code>nums</code> ，数组长度为 <strong>偶数</strong> ，由数目相等的正整数和负整数组成。</p>

<p>你需要 <strong>重排</strong> <code>nums</code> 中的元素，使修改后的数组满足下述条件：</p>

<ol>
	<li>任意&nbsp;<strong>连续</strong> 的两个整数 <strong>符号相反</strong></li>
	<li>对于符号相同的所有整数，<strong>保留</strong> 它们在 <code>nums</code> 中的 <strong>顺序</strong> 。</li>
	<li>重排后数组以正整数开头。</li>
</ol>

<p>重排元素满足上述条件后，返回修改后的数组。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [3,1,-2,-5,2,-4]
<strong>输出：</strong>[3,-2,1,-5,2,-4]
<strong>解释：</strong>
nums 中的正整数是 [3,1,2] ，负整数是 [-2,-5,-4] 。
重排的唯一可行方案是 [3,-2,1,-5,2,-4]，能满足所有条件。
像 [1,-2,2,-5,3,-4]、[3,1,2,-2,-5,-4]、[-2,3,-5,1,-4,2] 这样的其他方案是不正确的，因为不满足一个或者多个条件。 
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [-1,1]
<strong>输出：</strong>[1,-1]
<strong>解释：</strong>
1 是 nums 中唯一一个正整数，-1 是 nums 中唯一一个负整数。
所以 nums 重排为 [1,-1] 。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>nums.length</code> 是 <strong>偶数</strong></li>
	<li><code>1 &lt;= |nums[i]| &lt;= 10<sup>5</sup></code></li>
	<li><code>nums</code> 由 <strong>相等</strong> 数量的正整数和负整数组成</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def rearrangeArray(self, nums: List[int]) -> List[int]:
        ans = [0] * len(nums)
        i, j = 0, 1
        for num in nums:
            if num > 0:
                ans[i] = num
                i += 2
            else:
                ans[j] = num
                j += 2
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {

    public int[] rearrangeArray(int[] nums) {
        int[] ans = new int[nums.length];
        int i = 0, j = 1;
        for (int num : nums) {
            if (num > 0) {
                ans[i] = num;
                i += 2;
            } else {
                ans[j] = num;
                j += 2;
            }
        }
        return ans;
    }
}

```

### **C++**

```cpp
class Solution {
public:
    vector<int> rearrangeArray(vector<int>& nums) {
        vector<int> ans(nums.size());
        int i = 0, j = 1;
        for (int num : nums) {
            if (num > 0) {
                ans[i] = num;
                i += 2;
            } else {
                ans[j] = num;
                j += 2;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func rearrangeArray(nums []int) []int {
	ans := make([]int, len(nums))
	i, j := 0, 1
	for _, num := range nums {
		if num > 0 {
			ans[i] = num
			i += 2
		} else {
			ans[j] = num
			j += 2
		}
	}
	return ans
}
```

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts
function rearrangeArray(nums: number[]): number[] {
    let ans = [];
    let i = 0,
        j = 1;
    for (let num of nums) {
        if (num > 0) {
            ans[i] = num;
            i += 2;
        } else {
            ans[j] = num;
            j += 2;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
