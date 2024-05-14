# [2475. Number of Unequal Triplets in Array](https://leetcode.com/problems/number-of-unequal-triplets-in-array)

[中文文档](/solution/2400-2499/2475.Number%20of%20Unequal%20Triplets%20in%20Array/README.md)

<!-- tags:Array,Hash Table,Sorting -->

<!-- difficulty:Easy -->

## Description

<p>You are given a <strong>0-indexed</strong> array of positive integers <code>nums</code>. Find the number of triplets <code>(i, j, k)</code> that meet the following conditions:</p>

<ul>
	<li><code>0 &lt;= i &lt; j &lt; k &lt; nums.length</code></li>
	<li><code>nums[i]</code>, <code>nums[j]</code>, and <code>nums[k]</code> are <strong>pairwise distinct</strong>.
	<ul>
		<li>In other words, <code>nums[i] != nums[j]</code>, <code>nums[i] != nums[k]</code>, and <code>nums[j] != nums[k]</code>.</li>
	</ul>
	</li>
</ul>

<p>Return <em>the number of triplets that meet the conditions.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,4,2,4,3]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The following triplets meet the conditions:
- (0, 2, 4) because 4 != 2 != 3
- (1, 2, 4) because 4 != 2 != 3
- (2, 3, 4) because 2 != 4 != 3
Since there are 3 triplets, we return 3.
Note that (2, 0, 4) is not a valid triplet because 2 &gt; 0.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1,1,1]
<strong>Output:</strong> 0
<strong>Explanation:</strong> No triplets meet the conditions so we return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
</ul>

## Solutions

### Solution 1: Brute Force Enumeration

We can directly enumerate all triples $(i, j, k)$ and count all the ones that meet the conditions.

The time complexity is $O(n^3)$, where $n$ is the length of the array $nums$. The space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def unequalTriplets(self, nums: List[int]) -> int:
        n = len(nums)
        ans = 0
        for i in range(n):
            for j in range(i + 1, n):
                for k in range(j + 1, n):
                    ans += (
                        nums[i] != nums[j] and nums[j] != nums[k] and nums[i] != nums[k]
                    )
        return ans
```

```java
class Solution {
    public int unequalTriplets(int[] nums) {
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                for (int k = j + 1; k < n; ++k) {
                    if (nums[i] != nums[j] && nums[j] != nums[k] && nums[i] != nums[k]) {
                        ++ans;
                    }
                }
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int unequalTriplets(vector<int>& nums) {
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = i + 1; j < n; ++j) {
                for (int k = j + 1; k < n; ++k) {
                    if (nums[i] != nums[j] && nums[j] != nums[k] && nums[i] != nums[k]) {
                        ++ans;
                    }
                }
            }
        }
        return ans;
    }
};
```

```go
func unequalTriplets(nums []int) (ans int) {
	n := len(nums)
	for i := 0; i < n; i++ {
		for j := i + 1; j < n; j++ {
			for k := j + 1; k < n; k++ {
				if nums[i] != nums[j] && nums[j] != nums[k] && nums[i] != nums[k] {
					ans++
				}
			}
		}
	}
	return
}
```

```ts
function unequalTriplets(nums: number[]): number {
    const n = nums.length;
    let ans = 0;
    for (let i = 0; i < n - 2; i++) {
        for (let j = i + 1; j < n - 1; j++) {
            for (let k = j + 1; k < n; k++) {
                if (nums[i] !== nums[j] && nums[j] !== nums[k] && nums[i] !== nums[k]) {
                    ans++;
                }
            }
        }
    }
    return ans;
}
```

```rust
impl Solution {
    pub fn unequal_triplets(nums: Vec<i32>) -> i32 {
        let n = nums.len();
        let mut ans = 0;
        for i in 0..n - 2 {
            for j in i + 1..n - 1 {
                for k in j + 1..n {
                    if nums[i] != nums[j] && nums[j] != nums[k] && nums[i] != nums[k] {
                        ans += 1;
                    }
                }
            }
        }
        ans
    }
}
```

<!-- tabs:end -->

### Solution 2: Sorting + Enumeration of Middle Elements + Binary Search

We can also sort the array $nums$ first.

Then traverse $nums$, enumerate the middle element $nums[j]$, and use binary search to find the nearest index $i$ on the left side of $nums[j]$ such that $nums[i] < nums[j]$; find the nearest index $k$ on the right side of $nums[j]$ such that $nums[k] > nums[j]$. Then the number of triples with $nums[j]$ as the middle element and meeting the conditions is $(i + 1) \times (n - k)$, which is added to the answer.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(\log n)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def unequalTriplets(self, nums: List[int]) -> int:
        nums.sort()
        ans, n = 0, len(nums)
        for j in range(1, n - 1):
            i = bisect_left(nums, nums[j], hi=j) - 1
            k = bisect_right(nums, nums[j], lo=j + 1)
            ans += (i >= 0 and k < n) * (i + 1) * (n - k)
        return ans
```

```java
class Solution {
    public int unequalTriplets(int[] nums) {
        Arrays.sort(nums);
        int ans = 0, n = nums.length;
        for (int j = 1; j < n - 1; ++j) {
            int i = search(nums, nums[j], 0, j) - 1;
            int k = search(nums, nums[j] + 1, j + 1, n);
            if (i >= 0 && k < n) {
                ans += (i + 1) * (n - k);
            }
        }
        return ans;
    }

    private int search(int[] nums, int x, int left, int right) {
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

```cpp
class Solution {
public:
    int unequalTriplets(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int ans = 0, n = nums.size();
        for (int j = 1; j < n - 1; ++j) {
            int i = lower_bound(nums.begin(), nums.begin() + j, nums[j]) - nums.begin() - 1;
            int k = upper_bound(nums.begin() + j + 1, nums.end(), nums[j]) - nums.begin();
            if (i >= 0 && k < n) {
                ans += (i + 1) * (n - k);
            }
        }
        return ans;
    }
};
```

```go
func unequalTriplets(nums []int) (ans int) {
	sort.Ints(nums)
	n := len(nums)
	for j := 1; j < n-1; j++ {
		i := sort.Search(j, func(h int) bool { return nums[h] >= nums[j] }) - 1
		k := sort.Search(n, func(h int) bool { return h > j && nums[h] > nums[j] })
		if i >= 0 && k < n {
			ans += (i + 1) * (n - k)
		}
	}
	return
}
```

```ts
function unequalTriplets(nums: number[]): number {
    const n = nums.length;
    const cnt = new Map<number, number>();
    for (const num of nums) {
        cnt.set(num, (cnt.get(num) ?? 0) + 1);
    }
    let ans = 0;
    let a = 0;
    for (const b of cnt.values()) {
        const c = n - a - b;
        ans += a * b * c;
        a += b;
    }
    return ans;
}
```

```rust
use std::collections::HashMap;
impl Solution {
    pub fn unequal_triplets(nums: Vec<i32>) -> i32 {
        let mut cnt = HashMap::new();
        for num in nums.iter() {
            *cnt.entry(num).or_insert(0) += 1;
        }
        let n = nums.len();
        let mut ans = 0;
        let mut a = 0;
        for v in cnt.values() {
            let b = n - a - v;
            ans += v * a * b;
            a += v;
        }
        ans as i32
    }
}
```

<!-- tabs:end -->

### Solution 3: Hash Table

We can also use a hash table $cnt$ to count the number of each element in the array $nums$.

Then traverse the hash table $cnt$, enumerate the number of middle elements $b$, and denote the number of elements on the left as $a$. Then the number of elements on the right is $c = n - a - b$. At this time, the number of triples that meet the conditions is $a \times b \times c$, which is added to the answer. Then update $a = a + b$ and continue to enumerate the number of middle elements $b$.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def unequalTriplets(self, nums: List[int]) -> int:
        cnt = Counter(nums)
        n = len(nums)
        ans = a = 0
        for b in cnt.values():
            c = n - a - b
            ans += a * b * c
            a += b
        return ans
```

```java
class Solution {
    public int unequalTriplets(int[] nums) {
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int v : nums) {
            cnt.merge(v, 1, Integer::sum);
        }
        int ans = 0, a = 0;
        int n = nums.length;
        for (int b : cnt.values()) {
            int c = n - a - b;
            ans += a * b * c;
            a += b;
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int unequalTriplets(vector<int>& nums) {
        unordered_map<int, int> cnt;
        for (int& v : nums) {
            ++cnt[v];
        }
        int ans = 0, a = 0;
        int n = nums.size();
        for (auto& [_, b] : cnt) {
            int c = n - a - b;
            ans += a * b * c;
            a += b;
        }
        return ans;
    }
};
```

```go
func unequalTriplets(nums []int) (ans int) {
	cnt := map[int]int{}
	for _, v := range nums {
		cnt[v]++
	}
	a, n := 0, len(nums)
	for _, b := range cnt {
		c := n - a - b
		ans += a * b * c
		a += b
	}
	return
}
```

```rust
use std::collections::HashMap;

impl Solution {
    pub fn unequal_triplets(nums: Vec<i32>) -> i32 {
        let cnt = nums.iter().fold(HashMap::new(), |mut map, &n| {
            *map.entry(n).or_insert(0) += 1;
            map
        });

        let mut ans = 0;
        let n = nums.len();
        let mut a = 0;
        for &b in cnt.values() {
            let c = n - a - b;
            ans += a * b * c;
            a += b;
        }

        ans as i32
    }
}
```

<!-- tabs:end -->

### Solution 4

<!-- tabs:start -->

```rust
impl Solution {
    pub fn unequal_triplets(nums: Vec<i32>) -> i32 {
        let mut ans = 0;
        let mut nums = nums;
        nums.sort();
        let n = nums.len();

        for i in 1..n - 1 {
            let mut l = 0;
            let mut r = i;
            while l < r {
                let mid = (l + r) >> 1;
                if nums[mid] >= nums[i] {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            let j = r;

            let mut l = i + 1;
            let mut r = n;
            while l < r {
                let mid = (l + r) >> 1;
                if nums[mid] > nums[i] {
                    r = mid;
                } else {
                    l = mid + 1;
                }
            }
            let k = r;

            ans += j * (n - k);
        }

        ans as i32
    }
}
```

<!-- tabs:end -->

<!-- end -->
