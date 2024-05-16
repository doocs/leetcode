---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1346.Check%20If%20N%20and%20Its%20Double%20Exist/README_EN.md
rating: 1225
source: Weekly Contest 175 Q1
tags:
    - Array
    - Hash Table
    - Two Pointers
    - Binary Search
    - Sorting
---

# [1346. Check If N and Its Double Exist](https://leetcode.com/problems/check-if-n-and-its-double-exist)

[中文文档](/solution/1300-1399/1346.Check%20If%20N%20and%20Its%20Double%20Exist/README.md)

## Description

<p>Given an array <code>arr</code> of integers, check if there exist two indices <code>i</code> and <code>j</code> such that :</p>

<ul>
	<li><code>i != j</code></li>
	<li><code>0 &lt;= i, j &lt; arr.length</code></li>
	<li><code>arr[i] == 2 * arr[j]</code></li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [10,2,5,3]
<strong>Output:</strong> true
<strong>Explanation:</strong> For i = 0 and j = 2, arr[i] == 10 == 2 * 5 == 2 * arr[j]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [3,1,7,11]
<strong>Output:</strong> false
<strong>Explanation:</strong> There is no i and j that satisfy the conditions.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= arr.length &lt;= 500</code></li>
	<li><code>-10<sup>3</sup> &lt;= arr[i] &lt;= 10<sup>3</sup></code></li>
</ul>

## Solutions

### Solution 1: Hash Table

We define a hash table $s$ to record the elements that have been visited.

Traverse the array $arr$. For each element $x$, if either double of $x$ or half of $x$ is in the hash table $s$, then return `true`. Otherwise, add $x$ to the hash table $s$.

If no element satisfying the condition is found after the traversal, return `false`.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array $arr$.

<!-- tabs:start -->

```python
class Solution:
    def checkIfExist(self, arr: List[int]) -> bool:
        s = set()
        for x in arr:
            if x * 2 in s or (x % 2 == 0 and x // 2 in s):
                return True
            s.add(x)
        return False
```

```java
class Solution {
    public boolean checkIfExist(int[] arr) {
        Set<Integer> s = new HashSet<>();
        for (int x : arr) {
            if (s.contains(x * 2) || ((x % 2 == 0 && s.contains(x / 2)))) {
                return true;
            }
            s.add(x);
        }
        return false;
    }
}
```

```cpp
class Solution {
public:
    bool checkIfExist(vector<int>& arr) {
        unordered_set<int> s;
        for (int x : arr) {
            if (s.contains(x * 2) || (x % 2 == 0 && s.contains(x / 2))) {
                return true;
            }
            s.insert(x);
        }
        return false;
    }
};
```

```go
func checkIfExist(arr []int) bool {
	s := map[int]bool{}
	for _, x := range arr {
		if s[x*2] || (x%2 == 0 && s[x/2]) {
			return true
		}
		s[x] = true
	}
	return false
}
```

```ts
function checkIfExist(arr: number[]): boolean {
    const s: Set<number> = new Set();
    for (const x of arr) {
        if (s.has(x * 2) || (x % 2 === 0 && s.has((x / 2) | 0))) {
            return true;
        }
        s.add(x);
    }
    return false;
}
```

<!-- tabs:end -->

<!-- end -->
