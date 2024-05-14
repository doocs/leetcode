# [2190. Most Frequent Number Following Key In an Array](https://leetcode.com/problems/most-frequent-number-following-key-in-an-array)

[中文文档](/solution/2100-2199/2190.Most%20Frequent%20Number%20Following%20Key%20In%20an%20Array/README.md)

<!-- tags:Array,Hash Table,Counting -->

<!-- difficulty:Easy -->

## Description

<p>You are given a <strong>0-indexed</strong> integer array <code>nums</code>.<strong> </strong>You are also given an integer <code>key</code>, which is present in <code>nums</code>.</p>

<p>For every unique integer <code>target</code> in <code>nums</code>, <strong>count</strong> the number of times <code>target</code> immediately follows an occurrence of <code>key</code> in <code>nums</code>. In other words, count the number of indices <code>i</code> such that:</p>

<ul>
	<li><code>0 &lt;= i &lt;= nums.length - 2</code>,</li>
	<li><code>nums[i] == key</code> and,</li>
	<li><code>nums[i + 1] == target</code>.</li>
</ul>

<p>Return <em>the </em><code>target</code><em> with the <strong>maximum</strong> count</em>. The test cases will be generated such that the <code>target</code> with maximum count is unique.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,100,200,1,100], key = 1
<strong>Output:</strong> 100
<strong>Explanation:</strong> For target = 100, there are 2 occurrences at indices 1 and 4 which follow an occurrence of key.
No other integers follow an occurrence of key, so we return 100.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,2,2,2,3], key = 2
<strong>Output:</strong> 2
<strong>Explanation:</strong> For target = 2, there are 3 occurrences at indices 1, 2, and 3 which follow an occurrence of key.
For target = 3, there is only one occurrence at index 4 which follows an occurrence of key.
target = 2 has the maximum number of occurrences following an occurrence of key, so we return 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 1000</code></li>
	<li>The test cases will be generated such that the answer is unique.</li>
</ul>

## Solutions

### Solution 1

<!-- tabs:start -->

```python
class Solution:
    def mostFrequent(self, nums: List[int], key: int) -> int:
        cnt = Counter()
        ans = mx = 0
        for a, b in pairwise(nums):
            if a == key:
                cnt[b] += 1
                if mx < cnt[b]:
                    mx = cnt[b]
                    ans = b
        return ans
```

```java
class Solution {
    public int mostFrequent(int[] nums, int key) {
        int[] cnt = new int[1001];
        int ans = 0, mx = 0;
        for (int i = 0; i < nums.length - 1; ++i) {
            if (nums[i] == key) {
                if (mx < ++cnt[nums[i + 1]]) {
                    mx = cnt[nums[i + 1]];
                    ans = nums[i + 1];
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
    int mostFrequent(vector<int>& nums, int key) {
        int cnt[1001]{};
        int ans = 0, mx = 0;
        for (int i = 0; i < nums.size() - 1; ++i) {
            if (nums[i] == key) {
                if (mx < ++cnt[nums[i + 1]]) {
                    mx = cnt[nums[i + 1]];
                    ans = nums[i + 1];
                }
            }
        }
        return ans;
    }
};
```

```go
func mostFrequent(nums []int, key int) (ans int) {
	cnt := [1001]int{}
	mx := 0
	for i, x := range nums[1:] {
		if nums[i] == key {
			cnt[x]++
			if mx < cnt[x] {
				mx = cnt[x]
				ans = x
			}
		}
	}
	return
}
```

```ts
function mostFrequent(nums: number[], key: number): number {
    const cnt: number[] = new Array(1001).fill(0);
    let ans = 0;
    let mx = 0;
    for (let i = 0; i < nums.length - 1; ++i) {
        if (nums[i] === key) {
            if (mx < ++cnt[nums[i + 1]]) {
                mx = cnt[nums[i + 1]];
                ans = nums[i + 1];
            }
        }
    }
    return ans;
}
```

```php
class Solution {
    /**
     * @param Integer[] $nums
     * @param Integer $key
     * @return Integer
     */
    function mostFrequent($nums, $key) {
        $max = $maxNum = 0;
        for ($i = 0; $i < count($nums) - 1; $i++) {
            if ($nums[$i] == $key) {
                $hashtable[$nums[$i + 1]] += 1;
                $tmp = $hashtable[$nums[$i + 1]];
                if ($tmp > $max) {
                    $max = $tmp;
                    $maxNum = $nums[$i + 1];
                }
            }
        }
        return $maxNum;
    }
}
```

<!-- tabs:end -->

<!-- end -->
