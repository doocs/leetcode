# [163. 缺失的区间](https://leetcode.cn/problems/missing-ranges)

[English Version](/solution/0100-0199/0163.Missing%20Ranges/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个 <strong>排序</strong> 的整数数组 <code>nums</code><em><strong>&nbsp;</strong></em>，其中元素的范围在闭区间&nbsp;<code>[lower, upper]</code>&nbsp;当中。</p>

<p>如果一个数字 <code>x</code> 在 <code>[lower, upper]</code>&nbsp;区间内，并且 <code>x</code> 不在 <code>nums</code> 中，则认为 <code>x</code> <strong>缺失</strong>。</p>

<p>返回&nbsp;<strong>准确涵盖所有缺失数字&nbsp;</strong>的 <strong>最小排序</strong> 区间列表。也就是说，<code>nums</code> 的任何元素都不在任何区间内，并且每个缺失的数字都在其中一个区间内。</p>

<p>列表中的每个区间 <code>[a,b]</code> 应该输出:</p>

<ul>
	<li>如果&nbsp;&nbsp;<code>a != b</code>&nbsp;输出&nbsp;<code>"a-&gt;b"</code></li>
	<li>如果&nbsp;<code>a == b</code>&nbsp;输出 <code>"a"</code></li>
</ul>

<p>&nbsp;</p>

<p><strong class="example">示例 1：</strong></p>

<pre>
<strong>输入: </strong>nums = <code>[0, 1, 3, 50, 75]</code>, lower = 0 , upper = 99
<strong>输出: </strong><code>["2", "4-&gt;49", "51-&gt;74", "76-&gt;99"]</code>
<strong>解释：</strong>返回的区间是：
[2,2] --&gt; "2"
[4,49] --&gt; "4-&gt;49"
[51,74] --&gt; "51-&gt;74"
[76,99] --&gt; "76-&gt;99"
</pre>

<p><strong class="example">示例 2：</strong></p>

<pre>
<strong>输入：</strong> nums = [-1], lower = -1, upper = -1
<strong>输出：</strong> []
<b>解释：</b>&nbsp;没有缺失的区间，因为没有缺失的数字。</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>-10<sup>9</sup>&nbsp;&lt;= lower &lt;= upper &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= nums.length &lt;= 100</code></li>
	<li><code>lower &lt;= nums[i] &lt;= upper</code></li>
	<li><code>nums 的所有值都是唯一的。</code></li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

**方法一：模拟**

按照题意模拟即可。

时间复杂度 $O(n)$，忽略答案的空间消耗，空间复杂度 $O(1)$。其中 $n$ 为数组 `nums` 的长度。

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def findMissingRanges(self, nums: List[int], lower: int, upper: int) -> List[str]:
        def f(a, b):
            return str(a) if a == b else f'{a}->{b}'

        n = len(nums)
        if n == 0:
            return [f(lower, upper)]
        ans = []
        if nums[0] > lower:
            ans.append(f(lower, nums[0] - 1))
        for a, b in pairwise(nums):
            if b - a > 1:
                ans.append(f(a + 1, b - 1))
        if nums[-1] < upper:
            ans.append(f(nums[-1] + 1, upper))
        return ans
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        int n = nums.length;
        List<String> ans = new ArrayList<>();
        if (n == 0) {
            ans.add(f(lower, upper));
            return ans;
        }
        if (nums[0] > lower) {
            ans.add(f(lower, nums[0] - 1));
        }
        for (int i = 1; i < n; ++i) {
            int a = nums[i - 1], b = nums[i];
            if (b - a > 1) {
                ans.add(f(a + 1, b - 1));
            }
        }
        if (nums[n - 1] < upper) {
            ans.add(f(nums[n - 1] + 1, upper));
        }
        return ans;
    }

    private String f(int a, int b) {
        return a == b ? a + "" : a + "->" + b;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> findMissingRanges(vector<int>& nums, int lower, int upper) {
        auto f = [](int a, int b) {
            return a == b ? to_string(a) : to_string(a) + "->" + to_string(b);
        };
        int n = nums.size();
        vector<string> ans;
        if (n == 0) {
            ans.emplace_back(f(lower, upper));
            return ans;
        }
        if (nums[0] > lower) {
            ans.emplace_back(f(lower, nums[0] - 1));
        }
        for (int i = 1; i < n; ++i) {
            int a = nums[i - 1], b = nums[i];
            if (b - a > 1) {
                ans.emplace_back(f(a + 1, b - 1));
            }
        }
        if (nums[n - 1] < upper) {
            ans.emplace_back(f(nums[n - 1] + 1, upper));
        }
        return ans;
    }
};
```

### **Go**

```go
func findMissingRanges(nums []int, lower int, upper int) (ans []string) {
	f := func(a, b int) string {
		if a == b {
			return strconv.Itoa(a)
		}
		return strconv.Itoa(a) + "->" + strconv.Itoa(b)
	}
	n := len(nums)
	if n == 0 {
		ans = append(ans, f(lower, upper))
		return
	}
	if nums[0] > lower {
		ans = append(ans, f(lower, nums[0]-1))
	}
	for i := 1; i < n; i++ {
		a, b := nums[i-1], nums[i]
		if b-a > 1 {
			ans = append(ans, f(a+1, b-1))
		}
	}
	if nums[n-1] < upper {
		ans = append(ans, f(nums[n-1]+1, upper))
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
