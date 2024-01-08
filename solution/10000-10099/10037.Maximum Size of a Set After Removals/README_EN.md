# [10037. Maximum Size of a Set After Removals](https://leetcode.com/problems/maximum-size-of-a-set-after-removals)

[中文文档](/solution/10000-10099/10037.Maximum%20Size%20of%20a%20Set%20After%20Removals/README.md)

## Description

<p>You are given two <strong>0-indexed</strong> integer arrays <code>nums1</code> and <code>nums2</code> of even length <code>n</code>.</p>

<p>You must remove <code>n / 2</code> elements from <code>nums1</code> and <code>n / 2</code> elements from <code>nums2</code>. After the removals, you insert the remaining elements of <code>nums1</code> and <code>nums2</code> into a set <code>s</code>.</p>

<p>Return <em>the <strong>maximum</strong> possible size of the set</em> <code>s</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2,1,2], nums2 = [1,1,1,1]
<strong>Output:</strong> 2
<strong>Explanation:</strong> We remove two occurences of 1 from nums1 and nums2. After the removals, the arrays become equal to nums1 = [2,2] and nums2 = [1,1]. Therefore, s = {1,2}.
It can be shown that 2 is the maximum possible size of the set s after the removals.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2,3,4,5,6], nums2 = [2,3,2,3,2,3]
<strong>Output:</strong> 5
<strong>Explanation:</strong> We remove 2, 3, and 6 from nums1, as well as 2 and two occurrences of 3 from nums2. After the removals, the arrays become equal to nums1 = [1,4,5] and nums2 = [2,3,2]. Therefore, s = {1,2,3,4,5}.
It can be shown that 5 is the maximum possible size of the set s after the removals.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,1,2,2,3,3], nums2 = [4,4,5,5,6,6]
<strong>Output:</strong> 6
<strong>Explanation:</strong> We remove 1, 2, and 3 from nums1, as well as 4, 5, and 6 from nums2. After the removals, the arrays become equal to nums1 = [1,2,3] and nums2 = [4,5,6]. Therefore, s = {1,2,3,4,5,6}.
It can be shown that 6 is the maximum possible size of the set s after the removals.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>n</code> is even.</li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 10<sup>9</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumSetSize(self, nums1: List[int], nums2: List[int]) -> int:
        s1 = set(nums1)
        s2 = set(nums2)
        n = len(nums1)
        a = min(len(s1 - s2), n // 2)
        b = min(len(s2 - s1), n // 2)
        return min(a + b + len(s1 & s2), n)
```

### **Java**

```java
class Solution {
    public int maximumSetSize(int[] nums1, int[] nums2) {
        Set<Integer> s1 = new HashSet<>();
        Set<Integer> s2 = new HashSet<>();
        for (int x : nums1) {
            s1.add(x);
        }
        for (int x : nums2) {
            s2.add(x);
        }
        int n = nums1.length;
        int a = 0, b = 0, c = 0;
        for (int x : s1) {
            if (!s2.contains(x)) {
                ++a;
            }
        }
        for (int x : s2) {
            if (!s1.contains(x)) {
                ++b;
            } else {
                ++c;
            }
        }
        a = Math.min(a, n / 2);
        b = Math.min(b, n / 2);
        return Math.min(a + b + c, n);
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumSetSize(vector<int>& nums1, vector<int>& nums2) {
        unordered_set<int> s1(nums1.begin(), nums1.end());
        unordered_set<int> s2(nums2.begin(), nums2.end());
        int n = nums1.size();
        int a = 0, b = 0, c = 0;
        for (int x : s1) {
            if (!s2.count(x)) {
                ++a;
            }
        }
        for (int x : s2) {
            if (!s1.count(x)) {
                ++b;
            } else {
                ++c;
            }
        }
        a = min(a, n / 2);
        b = min(b, n / 2);
        return min(a + b + c, n);
    }
};
```

### **Go**

```go
func maximumSetSize(nums1 []int, nums2 []int) int {
	s1 := map[int]bool{}
	s2 := map[int]bool{}
	for _, x := range nums1 {
		s1[x] = true
	}
	for _, x := range nums2 {
		s2[x] = true
	}
	a, b, c := 0, 0, 0
	for x := range s1 {
		if !s2[x] {
			a++
		}
	}
	for x := range s2 {
		if !s1[x] {
			b++
		} else {
			c++
		}
	}
	n := len(nums1)
	a = min(a, n/2)
	b = min(b, n/2)
	return min(a+b+c, n)
}
```

### **TypeScript**

```ts
function maximumSetSize(nums1: number[], nums2: number[]): number {
    const s1: Set<number> = new Set(nums1);
    const s2: Set<number> = new Set(nums2);
    const n = nums1.length;
    let [a, b, c] = [0, 0, 0];
    for (const x of s1) {
        if (!s2.has(x)) {
            ++a;
        }
    }
    for (const x of s2) {
        if (!s1.has(x)) {
            ++b;
        } else {
            ++c;
        }
    }
    a = Math.min(a, n >> 1);
    b = Math.min(b, n >> 1);
    return Math.min(a + b + c, n);
}
```

### **...**

```

```

<!-- tabs:end -->
