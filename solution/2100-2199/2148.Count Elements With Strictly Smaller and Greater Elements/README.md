# [2148. 元素计数](https://leetcode.cn/problems/count-elements-with-strictly-smaller-and-greater-elements)

[English Version](/solution/2100-2199/2148.Count%20Elements%20With%20Strictly%20Smaller%20and%20Greater%20Elements/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给你一个整数数组 <code>nums</code> ，统计并返回在 <code>nums</code> 中同时至少具有一个严格较小元素和一个严格较大元素的元素数目。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [11,7,2,15]
<strong>输出：</strong>2
<strong>解释：</strong>元素 7 ：严格较小元素是元素 2 ，严格较大元素是元素 11 。
元素 11 ：严格较小元素是元素 7 ，严格较大元素是元素 15 。
总计有 2 个元素都满足在 nums 中同时存在一个严格较小元素和一个严格较大元素。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [-3,3,3,90]
<strong>输出：</strong>2
<strong>解释：</strong>元素 3 ：严格较小元素是元素 -3 ，严格较大元素是元素 90 。
由于有两个元素的值为 3 ，总计有 2 个元素都满足在 nums 中同时存在一个严格较小元素和一个严格较大元素。
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def countElements(self, nums: List[int]) -> int:
        mi, mx = min(nums), max(nums)
        return sum(mi < num < mx for num in nums)
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {

    public int countElements(int[] nums) {
        int mi = 1000000, mx = -1000000;
        for (int num : nums) {
            mi = Math.min(mi, num);
            mx = Math.max(mx, num);
        }
        int ans = 0;
        for (int num : nums) {
            if (mi < num && num < mx) {
                ++ans;
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
    int countElements(vector<int>& nums) {
        int mi = 1e6, mx = -1e6;
        for (int num : nums) {
            mi = min(mi, num);
            mx = max(mx, num);
        }
        int ans = 0;
        for (int num : nums)
            if (mi < num && num < mx)
                ++ans;
        return ans;
    }
};
```

### **Go**

```go
func countElements(nums []int) int {
	mi, mx := int(1e6), -int(1e6)
	for _, num := range nums {
		if num < mi {
			mi = num
		}
		if num > mx {
			mx = num
		}
	}
	ans := 0
	for _, num := range nums {
		if mi < num && num < mx {
			ans++
		}
	}
	return ans
}
```

### **TypeScript**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```ts
function countElements(nums: number[]): number {
    const min = Math.min(...nums),
        max = Math.max(...nums);
    let ans = 0;
    for (let i = 0; i < nums.length; ++i) {
        let cur = nums[i];
        if (cur < max && cur > min) {
            ++ans;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
