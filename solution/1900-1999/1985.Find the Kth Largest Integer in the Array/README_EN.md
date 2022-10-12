# [1985. Find the Kth Largest Integer in the Array](https://leetcode.com/problems/find-the-kth-largest-integer-in-the-array)

[中文文档](/solution/1900-1999/1985.Find%20the%20Kth%20Largest%20Integer%20in%20the%20Array/README.md)

## Description

<p>You are given an array of strings <code>nums</code> and an integer <code>k</code>. Each string in <code>nums</code> represents an integer without leading zeros.</p>

<p>Return <em>the string that represents the </em><code>k<sup>th</sup></code><em><strong> largest integer</strong> in </em><code>nums</code>.</p>

<p><strong>Note</strong>: Duplicate numbers should be counted distinctly. For example, if <code>nums</code> is <code>[&quot;1&quot;,&quot;2&quot;,&quot;2&quot;]</code>, <code>&quot;2&quot;</code> is the first largest integer, <code>&quot;2&quot;</code> is the second-largest integer, and <code>&quot;1&quot;</code> is the third-largest integer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [&quot;3&quot;,&quot;6&quot;,&quot;7&quot;,&quot;10&quot;], k = 4
<strong>Output:</strong> &quot;3&quot;
<strong>Explanation:</strong>
The numbers in nums sorted in non-decreasing order are [&quot;3&quot;,&quot;6&quot;,&quot;7&quot;,&quot;10&quot;].
The 4<sup>th</sup> largest integer in nums is &quot;3&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [&quot;2&quot;,&quot;21&quot;,&quot;12&quot;,&quot;1&quot;], k = 3
<strong>Output:</strong> &quot;2&quot;
<strong>Explanation:</strong>
The numbers in nums sorted in non-decreasing order are [&quot;1&quot;,&quot;2&quot;,&quot;12&quot;,&quot;21&quot;].
The 3<sup>rd</sup> largest integer in nums is &quot;2&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [&quot;0&quot;,&quot;0&quot;], k = 2
<strong>Output:</strong> &quot;0&quot;
<strong>Explanation:</strong>
The numbers in nums sorted in non-decreasing order are [&quot;0&quot;,&quot;0&quot;].
The 2<sup>nd</sup> largest integer in nums is &quot;0&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>1 &lt;= nums[i].length &lt;= 100</code></li>
	<li><code>nums[i]</code> consists of only digits.</li>
	<li><code>nums[i]</code> will not have any leading zeros.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def kthLargestNumber(self, nums: List[str], k: int) -> str:
        def cmp(a, b):
            if len(a) != len(b):
                return len(b) - len(a)
            return 1 if b > a else -1

        nums.sort(key=cmp_to_key(cmp))
        return nums[k - 1]
```

### **Java**

```java
class Solution {
    public String kthLargestNumber(String[] nums, int k) {
        Arrays.sort(
            nums, (a, b) -> a.length() == b.length() ? b.compareTo(a) : b.length() - a.length());
        return nums[k - 1];
    }
}
```

### **C++**

```cpp
class Solution {
public:
    string kthLargestNumber(vector<string>& nums, int k) {
        auto cmp = [](const string& a, const string& b) { return a.size() == b.size() ? a > b : a.size() > b.size(); };
        sort(nums.begin(), nums.end(), cmp);
        return nums[k - 1];
    }
};
```

### **Go**

```go
func kthLargestNumber(nums []string, k int) string {
	sort.Slice(nums, func(i, j int) bool {
		a, b := nums[i], nums[j]
		if len(a) == len(b) {
			return a > b
		}
		return len(a) > len(b)
	})
	return nums[k-1]
}
```

### **...**

```

```

<!-- tabs:end -->
