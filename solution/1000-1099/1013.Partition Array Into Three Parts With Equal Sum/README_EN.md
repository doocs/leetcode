---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1000-1099/1013.Partition%20Array%20Into%20Three%20Parts%20With%20Equal%20Sum/README_EN.md
rating: 1378
source: Weekly Contest 129 Q1
tags:
    - Greedy
    - Array
---

<!-- problem:start -->

# [1013. Partition Array Into Three Parts With Equal Sum](https://leetcode.com/problems/partition-array-into-three-parts-with-equal-sum)

[中文文档](/solution/1000-1099/1013.Partition%20Array%20Into%20Three%20Parts%20With%20Equal%20Sum/README.md)

## Description

<!-- description:start -->

<p>Given an array of integers <code>arr</code>, return <code>true</code> if we can partition the array into three <strong>non-empty</strong> parts with equal sums.</p>

<p>Formally, we can partition the array if we can find indexes <code>i + 1 &lt; j</code> with <code>(arr[0] + arr[1] + ... + arr[i] == arr[i + 1] + arr[i + 2] + ... + arr[j - 1] == arr[j] + arr[j + 1] + ... + arr[arr.length - 1])</code></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [0,2,1,-6,6,-7,9,1,2,0,1]
<strong>Output:</strong> true
<strong>Explanation: </strong>0 + 2 + 1 = -6 + 6 - 7 + 9 + 1 = 2 + 0 + 1
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [0,2,1,-6,6,7,9,-1,2,0,1]
<strong>Output:</strong> false
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> arr = [3,3,6,5,-2,2,5,1,-9,4]
<strong>Output:</strong> true
<strong>Explanation: </strong>3 + 3 = 6 = 5 - 2 + 2 + 5 + 1 - 9 + 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= arr.length &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= arr[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Traversal and Summation

First, we calculate the sum of the entire array and check if the sum is divisible by 3. If it is not, we directly return $\textit{false}$.

Otherwise, let $\textit{s}$ represent the sum of each part. We use a variable $\textit{cnt}$ to record the number of parts found so far, and another variable $\textit{t}$ to record the current part's sum. Initially, $\textit{cnt} = 0$ and $\textit{t} = 0$.

Then we traverse the array. For each element $x$, we add $x$ to $\textit{t}$. If $\textit{t}$ equals $s$, it means we have found one part, so we increment $\textit{cnt}$ by one and reset $\textit{t}$ to 0.

Finally, we check if $\textit{cnt}$ is greater than or equal to 3.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{arr}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def canThreePartsEqualSum(self, arr: List[int]) -> bool:
        s, mod = divmod(sum(arr), 3)
        if mod:
            return False
        cnt = t = 0
        for x in arr:
            t += x
            if t == s:
                cnt += 1
                t = 0
        return cnt >= 3
```

#### Java

```java
class Solution {
    public boolean canThreePartsEqualSum(int[] arr) {
        int s = Arrays.stream(arr).sum();
        if (s % 3 != 0) {
            return false;
        }
        s /= 3;
        int cnt = 0, t = 0;
        for (int x : arr) {
            t += x;
            if (t == s) {
                cnt++;
                t = 0;
            }
        }
        return cnt >= 3;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool canThreePartsEqualSum(vector<int>& arr) {
        int s = accumulate(arr.begin(), arr.end(), 0);
        if (s % 3) {
            return false;
        }
        s /= 3;
        int cnt = 0, t = 0;
        for (int x : arr) {
            t += x;
            if (t == s) {
                t = 0;
                cnt++;
            }
        }
        return cnt >= 3;
    }
};
```

#### Go

```go
func canThreePartsEqualSum(arr []int) bool {
	s := 0
	for _, x := range arr {
		s += x
	}
	if s%3 != 0 {
		return false
	}
	s /= 3
	cnt, t := 0, 0
	for _, x := range arr {
		t += x
		if t == s {
			cnt++
			t = 0
		}
	}
	return cnt >= 3
}
```

#### TypeScript

```ts
function canThreePartsEqualSum(arr: number[]): boolean {
    let s = arr.reduce((a, b) => a + b);
    if (s % 3) {
        return false;
    }
    s = (s / 3) | 0;
    let [cnt, t] = [0, 0];
    for (const x of arr) {
        t += x;
        if (t == s) {
            cnt++;
            t = 0;
        }
    }
    return cnt >= 3;
}
```

#### Rust

```rust
impl Solution {
    pub fn can_three_parts_equal_sum(arr: Vec<i32>) -> bool {
        let sum: i32 = arr.iter().sum();
        let s = sum / 3;
        let mod_val = sum % 3;
        if mod_val != 0 {
            return false;
        }

        let mut cnt = 0;
        let mut t = 0;
        for &x in &arr {
            t += x;
            if t == s {
                cnt += 1;
                t = 0;
            }
        }

        cnt >= 3
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
