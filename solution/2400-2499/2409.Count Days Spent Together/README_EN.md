---
comments: true
difficulty: Easy
rating: 1562
source: Biweekly Contest 87 Q1
tags:
    - Math
    - String
---

<!-- problem:start -->

# [2409. Count Days Spent Together](https://leetcode.com/problems/count-days-spent-together)

[中文文档](/solution/2400-2499/2409.Count%20Days%20Spent%20Together/README.md)

## Description

<!-- description:start -->

<p>Alice and Bob are traveling to Rome for separate business meetings.</p>

<p>You are given 4 strings <code>arriveAlice</code>, <code>leaveAlice</code>, <code>arriveBob</code>, and <code>leaveBob</code>. Alice will be in the city from the dates <code>arriveAlice</code> to <code>leaveAlice</code> (<strong>inclusive</strong>), while Bob will be in the city from the dates <code>arriveBob</code> to <code>leaveBob</code> (<strong>inclusive</strong>). Each will be a 5-character string in the format <code>&quot;MM-DD&quot;</code>, corresponding to the month and day of the date.</p>

<p>Return<em> the total number of days that Alice and Bob are in Rome together.</em></p>

<p>You can assume that all dates occur in the <strong>same</strong> calendar year, which is <strong>not</strong> a leap year. Note that the number of days per month can be represented as: <code>[31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arriveAlice = &quot;08-15&quot;, leaveAlice = &quot;08-18&quot;, arriveBob = &quot;08-16&quot;, leaveBob = &quot;08-19&quot;
<strong>Output:</strong> 3
<strong>Explanation:</strong> Alice will be in Rome from August 15 to August 18. Bob will be in Rome from August 16 to August 19. They are both in Rome together on August 16th, 17th, and 18th, so the answer is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arriveAlice = &quot;10-01&quot;, leaveAlice = &quot;10-31&quot;, arriveBob = &quot;11-01&quot;, leaveBob = &quot;12-31&quot;
<strong>Output:</strong> 0
<strong>Explanation:</strong> There is no day when Alice and Bob are in Rome together, so we return 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li>All dates are provided in the format <code>&quot;MM-DD&quot;</code>.</li>
	<li>Alice and Bob&#39;s arrival dates are <strong>earlier than or equal to</strong> their leaving dates.</li>
	<li>The given dates are valid dates of a <strong>non-leap</strong> year.</li>
</ul>

<!-- description:end -->

## Solutions

<!-- solution:start -->

### Solution 1: Prefix Sum

<!-- thinking:start -->

> **Thinking**
>
> Days together are the length of the intersection of two closed intervals. Scanning every day of the year fits the constraints, but the inputs are $\texttt{MM-DD}$ strings, so both comparison and counting need one shared index.
>
> Adding the preceding month lengths on each conversion repeats the same constant work. Lexicographic order of this format matches the calendar, so the overlap runs from the later arrival to the earlier departure. Month lengths in a non-leap year are fixed; once their prefix sums $f$ are known, month $m$ and day $d$ is day $f_{m-1}+d$ of the year.
>
> The two endpoints are converted through $f$, then subtracted and adjusted by one. If the start falls after the end, the intersection is empty.

<!-- thinking:end -->

We take the later arrival and the earlier departure, map both dates to a day of the year with the month-length prefix, and return the length of that closed interval.

The time complexity is $O(1)$, and the space complexity is $O(1)$.

<!-- tabs:start -->

#### Python3

```python
f = list(accumulate([31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31], initial=0))


class Solution:
    def countDaysTogether(
        self, arriveAlice: str, leaveAlice: str, arriveBob: str, leaveBob: str
    ) -> int:
        start = max(arriveAlice, arriveBob)
        end = min(leaveAlice, leaveBob)
        d1 = f[int(start[:2]) - 1] + int(start[3:])
        d2 = f[int(end[:2]) - 1] + int(end[3:])
        return max(d2 - d1 + 1, 0)
```

#### Java

```java
class Solution {
    static final int[] f = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

    public int countDaysTogether(
        String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        String start = arriveAlice.compareTo(arriveBob) > 0 ? arriveAlice : arriveBob;
        String end = leaveAlice.compareTo(leaveBob) < 0 ? leaveAlice : leaveBob;
        int d1
            = f[Integer.parseInt(start.substring(0, 2)) - 1] + Integer.parseInt(start.substring(3));
        int d2 = f[Integer.parseInt(end.substring(0, 2)) - 1] + Integer.parseInt(end.substring(3));
        return Math.max(d2 - d1 + 1, 0);
    }
}
```

#### C++

```cpp
class Solution {
public:
    static constexpr int f[] = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

    int countDaysTogether(string arriveAlice, string leaveAlice, string arriveBob, string leaveBob) {
        string start = max(arriveAlice, arriveBob);
        string end = min(leaveAlice, leaveBob);
        int d1 = f[stoi(start.substr(0, 2)) - 1] + stoi(start.substr(3));
        int d2 = f[stoi(end.substr(0, 2)) - 1] + stoi(end.substr(3));
        return max(d2 - d1 + 1, 0);
    }
};
```

#### Go

```go
var f = [12]int{0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334}

func countDaysTogether(arriveAlice string, leaveAlice string, arriveBob string, leaveBob string) int {
	start := arriveAlice
	if arriveBob > start {
		start = arriveBob
	}
	end := leaveAlice
	if leaveBob < end {
		end = leaveBob
	}
	d1 := f[int(start[0]-'0')*10+int(start[1]-'0')-1] + int(start[3]-'0')*10 + int(start[4]-'0')
	d2 := f[int(end[0]-'0')*10+int(end[1]-'0')-1] + int(end[3]-'0')*10 + int(end[4]-'0')
	return max(d2-d1+1, 0)
}
```

#### TypeScript

```ts
const f = [0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334];

function countDaysTogether(
    arriveAlice: string,
    leaveAlice: string,
    arriveBob: string,
    leaveBob: string,
): number {
    const start = arriveAlice > arriveBob ? arriveAlice : arriveBob;
    const end = leaveAlice < leaveBob ? leaveAlice : leaveBob;
    const d1 = f[Number(start.slice(0, 2)) - 1] + Number(start.slice(3));
    const d2 = f[Number(end.slice(0, 2)) - 1] + Number(end.slice(3));
    return Math.max(d2 - d1 + 1, 0);
}
```

#### Rust

```rust
impl Solution {
    pub fn count_days_together(
        arrive_alice: String,
        leave_alice: String,
        arrive_bob: String,
        leave_bob: String,
    ) -> i32 {
        let f = [0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334];
        let start = arrive_alice.max(arrive_bob);
        let end = leave_alice.min(leave_bob);
        let d1 = f[start[..2].parse::<usize>().unwrap() - 1] + start[3..].parse::<i32>().unwrap();
        let d2 = f[end[..2].parse::<usize>().unwrap() - 1] + end[3..].parse::<i32>().unwrap();
        (d2 - d1 + 1).max(0)
    }
}
```

<!-- tabs:end -->

<!-- solution:end -->

<!-- problem:end -->
