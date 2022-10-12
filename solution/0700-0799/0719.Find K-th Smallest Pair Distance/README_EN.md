# [719. Find K-th Smallest Pair Distance](https://leetcode.com/problems/find-k-th-smallest-pair-distance)

[中文文档](/solution/0700-0799/0719.Find%20K-th%20Smallest%20Pair%20Distance/README.md)

## Description

<p>The <strong>distance of a pair</strong> of integers <code>a</code> and <code>b</code> is defined as the absolute difference between <code>a</code> and <code>b</code>.</p>

<p>Given an integer array <code>nums</code> and an integer <code>k</code>, return <em>the</em> <code>k<sup>th</sup></code> <em>smallest <strong>distance among all the pairs</strong></em> <code>nums[i]</code> <em>and</em> <code>nums[j]</code> <em>where</em> <code>0 &lt;= i &lt; j &lt; nums.length</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,1], k = 1
<strong>Output:</strong> 0
<strong>Explanation:</strong> Here are all the pairs:
(1,3) -&gt; 2
(1,1) -&gt; 0
(3,1) -&gt; 2
Then the 1<sup>st</sup> smallest distance pair is (1,1), and its distance is 0.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,1], k = 2
<strong>Output:</strong> 0
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,6,1], k = 3
<strong>Output:</strong> 5
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>2 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= 10<sup>6</sup></code></li>
	<li><code>1 &lt;= k &lt;= n * (n - 1) / 2</code></li>
</ul>

## Solutions

<!-- tabs:start -->

### **Python3**

```python
class Solution:
    def smallestDistancePair(self, nums: List[int], k: int) -> int:
        def count(dist):
            cnt = 0
            for i, b in enumerate(nums):
                a = b - dist
                j = bisect_left(nums, a, 0, i)
                cnt += i - j
            return cnt

        nums.sort()
        return bisect_left(range(nums[-1] - nums[0]), k, key=count)
```

### **Java**

```java
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0, right = nums[nums.length - 1] - nums[0];
        while (left < right) {
            int mid = (left + right) >> 1;
            if (count(mid, nums) >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private int count(int dist, int[] nums) {
        int cnt = 0;
        for (int i = 0; i < nums.length; ++i) {
            int left = 0, right = i;
            while (left < right) {
                int mid = (left + right) >> 1;
                int target = nums[i] - dist;
                if (nums[mid] >= target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            cnt += i - left;
        }
        return cnt;
    }
}
```

### **TypeScript**

```ts
function smallestDistancePair(nums: number[], k: number): number {
    nums.sort((a, b) => a - b);
    const n = nums.length;
    let left = 0,
        right = nums[n - 1] - nums[0];
    while (left < right) {
        let mid = (left + right) >> 1;
        let count = 0,
            i = 0;
        for (let j = 0; j < n; j++) {
            while (nums[j] - nums[i] > mid) {
                i++;
            }
            count += j - i;
        }
        if (count >= k) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}
```

### **C++**

```cpp
class Solution {
public:
    int smallestDistancePair(vector<int>& nums, int k) {
        sort(nums.begin(), nums.end());
        int left = 0, right = nums.back() - nums.front();
        while (left < right) {
            int mid = (left + right) >> 1;
            if (count(mid, k, nums) >= k)
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }

    int count(int dist, int k, vector<int>& nums) {
        int cnt = 0;
        for (int i = 0; i < nums.size(); ++i) {
            int target = nums[i] - dist;
            int j = lower_bound(nums.begin(), nums.end(), target) - nums.begin();
            cnt += i - j;
        }
        return cnt;
    }
};
```

### **Go**

```go
func smallestDistancePair(nums []int, k int) int {
	sort.Ints(nums)
	n := len(nums)
	left, right := 0, nums[n-1]-nums[0]
	count := func(dist int) int {
		cnt := 0
		for i, v := range nums {
			target := v - dist
			left, right := 0, i
			for left < right {
				mid := (left + right) >> 1
				if nums[mid] >= target {
					right = mid
				} else {
					left = mid + 1
				}
			}
			cnt += i - left
		}
		return cnt
	}
	for left < right {
		mid := (left + right) >> 1
		if count(mid) >= k {
			right = mid
		} else {
			left = mid + 1
		}
	}
	return left
}
```

### **...**

```

```

<!-- tabs:end -->
