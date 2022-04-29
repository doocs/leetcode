# [228. 汇总区间](https://leetcode.cn/problems/summary-ranges)

[English Version](/solution/0200-0299/0228.Summary%20Ranges/README_EN.md)

## 题目描述

<!-- 这里写题目描述 -->

<p>给定一个 &nbsp;<strong>无重复元素</strong> 的&nbsp;<strong>有序</strong> 整数数组 <code>nums</code> 。</p>

<p>返回 <em><strong>恰好覆盖数组中所有数字</strong> 的 <strong>最小有序</strong> 区间范围列表&nbsp;</em>。也就是说，<code>nums</code> 的每个元素都恰好被某个区间范围所覆盖，并且不存在属于某个范围但不属于 <code>nums</code> 的数字 <code>x</code> 。</p>

<p>列表中的每个区间范围 <code>[a,b]</code> 应该按如下格式输出：</p>

<ul>
	<li><code>"a-&gt;b"</code> ，如果 <code>a != b</code></li>
	<li><code>"a"</code> ，如果 <code>a == b</code></li>
</ul>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,1,2,4,5,7]
<strong>输出：</strong>["0-&gt;2","4-&gt;5","7"]
<strong>解释：</strong>区间范围是：
[0,2] --&gt; "0-&gt;2"
[4,5] --&gt; "4-&gt;5"
[7,7] --&gt; "7"
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [0,2,3,4,6,8,9]
<strong>输出：</strong>["0","2-&gt;4","6","8-&gt;9"]
<strong>解释：</strong>区间范围是：
[0,0] --&gt; "0"
[2,4] --&gt; "2-&gt;4"
[6,6] --&gt; "6"
[8,9] --&gt; "8-&gt;9"
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 20</code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>nums</code> 中的所有值都 <strong>互不相同</strong></li>
	<li><code>nums</code> 按升序排列</li>
</ul>

## 解法

<!-- 这里可写通用的实现逻辑 -->

<!-- tabs:start -->

### **Python3**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```python
class Solution:
    def summaryRanges(self, nums: List[int]) -> List[str]:
        def make(nums, i, j):
            return str(nums[i]) if i == j else f'{nums[i]}->{nums[j]}'

        i = j = 0
        n = len(nums)
        res = []
        while j < n:
            while j + 1 < n and nums[j] + 1 == nums[j + 1]:
                j += 1
            res.append(make(nums, i, j))
            i = j + 1
            j = i
        return res
```

### **Java**

<!-- 这里可写当前语言的特殊实现逻辑 -->

```java
class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        for (int i = 0, j = 0, n = nums.length; j < n;) {
            while (j + 1 < n && nums[j] + 1 == nums[j + 1]) {
                ++j;
            }
            res.add(make(nums, i, j));
            i = j + 1;
            j = i;
        }
        return res;
    }

    private String make(int[] nums, int i, int j) {
        return i == j ? String.valueOf(nums[i]) : String.format("%d->%d", nums[i], nums[j]);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> summaryRanges(vector<int>& nums) {
        vector<string> res;
        for (int i = 0, j = 0, n = nums.size(); j < n;) {
            while (j + 1 < n && nums[j] + 1 == nums[j + 1]) ++j;
            res.push_back(make(nums, i, j));
            i = j + 1;
            j = i;
        }
        return res;
    }

    string make(vector<int>& nums, int i, int j) {
        return i == j ? to_string(nums[i]) : to_string(nums[i]) + "->" + to_string(nums[j]);
    }
};
```

### **Go**

```go
func summaryRanges(nums []int) []string {
	var res []string
	for i, j, n := 0, 0, len(nums); j < n; {
		for j+1 < n && nums[j]+1 == nums[j+1] {
			j++
		}
		res = append(res, make(nums, i, j))
		i = j + 1
		j = i
	}
	return res
}

func make(nums []int, i, j int) string {
	if i == j {
		return strconv.Itoa(nums[i])
	}
	return strconv.Itoa(nums[i]) + "->" + strconv.Itoa(nums[j])
}
```

### **C#**

```cs
public class Solution {
    public IList<string> SummaryRanges(int[] nums) {
        var res = new List<string>();
        for (int i = 0, j = 0, n = nums.Length; j < n;)
        {
            while (j + 1 < n && nums[j] + 1 == nums[j + 1])
            {
                ++j;
            }
            res.Add(make(nums, i, j));
            i = j + 1;
            j = i;
        }
        return res;
    }

    public string make(int[] nums, int i, int j) {
        return i == j ? nums[i].ToString() : string.Format("{0}->{1}", nums[i], nums[j]);
    }
}
```

### **...**

```

```

<!-- tabs:end -->
