# [1980. Find Unique Binary String](https://leetcode.com/problems/find-unique-binary-string)

[中文文档](/solution/1900-1999/1980.Find%20Unique%20Binary%20String/README.md)

## Description

<p>Given an array of strings <code>nums</code> containing <code>n</code> <strong>unique</strong> binary strings each of length <code>n</code>, return <em>a binary string of length </em><code>n</code><em> that <strong>does not appear</strong> in </em><code>nums</code><em>. If there are multiple answers, you may return <strong>any</strong> of them</em>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [&quot;01&quot;,&quot;10&quot;]
<strong>Output:</strong> &quot;11&quot;
<strong>Explanation:</strong> &quot;11&quot; does not appear in nums. &quot;00&quot; would also be correct.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [&quot;00&quot;,&quot;01&quot;]
<strong>Output:</strong> &quot;11&quot;
<strong>Explanation:</strong> &quot;11&quot; does not appear in nums. &quot;10&quot; would also be correct.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [&quot;111&quot;,&quot;011&quot;,&quot;001&quot;]
<strong>Output:</strong> &quot;101&quot;
<strong>Explanation:</strong> &quot;101&quot; does not appear in nums. &quot;000&quot;, &quot;010&quot;, &quot;100&quot;, and &quot;110&quot; would also be correct.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 16</code></li>
	<li><code>nums[i].length == n</code></li>
	<li><code>nums[i] </code>is either <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
	<li>All the strings of <code>nums</code> are <strong>unique</strong>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findDifferentBinaryString(self, nums: List[str]) -> str:
        s = set(num.count("1") for num in nums)
        n = len(nums)
        for i in range(n + 1):
            if i not in s:
                return "1" * i + "0" * (n - i)
        return ""
```

### **Java**

```java
class Solution {
    public String findDifferentBinaryString(String[] nums) {
        Set<Integer> s = count(nums);
        int n = nums.length;
        for (int i = 0; i < n + 1; ++i) {
            if (!s.contains(i)) {
                return "1".repeat(i) + "0".repeat(n - i);
            }
        }
        return "";
    }

    private Set<Integer> count(String[] nums) {
        Set<Integer> s = new HashSet<>();
        for (String num : nums) {
            int t = 0;
            for (char c : num.toCharArray()) {
                if (c == '1') {
                    ++t;
                }
            }
            s.add(t);
        }
        return s;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string findDifferentBinaryString(vector<string>& nums) {
        auto s = count(nums);
        for (int i = 0, n = nums.size(); i < n + 1; ++i) {
            if (!s.count(i))
                return repeat("1", i) + repeat("0", n - i);
        }
        return "";
    }

    unordered_set<int> count(vector<string>& nums) {
        unordered_set<int> s;
        for (auto& num : nums) {
            int t = 0;
            for (char c : num) {
                if (c == '1')
                    ++t;
            }
            s.insert(t);
        }
        return s;
    }

    string repeat(string s, int n) {
        string res = "";
        for (int i = 0; i < n; ++i) {
            res += s;
        }
        return res;
    }
};
```

### **Go**

```go
func findDifferentBinaryString(nums []string) string {
	count := func() []bool {
		s := make([]bool, 17)
		for _, num := range nums {
			t := 0
			for _, c := range num {
				if c == '1' {
					t++
				}
			}
			s[t] = true
		}
		return s
	}
	s := count()
	for i, n := 0, len(nums); i <= n; i++ {
		if !s[i] {
			return strings.Repeat("1", i) + strings.Repeat("0", n-i)
		}
	}
	return ""
}
```

### **...**

```

```

<!-- tabs:end -->
