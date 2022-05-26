# [228. Summary Ranges](https://leetcode.com/problems/summary-ranges)

[中文文档](/solution/0200-0299/0228.Summary%20Ranges/README.md)

## Description

<p>You are given a <strong>sorted unique</strong> integer array <code>nums</code>.</p>

<p>A <strong>range</strong> <code>[a,b]</code> is the set of all integers from <code>a</code> to <code>b</code> (inclusive).</p>

<p>Return <em>the <strong>smallest sorted</strong> list of ranges that <strong>cover all the numbers in the array exactly</strong></em>. That is, each element of <code>nums</code> is covered by exactly one of the ranges, and there is no integer <code>x</code> such that <code>x</code> is in one of the ranges but not in <code>nums</code>.</p>

<p>Each range <code>[a,b]</code> in the list should be output as:</p>

<ul>
	<li><code>&quot;a-&gt;b&quot;</code> if <code>a != b</code></li>
	<li><code>&quot;a&quot;</code> if <code>a == b</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,2,4,5,7]
<strong>Output:</strong> [&quot;0-&gt;2&quot;,&quot;4-&gt;5&quot;,&quot;7&quot;]
<strong>Explanation:</strong> The ranges are:
[0,2] --&gt; &quot;0-&gt;2&quot;
[4,5] --&gt; &quot;4-&gt;5&quot;
[7,7] --&gt; &quot;7&quot;
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,2,3,4,6,8,9]
<strong>Output:</strong> [&quot;0&quot;,&quot;2-&gt;4&quot;,&quot;6&quot;,&quot;8-&gt;9&quot;]
<strong>Explanation:</strong> The ranges are:
[0,0] --&gt; &quot;0&quot;
[2,4] --&gt; &quot;2-&gt;4&quot;
[6,6] --&gt; &quot;6&quot;
[8,9] --&gt; &quot;8-&gt;9&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= nums.length &lt;= 20</code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li>All the values of <code>nums</code> are <strong>unique</strong>.</li>
	<li><code>nums</code> is sorted in ascending order.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

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
