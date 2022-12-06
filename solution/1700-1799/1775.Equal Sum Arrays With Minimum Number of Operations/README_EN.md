# [1775. Equal Sum Arrays With Minimum Number of Operations](https://leetcode.com/problems/equal-sum-arrays-with-minimum-number-of-operations)

[中文文档](/solution/1700-1799/1775.Equal%20Sum%20Arrays%20With%20Minimum%20Number%20of%20Operations/README.md)

## Description

<p>You are given two arrays of integers <code>nums1</code> and <code><font face="monospace">nums2</font></code>, possibly of different lengths. The values in the arrays are between <code>1</code> and <code>6</code>, inclusive.</p>

<p>In one operation, you can change any integer&#39;s value in <strong>any </strong>of the arrays to <strong>any</strong> value between <code>1</code> and <code>6</code>, inclusive.</p>

<p>Return <em>the minimum number of operations required to make the sum of values in </em><code>nums1</code><em> equal to the sum of values in </em><code>nums2</code><em>.</em> Return <code>-1</code>​​​​​ if it is not possible to make the sum of the two arrays equal.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2,3,4,5,6], nums2 = [1,1,2,2,2,2]
<strong>Output:</strong> 3
<strong>Explanation:</strong> You can make the sums of nums1 and nums2 equal with 3 operations. All indices are 0-indexed.
- Change nums2[0] to 6. nums1 = [1,2,3,4,5,6], nums2 = [<u><strong>6</strong></u>,1,2,2,2,2].
- Change nums1[5] to 1. nums1 = [1,2,3,4,5,<strong><u>1</u></strong>], nums2 = [6,1,2,2,2,2].
- Change nums1[2] to 2. nums1 = [1,2,<strong><u>2</u></strong>,4,5,1], nums2 = [6,1,2,2,2,2].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,1,1,1,1,1,1], nums2 = [6]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There is no way to decrease the sum of nums1 or to increase the sum of nums2 to make them equal.
</pre>

<p><strong class="example">Example 3:</strong></p>

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
        arr = [6 - v for v in nums1] + [v - 1 for v in nums2]
        d = s2 - s1
        for i, v in enumerate(sorted(arr, reverse=True), 1):
            d -= v
            if d <= 0:
                return i
        return -1
```

```python
class Solution:
    def minOperations(self, nums1: List[int], nums2: List[int]) -> int:
        s1, s2 = sum(nums1), sum(nums2)
        if s1 == s2:
            return 0
        if s1 > s2:
            return self.minOperations(nums2, nums1)
        cnt = Counter([6 - v for v in nums1] + [v - 1 for v in nums2])
        d = s2 - s1
        ans = 0
        for i in range(5, 0, -1):
            while cnt[i] and d > 0:
                d -= i
                cnt[i] -= 1
                ans += 1
        return ans if d <= 0 else -1
```

### **Java**

```java
class Solution {
    public int minOperations(int[] nums1, int[] nums2) {
        int s1 = Arrays.stream(nums1).sum();
        int s2 = Arrays.stream(nums2).sum();
        if (s1 == s2) {
            return 0;
        }
        if (s1 > s2) {
            return minOperations(nums2, nums1);
        }
        int d = s2 - s1;
        int[] arr = new int[nums1.length + nums2.length];
        int k = 0;
        for (int v : nums1) {
            arr[k++] = 6 - v;
        }
        for (int v : nums2) {
            arr[k++] = v - 1;
        }
        Arrays.sort(arr);
        for (int i = 1, j = arr.length - 1; j >= 0; ++i, --j) {
            d -= arr[j];
            if (d <= 0) {
                return i;
            }
        }
        return -1;
    }
}
```

```java
class Solution {
    public int minOperations(int[] nums1, int[] nums2) {
        int s1 = Arrays.stream(nums1).sum();
        int s2 = Arrays.stream(nums2).sum();
        if (s1 == s2) {
            return 0;
        }
        if (s1 > s2) {
            return minOperations(nums2, nums1);
        }
        int d = s2 - s1;
        int[] cnt = new int[6];
        for (int v : nums1) {
            ++cnt[6 - v];
        }
        for (int v : nums2) {
            ++cnt[v - 1];
        }
        int ans = 0;
        for (int i = 5; i > 0; --i) {
            while (cnt[i] > 0 && d > 0) {
                d -= i;
                --cnt[i];
                ++ans;
            }
        }
        return d <= 0 ? ans : -1;
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
        int d = s2 - s1;
        int arr[nums1.size() + nums2.size()];
        int k = 0;
        for (int& v : nums1) arr[k++] = 6 - v;
        for (int& v : nums2) arr[k++] = v - 1;
        sort(arr, arr + k, greater<>());
        for (int i = 0; i < k; ++i) {
            d -= arr[i];
            if (d <= 0) return i + 1;
        }
        return -1;
    }
};
```

```cpp
class Solution {
public:
    int minOperations(vector<int>& nums1, vector<int>& nums2) {
        int s1 = accumulate(nums1.begin(), nums1.end(), 0);
        int s2 = accumulate(nums2.begin(), nums2.end(), 0);
        if (s1 == s2) return 0;
        if (s1 > s2) return minOperations(nums2, nums1);
        int d = s2 - s1;
        int cnt[6] = {0};
        for (int& v : nums1) ++cnt[6 - v];
        for (int& v : nums2) ++cnt[v - 1];
        int ans = 0;
        for (int i = 5; i; --i) {
            while (cnt[i] && d > 0) {
                d -= i;
                --cnt[i];
                ++ans;
            }
        }
        return d <= 0 ? ans : -1;
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
	d := s2 - s1
	arr := []int{}
	for _, v := range nums1 {
		arr = append(arr, 6-v)
	}
	for _, v := range nums2 {
		arr = append(arr, v-1)
	}
	sort.Sort(sort.Reverse(sort.IntSlice(arr)))
	for i, v := range arr {
		d -= v
		if d <= 0 {
			return i + 1
		}
	}
	return -1
}

func sum(nums []int) (s int) {
	for _, v := range nums {
		s += v
	}
	return
}
```

```go
func minOperations(nums1 []int, nums2 []int) (ans int) {
	s1, s2 := sum(nums1), sum(nums2)
	if s1 == s2 {
		return 0
	}
	if s1 > s2 {
		return minOperations(nums2, nums1)
	}
	d := s2 - s1
	cnt := [6]int{}
	for _, v := range nums1 {
		cnt[6-v]++
	}
	for _, v := range nums2 {
		cnt[v-1]++
	}
	for i := 5; i > 0; i-- {
		for cnt[i] > 0 && d > 0 {
			d -= i
			cnt[i]--
			ans++
		}
	}
	if d <= 0 {
		return
	}
	return -1
}

func sum(nums []int) (s int) {
	for _, v := range nums {
		s += v
	}
	return
}
```

### **...**

```

```

<!-- tabs:end -->
