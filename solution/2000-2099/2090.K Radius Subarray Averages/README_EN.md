# [2090. K Radius Subarray Averages](https://leetcode.com/problems/k-radius-subarray-averages)

[中文文档](/solution/2000-2099/2090.K%20Radius%20Subarray%20Averages/README.md)

<!-- tags:Array,Sliding Window -->

<!-- difficulty:Medium -->

## Description

<p>You are given a <strong>0-indexed</strong> array <code>nums</code> of <code>n</code> integers, and an integer <code>k</code>.</p>

<p>The <strong>k-radius average</strong> for a subarray of <code>nums</code> <strong>centered</strong> at some index <code>i</code> with the <strong>radius</strong> <code>k</code> is the average of <strong>all</strong> elements in <code>nums</code> between the indices <code>i - k</code> and <code>i + k</code> (<strong>inclusive</strong>). If there are less than <code>k</code> elements before <strong>or</strong> after the index <code>i</code>, then the <strong>k-radius average</strong> is <code>-1</code>.</p>

<p>Build and return <em>an array </em><code>avgs</code><em> of length </em><code>n</code><em> where </em><code>avgs[i]</code><em> is the <strong>k-radius average</strong> for the subarray centered at index </em><code>i</code>.</p>

<p>The <strong>average</strong> of <code>x</code> elements is the sum of the <code>x</code> elements divided by <code>x</code>, using <strong>integer division</strong>. The integer division truncates toward zero, which means losing its fractional part.</p>

<ul>
	<li>For example, the average of four elements <code>2</code>, <code>3</code>, <code>1</code>, and <code>5</code> is <code>(2 + 3 + 1 + 5) / 4 = 11 / 4 = 2.75</code>, which truncates to <code>2</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://fastly.jsdelivr.net/gh/doocs/leetcode@main/solution/2000-2099/2090.K%20Radius%20Subarray%20Averages/images/eg1.png" style="width: 343px; height: 119px;" />
<pre>
<strong>Input:</strong> nums = [7,4,3,9,1,8,5,2,6], k = 3
<strong>Output:</strong> [-1,-1,-1,5,4,4,-1,-1,-1]
<strong>Explanation:</strong>
- avg[0], avg[1], and avg[2] are -1 because there are less than k elements <strong>before</strong> each index.
- The sum of the subarray centered at index 3 with radius 3 is: 7 + 4 + 3 + 9 + 1 + 8 + 5 = 37.
  Using <strong>integer division</strong>, avg[3] = 37 / 7 = 5.
- For the subarray centered at index 4, avg[4] = (4 + 3 + 9 + 1 + 8 + 5 + 2) / 7 = 4.
- For the subarray centered at index 5, avg[5] = (3 + 9 + 1 + 8 + 5 + 2 + 6) / 7 = 4.
- avg[6], avg[7], and avg[8] are -1 because there are less than k elements <strong>after</strong> each index.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [100000], k = 0
<strong>Output:</strong> [100000]
<strong>Explanation:</strong>
- The sum of the subarray centered at index 0 with radius 0 is: 100000.
  avg[0] = 100000 / 1 = 100000.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [8], k = 100000
<strong>Output:</strong> [-1]
<strong>Explanation:</strong> 
- avg[0] is -1 because there are less than k elements before and after index 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= nums[i], k &lt;= 10<sup>5</sup></code></li>
</ul>

## Solutions

### Solution 1: Sliding Window

The number of elements in a subarray with radius $k$ is $k \times 2 + 1$. Therefore, we can redefine $k$ as $k \times 2 + 1$.

We create an answer array $ans$ of length $n$, initially each element is $-1$.

Next, we first check whether $k$ is greater than the length $n$ of the array `nums`. If it is, we directly return the answer array.

Otherwise, we calculate the sum $s$ of the first $k$ elements of the array `nums`, and assign the quotient of $s$ divided by $k$ to the $j$-th element of the answer array $ans$, where $j = k / 2$.

Then, we start traversing the array `nums` from $k$. For each iteration, we add the value of $nums[i]$ to $s$ and subtract the value of $nums[i - k]$, and update $j = j + 1$. Then we get the sum $s$ of the subarray with the $j$-th element as the center and radius $k$, and assign the quotient of $s$ divided by $k$ to the $j$-th element of the answer array $ans$.

Finally, we return the answer array.

The time complexity is $O(n)$, where $n$ is the length of the array `nums`. Ignoring the space consumption of the answer, the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def getAverages(self, nums: List[int], k: int) -> List[int]:
        k = k << 1 | 1
        n = len(nums)
        ans = [-1] * n
        if k > n:
            return ans
        s = sum(nums[:k])
        j = k // 2
        ans[j] = s // k
        for i in range(k, n):
            j += 1
            s += nums[i] - nums[i - k]
            ans[j] = s // k
        return ans
```

```java
class Solution {
    public int[] getAverages(int[] nums, int k) {
        k = k << 1 | 1;
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        if (k > n) {
            return ans;
        }
        long s = 0;
        for (int i = 0; i < k; ++i) {
            s += nums[i];
        }
        int j = k / 2;
        ans[j] = (int) (s / k);
        for (int i = k; i < n; ++i) {
            s += nums[i] - nums[i - k];
            ans[++j] = (int) (s / k);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> getAverages(vector<int>& nums, int k) {
        k = k << 1 | 1;
        int n = nums.size();
        vector<int> ans(n, -1);
        if (k > n) {
            return ans;
        }
        long long s = accumulate(nums.begin(), nums.begin() + k, 0LL);
        int j = k / 2;
        ans[j] = s / k;
        for (int i = k; i < n; ++i) {
            s += nums[i] - nums[i - k];
            ans[++j] = s / k;
        }
        return ans;
    }
};
```

```go
func getAverages(nums []int, k int) []int {
	k = k<<1 | 1
	n := len(nums)
	ans := make([]int, n)
	for i := range ans {
		ans[i] = -1
	}
	if k > n {
		return ans
	}
	s := 0
	for _, x := range nums[:k] {
		s += x
	}
	j := k >> 1
	ans[j] = s / k
	for i := k; i < n; i++ {
		s += nums[i] - nums[i-k]
		j++
		ans[j] = s / k
	}
	return ans
}
```

```ts
function getAverages(nums: number[], k: number): number[] {
    k = (k << 1) | 1;
    const n = nums.length;
    const ans: number[] = Array(n).fill(-1);
    if (k > n) {
        return ans;
    }
    let s = nums.slice(0, k).reduce((acc, cur) => acc + cur, 0);
    let j = k >> 1;
    ans[j] = Math.floor(s / k);
    for (let i = k; i < n; ++i) {
        s += nums[i] - nums[i - k];
        ans[++j] = Math.floor(s / k);
    }
    return ans;
}
```

<!-- tabs:end -->

### Solution 2: Another Way of Sliding Window

We maintain a window of size $k \times 2 + 1$, and let the sum of all elements in the window be $s$.

Like Solution 1, we create an answer array $ans$ of length $n$, initially each element is $-1$.

Next, we traverse the array `nums`, add the value of $nums[i]$ to the sum $s$. If $i \geq k \times 2$, it means the window size is $k \times 2 + 1$ now, so we set $ans[i-k] = \frac{s}{k \times 2 + 1}$, then we subtract the value of $nums[i - k \times 2]$ from the sum $s$. Continue to the next element.

Finally, we return the answer array.

The time complexity is $O(n)$, where $n$ is the length of the array `nums`. Ignoring the space consumption of the answer, the space complexity is $O(1)$.

<!-- tabs:start -->

```python
class Solution:
    def getAverages(self, nums: List[int], k: int) -> List[int]:
        s = 0
        ans = [-1] * len(nums)
        for i, v in enumerate(nums):
            s += v
            if i >= k * 2:
                ans[i - k] = s // (k * 2 + 1)
                s -= nums[i - k * 2]
        return ans
```

```java
class Solution {
    public int[] getAverages(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        long s = 0;
        for (int i = 0; i < n; ++i) {
            s += nums[i];
            if (i >= k * 2) {
                ans[i - k] = (int) (s / (k * 2 + 1));
                s -= nums[i - k * 2];
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    vector<int> getAverages(vector<int>& nums, int k) {
        int n = nums.size();
        vector<int> ans(n, -1);
        long s = 0;
        for (int i = 0; i < n; ++i) {
            s += nums[i];
            if (i >= k * 2) {
                ans[i - k] = s / (k * 2 + 1);
                s -= nums[i - k * 2];
            }
        }
        return ans;
    }
};
```

```go
func getAverages(nums []int, k int) []int {
	ans := make([]int, len(nums))
	s := 0
	for i, v := range nums {
		ans[i] = -1
		s += v
		if i >= k*2 {
			ans[i-k] = s / (k*2 + 1)
			s -= nums[i-k*2]
		}
	}
	return ans
}
```

```ts
function getAverages(nums: number[], k: number): number[] {
    const n = nums.length;
    const ans: number[] = new Array(n).fill(-1);
    let s = 0;
    for (let i = 0; i < n; ++i) {
        s += nums[i];
        if (i >= k * 2) {
            ans[i - k] = Math.floor(s / (k * 2 + 1));
            s -= nums[i - k * 2];
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
