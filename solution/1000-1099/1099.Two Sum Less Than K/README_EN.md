# [1099. Two Sum Less Than K](https://leetcode.com/problems/two-sum-less-than-k)

[中文文档](/solution/1000-1099/1099.Two%20Sum%20Less%20Than%20K/README.md)

## Description

<p>Given an array <code>nums</code> of integers and&nbsp;integer <code>k</code>, return the maximum <code>sum</code> such that there exists <code>i &lt; j</code> with <code>nums[i] + nums[j] = sum</code> and <code>sum &lt; k</code>. If no <code>i</code>, <code>j</code> exist satisfying this equation, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [34,23,1,24,75,33,54,8], k = 60
<strong>Output:</strong> 58
<strong>Explanation: </strong>We can use 34 and 24 to sum 58 which is less than 60.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [10,20,30], k = 15
<strong>Output:</strong> -1
<strong>Explanation: </strong>In this case it is not possible to get a pair sum less that 15.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>1 &lt;= k &lt;= 2000</code></li>
</ul>

## Solutions

**Solution 1: Sorting + Binary Search**

We can first sort the array $nums$, and initialize the answer as $-1$.

Next, we enumerate each element $nums[i]$ in the array, and find the maximum $nums[j]$ in the array that satisfies $nums[j] + nums[i] < k$. Here, we can use binary search to speed up the search process. If we find such a $nums[j]$, then we can update the answer, i.e., $ans = \max(ans, nums[i] + nums[j])$.

After the enumeration ends, return the answer.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Here, $n$ is the length of the array $nums$.

**Solution 2: Sorting + Two Pointers**

Similar to Method 1, we can first sort the array $nums$, and initialize the answer as $-1$.

Next, we use two pointers $i$ and $j$ to point to the left and right ends of the array, respectively. Each time we judge whether $s = nums[i] + nums[j]$ is less than $k$. If it is less than $k$, then we can update the answer, i.e., $ans = \max(ans, s)$, and move $i$ one step to the right, otherwise move $j$ one step to the left.

After the enumeration ends, return the answer.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def twoSumLessThanK(self, nums: List[int], k: int) -> int:
        nums.sort()
        ans = -1
        for i, x in enumerate(nums):
            j = bisect_left(nums, k - x, lo=i + 1) - 1
            if i < j:
                ans = max(ans, x + nums[j])
        return ans
```

```python
class Solution:
    def twoSumLessThanK(self, nums: List[int], k: int) -> int:
        nums.sort()
        i, j = 0, len(nums) - 1
        ans = -1
        while i < j:
            if (s := nums[i] + nums[j]) < k:
                ans = max(ans, s)
                i += 1
            else:
                j -= 1
        return ans
```

### **Java**

```java
class Solution {
    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = -1;
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            int j = search(nums, k - nums[i], i + 1, n) - 1;
            if (i < j) {
                ans = Math.max(ans, nums[i] + nums[j]);
            }
        }
        return ans;
    }

    private int search(int[] nums, int x, int l, int r) {
        while (l < r) {
            int mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }
}
```

```java
class Solution {
    public int twoSumLessThanK(int[] nums, int k) {
        Arrays.sort(nums);
        int ans = -1;
        for (int i = 0, j = nums.length - 1; i < j;) {
            int s = nums[i] + nums[j];
            if (s < k) {
                ans = Math.max(ans, s);
                ++i;
            } else {
                --j;
            }
        }
        return ans;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    int twoSumLessThanK(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int ans = -1, n = nums.size();
        for (int i = 0; i < n; ++i) {
            int j = lower_bound(nums.begin() + i + 1, nums.end(), k - nums[i]) - nums.begin() - 1;
            if (i < j) {
                ans = max(ans, nums[i] + nums[j]);
            }
        }
        return ans;
    }
};
```

```cpp
class Solution {
public:
    int twoSumLessThanK(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int ans = -1;
        for (int i = 0, j = nums.size() - 1; i < j;) {
            int s = nums[i] + nums[j];
            if (s < k) {
                ans = max(ans, s);
                ++i;
            } else {
                --j;
            }
        }
        return ans;
    }
};
```

### **Go**

```go
func twoSumLessThanK(nums []int, k int) int {
	sort.Ints(nums)
	ans := -1
	for i, x := range nums {
		j := sort.SearchInts(nums[i+1:], k-x) + i
		if v := nums[i] + nums[j]; i < j && ans < v {
			ans = v
		}
	}
	return ans
}
```

```go
func twoSumLessThanK(nums []int, k int) int {
	sort.Ints(nums)
	ans := -1
	for i, j := 0, len(nums)-1; i < j; {
		if s := nums[i] + nums[j]; s < k {
			ans = max(ans, s)
			i++
		} else {
			j--
		}
	}
	return ans
}
```

### **TypeScript**

```ts
function twoSumLessThanK(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    let ans = -1;
    for (let i = 0, j = nums.length - 1; i < j; ) {
        const s = nums[i] + nums[j];
        if (s < k) {
            ans = Math.max(ans, s);
            ++i;
        } else {
            --j;
        }
    }
    return ans;
}
```

### **...**

```

```

<!-- tabs:end -->
