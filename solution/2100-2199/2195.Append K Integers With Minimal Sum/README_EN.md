# [2195. Append K Integers With Minimal Sum](https://leetcode.com/problems/append-k-integers-with-minimal-sum)

[中文文档](/solution/2100-2199/2195.Append%20K%20Integers%20With%20Minimal%20Sum/README.md)

## Description

<p>You are given an integer array <code>nums</code> and an integer <code>k</code>. Append <code>k</code> <strong>unique positive</strong> integers that do <strong>not</strong> appear in <code>nums</code> to <code>nums</code> such that the resulting total sum is <strong>minimum</strong>.</p>

<p>Return<em> the sum of the</em> <code>k</code> <em>integers appended to</em> <code>nums</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,4,25,10,25], k = 2
<strong>Output:</strong> 5
<strong>Explanation:</strong> The two unique positive integers that do not appear in nums which we append are 2 and 3.
The resulting sum of nums is 1 + 4 + 25 + 10 + 25 + 2 + 3 = 70, which is the minimum.
The sum of the two integers appended is 2 + 3 = 5, so we return 5.</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,6], k = 6
<strong>Output:</strong> 25
<strong>Explanation:</strong> The six unique positive integers that do not appear in nums which we append are 1, 2, 3, 4, 7, and 8.
The resulting sum of nums is 5 + 6 + 1 + 2 + 3 + 4 + 7 + 8 = 36, which is the minimum. 
The sum of the six integers appended is 1 + 2 + 3 + 4 + 7 + 8 = 25, so we return 25.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= k &lt;= 10<sup>8</sup></code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def minimalKSum(self, nums: List[int], k: int) -> int:
        nums.append(0)
        nums.append(2 * 10**9)
        nums.sort()
        ans = 0
        for a, b in pairwise(nums):
            n = min(k, b - a - 1)
            if n <= 0:
                continue
            k -= n
            ans += (a + 1 + a + n) * n // 2
            if k == 0:
                break
        return ans
```

### **Java**

```java
class Solution {
    public long minimalKSum(int[] nums, int k) {
        int[] arr = new int[nums.length + 2];
        arr[arr.length - 1] = (int) 2e9;
        for (int i = 0; i < nums.length; ++i) {
            arr[i + 1] = nums[i];
        }
        Arrays.sort(arr);
        long ans = 0;
        for (int i = 1; i < arr.length; ++i) {
            int a = arr[i - 1], b = arr[i];
            int n = Math.min(k, b - a - 1);
            if (n <= 0) {
                continue;
            }
            k -= n;
            ans += (long) (a + 1 + a + n) * n / 2;
            if (k == 0) {
                break;
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
    long long minimalKSum(vector<int>& nums, int k) {
        nums.push_back(0);
        nums.push_back(2e9);
        sort(nums.begin(), nums.end());
        long long ans = 0;
        for (int i = 1; i < nums.size(); ++i) {
            int a = nums[i - 1], b = nums[i];
            int n = min(k, b - a - 1);
            if (n <= 0) continue;
            k -= n;
            ans += 1ll * (a + 1 + a + n) * n / 2;
            if (k == 0) break;
        }
        return ans;
    }
};
```

### **Go**

```go
func minimalKSum(nums []int, k int) int64 {
	nums = append(nums, 0, 2e9)
	sort.Ints(nums)
	ans := 0
	for i := 1; i < len(nums); i++ {
		a, b := nums[i-1], nums[i]
		n := min(k, b-a-1)
		if n <= 0 {
			continue
		}
		k -= n
		ans += (a + 1 + a + n) * n / 2
		if k == 0 {
			break
		}
	}
	return int64(ans)
}

func min(a, b int) int {
	if a < b {
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
