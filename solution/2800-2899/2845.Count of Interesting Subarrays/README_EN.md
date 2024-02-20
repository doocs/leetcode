# [2845. Count of Interesting Subarrays](https://leetcode.com/problems/count-of-interesting-subarrays)

[中文文档](/solution/2800-2899/2845.Count%20of%20Interesting%20Subarrays/README.md)

<!-- tags:Array,Hash Table,Prefix Sum -->

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>, an integer <code>modulo</code>, and an integer <code>k</code>.</p>

<p>Your task is to find the count of subarrays that are <strong>interesting</strong>.</p>

<p>A <strong>subarray</strong> <code>nums[l..r]</code> is <strong>interesting</strong> if the following condition holds:</p>

<ul>
	<li>Let <code>cnt</code> be the number of indices <code>i</code> in the range <code>[l, r]</code> such that <code>nums[i] % modulo == k</code>. Then, <code>cnt % modulo == k</code>.</li>
</ul>

<p>Return <em>an integer denoting the count of interesting subarrays. </em></p>

<p><span><strong>Note:</strong> A subarray is <em>a contiguous non-empty sequence of elements within an array</em>.</span></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,4], modulo = 2, k = 1
<strong>Output:</strong> 3
<strong>Explanation:</strong> In this example the interesting subarrays are: 
The subarray nums[0..0] which is [3]. 
- There is only one index, i = 0, in the range [0, 0] that satisfies nums[i] % modulo == k. 
- Hence, cnt = 1 and cnt % modulo == k.  
The subarray nums[0..1] which is [3,2].
- There is only one index, i = 0, in the range [0, 1] that satisfies nums[i] % modulo == k.  
- Hence, cnt = 1 and cnt % modulo == k.
The subarray nums[0..2] which is [3,2,4]. 
- There is only one index, i = 0, in the range [0, 2] that satisfies nums[i] % modulo == k. 
- Hence, cnt = 1 and cnt % modulo == k. 
It can be shown that there are no other interesting subarrays. So, the answer is 3.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1,9,6], modulo = 3, k = 0
<strong>Output:</strong> 2
<strong>Explanation: </strong>In this example the interesting subarrays are: 
The subarray nums[0..3] which is [3,1,9,6]. 
- There are three indices, i = 0, 2, 3, in the range [0, 3] that satisfy nums[i] % modulo == k. 
- Hence, cnt = 3 and cnt % modulo == k. 
The subarray nums[1..1] which is [1]. 
- There is no index, i, in the range [1, 1] that satisfies nums[i] % modulo == k. 
- Hence, cnt = 0 and cnt % modulo == k. 
It can be shown that there are no other interesting subarrays. So, the answer is 2.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5 </sup></code></li>
	<li><code>1 &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
	<li><code>1 &lt;= modulo &lt;= 10<sup>9</sup></code></li>
	<li><code>0 &lt;= k &lt; modulo</code></li>
</ul>

## Solutions

### Solution 1: Hash Table + Prefix Sum

The problem requires the number of indices $i$ in an interval that satisfy $nums[i] \bmod modulo = k$. We can transform the array $nums$ into a $0-1$ array $arr$, where $arr[i] = 1$ indicates $nums[i] \bmod modulo = k$, otherwise $arr[i] = 0$.

For an interval $[l, r]$, we can calculate the number of $1$s in $arr[l..r]$ through the prefix sum array $s$, i.e., $s[r] - s[l - 1]$, where $s[0] = 0$.

We use a hash table $cnt$ to record the number of occurrences of the prefix sum $s \bmod modulo$, initially $cnt[0]=1$.

Next, we traverse the array $arr$, calculate the prefix sum $s$, add the number of occurrences of $(s-k) \bmod modulo$ to the answer, and then add $1$ to the number of occurrences of $s \bmod modulo$.

After the traversal ends, return the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Here, $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def countInterestingSubarrays(self, nums: List[int], modulo: int, k: int) -> int:
        arr = [int(x % modulo == k) for x in nums]
        cnt = Counter()
        cnt[0] = 1
        ans = s = 0
        for x in arr:
            s += x
            ans += cnt[(s - k) % modulo]
            cnt[s % modulo] += 1
        return ans
```

```java
class Solution {
    public long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int n = nums.size();
        int[] arr = new int[n];
        for (int i = 0; i < n; ++i) {
            arr[i] = nums.get(i) % modulo == k ? 1 : 0;
        }
        Map<Integer, Integer> cnt = new HashMap<>();
        cnt.put(0, 1);
        long ans = 0;
        int s = 0;
        for (int x : arr) {
            s += x;
            ans += cnt.getOrDefault((s - k + modulo) % modulo, 0);
            cnt.merge(s % modulo, 1, Integer::sum);
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    long long countInterestingSubarrays(vector<int>& nums, int modulo, int k) {
        int n = nums.size();
        vector<int> arr(n);
        for (int i = 0; i < n; ++i) {
            arr[i] = int(nums[i] % modulo == k);
        }
        unordered_map<int, int> cnt;
        cnt[0] = 1;
        long long ans = 0;
        int s = 0;
        for (int x : arr) {
            s += x;
            ans += cnt[(s - k + modulo) % modulo];
            cnt[s % modulo]++;
        }
        return ans;
    }
};
```

```go
func countInterestingSubarrays(nums []int, modulo int, k int) (ans int64) {
	arr := make([]int, len(nums))
	for i, x := range nums {
		if x%modulo == k {
			arr[i] = 1
		}
	}
	cnt := map[int]int{}
	cnt[0] = 1
	s := 0
	for _, x := range arr {
		s += x
		ans += int64(cnt[(s-k+modulo)%modulo])
		cnt[s%modulo]++
	}
	return
}
```

```ts
function countInterestingSubarrays(nums: number[], modulo: number, k: number): number {
    const arr: number[] = [];
    for (const x of nums) {
        arr.push(x % modulo === k ? 1 : 0);
    }
    const cnt: Map<number, number> = new Map();
    cnt.set(0, 1);
    let ans = 0;
    let s = 0;
    for (const x of arr) {
        s += x;
        ans += cnt.get((s - k + modulo) % modulo) || 0;
        cnt.set(s % modulo, (cnt.get(s % modulo) || 0) + 1);
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
