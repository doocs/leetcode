---
comments: true
difficulty: Medium
edit_url: https://github.com/doocs/leetcode/edit/main/solution/3800-3899/3843.First%20Element%20with%20Unique%20Frequency/README_EN.md
rating: 1347
source: Weekly Contest 489 Q2
---

<!-- problem:start -->

# [3843. First Element with Unique Frequency](https://leetcode.com/problems/first-element-with-unique-frequency)

[中文文档](/solution/3800-3899/3843.First%20Element%20with%20Unique%20Frequency/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>nums</code>.</p>

<p>Return an integer denoting the <strong>first</strong> element (scanning from left to right) in <code>nums</code> whose <strong>frequency</strong> is <strong>unique</strong>. That is, no other integer appears the same number of times in <code>nums</code>. If there is no such element, return -1.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [20,10,30,30]</span></p>

<p><strong>Output:</strong> <span class="example-io">30</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>20 appears once.</li>
	<li>10 appears once.</li>
	<li>30 appears twice.</li>
	<li>The frequency of 30 is unique because no other integer appears exactly twice.</li>
</ul>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [20,20,10,30,30,30]</span></p>

<p><strong>Output:</strong> <span class="example-io">20</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>20 appears twice.</li>
	<li>10 appears once.</li>
	<li>30 appears 3 times.</li>
	<li>The frequency of 20, 10, and 30 are unique. The first element that has unique frequency is 20.</li>
</ul>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [10,10,20,20]</span></p>

<p><strong>Output:</strong> <span class="example-io">-1</span></p>

<p><strong>Explanation:</strong></p>

<ul>
	<li>10 appears twice.</li>
	<li>20 appears twice.</li>
	<li>No element has a unique frequency.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We use a hash table $\textit{cnt}$ to count the occurrences of each element, and then use another hash table $\textit{freq}$ to count the frequency of each occurrence count. Finally, we traverse the array $\textit{nums}$ again. For each element $x$, if the value of $\textit{freq}[\textit{cnt}[x]]$ is 1, it means the occurrence frequency of $x$ is unique, and we return $x$. If no such element is found after traversing, return -1.

The time complexity is $O(n)$, and the space complexity is $O(n)$, where $n$ is the length of the array $\textit{nums}$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def firstUniqueFreq(self, nums: List[int]) -> int:
        cnt = Counter(nums)
        freq = Counter(cnt.values())
        for x in nums:
            if freq[cnt[x]] == 1:
                return x
        return -1
```

#### Java

```java
class Solution {
    public int firstUniqueFreq(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int x : nums) {
            cnt.merge(x, 1, Integer::sum);
        }
        Map<Integer, Integer> freq = new HashMap<>();
        for (int v : cnt.values()) {
            freq.merge(v, 1, Integer::sum);
        }
        for (int x : nums) {
            if (freq.get(cnt.get(x)) == 1) {
                return x;
            }
        }
        return -1;
    }
}
```

#### C++

```cpp
class Solution {
public:
    int firstUniqueFreq(vector<int>& nums) {
        unordered_map<int, int> cnt;
        for (int x : nums) {
            ++cnt[x];
        }

        unordered_map<int, int> freq;
        for (auto& [_, v] : cnt) {
            ++freq[v];
        }

        for (int x : nums) {
            if (freq[cnt[x]] == 1) {
                return x;
            }
        }

        return -1;
    }
};
```

#### Go

```go
func firstUniqueFreq(nums []int) int {
	cnt := make(map[int]int)
	for _, x := range nums {
		cnt[x]++
	}

	freq := make(map[int]int)
	for _, v := range cnt {
		freq[v]++
	}

	for _, x := range nums {
		if freq[cnt[x]] == 1 {
			return x
		}
	}

	return -1
}
```

#### TypeScript

```ts
function firstUniqueFreq(nums: number[]): number {
    const cnt = new Map<number, number>();
    for (const x of nums) {
        cnt.set(x, (cnt.get(x) ?? 0) + 1);
    }

    const freq = new Map<number, number>();
    for (const v of cnt.values()) {
        freq.set(v, (freq.get(v) ?? 0) + 1);
    }

    for (const x of nums) {
        if (freq.get(cnt.get(x)!) === 1) {
            return x;
        }
    }

    return -1;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
