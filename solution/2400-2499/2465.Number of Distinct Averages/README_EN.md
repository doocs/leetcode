# [2465. Number of Distinct Averages](https://leetcode.com/problems/number-of-distinct-averages)

[中文文档](/solution/2400-2499/2465.Number%20of%20Distinct%20Averages/README.md)

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> of <strong>even</strong> length.</p>

<p>As long as <code>nums</code> is <strong>not</strong> empty, you must repetitively:</p>

<ul>
	<li>Find the minimum number in <code>nums</code> and remove it.</li>
	<li>Find the maximum number in <code>nums</code> and remove it.</li>
	<li>Calculate the average of the two removed numbers.</li>
</ul>

<p>The <strong>average</strong> of two numbers <code>a</code> and <code>b</code> is <code>(a + b) / 2</code>.</p>

<ul>
	<li>For example, the average of <code>2</code> and <code>3</code> is <code>(2 + 3) / 2 = 2.5</code>.</li>
</ul>

<p>Return<em> the number of <strong>distinct</strong> averages calculated using the above process</em>.</p>

<p><strong>Note</strong> that when there is a tie for a minimum or maximum number, any can be removed.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,1,4,0,3,5]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
1. Remove 0 and 5, and the average is (0 + 5) / 2 = 2.5. Now, nums = [4,1,4,3].
2. Remove 1 and 4. The average is (1 + 4) / 2 = 2.5, and nums = [4,3].
3. Remove 3 and 4, and the average is (3 + 4) / 2 = 3.5.
Since there are 2 distinct numbers among 2.5, 2.5, and 3.5, we return 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,100]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
There is only one average to be calculated after removing 1 and 100, so we return 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>nums.length</code> is even.</li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def distinctAverages(self, nums: List[int]) -> int:
        n = len(nums)
        nums.sort()
        return len(set(nums[i] + nums[n - i - 1] for i in range(n >> 1)))
```

### **Java**

```java
class Solution {
    public int distinctAverages(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        Set<Integer> s = new HashSet<>();
        for (int i = 0; i<n> > 1; ++i) {
            s.add(nums[i] + nums[n - i - 1]);
        }
        return s.size();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int distinctAverages(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int n = nums.size();
        unordered_set<int> s;
        for (int i = 0; i < n >> 1; ++i) s.insert(nums[i] + nums[n - i - 1]);
        return s.size();
    }
};
```

### **Go**

```go
func distinctAverages(nums []int) int {
	sort.Ints(nums)
	n := len(nums)
	s := map[int]struct{}{}
	for i := 0; i < n>>1; i++ {
		s[nums[i]+nums[n-i-1]] = struct{}{}
	}
	return len(s)
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
