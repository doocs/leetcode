---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1800-1899/1852.Distinct%20Numbers%20in%20Each%20Subarray/README_EN.md
tags:
    - Array
    - Hash Table
    - Sliding Window
---

<!-- problem:start -->

# [1852. Distinct Numbers in Each Subarray 🔒](https://leetcode.com/problems/distinct-numbers-in-each-subarray)

[中文文档](/solution/1800-1899/1852.Distinct%20Numbers%20in%20Each%20Subarray/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>nums</code> and an integer <code>k</code>, you are asked to construct the array <code>ans</code> of size <code>n-k+1</code> where <code>ans[i]</code> is the number of <strong>distinct</strong> numbers in the subarray <code>nums[i:i+k-1] = [nums[i], nums[i+1], ..., nums[i+k-1]]</code>.</p>

<p>Return <em>the array </em><code>ans</code>.</p>

<p>&nbsp;</p>

<p><strong class="example">Example 1:</strong></p>

<pre>

<strong>Input:</strong> nums = [1,2,3,2,2,1,3], k = 3

<strong>Output:</strong> [3,2,2,2,3]

<strong>Explanation: </strong>The number of distinct elements in each subarray goes as follows:

- nums[0:2] = [1,2,3] so ans[0] = 3

- nums[1:3] = [2,3,2] so ans[1] = 2

- nums[2:4] = [3,2,2] so ans[2] = 2

- nums[3:5] = [2,2,1] so ans[3] = 2

- nums[4:6] = [2,1,3] so ans[4] = 3

</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>

<strong>Input:</strong> nums = [1,1,1,1,2,3,4], k = 4

<strong>Output:</strong> [1,2,3,4]

<strong>Explanation: </strong>The number of distinct elements in each subarray goes as follows:

- nums[0:3] = [1,1,1,1] so ans[0] = 1

- nums[1:4] = [1,1,1,2] so ans[1] = 2

- nums[2:5] = [1,1,2,3] so ans[2] = 3

- nums[3:6] = [1,2,3,4] so ans[3] = 4

</pre>

<p>&nbsp;</p>

<p><strong>Constraints:</strong></p>

<ul>

    <li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>

    <li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>

</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sliding Window + Hash Table

We use a hash table $cnt$ to record the occurrence times of each number in the subarray of length $k$.

Next, we first traverse the first $k$ elements of the array, record the occurrence times of each element, and after the traversal, we take the size of the hash table as the first element of the answer array.

Then, we continue to traverse the array from the index $k$. Each time we traverse, we increase the occurrence times of the current element by one, and decrease the occurrence times of the element on the left of the current element by one. If the occurrence times of the left element become $0$ after subtraction, we remove it from the hash table. Then we take the size of the hash table as the next element of the answer array, and continue to traverse.

After the traversal, we return the answer array.

The time complexity is $O(n)$, and the space complexity is $O(k)$. Where $n$ is the length of the array $nums$, and $k$ is the parameter given by the problem.

<!-- tabs:start -->

```python
class Solution:
    def distinctNumbers(self, nums: List[int], k: int) -> List[int]:
        cnt = Counter(nums[:k])
        ans = [len(cnt)]
        for i in range(k, len(nums)):
            cnt[nums[i]] += 1
            cnt[nums[i - k]] -= 1
            if cnt[nums[i - k]] == 0:
                cnt.pop(nums[i - k])
            ans.append(len(cnt))
        return ans
```

```java
class Solution {
    public int[] distinctNumbers(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int i = 0; i < k; ++i) {
            cnt.merge(nums[i], 1, Integer::sum);
        }
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        ans[0] = cnt.size();
        for (int i = k; i < n; ++i) {
            cnt.merge(nums[i], 1, Integer::sum);
            if (cnt.merge(nums[i - k], -1, Integer::sum) == 0) {
                cnt.remove(nums[i - k]);
            }
            ans[i - k + 1] = cnt.size();
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> distinctNumbers(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        for (int i = 0; i < k; ++i) {
            ++cnt[nums[i]];
        }
        int n = nums.size();
        vector<int> ans;
        ans.push_back(cnt.size());
        for (int i = k; i < n; ++i) {
            ++cnt[nums[i]];
            if (--cnt[nums[i - k]] == 0) {
                cnt.erase(nums[i - k]);
            }
            ans.push_back(cnt.size());
        }
        return ans;
    }
};
```

```go
func distinctNumbers(nums []int, k int) []int {
	cnt := map[int]int{}
	for _, x := range nums[:k] {
		cnt[x]++
	}
	ans := []int{len(cnt)}
	for i := k; i < len(nums); i++ {
		cnt[nums[i]]++
		cnt[nums[i-k]]--
		if cnt[nums[i-k]] == 0 {
			delete(cnt, nums[i-k])
		}
		ans = append(ans, len(cnt))
	}
	return ans
}
```

```ts
function distinctNumbers(nums: number[], k: number): number[] {
    const cnt: Map<number, number> = new Map();
    for (let i = 0; i < k; ++i) {
        cnt.set(nums[i], (cnt.get(nums[i]) ?? 0) + 1);
    }
    const ans: number[] = [cnt.size];
    for (let i = k; i < nums.length; ++i) {
        cnt.set(nums[i], (cnt.get(nums[i]) ?? 0) + 1);
        cnt.set(nums[i - k], cnt.get(nums[i - k])! - 1);
        if (cnt.get(nums[i - k]) === 0) {
            cnt.delete(nums[i - k]);
        }
        ans.push(cnt.size);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Sliding Window + Array

We can also use an array to replace the hash table, which can improve performance to some extent.

The time complexity is $O(n)$, and the space complexity is $O(M)$. Where $n$ is the length of the array $nums$, and $M$ is the maximum value in the array $nums$. In this problem, $M \leq 10^5$.

<!-- tabs:start -->

```java
class Solution {
    public int[] distinctNumbers(int[] nums, int k) {
        int m = 0;
        for (int x : nums) {
            m = Math.max(m, x);
        }
        int[] cnt = new int[m + 1];
        int v = 0;
        for (int i = 0; i < k; ++i) {
            if (++cnt[nums[i]] == 1) {
                ++v;
            }
        }
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        ans[0] = v;
        for (int i = k; i < n; ++i) {
            if (++cnt[nums[i]] == 1) {
                ++v;
            }
            if (--cnt[nums[i - k]] == 0) {
                --v;
            }
            ans[i - k + 1] = v;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> distinctNumbers(vector<int>& nums, int k) {
        int m = *max_element(begin(nums), end(nums));
        int cnt[m + 1];
        memset(cnt, 0, sizeof(cnt));
        int n = nums.size();
        int v = 0;
        vector<int> ans(n - k + 1);
        for (int i = 0; i < k; ++i) {
            if (++cnt[nums[i]] == 1) {
                ++v;
            }
        }
        ans[0] = v;
        for (int i = k; i < n; ++i) {
            if (++cnt[nums[i]] == 1) {
                ++v;
            }
            if (--cnt[nums[i - k]] == 0) {
                --v;
            }
            ans[i - k + 1] = v;
        }
        return ans;
    }
};
```

```go
func distinctNumbers(nums []int, k int) (ans []int) {
	m := slices.Max(nums)
	cnt := make([]int, m+1)
	v := 0
	for _, x := range nums[:k] {
		cnt[x]++
		if cnt[x] == 1 {
			v++
		}
	}
	ans = append(ans, v)
	for i := k; i < len(nums); i++ {
		cnt[nums[i]]++
		if cnt[nums[i]] == 1 {
			v++
		}
		cnt[nums[i-k]]--
		if cnt[nums[i-k]] == 0 {
			v--
		}
		ans = append(ans, v)
	}
	return
}
```

```ts
function distinctNumbers(nums: number[], k: number): number[] {
    const m = Math.max(...nums);
    const cnt: number[] = Array(m + 1).fill(0);
    let v: number = 0;
    for (let i = 0; i < k; ++i) {
        if (++cnt[nums[i]] === 1) {
            v++;
        }
    }
    const ans: number[] = [v];
    for (let i = k; i < nums.length; ++i) {
        if (++cnt[nums[i]] === 1) {
            v++;
        }
        if (--cnt[nums[i - k]] === 0) {
            v--;
        }
        ans.push(v);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
