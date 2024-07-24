---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/1500-1599/1550.Three%20Consecutive%20Odds/README_EN.md
rating: 1221
source: Weekly Contest 202 Q1
tags:
    - Array
---

<!-- problem:start -->

# [1550. Three Consecutive Odds](https://leetcode.com/problems/three-consecutive-odds)

[中文文档](/solution/1500-1599/1550.Three%20Consecutive%20Odds/README.md)

## Description

<!-- description:start -->

Given an integer array <code>arr</code>, return <code>true</code>&nbsp;if there are three consecutive odd numbers in the array. Otherwise, return&nbsp;<code>false</code>.

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [2,6,4,1]
<strong>Output:</strong> false
<b>Explanation:</b> There are no three consecutive odds.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [1,2,34,3,4,5,7,23,12]
<strong>Output:</strong> true
<b>Explanation:</b> [5,7,23] are three consecutive odds.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 1000</code></li>
	<li><code>1 &lt;= arr[i] &lt;= 1000</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Iteration + Counting

We use a variable $\textit{cnt}$ to record the current count of consecutive odd numbers.

Next, we iterate through the array. If the current element is odd, then $\textit{cnt}$ is incremented by one. If $\textit{cnt}$ equals 3, then return $\textit{True}$. If the current element is even, then $\textit{cnt}$ is reset to zero.

After the iteration, if three consecutive odd numbers are not found, then return $\textit{False}$.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{arr}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def threeConsecutiveOdds(self, arr: List[int]) -> bool:
        cnt = 0
        for x in arr:
            if x & 1:
                cnt += 1
                if cnt == 3:
                    return True
            else:
                cnt = 0
        return False
```

#### Java

```java
class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {
        int cnt = 0;
        for (int x : arr) {
            if (x % 2 == 1) {
                if (++cnt == 3) {
                    return true;
                }
            } else {
                cnt = 0;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool threeConsecutiveOdds(vector<int>& arr) {
        int cnt = 0;
        for (int x : arr) {
            if (x & 1) {
                if (++cnt == 3) {
                    return true;
                }
            } else {
                cnt = 0;
            }
        }
        return false;
    }
};
```

#### Go

```go
func threeConsecutiveOdds(arr []int) bool {
	cnt := 0
	for _, x := range arr {
		if x&1 == 1 {
			cnt++
			if cnt == 3 {
				return true
			}
		} else {
			cnt = 0
		}
	}
	return false
}
```

#### TypeScript

```ts
function threeConsecutiveOdds(arr: number[]): boolean {
    let cnt = 0;
    for (const x of arr) {
        if (x & 1) {
            if (++cnt == 3) {
                return true;
            }
        } else {
            cnt = 0;
        }
    }
    return false;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- solution:start -->

### Solution 2: Iteration + Bitwise Operation

Based on the properties of bitwise operations, the result of a bitwise AND operation between two numbers is odd if and only if both numbers are odd. If there are three consecutive numbers whose bitwise AND result is odd, then these three numbers are all odd.

Therefore, we only need to iterate through the array and check if there exists three consecutive numbers whose bitwise AND result is odd. If such numbers exist, return $\textit{True}$; otherwise, return $\textit{False}$.

The time complexity is $O(n)$, where $n$ is the length of the array $\textit{arr}$. The space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def threeConsecutiveOdds(self, arr: List[int]) -> bool:
        return any(x & arr[i + 1] & arr[i + 2] & 1 for i, x in enumerate(arr[:-2]))
```

#### Java

```java
class Solution {
    public boolean threeConsecutiveOdds(int[] arr) {
        for (int i = 2, n = arr.length; i < n; ++i) {
            if ((arr[i - 2] & arr[i - 1] & arr[i] & 1) == 1) {
                return true;
            }
        }
        return false;
    }
}
```

#### C++

```cpp
class Solution {
public:
    bool threeConsecutiveOdds(vector<int>& arr) {
        for (int i = 2, n = arr.size(); i < n; ++i) {
            if (arr[i - 2] & arr[i - 1] & arr[i] & 1) {
                return true;
            }
        }
        return false;
    }
};
```

#### Go

```go
func threeConsecutiveOdds(arr []int) bool {
	for i, n := 2, len(arr); i < n; i++ {
		if arr[i-2]&arr[i-1]&arr[i]&1 == 1 {
			return true
		}
	}
	return false
}
```

#### TypeScript

```ts
function threeConsecutiveOdds(arr: number[]): boolean {
    const n = arr.length;
    for (let i = 2; i < n; ++i) {
        if (arr[i - 2] & arr[i - 1] & arr[i] & 1) {
            return true;
        }
    }
    return false;
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
