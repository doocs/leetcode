# [1775. Equal Sum Arrays With Minimum Number of Operations](https://leetcode.com/problems/equal-sum-arrays-with-minimum-number-of-operations)

[中文文档](/solution/1700-1799/1775.Equal%20Sum%20Arrays%20With%20Minimum%20Number%20of%20Operations/README.md)

## Description

<p>You are given two arrays of integers <code>nums1</code> and <code><font face="monospace">nums2</font></code>, possibly of different lengths. The values in the arrays are between <code>1</code> and <code>6</code>, inclusive.</p>

<p>In one operation, you can change any integer&#39;s value in <strong>any </strong>of the arrays to <strong>any</strong> value between <code>1</code> and <code>6</code>, inclusive.</p>

<p>Return <em>the minimum number of operations required to make the sum of values in </em><code>nums1</code><em> equal to the sum of values in </em><code>nums2</code><em>.</em> Return <code>-1</code>​​​​​ if it is not possible to make the sum of the two arrays equal.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2,3,4,5,6], nums2 = [1,1,2,2,2,2]
<strong>Output:</strong> 3
<strong>Explanation:</strong> You can make the sums of nums1 and nums2 equal with 3 operations. All indices are 0-indexed.
- Change nums2[0] to 6. nums1 = [1,2,3,4,5,6], nums2 = [<u><strong>6</strong></u>,1,2,2,2,2].
- Change nums1[5] to 1. nums1 = [1,2,3,4,5,<strong><u>1</u></strong>], nums2 = [6,1,2,2,2,2].
- Change nums1[2] to 2. nums1 = [1,2,<strong><u>2</u></strong>,4,5,1], nums2 = [6,1,2,2,2,2].
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,1,1,1,1,1,1], nums2 = [6]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There is no way to decrease the sum of nums1 or to increase the sum of nums2 to make them equal.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [6,6], nums2 = [1]
<strong>Output:</strong> 3
<strong>Explanation:</strong> You can make the sums of nums1 and nums2 equal with 3 operations. All indices are 0-indexed. 
- Change nums1[0] to 2. nums1 = [<strong><u>2</u></strong>,6], nums2 = [1].
- Change nums1[1] to 2. nums1 = [2,<strong><u>2</u></strong>], nums2 = [1].
- Change nums2[0] to 4. nums1 = [2,2], nums2 = [<strong><u>4</u></strong>].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 6</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minOperations(self, nums1: List[int], nums2: List[int]) -> int:
        s1, s2 = sum(nums1), sum(nums2)
        if s1 == s2:
            return 0
        if s1 > s2:
            return self.minOperations(nums2, nums1)
        freq = [0] * 6
        for x in nums1:
            freq[6 - x] += 1
        for x in nums2:
            freq[x - 1] += 1
        diff = s2 - s1
        ans, i = 0, 5
        while i > 0 and diff > 0:
            while freq[i] and diff > 0:
                diff -= i
                freq[i] -= 1
                ans += 1
            i -= 1
        return -1 if diff > 0 else ans
```

### **Java**

```java
class Solution {
    public int minOperations(int[] nums1, int[] nums2) {
        int s1 = sum(nums1);
        int s2 = sum(nums2);
        if (s1 == s2) {
            return 0;
        }
        if (s1 > s2) {
            return minOperations(nums2, nums1);
        }
        int[] freq = new int[6];
        for (int x : nums1) {
            ++freq[6 - x];
        }
        for (int x : nums2) {
            ++freq[x - 1];
        }
        int diff = s2 - s1;
        int ans = 0;
        for (int i = 5; i > 0 && diff > 0; --i) {
            while (freq[i] > 0 && diff > 0) {
                diff -= i;
                --freq[i];
                ++ans;
            }
        }
        return diff > 0 ? - 1 : ans;
    }

    private int sum(int[] nums) {
        int s = 0;
        for (int x : nums) {
            s += x;
        }
        return s;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums1, vector<int>& nums2) {
        int s1 = accumulate(nums1.begin(), nums1.end(), 0);
        int s2 = accumulate(nums2.begin(), nums2.end(), 0);
        if (s1 == s2) return 0;
        if (s1 > s2) return minOperations(nums2, nums1);
        vector<int> freq(6);
        for (int x : nums1) ++freq[6 - x];
        for (int x : nums2) ++freq[x - 1];
        int diff = s2 - s1;
        int ans = 0;
        for (int i = 5; i > 0 && diff > 0; --i) {
            while (freq[i] && diff > 0) {
                diff -= i;
                --freq[i];
                ++ans;
            }
        }
        return diff > 0 ? -1 : ans;
    }
};
```

### **Go**

```go
func minOperations(nums1 []int, nums2 []int) int {
	s1, s2 := sum(nums1), sum(nums2)
	if s1 == s2 {
		return 0
	}
	if s1 > s2 {
		return minOperations(nums2, nums1)
	}
	freq := make([]int, 6)
	for _, x := range nums1 {
		freq[6-x]++
	}
	for _, x := range nums2 {
		freq[x-1]++
	}
	diff := s2 - s1
	ans := 0
	for i := 5; i > 0 && diff > 0; i-- {
		for freq[i] > 0 && diff > 0 {
			diff -= i
			freq[i]--
			ans++
		}
	}
	if diff > 0 {
		return -1
	}
	return ans
}

func sum(nums []int) int {
	s := 0
	for _, x := range nums {
		s += x
	}
	return s
}
```

### **...**

```

```

<!-- tabs:end -->
