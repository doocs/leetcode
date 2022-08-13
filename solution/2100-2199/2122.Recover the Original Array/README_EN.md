# [2122. Recover the Original Array](https://leetcode.com/problems/recover-the-original-array)

[中文文档](/solution/2100-2199/2122.Recover%20the%20Original%20Array/README.md)

## Description

<p>Alice had a <strong>0-indexed</strong> array <code>arr</code> consisting of <code>n</code> <strong>positive</strong> integers. She chose an arbitrary <strong>positive integer</strong> <code>k</code> and created two new <strong>0-indexed</strong> integer arrays <code>lower</code> and <code>higher</code> in the following manner:</p>

<ol>
	<li><code>lower[i] = arr[i] - k</code>, for every index <code>i</code> where <code>0 &lt;= i &lt; n</code></li>
	<li><code>higher[i] = arr[i] + k</code>, for every index <code>i</code> where <code>0 &lt;= i &lt; n</code></li>
</ol>

<p>Unfortunately, Alice lost all three arrays. However, she remembers the integers that were present in the arrays <code>lower</code> and <code>higher</code>, but not the array each integer belonged to. Help Alice and recover the original array.</p>

<p>Given an array <code>nums</code> consisting of <code>2n</code> integers, where <strong>exactly</strong> <code>n</code> of the integers were present in <code>lower</code> and the remaining in <code>higher</code>, return <em>the <strong>original</strong> array</em> <code>arr</code>. In case the answer is not unique, return <em><strong>any</strong> valid array</em>.</p>

<p><strong>Note:</strong> The test cases are generated such that there exists <strong>at least one</strong> valid array <code>arr</code>.</p>

<p>&nbsp;</p>
<p><strong>Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,10,6,4,8,12]
<strong>Output:</strong> [3,7,11]
<strong>Explanation:</strong>
If arr = [3,7,11] and k = 1, we get lower = [2,6,10] and higher = [4,8,12].
Combining lower and higher gives us [2,6,10,4,8,12], which is a permutation of nums.
Another valid possibility is that arr = [5,7,9] and k = 3. In that case, lower = [2,4,6] and higher = [8,10,12]. 
</pre>

<p><strong>Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,3,3]
<strong>Output:</strong> [2,2]
<strong>Explanation:</strong>
If arr = [2,2] and k = 1, we get lower = [1,1] and higher = [3,3].
Combining lower and higher gives us [1,1,3,3], which is equal to nums.
Note that arr cannot be [1,3] because in that case, the only possible way to obtain [1,1,3,3] is with k = 0.
This is invalid since k must be positive.
</pre>

<p><strong>Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [5,435]
<strong>Output:</strong> [220]
<strong>Explanation:</strong>
The only possible combination is arr = [220] and k = 215. Using them, we get lower = [5] and higher = [435].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 * n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li>The test cases are generated such that there exists <strong>at least one</strong> valid array <code>arr</code>.</li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def recoverArray(self, nums: List[int]) -> List[int]:
        nums.sort()
        n = len(nums)
        for i in range(1, n):
            d = nums[i] - nums[0]
            if d == 0 or d % 2 == 1:
                continue
            vis = [False] * n
            vis[i] = True
            ans = [(nums[0] + nums[i]) >> 1]
            l, r = 1, i + 1
            while r < n:
                while l < n and vis[l]:
                    l += 1
                while r < n and nums[r] - nums[l] < d:
                    r += 1
                if r == n or nums[r] - nums[l] > d:
                    break
                vis[r] = True
                ans.append((nums[l] + nums[r]) >> 1)
                l, r = l + 1, r + 1
            if len(ans) == (n >> 1):
                return ans
        return []
```

### **Java**

```java
class Solution {
    public int[] recoverArray(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1, n = nums.length; i < n; ++i) {
            int d = nums[i] - nums[0];
            if (d == 0 || d % 2 == 1) {
                continue;
            }
            boolean[] vis = new boolean[n];
            vis[i] = true;
            List<Integer> t = new ArrayList<>();
            t.add((nums[0] + nums[i]) >> 1);
            for (int l = 1, r = i + 1; r < n; ++l, ++r) {
                while (l < n && vis[l]) {
                    ++l;
                }
                while (r < n && nums[r] - nums[l] < d) {
                    ++r;
                }
                if (r == n || nums[r] - nums[l] > d) {
                    break;
                }
                vis[r] = true;
                t.add((nums[l] + nums[r]) >> 1);
            }
            if (t.size() == (n >> 1)) {
                int[] ans = new int[t.size()];
                int idx = 0;
                for (int e : t) {
                    ans[idx++] = e;
                }
                return ans;
            }
        }
        return null;
    }
}
```

### **C++**

```cpp
class Solution {
public:
    vector<int> recoverArray(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        for (int i = 1, n = nums.size(); i < n; ++i) {
            int d = nums[i] - nums[0];
            if (d == 0 || d % 2 == 1) continue;
            vector<bool> vis(n);
            vis[i] = true;
            vector<int> ans;
            ans.push_back((nums[0] + nums[i]) >> 1);
            for (int l = 1, r = i + 1; r < n; ++l, ++r) {
                while (l < n && vis[l]) ++l;
                while (r < n && nums[r] - nums[l] < d) ++r;
                if (r == n || nums[r] - nums[l] > d) break;
                vis[r] = true;
                ans.push_back((nums[l] + nums[r]) >> 1);
            }
            if (ans.size() == (n >> 1)) return ans;
        }
        return {};
    }
};
```

### **Go**

```go
func recoverArray(nums []int) []int {
	sort.Ints(nums)
	for i, n := 1, len(nums); i < n; i++ {
		d := nums[i] - nums[0]
		if d == 0 || d%2 == 1 {
			continue
		}
		vis := make([]bool, n)
		vis[i] = true
		ans := []int{(nums[0] + nums[i]) >> 1}
		for l, r := 1, i+1; r < n; l, r = l+1, r+1 {
			for l < n && vis[l] {
				l++
			}
			for r < n && nums[r]-nums[l] < d {
				r++
			}
			if r == n || nums[r]-nums[l] > d {
				break
			}
			vis[r] = true
			ans = append(ans, (nums[l]+nums[r])>>1)
		}
		if len(ans) == (n >> 1) {
			return ans
		}
	}
	return []int{}
}
```

### **TypeScript**

```ts

```

### **...**

```

```

<!-- tabs:end -->
