# [2032. Two Out of Three](https://leetcode.com/problems/two-out-of-three)

[中文文档](/solution/2000-2099/2032.Two%20Out%20of%20Three/README.md)

## Description

Given three integer arrays <code>nums1</code>, <code>nums2</code>, and <code>nums3</code>, return <em>a <strong>distinct</strong> array containing all the values that are present in <strong>at least two</strong> out of the three arrays. You may return the values in <strong>any</strong> order</em>.

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,1,3,2], nums2 = [2,3], nums3 = [3]
<strong>Output:</strong> [3,2]
<strong>Explanation:</strong> The values that are present in at least two arrays are:
- 3, in all three arrays.
- 2, in nums1 and nums2.
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [3,1], nums2 = [2,3], nums3 = [1,2]
<strong>Output:</strong> [2,3,1]
<strong>Explanation:</strong> The values that are present in at least two arrays are:
- 2, in nums2 and nums3.
- 3, in nums1 and nums2.
- 1, in nums1 and nums3.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2,2], nums2 = [4,3,3], nums3 = [5]
<strong>Output:</strong> []
<strong>Explanation:</strong> No value is present in at least two arrays.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length, nums3.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums1[i], nums2[j], nums3[k] &lt;= 100</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def twoOutOfThree(
        self, nums1: List[int], nums2: List[int], nums3: List[int]
    ) -> List[int]:
        s1, s2, s3 = set(nums1), set(nums2), set(nums3)
        ans = []
        for i in range(1, 101):
            a, b, c = i in s1, i in s2, i in s3
            if a + b + c > 1:
                ans.append(i)
        return ans
```

### **Java**

```java
class Solution {
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        int[] s1 = get(nums1), s2 = get(nums2), s3 = get(nums3);
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= 100; ++i) {
            if (s1[i] + s2[i] + s3[i] > 1) {
                ans.add(i);
            }
        }
        return ans;
    }

    private int[] get(int[] nums) {
        int[] s = new int[101];
        for (int num : nums) {
            s[num] = 1;
        }
        return s;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> twoOutOfThree(vector<int>& nums1, vector<int>& nums2, vector<int>& nums3) {
        auto s1 = get(nums1), s2 = get(nums2), s3 = get(nums3);
        vector<int> ans;
        for (int i = 1; i <= 100; ++i)
            if (s1[i] + s2[i] + s3[i] > 1)
                ans.push_back(i);
        return ans;
    }

    vector<int> get(vector<int>& nums) {
        vector<int> s(101);
        for (int num : nums) s[num] = 1;
        return s;
    }
};
```

### **Go**

```go
func twoOutOfThree(nums1 []int, nums2 []int, nums3 []int) []int {
	s1, s2, s3 := get(nums1), get(nums2), get(nums3)
	var ans []int
	for i := 1; i <= 100; i++ {
		a, b, c := 0, 0, 0
		if s1[i] {
			a++
		}
		if s2[i] {
			b++
		}
		if s3[i] {
			c++
		}
		if a+b+c > 1 {
			ans = append(ans, i)
		}
	}
	return ans
}

func get(nums []int) map[int]bool {
	s := make(map[int]bool, 101)
	for _, num := range nums {
		s[num] = true
	}
	return s
}
```

### **...**

```

```

<!-- tabs:end -->
