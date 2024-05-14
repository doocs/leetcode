# [3005. Count Elements With Maximum Frequency](https://leetcode.com/problems/count-elements-with-maximum-frequency)

[中文文档](/solution/3000-3099/3005.Count%20Elements%20With%20Maximum%20Frequency/README.md)

<!-- tags:Array,Hash Table,Counting -->

<!-- difficulty:Easy -->

## Description

<p>You are given an array <code>nums</code> consisting of <strong>positive</strong> integers.</p>

<p>Return <em>the <strong>total frequencies</strong> of elements in</em><em> </em><code>nums</code>&nbsp;<em>such that those elements all have the <strong>maximum</strong> frequency</em>.</p>

<p>The <strong>frequency</strong> of an element is the number of occurrences of that element in the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,2,3,1,4]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The elements 1 and 2 have a frequency of 2 which is the maximum frequency in the array.
So the number of elements in the array with maximum frequency is 4.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5]
<strong>Output:</strong> 5
<strong>Explanation:</strong> All elements of the array have a frequency of 1 which is the maximum.
So the number of elements in the array with maximum frequency is 5.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>1 &lt;= nums[i] &lt;= 100</code></li>
</ul>

## Solutions

### Solution 1: Counting

We can use a hash table or array $cnt$ to record the occurrence of each element.

Then we traverse $cnt$ to find the element with the most occurrences, and let its occurrence be $mx$. We sum up the occurrences of elements that appear $mx$ times, which is the answer.

The time complexity is $O(n)$, and the space complexity is $O(n)$. Where $n$ is the length of the array $nums$.

<!-- tabs:start -->

```python
class Solution:
    def maxFrequencyElements(self, nums: List[int]) -> int:
        cnt = Counter(nums)
        mx = max(cnt.values())
        return sum(x for x in cnt.values() if x == mx)
```

```java
class Solution {
    public int maxFrequencyElements(int[] nums) {
        int[] cnt = new int[101];
        for (int x : nums) {
            ++cnt[x];
        }
        int ans = 0, mx = -1;
        for (int x : cnt) {
            if (mx < x) {
                mx = x;
                ans = x;
            } else if (mx == x) {
                ans += x;
            }
        }
        return ans;
    }
}
```

```cpp
class Solution {
public:
    int maxFrequencyElements(vector<int>& nums) {
        int cnt[101]{};
        for (int x : nums) {
            ++cnt[x];
        }
        int ans = 0, mx = -1;
        for (int x : cnt) {
            if (mx < x) {
                mx = x;
                ans = x;
            } else if (mx == x) {
                ans += x;
            }
        }
        return ans;
    }
};
```

```go
func maxFrequencyElements(nums []int) (ans int) {
	cnt := [101]int{}
	for _, x := range nums {
		cnt[x]++
	}
	mx := -1
	for _, x := range cnt {
		if mx < x {
			mx, ans = x, x
		} else if mx == x {
			ans += x
		}
	}
	return
}
```

```ts
function maxFrequencyElements(nums: number[]): number {
    const cnt: number[] = Array(101).fill(0);
    for (const x of nums) {
        ++cnt[x];
    }
    let [ans, mx] = [0, -1];
    for (const x of cnt) {
        if (mx < x) {
            mx = x;
            ans = x;
        } else if (mx === x) {
            ans += x;
        }
    }
    return ans;
}
```

<!-- tabs:end -->

<!-- end -->
