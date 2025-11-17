---
comments: true
difficulty: Easy
edit_url: https://github.com/doocs/leetcode/edit/main/solution/2000-2099/2094.Finding%203-Digit%20Even%20Numbers/README_EN.md
rating: 1454
source: Weekly Contest 270 Q1
tags:
    - Recursion
    - Array
    - Hash Table
    - Enumeration
    - Sorting
---

<!-- problem:start -->

# [2094. Finding 3-Digit Even Numbers](https://leetcode.com/problems/finding-3-digit-even-numbers)

[中文文档](/solution/2000-2099/2094.Finding%203-Digit%20Even%20Numbers/README.md)

## Description

<!-- description:start -->

<p>You are given an integer array <code>digits</code>, where each element is a digit. The array may contain duplicates.</p>

<p>You need to find <strong>all</strong> the <strong>unique</strong> integers that follow the given requirements:</p>

<ul>
	<li>The integer consists of the <strong>concatenation</strong> of <strong>three</strong> elements from <code>digits</code> in <strong>any</strong> arbitrary order.</li>
	<li>The integer does not have <strong>leading zeros</strong>.</li>
	<li>The integer is <strong>even</strong>.</li>
</ul>

<p>For example, if the given <code>digits</code> were <code>[1, 2, 3]</code>, integers <code>132</code> and <code>312</code> follow the requirements.</p>

<p>Return <em>a <strong>sorted</strong> array of the unique integers.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> digits = [2,1,3,0]
<strong>Output:</strong> [102,120,130,132,210,230,302,310,312,320]
<strong>Explanation:</strong> All the possible integers that follow the requirements are in the output array. 
Notice that there are no <strong>odd</strong> integers or integers with <strong>leading zeros</strong>.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> digits = [2,2,8,8,2]
<strong>Output:</strong> [222,228,282,288,822,828,882]
<strong>Explanation:</strong> The same digit can be used as many times as it appears in digits. 
In this example, the digit 8 is used twice each time in 288, 828, and 882. 
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> digits = [3,7,5]
<strong>Output:</strong> []
<strong>Explanation:</strong> No <strong>even</strong> integers can be formed using the given digits.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= digits.length &lt;= 100</code></li>
	<li><code>0 &lt;= digits[i] &lt;= 9</code></li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Counting + Enumeration

First, we count the occurrence of each digit in $\textit{digits}$, recording it in an array or hash table $\textit{cnt}$.

Then, we enumerate all even numbers in the range $[100, 1000)$, checking if each digit of the even number does not exceed the corresponding digit's count in $\textit{cnt}$. If so, we add this even number to the answer array.

Finally, we return the answer array.

The time complexity is $O(k \times 10^k)$, where $k$ is the number of digits of the target even number, which is $3$ in this problem. Ignoring the space consumed by the answer, the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
class Solution:
    def findEvenNumbers(self, digits: List[int]) -> List[int]:
        cnt = Counter(digits)
        ans = []
        for x in range(100, 1000, 2):
            cnt1 = Counter()
            y = x
            while y:
                y, v = divmod(y, 10)
                cnt1[v] += 1
            if all(cnt[i] >= cnt1[i] for i in range(10)):
                ans.append(x)
        return ans
```

#### Java

```java
class Solution {
    public int[] findEvenNumbers(int[] digits) {
        int[] cnt = new int[10];
        for (int x : digits) {
            ++cnt[x];
        }
        List<Integer> ans = new ArrayList<>();
        for (int x = 100; x < 1000; x += 2) {
            int[] cnt1 = new int[10];
            for (int y = x; y > 0; y /= 10) {
                ++cnt1[y % 10];
            }
            boolean ok = true;
            for (int i = 0; i < 10 && ok; ++i) {
                ok = cnt[i] >= cnt1[i];
            }
            if (ok) {
                ans.add(x);
            }
        }
        return ans.stream().mapToInt(i -> i).toArray();
    }
}
```

#### C++

```cpp
class Solution {
public:
    vector<int> findEvenNumbers(vector<int>& digits) {
        int cnt[10]{};
        for (int x : digits) {
            ++cnt[x];
        }
        vector<int> ans;
        for (int x = 100; x < 1000; x += 2) {
            int cnt1[10]{};
            for (int y = x; y; y /= 10) {
                ++cnt1[y % 10];
            }
            bool ok = true;
            for (int i = 0; i < 10 && ok; ++i) {
                ok = cnt[i] >= cnt1[i];
            }
            if (ok) {
                ans.push_back(x);
            }
        }
        return ans;
    }
};
```

#### Go

```go
func findEvenNumbers(digits []int) (ans []int) {
	cnt := [10]int{}
	for _, x := range digits {
		cnt[x]++
	}
	for x := 100; x < 1000; x += 2 {
		cnt1 := [10]int{}
		for y := x; y > 0; y /= 10 {
			cnt1[y%10]++
		}
		ok := true
		for i := 0; i < 10 && ok; i++ {
			ok = cnt[i] >= cnt1[i]
		}
		if ok {
			ans = append(ans, x)
		}
	}
	return
}
```

#### TypeScript

```ts
function findEvenNumbers(digits: number[]): number[] {
    const cnt: number[] = Array(10).fill(0);
    for (const x of digits) {
        ++cnt[x];
    }
    const ans: number[] = [];
    for (let x = 100; x < 1000; x += 2) {
        const cnt1: number[] = Array(10).fill(0);
        for (let y = x; y; y = Math.floor(y / 10)) {
            ++cnt1[y % 10];
        }
        let ok = true;
        for (let i = 0; i < 10 && ok; ++i) {
            ok = cnt[i] >= cnt1[i];
        }
        if (ok) {
            ans.push(x);
        }
    }
    return ans;
}
```

#### JavaScript

```js
/**
 * @param {number[]} digits
 * @return {number[]}
 */
var findEvenNumbers = function (digits) {
    const cnt = Array(10).fill(0);
    for (const x of digits) {
        ++cnt[x];
    }
    const ans = [];
    for (let x = 100; x < 1000; x += 2) {
        const cnt1 = Array(10).fill(0);
        for (let y = x; y; y = Math.floor(y / 10)) {
            ++cnt1[y % 10];
        }
        let ok = true;
        for (let i = 0; i < 10 && ok; ++i) {
            ok = cnt[i] >= cnt1[i];
        }
        if (ok) {
            ans.push(x);
        }
    }
    return ans;
};
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
