# [163. 缺失的区间](https://leetcode.cn/problems/missing-ranges)

[English Version](/solution/0100-0199/0163.Missing%20Ranges/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个排序的整数数组 <em><strong>nums&nbsp;</strong></em>，其中元素的范围在&nbsp;<strong>闭区间</strong>&nbsp;<strong>[<em>lower, upper</em>]</strong>&nbsp;当中，返回不包含在数组中的缺失区间。</p>

<p><strong>示例：</strong></p>

<pre><strong>输入: </strong><strong><em>nums</em></strong> = <code>[0, 1, 3, 50, 75]</code>, <strong><em>lower</em></strong> = 0 和 <strong><em>upper</em></strong> = 99,
<strong>输出: </strong><code>[&quot;2&quot;, &quot;4-&gt;49&quot;, &quot;51-&gt;74&quot;, &quot;76-&gt;99&quot;]</code>
</pre>

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
