---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1300-1399/1365.How%20Many%20Numbers%20Are%20Smaller%20Than%20the%20Current%20Number/README_EN.md
rating: 1152
source: Weekly Contest 178 Q1
tags:
    - Array
    - Hash Table
    - Counting Sort
    - Sorting
---

<!-- problem:start -->

# [1365. How Many Numbers Are Smaller Than the Current Number](https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number)

[中文文档](/solution/1300-1399/1365.How%20Many%20Numbers%20Are%20Smaller%20Than%20the%20Current%20Number/README.md)

## Description

<!-- description:start -->

<p>Given the array <code>nums</code>, for each <code>nums[i]</code> find out how many numbers in the array are smaller than it. That is, for each <code>nums[i]</code> you have to count the number of valid <code>j&#39;s</code>&nbsp;such that&nbsp;<code>j != i</code> <strong>and</strong> <code>nums[j] &lt; nums[i]</code>.</p>

<p>Return the answer in an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [8,1,2,2,3]
<strong>Output:</strong> [4,0,1,1,3]
<strong>Explanation:</strong> 
For nums[0]=8 there exist four smaller numbers than it (1, 2, 2 and 3). 
For nums[1]=1 does not exist any smaller number than it.
For nums[2]=2 there exist one smaller number than it (1). 
For nums[3]=2 there exist one smaller number than it (1). 
For nums[4]=3 there exist three smaller numbers than it (1, 2 and 2).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [6,5,4,8]
<strong>Output:</strong> [2,1,0,3]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [7,7,7,7]
<strong>Output:</strong> [0,0,0,0]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 500</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Sorting + Binary Search

We can make a copy of the array $nums$, denoted as $arr$, and then sort $arr$ in ascending order.

Next, for each element $x$ in $nums$, we can use binary search to find the index $j$ of the first element that is greater than or equal to $x$. Then $j$ is the number of elements that are smaller than $x$. We can store $j$ in the answer array.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array $nums$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallerNumbersThanCurrent(self, nums: List[int]) -> List[int]:
        arr = sorted(nums)
        return [bisect_left(arr, x) for x in nums]
```

#### Java

```java
class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] arr = nums.clone();
        Arrays.sort(arr);
        for (int i = 0; i < nums.length; ++i) {
            nums[i] = search(arr, nums[i]);
        }
        return nums;
    }

    private int search(int[] nums, int x) {
        int l = 0, r = nums.length;
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

#### C++

```cpp
class Solution {
public:
    vector<int> smallerNumbersThanCurrent(vector<int>& nums) {
        vector<int> arr = nums;
        sort(arr.begin(), arr.end());
        for (int i = 0; i < nums.size(); ++i) {
            nums[i] = lower_bound(arr.begin(), arr.end(), nums[i]) - arr.begin();
        }
        return nums;
    }
};
```

#### Go

```go
func smallerNumbersThanCurrent(nums []int) (ans []int) {
	arr := make([]int, len(nums))
	copy(arr, nums)
	sort.Ints(arr)
	for i, x := range nums {
		nums[i] = sort.SearchInts(arr, x)
	}
	return nums
}
```

#### TypeScript

```ts
function smallerNumbersThanCurrent(nums: number[]): number[] {
    const search = (nums: number[], x: number) => {
        let l = 0,
            r = nums.length;
        while (l < r) {
            const mid = (l + r) >> 1;
            if (nums[mid] >= x) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    };
    const arr = nums.slice().sort((a, b) => a - b);
    for (let i = 0; i < nums.length; ++i) {
        nums[i] = search(arr, nums[i]);
    }
    return nums;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Counting Sort + Prefix Sum

We notice that the range of elements in the array $nums$ is $[0, 100]$. Therefore, we can use the counting sort method to first count the number of each element in the array $nums$. Then we calculate the prefix sum of the counting array. Finally, we traverse the array $nums$. For each element $x$, we directly add the value of the element at index $x$ in the counting array to the answer array.

The time complexity is $O(n + M)$, and the space complexity is $O(M)$. Where $n$ and $M$ are the length and the maximum value of the array $nums$, respectively.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def smallerNumbersThanCurrent(self, nums: List[int]) -> List[int]:
        cnt = [0] * 102
        for x in nums:
            cnt[x + 1] += 1
        s = list(accumulate(cnt))
        return [s[x] for x in nums]
```

#### Java

```java
class Solution {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] cnt = new int[102];
        for (int x : nums) {
            ++cnt[x + 1];
        }
        for (int i = 1; i < cnt.length; ++i) {
            cnt[i] += cnt[i - 1];
        }
        int n = nums.length;
        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = cnt[nums[i]];
        }
        return ans;
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> smallerNumbersThanCurrent(vector<int>& nums) {
        int cnt[102]{};
        for (int& x : nums) {
            ++cnt[x + 1];
        }
        for (int i = 1; i < 102; ++i) {
            cnt[i] += cnt[i - 1];
        }
        vector<int> ans;
        for (int& x : nums) {
            ans.push_back(cnt[x]);
        }
        return ans;
    }
};
```

#### Go

```go
func smallerNumbersThanCurrent(nums []int) (ans []int) {
	cnt := [102]int{}
	for _, x := range nums {
		cnt[x+1]++
	}
	for i := 1; i < len(cnt); i++ {
		cnt[i] += cnt[i-1]
	}
	for _, x := range nums {
		ans = append(ans, cnt[x])
	}
	return
}
```

#### TypeScript

```ts
function smallerNumbersThanCurrent(nums: number[]): number[] {
    const cnt: number[] = new Array(102).fill(0);
    for (const x of nums) {
        ++cnt[x + 1];
    }
    for (let i = 1; i < cnt.length; ++i) {
        cnt[i] += cnt[i - 1];
    }
    const n = nums.length;
    const ans: number[] = new Array(n);
    for (let i = 0; i < n; ++i) {
        ans[i] = cnt[nums[i]];
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
