# [2465. Number of Distinct Averages](https://leetcode.com/problems/number-of-distinct-averages)

[中文文档](/solution/2400-2499/2465.Number%20of%20Distinct%20Averages/README.md)

<!-- tags:Array,Hash Table,Two Pointers,Sorting -->

<!-- difficulty:Easy -->

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> of <strong>even</strong> length.</p>

<p>As long as <code>nums</code> is <strong>not</strong> empty, you must repetitively:</p>

<ul>
	<li>Find the minimum number in <code>nums</code> and remove it.</li>
	<li>Find the maximum number in <code>nums</code> and remove it.</li>
	<li>Calculate the average of the two removed numbers.</li>
</ul>

<p>The <strong>average</strong> of two numbers <code>a</code> and <code>b</code> is <code>(a + b) / 2</code>.</p>

<ul>
	<li>For example, the average of <code>2</code> and <code>3</code> is <code>(2 + 3) / 2 = 2.5</code>.</li>
</ul>

<p>Return<em> the number of <strong>distinct</strong> averages calculated using the above process</em>.</p>

<p><strong>Note</strong> that when there is a tie for a minimum or maximum number, any can be removed.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,1,4,0,3,5]
<strong>Output:</strong> 2
<strong>Explanation:</strong>
1. Remove 0 and 5, and the average is (0 + 5) / 2 = 2.5. Now, nums = [4,1,4,3].
2. Remove 1 and 4. The average is (1 + 4) / 2 = 2.5, and nums = [4,3].
3. Remove 3 and 4, and the average is (3 + 4) / 2 = 3.5.
Since there are 2 distinct numbers among 2.5, 2.5, and 3.5, we return 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,100]
<strong>Output:</strong> 1
<strong>Explanation:</strong>
There is only one average to be calculated after removing 1 and 100, so we return 1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 100</code></li>
	<li><code>nums.length</code> is even.</li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## Solutions

### Solution 1: Sorting

The problem requires us to find the minimum and maximum values in the array $nums$ each time, delete them, and then calculate the average of the two deleted numbers. Therefore, we can first sort the array $nums$, then take the first and last elements of the array each time, calculate their sum, use a hash table or array $cnt$ to record the number of times each sum appears, and finally count the number of different sums.

The time complexity is $O(n \times \log n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def distinctAverages(self, nums: List[int]) -> int:
        nums.sort()
        return len(set(nums[i] + nums[-i - 1] for i in range(len(nums) >> 1)))
```

```java
class Solution {
    public int distinctAverages(int[] nums) {
        Arrays.sort(nums);
        Set<Integer> s = new HashSet<>();
        int n = nums.length;
        for (int i = 0; i < n >> 1; ++i) {
            s.add(nums[i] + nums[n - i - 1]);
        }
        return s.size();
    }
}
```

```cpp
class Solution {
public:
    int distinctAverages(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        unordered_set<int> s;
        int n = nums.size();
        for (int i = 0; i < n >> 1; ++i) {
            s.insert(nums[i] + nums[n - i - 1]);
        }
        return s.size();
    }
};
```

```go
func distinctAverages(nums []int) (ans int) {
	sort.Ints(nums)
	n := len(nums)
	s := map[int]struct{}{}
	for i := 0; i < n>>1; i++ {
		s[nums[i]+nums[n-i-1]] = struct{}{}
	}
	return len(s)
}
```

```ts
function distinctAverages(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const s: Set<number> = new Set();
    const n = nums.length;
    for (let i = 0; i < n >> 1; ++i) {
        s.add(nums[i] + nums[n - i - 1]);
    }
    return s.size;
}
```

```rust
impl Solution {
    pub fn distinct_averages(nums: Vec<i32>) -> i32 {
        let mut nums = nums;
        nums.sort();
        let n = nums.len();
        let mut cnt = vec![0; 201];
        let mut ans = 0;

        for i in 0..n >> 1 {
            let x = (nums[i] + nums[n - i - 1]) as usize;
            cnt[x] += 1;

            if cnt[x] == 1 {
                ans += 1;
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

### Solution 2

<!-- tabs:start -->

```python
class Solution:
    def distinctAverages(self, nums: List[int]) -> int:
        nums.sort()
        ans = 0
        cnt = Counter()
        for i in range(len(nums) >> 1):
            x = nums[i] + nums[-i - 1]
            cnt[x] += 1
            if cnt[x] == 1:
                ans += 1
        return ans
```

```java
class Solution {
    public int distinctAverages(int[] nums) {
        Arrays.sort(nums);
        int[] cnt = new int[201];
        int n = nums.length;
        int ans = 0;
        for (int i = 0; i < n >> 1; ++i) {
            if (++cnt[nums[i] + nums[n - i - 1]] == 1) {
                ++ans;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int distinctAverages(vector<int>& nums) {
        sort(nums.begin(), nums.end());
        int cnt[201]{};
        int n = nums.size();
        int ans = 0;
        for (int i = 0; i < n >> 1; ++i) {
            if (++cnt[nums[i] + nums[n - i - 1]] == 1) {
                ++ans;
            }
        }
        return ans;
    }
};
```

```go
func distinctAverages(nums []int) (ans int) {
	sort.Ints(nums)
	n := len(nums)
	cnt := [201]int{}
	for i := 0; i < n>>1; i++ {
		x := nums[i] + nums[n-i-1]
		cnt[x]++
		if cnt[x] == 1 {
			ans++
		}
	}
	return
}
```

```ts
function distinctAverages(nums: number[]): number {
    nums.sort((a, b) => a - b);
    const cnt: number[] = Array(201).fill(0);
    let ans = 0;
    const n = nums.length;
    for (let i = 0; i < n >> 1; ++i) {
        if (++cnt[nums[i] + nums[n - i - 1]] === 1) {
            ++ans;
        }
    }
    return ans;
}
```

```rust
use std::collections::HashMap;

impl Solution {
    pub fn distinct_averages(nums: Vec<i32>) -> i32 {
        let mut h = HashMap::new();
        let mut nums = nums;
        let mut ans = 0;
        let n = nums.len();
        nums.sort();

        for i in 0..n >> 1 {
            let x = nums[i] + nums[n - i - 1];
            *h.entry(x).or_insert(0) += 1;

            if *h.get(&x).unwrap() == 1 {
                ans += 1;
            }
        }

        ans
    }
}
```

<!-- tabs:end -->

### Solution 3

<!-- tabs:start -->

```rust
use std::collections::HashSet;

impl Solution {
    pub fn distinct_averages(nums: Vec<i32>) -> i32 {
        let mut set = HashSet::new();
        let mut ans = 0;
        let n = nums.len();
        let mut nums = nums;
        nums.sort();

        for i in 0..n >> 1 {
            let x = nums[i] + nums[n - i - 1];

            if set.contains(&x) {
                continue;
            }

            set.insert(x);
            ans += 1;
        }

        ans
    }
}
```

<!-- tabs:end -->

<!-- end -->
