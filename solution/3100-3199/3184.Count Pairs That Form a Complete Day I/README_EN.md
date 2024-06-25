---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3100-3199/3184.Count%20Pairs%20That%20Form%20a%20Complete%20Day%20I/README_EN.md
tags:
    - Array
    - Hash Table
    - Counting
---

<!-- problem:start -->

# [3184. Count Pairs That Form a Complete Day I](https://leetcode.com/problems/count-pairs-that-form-a-complete-day-i)

[中文文档](/solution/3100-3199/3184.Count%20Pairs%20That%20Form%20a%20Complete%20Day%20I/README.md)

## Description

<!-- description:start -->

<p>Given an integer array <code>hours</code> representing times in <strong>hours</strong>, return an integer denoting the number of pairs <code>i</code>, <code>j</code> where <code>i &lt; j</code> and <code>hours[i] + hours[j]</code> forms a <strong>complete day</strong>.</p>

<p>A <strong>complete day</strong> is defined as a time duration that is an <strong>exact</strong> <strong>multiple</strong> of 24 hours.</p>

<p>For example, 1 day is 24 hours, 2 days is 48 hours, 3 days is 72 hours, and so on.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">hours = [12,12,30,24,24]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p>The pairs of indices that form a complete day are <code>(0, 1)</code> and <code>(3, 4)</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">hours = [72,48,24,3]</span></p>

<p><strong>Output:</strong> <span class="example-io">3</span></p>

<p><strong>Explanation:</strong></p>

<p>The pairs of indices that form a complete day are <code>(0, 1)</code>, <code>(0, 2)</code>, and <code>(1, 2)</code>.</p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= hours.length &lt;= 100</code></li>
	<li><code>1 &lt;= hours[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting

We can use a hash table or an array $\text{cnt}$ of length $24$ to record the occurrence count of each hour modulo $24$.

Iterate through the array $\text{hours}$. For each hour $x$, we can find the number that, when added to $x$, results in a multiple of $24$, and after modulo $24$, this number is $(24 - x \bmod 24) \bmod 24$. We then accumulate the occurrence count of this number from the hash table or array. After that, we increment the occurrence count of $x$ modulo $24$ by one.

After iterating through the array $\text{hours}$, we can obtain the number of index pairs that meet the problem requirements.

The time complexity is $O(n)$, where $n$ is the length of the array $\text{hours}$. The space complexity is $O(C)$, where $C=24$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def countCompleteDayPairs(self, hours: List[int]) -> int:
        cnt = Counter()
        ans = 0
        for x in hours:
            ans += cnt[(24 - (x % 24)) % 24]
            cnt[x % 24] += 1
        return ans
```

#### Java

```java
class Solution {
    public int countCompleteDayPairs(int[] hours) {
        int[] cnt = new int[24];
        int ans = 0;
        for (int x : hours) {
            ans += cnt[(24 - x % 24) % 24];
            ++cnt[x % 24];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int countCompleteDayPairs(vector<int>& hours) {
        int cnt[24]{};
        int ans = 0;
        for (int x : hours) {
            ans += cnt[(24 - x % 24) % 24];
            ++cnt[x % 24];
        }
        return ans;
    }
};
```

#### Go

```go
func countCompleteDayPairs(hours []int) (ans int) {
	cnt := [24]int{}
	for _, x := range hours {
		ans += cnt[(24-x%24)%24]
		cnt[x%24]++
	}
	return
}
```

#### TypeScript

```ts
function countCompleteDayPairs(hours: number[]): number {
    const cnt: number[] = Array(24).fill(0);
    let ans: number = 0;
    for (const x of hours) {
        ans += cnt[(24 - (x % 24)) % 24];
        ++cnt[x % 24];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
