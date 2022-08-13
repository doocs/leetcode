# [17.05. Find Longest Subarray](https://leetcode.cn/problems/find-longest-subarray-lcci)

[中文文档](/lcci/17.05.Find%20Longest%20Subarray/README.md)

## Description

<p>Given an array filled with letters and numbers, find the longest subarray with an equal number of letters and numbers.</p>

<p>Return the subarray. If there are more than one answer, return the one which has the smallest&nbsp;index of its left endpoint. If there is no answer, return an empty arrary.</p>

<p><strong>Example 1:</strong></p>

<pre>

<strong>Input: </strong>[&quot;A&quot;,&quot;1&quot;,&quot;B&quot;,&quot;C&quot;,&quot;D&quot;,&quot;2&quot;,&quot;3&quot;,&quot;4&quot;,&quot;E&quot;,&quot;5&quot;,&quot;F&quot;,&quot;G&quot;,&quot;6&quot;,&quot;7&quot;,&quot;H&quot;,&quot;I&quot;,&quot;J&quot;,&quot;K&quot;,&quot;L&quot;,&quot;M&quot;]



<strong>Output: </strong>[&quot;A&quot;,&quot;1&quot;,&quot;B&quot;,&quot;C&quot;,&quot;D&quot;,&quot;2&quot;,&quot;3&quot;,&quot;4&quot;,&quot;E&quot;,&quot;5&quot;,&quot;F&quot;,&quot;G&quot;,&quot;6&quot;,&quot;7&quot;]

</pre>

<p><strong>Example 2:</strong></p>

<pre>

<strong>Input: </strong>[&quot;A&quot;,&quot;A&quot;]



<strong>Output: </strong>[]

</pre>

<p><strong>Note: </strong></p>

<ul>
	<li><code>array.length &lt;= 100000</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def findLongestSubarray(self, array: List[str]) -> List[str]:
        seen = {0: -1}
        t = mx = 0
        ans = []
        for i, s in enumerate(array):
            t += 1 if s.isalpha() else -1
            if t in seen:
                if mx < i - seen[t]:
                    mx = i - seen[t]
                    ans = array[seen[t] + 1 : i + 1]
            else:
                seen[t] = i
        return ans
```

### **Java**

```java
class Solution {
    public String[] findLongestSubarray(String[] array) {
        Map<Integer, Integer> seen = new HashMap<>();
        seen.put(0, -1);
        int t = 0, mx = 0;
        int j = 0;
        for (int i = 0; i < array.length; ++i) {
            t += Character.isDigit(array[i].charAt(0)) ? 1 : -1;
            if (seen.containsKey(t)) {
                if (mx < i - seen.get(t)) {
                    mx = i - seen.get(t);
                    j = seen.get(t) + 1;
                }
            } else {
                seen.put(t, i);
            }
        }
        String[] ans = new String[mx];
        for (int i = 0; i < mx; ++i) {
            ans[i] = array[i + j];
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<string> findLongestSubarray(vector<string>& array) {
        unordered_map<int, int> seen;
        seen[0] = -1;
        int t = 0, mx = 0, j = 0;
        for (int i = 0; i < array.size(); ++i) {
            t += isdigit(array[i][0]) ? 1 : -1;
            if (seen.count(t)) {
                if (mx < i - seen[t]) {
                    mx = i - seen[t];
                    j = seen[t] + 1;
                }
            } else {
                seen[t] = i;
            }
        }
        return {array.begin() + j, array.begin() + j + mx};
    }
};
```

### **Go**

```go
func findLongestSubarray(array []string) []string {
	seen := map[int]int{0: -1}
	t, mx, j := 0, 0, 0
	for i, s := range array {
		if unicode.IsDigit(rune(s[0])) {
			t++
		} else {
			t--
		}
		if k, ok := seen[t]; ok {
			if mx < i-k {
				mx = i - k
				j = k + 1
			}
		} else {
			seen[t] = i
		}
	}
	return array[j : j+mx]
}
```

### **...**

```

```

<!-- tabs:end -->
