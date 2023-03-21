# [2261. K Divisible Elements Subarrays](https://leetcode.com/problems/k-divisible-elements-subarrays)

[中文文档](/solution/2200-2299/2261.K%20Divisible%20Elements%20Subarrays/README.md)

## Description

<p>Given an integer array <code>nums</code> and two integers <code>k</code> and <code>p</code>, return <em>the number of <strong>distinct subarrays</strong> which have <strong>at most</strong></em> <code>k</code> <em>elements divisible by</em> <code>p</code>.</p>

<p>Two arrays <code>nums1</code> and <code>nums2</code> are said to be <strong>distinct</strong> if:</p>

<ul>
	<li>They are of <strong>different</strong> lengths, or</li>
	<li>There exists <strong>at least</strong> one index <code>i</code> where <code>nums1[i] != nums2[i]</code>.</li>
</ul>

<p>A <strong>subarray</strong> is defined as a <strong>non-empty</strong> contiguous sequence of elements in an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [<u><strong>2</strong></u>,3,3,<u><strong>2</strong></u>,<u><strong>2</strong></u>], k = 2, p = 2
<strong>Output:</strong> 11
<strong>Explanation:</strong>
The elements at indices 0, 3, and 4 are divisible by p = 2.
The 11 distinct subarrays which have at most k = 2 elements divisible by 2 are:
[2], [2,3], [2,3,3], [2,3,3,2], [3], [3,3], [3,3,2], [3,3,2,2], [3,2], [3,2,2], and [2,2].
Note that the subarrays [2] and [3] occur more than once in nums, but they should each be counted only once.
The subarray [2,3,3,2,2] should not be counted because it has 3 elements that are divisible by 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4], k = 4, p = 1
<strong>Output:</strong> 10
<strong>Explanation:</strong>
All element of nums are divisible by p = 1.
Also, every subarray of nums will have at most 4 elements that are divisible by 1.
Since all subarrays are distinct, the total number of subarrays satisfying all the constraints is 10.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 200</code></li>
	<li><code>1 &lt;= nums[i], p &lt;= 200</code></li>
	<li><code>1 &lt;= k &lt;= nums.length</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong></p>

<p>Can you solve this problem in O(n<sup>2</sup>) time complexity?</p>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def countDistinct(self, nums: List[int], k: int, p: int) -> int:
        n = len(nums)
        s = set()
        for i in range(n):
            cnt = 0
            for j in range(i, n):
                cnt += nums[j] % p == 0
                if cnt > k:
                    break
                s.add(tuple(nums[i: j + 1]))
        return len(s)
```

```python
class Solution:
    def countDistinct(self, nums: List[int], k: int, p: int) -> int:
        n = len(nums)
        s = set()
        for i in range(n):
            cnt = 0
            t = ""
            for x in nums[i:]:
                cnt += x % p == 0
                if cnt > k:
                    break
                t += str(x) + ","
                s.add(t)
        return len(s)
```

### **Java**

```java
class Solution {
    public int countDistinct(int[] nums, int k, int p) {
        int n = nums.length;
        Set<String> s = new HashSet<>();
        for (int i = 0; i < n; ++i) {
            int cnt = 0;
            String t = "";
            for (int j = i; j < n; ++j) {
                if (nums[j] % p == 0 && ++cnt > k) {
                    break;
                }
                t += nums[j] + ",";
                s.add(t);
            }
        }
        return s.size();
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int countDistinct(vector<int>& nums, int k, int p) {
        unordered_set<string> s;
        int n = nums.size();
        for (int i = 0; i < n; ++i) {
            int cnt = 0;
            string t;
            for (int j = i; j < n; ++j) {
                if (nums[j] % p == 0 && ++cnt > k) {
                    break;
                }
                t += to_string(nums[j]) + ",";
                s.insert(t);
            }
        }
        return s.size();
    }
};
```

### **Go**

```go
func countDistinct(nums []int, k int, p int) int {
	s := map[string]struct{}{}
	for i := range nums {
		cnt, t := 0, ""
		for _, x := range nums[i:] {
			if x%p == 0 {
				cnt++
				if cnt > k {
					break
				}
			}
			t += string(x) + ","
			s[t] = struct{}{}
		}
	}
	return len(s)
}
```

### **TypeScript**

```ts
function countDistinct(nums: number[], k: number, p: number): number {
    const n = nums.length;
    const s = new Set();
    for (let i = 0; i < n; ++i) {
        let cnt = 0;
        let t = '';
        for (let j = i; j < n; ++j) {
            if (nums[j] % p === 0 && ++cnt > k) {
                break;
            }
            t += nums[j].toString() + ',';
            s.add(t);
        }
    }
    return s.size;
}
```

### **...**

```

```

<!-- tabs:end -->
