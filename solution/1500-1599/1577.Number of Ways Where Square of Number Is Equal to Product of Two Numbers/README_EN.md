# [1577. Number of Ways Where Square of Number Is Equal to Product of Two Numbers](https://leetcode.com/problems/number-of-ways-where-square-of-number-is-equal-to-product-of-two-numbers)

[中文文档](/solution/1500-1599/1577.Number%20of%20Ways%20Where%20Square%20of%20Number%20Is%20Equal%20to%20Product%20of%20Two%20Numbers/README.md)

## Description

<p>Given two arrays of integers <code>nums1</code> and <code>nums2</code>, return the number of triplets formed (type 1 and type 2) under the following rules:</p>

<ul>
	<li>Type 1: Triplet (i, j, k) if <code>nums1[i]<sup>2</sup> == nums2[j] * nums2[k]</code> where <code>0 &lt;= i &lt; nums1.length</code> and <code>0 &lt;= j &lt; k &lt; nums2.length</code>.</li>
	<li>Type 2: Triplet (i, j, k) if <code>nums2[i]<sup>2</sup> == nums1[j] * nums1[k]</code> where <code>0 &lt;= i &lt; nums2.length</code> and <code>0 &lt;= j &lt; k &lt; nums1.length</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [7,4], nums2 = [5,2,8,9]
<strong>Output:</strong> 1
<strong>Explanation:</strong> Type 1: (1, 1, 2), nums1[1]<sup>2</sup> = nums2[1] * nums2[2]. (4<sup>2</sup> = 2 * 8). 
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,1], nums2 = [1,1,1]
<strong>Output:</strong> 9
<strong>Explanation:</strong> All Triplets are valid, because 1<sup>2</sup> = 1 * 1.
Type 1: (0,0,1), (0,0,2), (0,1,2), (1,0,1), (1,0,2), (1,1,2).  nums1[i]<sup>2</sup> = nums2[j] * nums2[k].
Type 2: (0,0,1), (1,0,1), (2,0,1). nums2[i]<sup>2</sup> = nums1[j] * nums1[k].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [7,7,8,3], nums2 = [1,2,9,7]
<strong>Output:</strong> 2
<strong>Explanation:</strong> There are 2 valid triplets.
Type 1: (3,0,2).  nums1[3]<sup>2</sup> = nums2[0] * nums2[2].
Type 2: (3,0,1).  nums2[3]<sup>2</sup> = nums1[0] * nums1[1].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def numTriplets(self, nums1: List[int], nums2: List[int]) -> int:
        cnt1 = Counter(nums1)
        cnt2 = Counter(nums2)
        ans = 0
        for a, x in cnt1.items():
            for b, y in cnt2.items():
                if a * a % b == 0:
                    c = a * a // b
                    if b == c:
                        ans += x * y * (y - 1)
                    else:
                        ans += x * y * cnt2[c]
                if b * b % a == 0:
                    c = b * b // a
                    if a == c:
                        ans += x * (x - 1) * y
                    else:
                        ans += x * y * cnt1[c]
        return ans >> 1
```

### **Java**

```java
class Solution {
    public int numTriplets(int[] nums1, int[] nums2) {
        Map<Integer, Integer> cnt1 = new HashMap<>();
        Map<Integer, Integer> cnt2 = new HashMap<>();
        for (int v : nums1) {
            cnt1.put(v, cnt1.getOrDefault(v, 0) + 1);
        }
        for (int v : nums2) {
            cnt2.put(v, cnt2.getOrDefault(v, 0) + 1);
        }
        long ans = 0;
        for (var e1 : cnt1.entrySet()) {
            long a = e1.getKey(), x = e1.getValue();
            for (var e2 : cnt2.entrySet()) {
                long b = e2.getKey(), y = e2.getValue();
                if ((a * a) % b == 0) {
                    long c = a * a / b;
                    if (b == c) {
                        ans += x * y * (y - 1);
                    } else {
                        ans += x * y * cnt2.getOrDefault((int) c, 0);
                    }
                }
                if ((b * b) % a == 0) {
                    long c = b * b / a;
                    if (a == c) {
                        ans += x * (x - 1) * y;
                    } else {
                        ans += x * y * cnt1.getOrDefault((int) c, 0);
                    }
                }
            }
        }
        return (int) (ans >> 1);
    }
}
```

### **Go**

```go
func numTriplets(nums1 []int, nums2 []int) (ans int) {
	cnt1 := map[int]int{}
	cnt2 := map[int]int{}
	for _, v := range nums1 {
		cnt1[v]++
	}
	for _, v := range nums2 {
		cnt2[v]++
	}
	for a, x := range cnt1 {
		for b, y := range cnt2 {
			if a*a%b == 0 {
				c := a * a / b
				if b == c {
					ans += x * y * (y - 1)
				} else {
					ans += x * y * cnt2[c]
				}
			}
			if b*b%a == 0 {
				c := b * b / a
				if a == c {
					ans += x * (x - 1) * y
				} else {
					ans += x * y * cnt1[c]
				}
			}
		}
	}
	ans /= 2
	return
}
```

### **...**

```

```

<!-- tabs:end -->
