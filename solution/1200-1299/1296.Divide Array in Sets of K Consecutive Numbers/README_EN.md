---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1200-1299/1296.Divide%20Array%20in%20Sets%20of%20K%20Consecutive%20Numbers/README_EN.md
rating: 1490
source: Weekly Contest 168 Q2
tags:
    - Greedy
    - Array
    - Hash Table
    - Sorting
---

<!-- problem:start -->

# [1296. Divide Array in Sets of K Consecutive Numbers](https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers)

[中文文档](/solution/1200-1299/1296.Divide%20Array%20in%20Sets%20of%20K%20Consecutive%20Numbers/README.md)

## Description

<!-- description:start -->

<p>Given an array of integers <code>nums</code> and a positive integer <code>k</code>, check whether it is possible to divide this array into sets of <code>k</code> consecutive numbers.</p>

<p>Return <code>true</code> <em>if it is possible</em>.<strong> </strong>Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,3,4,4,5,6], k = 4
<strong>Output:</strong> true
<strong>Explanation:</strong> Array can be divided into [1,2,3,4] and [3,4,5,6].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,1,2,3,4,3,4,5,9,10,11], k = 3
<strong>Output:</strong> true
<strong>Explanation:</strong> Array can be divided into [1,2,3] , [2,3,4] , [3,4,5] and [9,10,11].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4], k = 3
<strong>Output:</strong> false
<strong>Explanation:</strong> Each array should be divided in subarrays of size 3.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>
<strong>Note:</strong> This question is the same as&nbsp;846:&nbsp;<a href="https://leetcode.com/problems/hand-of-straights/" target="_blank">https://leetcode.com/problems/hand-of-straights/</a>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Sorting

We use a hash table $\textit{cnt}$ to count the occurrences of each number in the array $\textit{nums}$, and then sort the array $\textit{nums}$.

Next, we traverse the array $\textit{nums}$. For each number $v$ in the array, if the count of $v$ in the hash table $\textit{cnt}$ is not zero, we enumerate each number from $v$ to $v+k-1$. If the counts of these numbers in the hash table $\textit{cnt}$ are all non-zero, we decrement the counts of these numbers by 1. If the count becomes zero after decrementing, we remove these numbers from the hash table $\textit{cnt}$. Otherwise, it means we cannot divide the array into several subarrays of length $k$, and we return `false`. If we can divide the array into several subarrays of length $k$, we return `true` after the traversal.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isPossibleDivide(self, nums: List[int], k: int) -> bool:
        cnt = Counter(nums)
        for v in sorted(nums):
            if cnt[v]:
                for x in range(v, v + k):
                    if cnt[x] == 0:
                        return False
                    cnt[x] -= 1
                    if cnt[x] == 0:
                        cnt.pop(x)
        return True
```

#### Java

```java
class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int v : nums) {
            cnt.merge(v, 1, Integer::sum);
        }
        Arrays.sort(nums);
        for (int v : nums) {
            if (cnt.containsKey(v)) {
                for (int x = v; x < v + k; ++x) {
                    if (!cnt.containsKey(x)) {
                        return false;
                    }
                    if (cnt.merge(x, -1, Integer::sum) == 0) {
                        cnt.remove(x);
                    }
                }
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isPossibleDivide(vector<int>& nums, int k) {
        unordered_map<int, int> cnt;
        for (int& v : nums) ++cnt[v];
        sort(nums.begin(), nums.end());
        for (int& v : nums) {
            if (cnt.count(v)) {
                for (int x = v; x < v + k; ++x) {
                    if (!cnt.count(x)) {
                        return false;
                    }
                    if (--cnt[x] == 0) {
                        cnt.erase(x);
                    }
                }
            }
        }
        return true;
    }
};
```

#### Go

```go
func isPossibleDivide(nums []int, k int) bool {
	cnt := map[int]int{}
	for _, v := range nums {
		cnt[v]++
	}
	sort.Ints(nums)
	for _, v := range nums {
		if _, ok := cnt[v]; ok {
			for x := v; x < v+k; x++ {
				if _, ok := cnt[x]; !ok {
					return false
				}
				cnt[x]--
				if cnt[x] == 0 {
					delete(cnt, x)
				}
			}
		}
	}
	return true
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 1: Ordered Set

We can also use an ordered set to count the occurrences of each number in the array $\textit{nums}$.

Next, we loop to extract the minimum value $v$ from the ordered set, then enumerate each number from $v$ to $v+k-1$. If the occurrences of these numbers in the ordered set are all non-zero, we decrement the occurrence count of these numbers by 1. If the occurrence count becomes 0 after decrementing, we remove the number from the ordered set. Otherwise, it means we cannot divide the array into several subarrays of length $k$, and we return `false`. If we can divide the array into several subarrays of length $k$, we return `true` after the traversal.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def isPossibleDivide(self, nums: List[int], k: int) -> bool:
        if len(nums) % k != 0:
            return False
        sd = SortedDict()
        for h in nums:
            if h in sd:
                sd[h] += 1
            else:
                sd[h] = 1
        while sd:
            v = sd.peekitem(0)[0]
            for i in range(v, v + k):
                if i not in sd:
                    return False
                if sd[i] == 1:
                    sd.pop(i)
                else:
                    sd[i] -= 1
        return True
```

#### Java

```java
class Solution {
    public boolean isPossibleDivide(int[] nums, int k) {
        if (nums.length % k != 0) {
            return false;
        }
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        for (int h : nums) {
            tm.merge(h, 1, Integer::sum);
        }
        while (!tm.isEmpty()) {
            int v = tm.firstKey();
            for (int i = v; i < v + k; ++i) {
                if (!tm.containsKey(i)) {
                    return false;
                }
                if (tm.merge(i, -1, Integer::sum) == 0) {
                    tm.remove(i);
                }
            }
        }
        return true;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool isPossibleDivide(vector<int>& nums, int k) {
        if (nums.size() % k) {
            return false;
        }
        map<int, int> mp;
        for (int& h : nums) {
            mp[h] += 1;
        }
        while (!mp.empty()) {
            int v = mp.begin()->first;
            for (int i = v; i < v + k; ++i) {
                if (!mp.contains(i)) {
                    return false;
                }
                if (--mp[i] == 0) {
                    mp.erase(i);
                }
            }
        }
        return true;
    }
};
```

#### Go

```go
func isPossibleDivide(nums []int, k int) bool {
	if len(nums)%k != 0 {
		return false
	}
	m := treemap.NewWithIntComparator()
	for _, h := range nums {
		if v, ok := m.Get(h); ok {
			m.Put(h, v.(int)+1)
		} else {
			m.Put(h, 1)
		}
	}
	for !m.Empty() {
		v, _ := m.Min()
		for i := v.(int); i < v.(int)+k; i++ {
			if _, ok := m.Get(i); !ok {
				return false
			}
			if v, _ := m.Get(i); v.(int) == 1 {
				m.Remove(i)
			} else {
				m.Put(i, v.(int)-1)
			}
		}
	}
	return true
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
