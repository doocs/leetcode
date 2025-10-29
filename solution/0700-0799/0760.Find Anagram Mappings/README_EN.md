---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/0700-0799/0760.Find%20Anagram%20Mappings/README_EN.md
tags:
    - Array
    - Hash Table
---

<!-- problem:start -->

# [760. Find Anagram Mappings 🔒](https://leetcode.com/problems/find-anagram-mappings)

[中文文档](/solution/0700-0799/0760.Find%20Anagram%20Mappings/README.md)

## Description

<!-- description:start -->

<p>You are given two integer arrays <code>nums1</code> and <code>nums2</code> where <code>nums2</code> is <strong>an anagram</strong> of <code>nums1</code>. Both arrays may contain duplicates.</p>

<p>Return <em>an index mapping array </em><code>mapping</code><em> from </em><code>nums1</code><em> to </em><code>nums2</code><em> where </em><code>mapping[i] = j</code><em> means the </em><code>i<sup>th</sup></code><em> element in </em><code>nums1</code><em> appears in </em><code>nums2</code><em> at index </em><code>j</code>. If there are multiple answers, return <strong>any of them</strong>.</p>

<p>An array <code>a</code> is <strong>an anagram</strong> of an array <code>b</code> means <code>b</code> is made by randomizing the order of the elements in <code>a</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [12,28,46,32,50], nums2 = [50,12,32,46,28]
<strong>Output:</strong> [1,4,3,2,0]
<strong>Explanation:</strong> As mapping[0] = 1 because the 0<sup>th</sup> element of nums1 appears at nums2[1], and mapping[1] = 4 because the 1<sup>st</sup> element of nums1 appears at nums2[4], and so on.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [84,46], nums2 = [84,46]
<strong>Output:</strong> [0,1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length &lt;= 100</code></li>
	<li><code>nums2.length == nums1.length</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 10<sup>5</sup></code></li>
	<li><code>nums2</code> is an anagram of <code>nums1</code>.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Hash Table

We use a hash table $\textit{d}$ to store each element of the array $\textit{nums2}$ and its corresponding index. Then we iterate through the array $\textit{nums1}$, and for each element $\textit{nums1}[i]$, we retrieve its corresponding index from the hash table $\textit{d}$ and store it in the result array.

The time complexity is $O(n)$ and the space complexity is $O(n)$, where $n$ is the length of the array.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def anagramMappings(self, nums1: List[int], nums2: List[int]) -> List[int]:
        d = {x: i for i, x in enumerate(nums2)}
        return [d[x] for x in nums1]
```

#### Java

```java
class Solution {
    public int[] anagramMappings(int[] nums1, int[] nums2) {
        int n = nums1.length;
        Map<Integer, Integer> d = new HashMap<>(n);
        for (int i = 0; i < n; ++i) {
            d.put(nums2[i], i);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = d.get(nums1[i]);
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> anagramMappings(vector<int>& nums1, vector<int>& nums2) {
        int n = nums1.size();
        unordered_map<int, int> d;
        for (int i = 0; i < n; ++i) {
            d[nums2[i]] = i;
        }
        vector<int> ans;
        for (int x : nums1) {
            ans.push_back(d[x]);
        }
        return ans;
    }
};
```

#### Go

```go
func anagramMappings(nums1 []int, nums2 []int) []int {
	d := map[int]int{}
	for i, x := range nums2 {
		d[x] = i
	}
	ans := make([]int, len(nums1))
	for i, x := range nums1 {
		ans[i] = d[x]
	}
	return ans
}
```

#### TypeScript

```ts
function anagramMappings(nums1: number[], nums2: number[]): number[] {
    const d: Map<number, number> = new Map();
    for (let i = 0; i < nums2.length; ++i) {
        d.set(nums2[i], i);
    }
    return nums1.map(num => d.get(num)!);
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
