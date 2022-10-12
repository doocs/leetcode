# [2321. Maximum Score Of Spliced Array](https://leetcode.com/problems/maximum-score-of-spliced-array)

[中文文档](/solution/2300-2399/2321.Maximum%20Score%20Of%20Spliced%20Array/README.md)

## Description

<p>You are given two <strong>0-indexed</strong> integer arrays <code>nums1</code> and <code>nums2</code>, both of length <code>n</code>.</p>

<p>You can choose two integers <code>left</code> and <code>right</code> where <code>0 &lt;= left &lt;= right &lt; n</code> and <strong>swap</strong> the subarray <code>nums1[left...right]</code> with the subarray <code>nums2[left...right]</code>.</p>

<ul>
	<li>For example, if <code>nums1 = [1,2,3,4,5]</code> and <code>nums2 = [11,12,13,14,15]</code> and you choose <code>left = 1</code> and <code>right = 2</code>, <code>nums1</code> becomes <code>[1,<strong><u>12,13</u></strong>,4,5]</code> and <code>nums2</code> becomes <code>[11,<strong><u>2,3</u></strong>,14,15]</code>.</li>
</ul>

<p>You may choose to apply the mentioned operation <strong>once</strong> or not do anything.</p>

<p>The <strong>score</strong> of the arrays is the <strong>maximum</strong> of <code>sum(nums1)</code> and <code>sum(nums2)</code>, where <code>sum(arr)</code> is the sum of all the elements in the array <code>arr</code>.</p>

<p>Return <em>the <strong>maximum possible score</strong></em>.</p>

<p>A <strong>subarray</strong> is a contiguous sequence of elements within an array. <code>arr[left...right]</code> denotes the subarray that contains the elements of <code>nums</code> between indices <code>left</code> and <code>right</code> (<strong>inclusive</strong>).</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [60,60,60], nums2 = [10,90,10]
<strong>Output:</strong> 210
<strong>Explanation:</strong> Choosing left = 1 and right = 1, we have nums1 = [60,<u><strong>90</strong></u>,60] and nums2 = [10,<u><strong>60</strong></u>,10].
The score is max(sum(nums1), sum(nums2)) = max(210, 80) = 210.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [20,40,20,70,30], nums2 = [50,20,50,40,20]
<strong>Output:</strong> 220
<strong>Explanation:</strong> Choosing left = 3, right = 4, we have nums1 = [20,40,20,<u><strong>40,20</strong></u>] and nums2 = [50,20,50,<u><strong>70,30</strong></u>].
The score is max(sum(nums1), sum(nums2)) = max(140, 220) = 220.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [7,11,13], nums2 = [1,1,1]
<strong>Output:</strong> 31
<strong>Explanation:</strong> We choose not to swap any subarray.
The score is max(sum(nums1), sum(nums2)) = max(31, 3) = 31.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums1.length == nums2.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums1[i], nums2[i] &lt;= 10<sup>4</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def maximumsSplicedArray(self, nums1: List[int], nums2: List[int]) -> int:
        def f(nums1, nums2):
            d = [a - b for a, b in zip(nums1, nums2)]
            t = mx = d[0]
            for v in d[1:]:
                if t > 0:
                    t += v
                else:
                    t = v
                mx = max(mx, t)
            return mx

        s1, s2 = sum(nums1), sum(nums2)
        return max(s2 + f(nums1, nums2), s1 + f(nums2, nums1))
```

### **Java**

```java
class Solution {
    public int maximumsSplicedArray(int[] nums1, int[] nums2) {
        int s1 = 0, s2 = 0, n = nums1.length;
        for (int i = 0; i < n; ++i) {
            s1 += nums1[i];
            s2 += nums2[i];
        }
        return Math.max(s2 + f(nums1, nums2), s1 + f(nums2, nums1));
    }

    private int f(int[] nums1, int[] nums2) {
        int t = nums1[0] - nums2[0];
        int mx = t;
        for (int i = 1; i < nums1.length; ++i) {
            int v = nums1[i] - nums2[i];
            if (t > 0) {
                t += v;
            } else {
                t = v;
            }
            mx = Math.max(mx, t);
        }
        return mx;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int maximumsSplicedArray(vector<int>& nums1, vector<int>& nums2) {
        int s1 = 0, s2 = 0, n = nums1.size();
        for (int i = 0; i < n; ++i) {
            s1 += nums1[i];
            s2 += nums2[i];
        }
        return max(s2 + f(nums1, nums2), s1 + f(nums2, nums1));
    }

    int f(vector<int>& nums1, vector<int>& nums2) {
        int t = nums1[0] - nums2[0];
        int mx = t;
        for (int i = 1; i < nums1.size(); ++i) {
            int v = nums1[i] - nums2[i];
            if (t > 0)
                t += v;
            else
                t = v;
            mx = max(mx, t);
        }
        return mx;
    }
};
```

### **Go**

```go
func maximumsSplicedArray(nums1 []int, nums2 []int) int {
	s1, s2 := 0, 0
	n := len(nums1)
	for i, v := range nums1 {
		s1 += v
		s2 += nums2[i]
	}
	f := func(nums1, nums2 []int) int {
		t := nums1[0] - nums2[0]
		mx := t
		for i := 1; i < n; i++ {
			v := nums1[i] - nums2[i]
			if t > 0 {
				t += v
			} else {
				t = v
			}
			mx = max(mx, t)
		}
		return mx
	}
	return max(s2+f(nums1, nums2), s1+f(nums2, nums1))
}

func max(a, b int) int {
	if a > b {
		return a
	}
	return b
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
