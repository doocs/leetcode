---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3300-3399/3318.Find%20X-Sum%20of%20All%20K-Long%20Subarrays%20I/README_EN.md
rating: 1457
source: Weekly Contest 419 Q1
tags:
    - Array
    - Hash Table
    - Sliding Window
    - Heap (Priority Queue)
---

<!-- problem:start -->

# [3318. Find X-Sum of All K-Long Subarrays I](https://leetcode.com/problems/find-x-sum-of-all-k-long-subarrays-i)

[中文文档](/solution/3300-3399/3318.Find%20X-Sum%20of%20All%20K-Long%20Subarrays%20I/README.md)

## Description

<!-- description:start -->

<p>You are given an array <code>nums</code> of <code>n</code> integers and two integers <code>k</code> and <code>x</code>.</p>

<p>The <strong>x-sum</strong> of an array is calculated by the following procedure:</p>

<ul>
	<li>Count the occurrences of all elements in the array.</li>
	<li>Keep only the occurrences of the top <code>x</code> most frequent elements. If two elements have the same number of occurrences, the element with the <strong>bigger</strong> value is considered more frequent.</li>
	<li>Calculate the sum of the resulting array.</li>
</ul>

<p><strong>Note</strong> that if an array has less than <code>x</code> distinct elements, its <strong>x-sum</strong> is the sum of the array.</p>

<p>Return an integer array <code>answer</code> of length <code>n - k + 1</code> where <code>answer[i]</code> is the <strong>x-sum</strong> of the <span data-keyword="subarray-nonempty">subarray</span> <code>nums[i..i + k - 1]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [1,1,2,2,3,4,2,3], k = 6, x = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[6,10,12]</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>For subarray <code>[1, 1, 2, 2, 3, 4]</code>, only elements 1 and 2 will be kept in the resulting array. Hence, <code>answer[0] = 1 + 1 + 2 + 2</code>.</li>
	<li>For subarray <code>[1, 2, 2, 3, 4, 2]</code>, only elements 2 and 4 will be kept in the resulting array. Hence, <code>answer[1] = 2 + 2 + 2 + 4</code>. Note that 4 is kept in the array since it is bigger than 3 and 1 which occur the same number of times.</li>
	<li>For subarray <code>[2, 2, 3, 4, 2, 3]</code>, only elements 2 and 3 are kept in the resulting array. Hence, <code>answer[2] = 2 + 2 + 2 + 3 + 3</code>.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,8,7,8,7,5], k = 2, x = 2</span></p>

<p><strong>Output:</strong> <span class="example-io">[11,15,15,15,12]</span></p>

<p><strong>Explanation:</strong></p>

<p>Since <code>k == x</code>, <code>answer[i]</code> is equal to the sum of the subarray <code>nums[i..i + k - 1]</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n == nums.length &lt;= 50</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 50</code></li>
	<li><code>1 &lt;= x &lt;= k &lt;= nums.length</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table + Ordered Set

We use a hash table $\textit{cnt}$ to count the occurrences of each element in the window, an ordered set $\textit{l}$ to store the $x$ elements with the highest occurrences in the window, and another ordered set $\textit{r}$ to store the remaining elements.

We maintain a variable $\textit{s}$ to represent the sum of the elements in $\textit{l}$. Initially, we add the first $k$ elements to the window, update the ordered sets $\textit{l}$ and $\textit{r}$, and calculate the value of $\textit{s}$. If the size of $\textit{l}$ is less than $x$ and $\textit{r}$ is not empty, we repeatedly move the largest element from $\textit{r}$ to $\textit{l}$ until the size of $\textit{l}$ equals $x$, updating the value of $\textit{s}$ in the process. If the size of $\textit{l}$ is greater than $x$, we repeatedly move the smallest element from $\textit{l}$ to $\textit{r}$ until the size of $\textit{l}$ equals $x$, updating the value of $\textit{s}$ in the process. At this point, we can calculate the current window's $\textit{x-sum}$ and add it to the answer array. Then we remove the left boundary element of the window, update $\textit{cnt}$, and update the ordered sets $\textit{l}$ and $\textit{r}$, as well as the value of $\textit{s}$. Continue traversing the array until the traversal is complete.

The time complexity is $O(n \times \log k)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $\textit{nums}$.

Similar problems:

-   [3013. Divide an Array Into Subarrays With Minimum Cost II](/solution/3000-3099/3013.Divide%20an%20Array%20Into%20Subarrays%20With%20Minimum%20Cost%20II/README_EN.md)

<!-- tabs:start -->

#### Python3

```python
from sortedcontainers import SortedList


class Solution:
    def findXSum(self, nums: List[int], k: int, x: int) -> List[int]:
        def add(v: int):
            if cnt[v] == 0:
                return
            p = (cnt[v], v)
            if l and p > l[0]:
                nonlocal s
                s += p[0] * p[1]
                l.add(p)
            else:
                r.add(p)

        def remove(v: int):
            if cnt[v] == 0:
                return
            p = (cnt[v], v)
            if p in l:
                nonlocal s
                s -= p[0] * p[1]
                l.remove(p)
            else:
                r.remove(p)

        l = SortedList()
        r = SortedList()
        cnt = Counter()
        s = 0
        n = len(nums)
        ans = [0] * (n - k + 1)
        for i, v in enumerate(nums):
            remove(v)
            cnt[v] += 1
            add(v)
            j = i - k + 1
            if j < 0:
                continue
            while r and len(l) < x:
                p = r.pop()
                l.add(p)
                s += p[0] * p[1]
            while len(l) > x:
                p = l.pop(0)
                s -= p[0] * p[1]
                r.add(p)
            ans[j] = s

            remove(nums[j])
            cnt[nums[j]] -= 1
            add(nums[j])
        return ans
```

#### Java

```java
class Solution {
    private TreeSet<int[]> l = new TreeSet<>((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
    private TreeSet<int[]> r = new TreeSet<>(l.comparator());
    private Map<Integer, Integer> cnt = new HashMap<>();
    private int s;

    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        for (int i = 0; i < n; ++i) {
            int v = nums[i];
            remove(v);
            cnt.merge(v, 1, Integer::sum);
            add(v);
            int j = i - k + 1;
            if (j < 0) {
                continue;
            }
            while (!r.isEmpty() && l.size() < x) {
                var p = r.pollLast();
                s += p[0] * p[1];
                l.add(p);
            }
            while (l.size() > x) {
                var p = l.pollFirst();
                s -= p[0] * p[1];
                r.add(p);
            }
            ans[j] = s;

            remove(nums[j]);
            cnt.merge(nums[j], -1, Integer::sum);
            add(nums[j]);
        }
        return ans;
    }

    private void remove(int v) {
        if (!cnt.containsKey(v)) {
            return;
        }
        var p = new int[] {cnt.get(v), v};
        if (l.contains(p)) {
            l.remove(p);
            s -= p[0] * p[1];
        } else {
            r.remove(p);
        }
    }

    private void add(int v) {
        if (!cnt.containsKey(v)) {
            return;
        }
        var p = new int[] {cnt.get(v), v};
        if (!l.isEmpty() && l.comparator().compare(l.first(), p) < 0) {
            l.add(p);
            s += p[0] * p[1];
        } else {
            r.add(p);
        }
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> findXSum(vector<int>& nums, int k, int x) {
        using pii = pair<int, int>;
        set<pii> l, r;
        int s = 0;
        unordered_map<int, int> cnt;
        auto add = [&](int v) {
            if (cnt[v] == 0) {
                return;
            }
            pii p = {cnt[v], v};
            if (!l.empty() && p > *l.begin()) {
                s += p.first * p.second;
                l.insert(p);
            } else {
                r.insert(p);
            }
        };
        auto remove = [&](int v) {
            if (cnt[v] == 0) {
                return;
            }
            pii p = {cnt[v], v};
            auto it = l.find(p);
            if (it != l.end()) {
                s -= p.first * p.second;
                l.erase(it);
            } else {
                r.erase(p);
            }
        };
        vector<int> ans;
        for (int i = 0; i < nums.size(); ++i) {
            remove(nums[i]);
            ++cnt[nums[i]];
            add(nums[i]);

            int j = i - k + 1;
            if (j < 0) {
                continue;
            }

            while (!r.empty() && l.size() < x) {
                pii p = *r.rbegin();
                s += p.first * p.second;
                r.erase(p);
                l.insert(p);
            }
            while (l.size() > x) {
                pii p = *l.begin();
                s -= p.first * p.second;
                l.erase(p);
                r.insert(p);
            }
            ans.push_back(s);

            remove(nums[j]);
            --cnt[nums[j]];
            add(nums[j]);
        }
        return ans;
    }
};
```

#### Go

```go

```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
